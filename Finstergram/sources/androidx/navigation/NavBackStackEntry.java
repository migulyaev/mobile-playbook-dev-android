package androidx.navigation;

import android.os.Bundle;

/* loaded from: classes.dex */
final class NavBackStackEntry {
    private final Bundle mArgs;
    private final NavDestination mDestination;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NavBackStackEntry(NavDestination destination, Bundle args) {
        this.mDestination = destination;
        this.mArgs = args;
    }

    public NavDestination getDestination() {
        return this.mDestination;
    }

    public Bundle getArguments() {
        return this.mArgs;
    }
}
