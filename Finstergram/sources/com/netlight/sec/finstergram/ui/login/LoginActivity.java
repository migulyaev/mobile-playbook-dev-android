package com.netlight.sec.finstergram.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.snackbar.Snackbar;
import com.netlight.sec.finstergram.R;
import com.netlight.sec.finstergram.data.UserSettings;
import com.netlight.sec.finstergram.service.ImageStoreService;
import com.netlight.sec.finstergram.ui.FinstergramBaseActivity;
import com.netlight.sec.finstergram.ui.list.ImageListActivity;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: LoginActivity.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0002J\u0012\u0010\u0018\u001a\u00020\u00172\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0012\u0010\u001b\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0014J\u0012\u0010\u001f\u001a\u00020\u00172\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0002J\b\u0010 \u001a\u00020\u0017H\u0014J\u0010\u0010!\u001a\u00020\u00172\u0006\u0010\"\u001a\u00020#H\u0016R\u001c\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\t0\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0007R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u001c\u0010\u0010\u001a\n \u0005*\u0004\u0018\u00010\u00110\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0014\u001a\n \u0005*\u0004\u0018\u00010\u00110\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013¨\u0006$"}, d2 = {"Lcom/netlight/sec/finstergram/ui/login/LoginActivity;", "Lcom/netlight/sec/finstergram/ui/FinstergramBaseActivity;", "()V", "passwordField", "Landroid/widget/EditText;", "kotlin.jvm.PlatformType", "getPasswordField", "()Landroid/widget/EditText;", "registerOrLoginButton", "Landroid/widget/Button;", "getRegisterOrLoginButton", "()Landroid/widget/Button;", "usernameField", "getUsernameField", "viewModel", "Lcom/netlight/sec/finstergram/ui/login/LoginViewModel;", "welcomeSubtitle", "Landroid/widget/TextView;", "getWelcomeSubtitle", "()Landroid/widget/TextView;", "welcomeTitle", "getWelcomeTitle", "navigateToImageList", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onLoginButtonClick", "view", "Landroid/view/View;", "onPause", "onRegisterButtonClick", "onResume", "setBackgroundColor", TypedValues.Custom.S_COLOR, "", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginActivity extends FinstergramBaseActivity {
    private LoginViewModel viewModel;

    /* JADX INFO: Access modifiers changed from: private */
    public final TextView getWelcomeTitle() {
        return (TextView) findViewById(R.id.welcomeTitle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final TextView getWelcomeSubtitle() {
        return (TextView) findViewById(R.id.welcomeSubtitle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Button getRegisterOrLoginButton() {
        return (Button) findViewById(R.id.buttonRegisterLogin);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EditText getUsernameField() {
        return (EditText) findViewById(R.id.username);
    }

    private final EditText getPasswordField() {
        return (EditText) findViewById(R.id.password);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Intent serviceIntent = new Intent(this, (Class<?>) ImageStoreService.class);
        startService(serviceIntent);
        this.viewModel = (LoginViewModel) new ViewModelProvider(this).get(LoginViewModel.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.netlight.sec.finstergram.ui.FinstergramBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LoginViewModel loginViewModel = this.viewModel;
        LoginViewModel loginViewModel2 = null;
        if (loginViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            loginViewModel = null;
        }
        loginViewModel.init();
        LoginViewModel loginViewModel3 = this.viewModel;
        if (loginViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            loginViewModel3 = null;
        }
        if (loginViewModel3.getRegisteredUser().getValue() != null && !UserSettings.INSTANCE.getInstance().getRequirePassword()) {
            navigateToImageList();
            return;
        }
        LoginViewModel loginViewModel4 = this.viewModel;
        if (loginViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            loginViewModel2 = loginViewModel4;
        }
        final Function1<String, Unit> function1 = new Function1<String, Unit>() { // from class: com.netlight.sec.finstergram.ui.login.LoginActivity$onResume$1
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str) {
                invoke2(str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(String username) {
                TextView welcomeTitle;
                TextView welcomeSubtitle;
                Button registerOrLoginButton;
                EditText usernameField;
                TextView welcomeTitle2;
                TextView welcomeSubtitle2;
                Button registerOrLoginButton2;
                EditText usernameField2;
                if (username != null) {
                    welcomeTitle2 = LoginActivity.this.getWelcomeTitle();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = LoginActivity.this.getString(R.string.welcome_user);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.welcome_user)");
                    String format = String.format(string, Arrays.copyOf(new Object[]{username}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    welcomeTitle2.setText(format);
                    welcomeSubtitle2 = LoginActivity.this.getWelcomeSubtitle();
                    welcomeSubtitle2.setText(LoginActivity.this.getString(R.string.please_login));
                    registerOrLoginButton2 = LoginActivity.this.getRegisterOrLoginButton();
                    registerOrLoginButton2.setText("Login");
                    usernameField2 = LoginActivity.this.getUsernameField();
                    usernameField2.setVisibility(8);
                    return;
                }
                welcomeTitle = LoginActivity.this.getWelcomeTitle();
                welcomeTitle.setText(LoginActivity.this.getString(R.string.welcome));
                welcomeSubtitle = LoginActivity.this.getWelcomeSubtitle();
                welcomeSubtitle.setText(LoginActivity.this.getString(R.string.welcome_subtitle));
                registerOrLoginButton = LoginActivity.this.getRegisterOrLoginButton();
                registerOrLoginButton.setText("Register");
                usernameField = LoginActivity.this.getUsernameField();
                usernameField.setVisibility(0);
            }
        };
        loginViewModel2.getRegisteredUser().observe(this, new Observer() { // from class: com.netlight.sec.finstergram.ui.login.LoginActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LoginActivity.onResume$lambda$0(Function1.this, obj);
            }
        });
        getRegisterOrLoginButton().setOnClickListener(new View.OnClickListener() { // from class: com.netlight.sec.finstergram.ui.login.LoginActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LoginActivity.onResume$lambda$1(LoginActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$0(Function1 tmp0, Object p0) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$1(LoginActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LoginViewModel loginViewModel = this$0.viewModel;
        if (loginViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            loginViewModel = null;
        }
        if (loginViewModel.getRegisteredUser().getValue() != null) {
            this$0.onLoginButtonClick(it);
        } else {
            this$0.onRegisterButtonClick(it);
        }
    }

    @Override // com.netlight.sec.finstergram.ui.FinstergramBaseActivity
    public void setBackgroundColor(int color) {
        ((ConstraintLayout) findViewById(R.id.rootView)).setBackgroundColor(color);
    }

    private final void onLoginButtonClick(View view) {
        LoginViewModel loginViewModel = this.viewModel;
        if (loginViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            loginViewModel = null;
        }
        boolean authenticated = loginViewModel.authenticateUser(getPasswordField().getText().toString());
        if (authenticated) {
            navigateToImageList();
        } else {
            Intrinsics.checkNotNull(view);
            Snackbar.make(view, "Wrong credentials!", -1).show();
        }
    }

    private final void onRegisterButtonClick(View view) {
        String userName = getUsernameField().getText().toString();
        String password = getPasswordField().getText().toString();
        if (!(userName.length() == 0)) {
            if (!(password.length() == 0)) {
                LoginViewModel loginViewModel = this.viewModel;
                if (loginViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    loginViewModel = null;
                }
                boolean registrationSuccessful = loginViewModel.register(userName, password);
                if (registrationSuccessful) {
                    Intrinsics.checkNotNull(view);
                    Snackbar.make(view, "Registration successful", -1).show();
                    navigateToImageList();
                    return;
                } else {
                    Intrinsics.checkNotNull(view);
                    Snackbar.make(view, "Registration failed!", -1).show();
                    return;
                }
            }
        }
        Intrinsics.checkNotNull(view);
        Snackbar.make(view, "Empty Username or Password is not allowed!", -1).show();
    }

    private final void navigateToImageList() {
        Intent intent = new Intent(this, (Class<?>) ImageListActivity.class);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        getUsernameField().setText((CharSequence) null);
        getPasswordField().setText((CharSequence) null);
    }
}
