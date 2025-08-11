package androidx.navigation;

import android.os.Bundle;
import androidx.navigation.NavDestination;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public abstract class Navigator<D extends NavDestination> {
    private final CopyOnWriteArrayList<OnNavigatorBackPressListener> mOnBackPressListeners = new CopyOnWriteArrayList<>();

    /* loaded from: classes.dex */
    public interface Extras {
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    /* loaded from: classes.dex */
    public @interface Name {
        String value();
    }

    /* loaded from: classes.dex */
    public interface OnNavigatorBackPressListener {
        void onPopBackStack(Navigator navigator);
    }

    public abstract D createDestination();

    public abstract NavDestination navigate(D d, Bundle bundle, NavOptions navOptions, Extras extras);

    public abstract boolean popBackStack();

    public Bundle onSaveState() {
        return null;
    }

    public void onRestoreState(Bundle savedState) {
    }

    protected void onBackPressAdded() {
    }

    protected void onBackPressRemoved() {
    }

    public final void addOnNavigatorBackPressListener(OnNavigatorBackPressListener listener) {
        boolean added = this.mOnBackPressListeners.add(listener);
        if (added && this.mOnBackPressListeners.size() == 1) {
            onBackPressAdded();
        }
    }

    public final void removeOnNavigatorBackPressListener(OnNavigatorBackPressListener listener) {
        boolean removed = this.mOnBackPressListeners.remove(listener);
        if (removed && this.mOnBackPressListeners.isEmpty()) {
            onBackPressRemoved();
        }
    }

    public final void dispatchOnNavigatorBackPress() {
        Iterator<OnNavigatorBackPressListener> it = this.mOnBackPressListeners.iterator();
        while (it.hasNext()) {
            OnNavigatorBackPressListener listener = it.next();
            listener.onPopBackStack(this);
        }
    }
}
