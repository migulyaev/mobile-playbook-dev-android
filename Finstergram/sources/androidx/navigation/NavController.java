package androidx.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import androidx.core.app.TaskStackBuilder;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class NavController {
    private static final String KEY_BACK_STACK_ARGS = "android-support-nav:controller:backStackArgs";
    private static final String KEY_BACK_STACK_IDS = "android-support-nav:controller:backStackIds";
    static final String KEY_DEEP_LINK_EXTRAS = "android-support-nav:controller:deepLinkExtras";
    static final String KEY_DEEP_LINK_IDS = "android-support-nav:controller:deepLinkIds";
    public static final String KEY_DEEP_LINK_INTENT = "android-support-nav:controller:deepLinkIntent";
    private static final String KEY_NAVIGATOR_STATE = "android-support-nav:controller:navigatorState";
    private static final String KEY_NAVIGATOR_STATE_NAMES = "android-support-nav:controller:navigatorState:names";
    private static final String TAG = "NavController";
    private Activity mActivity;
    private Parcelable[] mBackStackArgsToRestore;
    private int[] mBackStackIdsToRestore;
    final Context mContext;
    private NavGraph mGraph;
    private NavInflater mInflater;
    private Bundle mNavigatorStateToRestore;
    final Deque<NavBackStackEntry> mBackStack = new ArrayDeque();
    private final NavigatorProvider mNavigatorProvider = new NavigatorProvider() { // from class: androidx.navigation.NavController.1
        @Override // androidx.navigation.NavigatorProvider
        public Navigator<? extends NavDestination> addNavigator(String name, Navigator<? extends NavDestination> navigator) {
            Navigator<? extends NavDestination> previousNavigator = super.addNavigator(name, navigator);
            if (previousNavigator != navigator) {
                if (previousNavigator != null) {
                    previousNavigator.removeOnNavigatorBackPressListener(NavController.this.mOnBackPressListener);
                }
                navigator.addOnNavigatorBackPressListener(NavController.this.mOnBackPressListener);
            }
            return previousNavigator;
        }
    };
    final Navigator.OnNavigatorBackPressListener mOnBackPressListener = new Navigator.OnNavigatorBackPressListener() { // from class: androidx.navigation.NavController.2
        @Override // androidx.navigation.Navigator.OnNavigatorBackPressListener
        public void onPopBackStack(Navigator navigator) {
            NavDestination lastFromNavigator = null;
            Iterator<NavBackStackEntry> iterator = NavController.this.mBackStack.descendingIterator();
            while (true) {
                if (!iterator.hasNext()) {
                    break;
                }
                NavDestination destination = iterator.next().getDestination();
                Navigator currentNavigator = NavController.this.getNavigatorProvider().getNavigator(destination.getNavigatorName());
                if (currentNavigator == navigator) {
                    lastFromNavigator = destination;
                    break;
                }
            }
            if (lastFromNavigator == null) {
                throw new IllegalArgumentException("Navigator " + navigator + " reported pop but did not have any destinations on the NavController back stack");
            }
            NavController.this.popBackStackInternal(lastFromNavigator.getId(), false);
            if (!NavController.this.mBackStack.isEmpty()) {
                NavController.this.mBackStack.removeLast();
            }
            NavController.this.dispatchOnDestinationChanged();
        }
    };
    private final CopyOnWriteArrayList<OnDestinationChangedListener> mOnDestinationChangedListeners = new CopyOnWriteArrayList<>();

    /* loaded from: classes.dex */
    public interface OnDestinationChangedListener {
        void onDestinationChanged(NavController navController, NavDestination navDestination, Bundle bundle);
    }

    public NavController(Context context) {
        this.mContext = context;
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                break;
            }
            if (context instanceof Activity) {
                this.mActivity = (Activity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        this.mNavigatorProvider.addNavigator(new NavGraphNavigator(this.mNavigatorProvider));
        this.mNavigatorProvider.addNavigator(new ActivityNavigator(this.mContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Context getContext() {
        return this.mContext;
    }

    public NavigatorProvider getNavigatorProvider() {
        return this.mNavigatorProvider;
    }

    public void addOnDestinationChangedListener(OnDestinationChangedListener listener) {
        if (!this.mBackStack.isEmpty()) {
            NavBackStackEntry backStackEntry = this.mBackStack.peekLast();
            listener.onDestinationChanged(this, backStackEntry.getDestination(), backStackEntry.getArguments());
        }
        this.mOnDestinationChangedListeners.add(listener);
    }

    public void removeOnDestinationChangedListener(OnDestinationChangedListener listener) {
        this.mOnDestinationChangedListeners.remove(listener);
    }

    public boolean popBackStack() {
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        return popBackStack(getCurrentDestination().getId(), true);
    }

    public boolean popBackStack(int destinationId, boolean inclusive) {
        boolean popped = popBackStackInternal(destinationId, inclusive);
        return popped && dispatchOnDestinationChanged();
    }

    boolean popBackStackInternal(int destinationId, boolean inclusive) {
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        ArrayList<Navigator> popOperations = new ArrayList<>();
        Iterator<NavBackStackEntry> iterator = this.mBackStack.descendingIterator();
        boolean foundDestination = false;
        while (true) {
            if (!iterator.hasNext()) {
                break;
            }
            NavDestination destination = iterator.next().getDestination();
            Navigator navigator = this.mNavigatorProvider.getNavigator(destination.getNavigatorName());
            if (inclusive || destination.getId() != destinationId) {
                popOperations.add(navigator);
            }
            if (destination.getId() == destinationId) {
                foundDestination = true;
                break;
            }
        }
        if (!foundDestination) {
            String destinationName = NavDestination.getDisplayName(this.mContext, destinationId);
            Log.i(TAG, "Ignoring popBackStack to destination " + destinationName + " as it was not found on the current back stack");
            return false;
        }
        boolean popped = false;
        Iterator<Navigator> it = popOperations.iterator();
        while (it.hasNext()) {
            Navigator navigator2 = it.next();
            if (!navigator2.popBackStack()) {
                break;
            }
            this.mBackStack.removeLast();
            popped = true;
        }
        return popped;
    }

    public boolean navigateUp() {
        if (getDestinationCountOnBackStack() == 1) {
            NavDestination currentDestination = getCurrentDestination();
            int destId = currentDestination.getId();
            for (NavGraph parent = currentDestination.getParent(); parent != null; parent = parent.getParent()) {
                if (parent.getStartDestination() != destId) {
                    TaskStackBuilder parentIntents = new NavDeepLinkBuilder(this).setDestination(parent.getId()).createTaskStackBuilder();
                    parentIntents.startActivities();
                    Activity activity = this.mActivity;
                    if (activity != null) {
                        activity.finish();
                    }
                    return true;
                }
                destId = parent.getId();
            }
            return false;
        }
        return popBackStack();
    }

    private int getDestinationCountOnBackStack() {
        int count = 0;
        for (NavBackStackEntry entry : this.mBackStack) {
            if (!(entry.getDestination() instanceof NavGraph)) {
                count++;
            }
        }
        return count;
    }

    boolean dispatchOnDestinationChanged() {
        while (!this.mBackStack.isEmpty() && (this.mBackStack.peekLast().getDestination() instanceof NavGraph) && popBackStackInternal(this.mBackStack.peekLast().getDestination().getId(), true)) {
        }
        if (!this.mBackStack.isEmpty()) {
            NavBackStackEntry backStackEntry = this.mBackStack.peekLast();
            Iterator<OnDestinationChangedListener> it = this.mOnDestinationChangedListeners.iterator();
            while (it.hasNext()) {
                OnDestinationChangedListener listener = it.next();
                listener.onDestinationChanged(this, backStackEntry.getDestination(), backStackEntry.getArguments());
            }
            return true;
        }
        return false;
    }

    public NavInflater getNavInflater() {
        if (this.mInflater == null) {
            this.mInflater = new NavInflater(this.mContext, this.mNavigatorProvider);
        }
        return this.mInflater;
    }

    public void setGraph(int graphResId) {
        setGraph(graphResId, (Bundle) null);
    }

    public void setGraph(int graphResId, Bundle startDestinationArgs) {
        setGraph(getNavInflater().inflate(graphResId), startDestinationArgs);
    }

    public void setGraph(NavGraph graph) {
        setGraph(graph, (Bundle) null);
    }

    public void setGraph(NavGraph graph, Bundle startDestinationArgs) {
        NavGraph navGraph = this.mGraph;
        if (navGraph != null) {
            popBackStackInternal(navGraph.getId(), true);
        }
        this.mGraph = graph;
        onGraphCreated(startDestinationArgs);
    }

    private void onGraphCreated(Bundle startDestinationArgs) {
        ArrayList<String> navigatorNames;
        Bundle bundle = this.mNavigatorStateToRestore;
        if (bundle != null && (navigatorNames = bundle.getStringArrayList(KEY_NAVIGATOR_STATE_NAMES)) != null) {
            Iterator<String> it = navigatorNames.iterator();
            while (it.hasNext()) {
                String name = it.next();
                Navigator navigator = this.mNavigatorProvider.getNavigator(name);
                Bundle bundle2 = this.mNavigatorStateToRestore.getBundle(name);
                if (bundle2 != null) {
                    navigator.onRestoreState(bundle2);
                }
            }
        }
        if (this.mBackStackIdsToRestore != null) {
            int index = 0;
            while (true) {
                int[] iArr = this.mBackStackIdsToRestore;
                if (index < iArr.length) {
                    int destinationId = iArr[index];
                    Bundle args = (Bundle) this.mBackStackArgsToRestore[index];
                    NavDestination node = findDestination(destinationId);
                    if (node == null) {
                        throw new IllegalStateException("unknown destination during restore: " + this.mContext.getResources().getResourceName(destinationId));
                    }
                    if (args != null) {
                        args.setClassLoader(this.mContext.getClassLoader());
                    }
                    this.mBackStack.add(new NavBackStackEntry(node, args));
                    index++;
                } else {
                    this.mBackStackIdsToRestore = null;
                    this.mBackStackArgsToRestore = null;
                    break;
                }
            }
        }
        if (this.mGraph != null && this.mBackStack.isEmpty()) {
            Activity activity = this.mActivity;
            boolean deepLinked = activity != null && handleDeepLink(activity.getIntent());
            if (!deepLinked) {
                navigate(this.mGraph, startDestinationArgs, (NavOptions) null, (Navigator.Extras) null);
            }
        }
    }

    public boolean handleDeepLink(Intent intent) {
        NavDestination.DeepLinkMatch matchingDeepLink;
        Object obj;
        NavGraph graph;
        if (intent == null) {
            return false;
        }
        Bundle extras = intent.getExtras();
        int[] deepLink = extras != null ? extras.getIntArray(KEY_DEEP_LINK_IDS) : null;
        Bundle bundle = new Bundle();
        Bundle deepLinkExtras = extras != null ? extras.getBundle(KEY_DEEP_LINK_EXTRAS) : null;
        if (deepLinkExtras != null) {
            bundle.putAll(deepLinkExtras);
        }
        if ((deepLink == null || deepLink.length == 0) && intent.getData() != null && (matchingDeepLink = this.mGraph.matchDeepLink(intent.getData())) != null) {
            deepLink = matchingDeepLink.getDestination().buildDeepLinkIds();
            bundle.putAll(matchingDeepLink.getMatchingArgs());
        }
        if (deepLink == null || deepLink.length == 0) {
            return false;
        }
        String invalidDestinationDisplayName = findInvalidDestinationDisplayNameInDeepLink(deepLink);
        if (invalidDestinationDisplayName != null) {
            Log.i(TAG, "Could not find destination " + invalidDestinationDisplayName + " in the navigation graph, ignoring the deep link from " + intent);
            return false;
        }
        bundle.putParcelable(KEY_DEEP_LINK_INTENT, intent);
        int flags = intent.getFlags();
        int i = 1;
        if ((flags & 268435456) != 0 && (flags & 32768) == 0) {
            intent.addFlags(32768);
            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this.mContext).addNextIntentWithParentStack(intent);
            taskStackBuilder.startActivities();
            Activity activity = this.mActivity;
            if (activity != null) {
                activity.finish();
            }
            return true;
        }
        if ((268435456 & flags) != 0) {
            if (!this.mBackStack.isEmpty()) {
                popBackStackInternal(this.mGraph.getId(), true);
            }
            int destinationId = 0;
            while (destinationId < deepLink.length) {
                int index = destinationId + 1;
                int destinationId2 = deepLink[destinationId];
                NavDestination node = findDestination(destinationId2);
                if (node == null) {
                    throw new IllegalStateException("unknown destination during deep link: " + NavDestination.getDisplayName(this.mContext, destinationId2));
                }
                navigate(node, bundle, new NavOptions.Builder().setEnterAnim(0).setExitAnim(0).build(), (Navigator.Extras) null);
                destinationId = index;
            }
            return true;
        }
        NavGraph graph2 = this.mGraph;
        int i2 = 0;
        while (i2 < deepLink.length) {
            int destinationId3 = deepLink[i2];
            NavDestination node2 = i2 == 0 ? this.mGraph : graph2.findNode(destinationId3);
            if (node2 == null) {
                throw new IllegalStateException("unknown destination during deep link: " + NavDestination.getDisplayName(this.mContext, destinationId3));
            }
            if (i2 != deepLink.length - i) {
                NavDestination navDestination = node2;
                while (true) {
                    graph = (NavGraph) navDestination;
                    if (!(graph.findNode(graph.getStartDestination()) instanceof NavGraph)) {
                        break;
                    }
                    navDestination = graph.findNode(graph.getStartDestination());
                }
                graph2 = graph;
                obj = null;
            } else {
                obj = null;
                navigate(node2, node2.addInDefaultArgs(bundle), new NavOptions.Builder().setPopUpTo(this.mGraph.getId(), true).setEnterAnim(0).setExitAnim(0).build(), (Navigator.Extras) null);
            }
            i2++;
            i = 1;
        }
        return true;
    }

    private String findInvalidDestinationDisplayNameInDeepLink(int[] deepLink) {
        NavGraph graph = this.mGraph;
        int i = 0;
        while (i < deepLink.length) {
            int destinationId = deepLink[i];
            NavDestination node = i == 0 ? this.mGraph : graph.findNode(destinationId);
            if (node == null) {
                return NavDestination.getDisplayName(this.mContext, destinationId);
            }
            if (i != deepLink.length - 1) {
                NavDestination navDestination = node;
                while (true) {
                    graph = (NavGraph) navDestination;
                    if (graph.findNode(graph.getStartDestination()) instanceof NavGraph) {
                        navDestination = graph.findNode(graph.getStartDestination());
                    }
                }
            }
            i++;
        }
        return null;
    }

    public NavGraph getGraph() {
        NavGraph navGraph = this.mGraph;
        if (navGraph == null) {
            throw new IllegalStateException("You must call setGraph() before calling getGraph()");
        }
        return navGraph;
    }

    public NavDestination getCurrentDestination() {
        if (this.mBackStack.isEmpty()) {
            return null;
        }
        return this.mBackStack.getLast().getDestination();
    }

    NavDestination findDestination(int destinationId) {
        NavGraph navGraph = this.mGraph;
        if (navGraph == null) {
            return null;
        }
        if (navGraph.getId() == destinationId) {
            return this.mGraph;
        }
        NavDestination currentNode = this.mBackStack.isEmpty() ? this.mGraph : this.mBackStack.getLast().getDestination();
        NavGraph currentGraph = currentNode instanceof NavGraph ? (NavGraph) currentNode : currentNode.getParent();
        return currentGraph.findNode(destinationId);
    }

    public void navigate(int resId) {
        navigate(resId, (Bundle) null);
    }

    public void navigate(int resId, Bundle args) {
        navigate(resId, args, null);
    }

    public void navigate(int resId, Bundle args, NavOptions navOptions) {
        navigate(resId, args, navOptions, (Navigator.Extras) null);
    }

    public void navigate(int resId, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        NavDestination currentNode = this.mBackStack.isEmpty() ? this.mGraph : this.mBackStack.getLast().getDestination();
        if (currentNode == null) {
            throw new IllegalStateException("no current navigation node");
        }
        int destId = resId;
        NavAction navAction = currentNode.getAction(resId);
        Bundle combinedArgs = null;
        if (navAction != null) {
            if (navOptions == null) {
                navOptions = navAction.getNavOptions();
            }
            destId = navAction.getDestinationId();
            Bundle navActionArgs = navAction.getDefaultArguments();
            if (navActionArgs != null) {
                combinedArgs = new Bundle();
                combinedArgs.putAll(navActionArgs);
            }
        }
        if (args != null) {
            if (combinedArgs == null) {
                combinedArgs = new Bundle();
            }
            combinedArgs.putAll(args);
        }
        if (destId == 0 && navOptions != null && navOptions.getPopUpTo() != -1) {
            popBackStack(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
            return;
        }
        if (destId == 0) {
            throw new IllegalArgumentException("Destination id == 0 can only be used in conjunction with a valid navOptions.popUpTo");
        }
        NavDestination node = findDestination(destId);
        if (node == null) {
            String dest = NavDestination.getDisplayName(this.mContext, destId);
            throw new IllegalArgumentException("navigation destination " + dest + (navAction != null ? " referenced from action " + NavDestination.getDisplayName(this.mContext, resId) : "") + " is unknown to this NavController");
        }
        navigate(node, combinedArgs, navOptions, navigatorExtras);
    }

    private void navigate(NavDestination node, Bundle args, NavOptions navOptions, Navigator.Extras navigatorExtras) {
        boolean popped = false;
        if (navOptions != null && navOptions.getPopUpTo() != -1) {
            popped = popBackStackInternal(navOptions.getPopUpTo(), navOptions.isPopUpToInclusive());
        }
        Navigator<NavDestination> navigator = this.mNavigatorProvider.getNavigator(node.getNavigatorName());
        Bundle finalArgs = node.addInDefaultArgs(args);
        NavDestination newDest = navigator.navigate(node, finalArgs, navOptions, navigatorExtras);
        if (newDest != null) {
            ArrayDeque<NavBackStackEntry> hierarchy = new ArrayDeque<>();
            for (NavGraph parent = newDest.getParent(); parent != null; parent = parent.getParent()) {
                hierarchy.addFirst(new NavBackStackEntry(parent, finalArgs));
            }
            Iterator<NavBackStackEntry> iterator = this.mBackStack.iterator();
            while (iterator.hasNext() && !hierarchy.isEmpty()) {
                NavDestination destination = iterator.next().getDestination();
                if (destination.equals(hierarchy.getFirst().getDestination())) {
                    hierarchy.removeFirst();
                }
            }
            this.mBackStack.addAll(hierarchy);
            NavBackStackEntry newBackStackEntry = new NavBackStackEntry(newDest, finalArgs);
            this.mBackStack.add(newBackStackEntry);
        }
        if (popped || newDest != null) {
            dispatchOnDestinationChanged();
        }
    }

    public void navigate(NavDirections directions) {
        navigate(directions.getActionId(), directions.getArguments());
    }

    public void navigate(NavDirections directions, NavOptions navOptions) {
        navigate(directions.getActionId(), directions.getArguments(), navOptions);
    }

    public void navigate(NavDirections directions, Navigator.Extras navigatorExtras) {
        navigate(directions.getActionId(), directions.getArguments(), (NavOptions) null, navigatorExtras);
    }

    public NavDeepLinkBuilder createDeepLink() {
        return new NavDeepLinkBuilder(this);
    }

    public Bundle saveState() {
        Bundle b = null;
        ArrayList<String> navigatorNames = new ArrayList<>();
        Bundle navigatorState = new Bundle();
        for (Map.Entry<String, Navigator<? extends NavDestination>> entry : this.mNavigatorProvider.getNavigators().entrySet()) {
            String name = entry.getKey();
            Bundle savedState = entry.getValue().onSaveState();
            if (savedState != null) {
                navigatorNames.add(name);
                navigatorState.putBundle(name, savedState);
            }
        }
        if (!navigatorNames.isEmpty()) {
            b = new Bundle();
            navigatorState.putStringArrayList(KEY_NAVIGATOR_STATE_NAMES, navigatorNames);
            b.putBundle(KEY_NAVIGATOR_STATE, navigatorState);
        }
        if (!this.mBackStack.isEmpty()) {
            if (b == null) {
                b = new Bundle();
            }
            int[] backStackIds = new int[this.mBackStack.size()];
            Parcelable[] backStackArgs = new Parcelable[this.mBackStack.size()];
            int index = 0;
            for (NavBackStackEntry backStackEntry : this.mBackStack) {
                backStackIds[index] = backStackEntry.getDestination().getId();
                backStackArgs[index] = backStackEntry.getArguments();
                index++;
            }
            b.putIntArray(KEY_BACK_STACK_IDS, backStackIds);
            b.putParcelableArray(KEY_BACK_STACK_ARGS, backStackArgs);
        }
        return b;
    }

    public void restoreState(Bundle navState) {
        if (navState == null) {
            return;
        }
        navState.setClassLoader(this.mContext.getClassLoader());
        this.mNavigatorStateToRestore = navState.getBundle(KEY_NAVIGATOR_STATE);
        this.mBackStackIdsToRestore = navState.getIntArray(KEY_BACK_STACK_IDS);
        this.mBackStackArgsToRestore = navState.getParcelableArray(KEY_BACK_STACK_ARGS);
    }
}
