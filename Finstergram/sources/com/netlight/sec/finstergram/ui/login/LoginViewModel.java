package com.netlight.sec.finstergram.ui.login;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.netlight.sec.finstergram.data.DatabaseHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LoginViewModel.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0007J\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0007R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0018"}, d2 = {"Lcom/netlight/sec/finstergram/ui/login/LoginViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_registeredUser", "Landroidx/lifecycle/MutableLiveData;", "", "appContext", "getAppContext", "()Landroid/app/Application;", "dbHelper", "Lcom/netlight/sec/finstergram/data/DatabaseHelper;", "registeredUser", "Landroidx/lifecycle/LiveData;", "getRegisteredUser", "()Landroidx/lifecycle/LiveData;", "authenticateUser", "", "password", "init", "", "register", "username", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginViewModel extends AndroidViewModel {
    private final MutableLiveData<String> _registeredUser;
    private final DatabaseHelper dbHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.dbHelper = new DatabaseHelper(getAppContext());
        this._registeredUser = new MutableLiveData<>(null);
    }

    private final Application getAppContext() {
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication<Application>()");
        return application;
    }

    public final LiveData<String> getRegisteredUser() {
        return this._registeredUser;
    }

    public final void init() {
        this._registeredUser.setValue(this.dbHelper.getUsernameIfRegistered());
    }

    public final boolean register(String username, String password) {
        Intrinsics.checkNotNullParameter(username, "username");
        Intrinsics.checkNotNullParameter(password, "password");
        boolean successful = this.dbHelper.registerUser(username, password);
        if (successful) {
            this._registeredUser.setValue(username);
        }
        return successful;
    }

    public final boolean authenticateUser(String password) {
        Intrinsics.checkNotNullParameter(password, "password");
        DatabaseHelper databaseHelper = this.dbHelper;
        String value = this._registeredUser.getValue();
        Intrinsics.checkNotNull(value);
        return databaseHelper.authenticateUser(value, password);
    }
}
