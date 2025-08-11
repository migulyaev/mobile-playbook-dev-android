package org.owasp.mstgkotlin;

import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.android.core.Json;
import com.github.kittinunf.fuel.android.extension.RequestsKt;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.FuelManager;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.github.kittinunf.result.Result;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: RegisterActivity.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u0012\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J \u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0006H\u0002¨\u0006\u0012"}, d2 = {"Lorg/owasp/mstgkotlin/RegisterActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "isMatchingPassword", "", "target1", "", "target2", "isValidEmail", "target", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "validate", NotificationCompat.CATEGORY_EMAIL, "password", "cpassword", "app_debug"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class RegisterActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        View findViewById = findViewById(R.id.endpoint);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        final EditText endpoint = (EditText) findViewById;
        View findViewById2 = findViewById(R.id.etusername);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        final EditText username = (EditText) findViewById2;
        View findViewById3 = findViewById(R.id.etemail);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        final EditText email = (EditText) findViewById3;
        View findViewById4 = findViewById(R.id.etpassword);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        final EditText password = (EditText) findViewById4;
        View findViewById5 = findViewById(R.id.etconfirmpassword);
        if (findViewById5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        final EditText cpassword = (EditText) findViewById5;
        View findViewById6 = findViewById(R.id.btnreset);
        if (findViewById6 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        Button btnreset = (Button) findViewById6;
        View findViewById7 = findViewById(R.id.btnsubmit);
        if (findViewById7 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        Button btnsubmit = (Button) findViewById7;
        View findViewById8 = findViewById(R.id.progressBar);
        if (findViewById8 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ProgressBar");
        }
        final ProgressBar progressbar = (ProgressBar) findViewById8;
        final Ref.ObjectRef encryptedEmail = new Ref.ObjectRef();
        encryptedEmail.element = "test";
        final Ref.ObjectRef encryptedPassword = new Ref.ObjectRef();
        encryptedPassword.element = "test";
        btnreset.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.RegisterActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                username.setText("");
                email.setText("");
                password.setText("");
                cpassword.setText("");
                Toast $receiver$iv = Toast.makeText(RegisterActivity.this, message$iv, 0);
                $receiver$iv.show();
                Intrinsics.checkExpressionValueIsNotNull($receiver$iv, "Toast\n        .makeText(…         show()\n        }");
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.RegisterActivity$onCreate$2
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r2v22, types: [T, java.lang.String] */
            /* JADX WARN: Type inference failed for: r2v26, types: [T, java.lang.String] */
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                boolean validate;
                FuelManager.Companion.getInstance().setBasePath(endpoint.getText().toString());
                RegisterActivity registerActivity = RegisterActivity.this;
                Editable text = email.getText();
                Intrinsics.checkExpressionValueIsNotNull(text, "email.text");
                Editable text2 = password.getText();
                Intrinsics.checkExpressionValueIsNotNull(text2, "password.text");
                Editable text3 = cpassword.getText();
                Intrinsics.checkExpressionValueIsNotNull(text3, "cpassword.text");
                validate = registerActivity.validate(text, text2, text3);
                if (validate) {
                    encryptedEmail.element = EndtoEndEncryptionKt.encrypt(email.getText().toString());
                    encryptedPassword.element = EndtoEndEncryptionKt.encrypt(password.getText().toString());
                }
                String loginbody = "{ \"name\" : \"" + ((Object) username.getText()) + "\", \"email\" : \"" + ((String) encryptedEmail.element) + "\", \"password\" : \"" + ((String) encryptedPassword.element) + "\" }";
                progressbar.setVisibility(0);
                Request req = Request.body$default(Fuel.Companion.post$default(Fuel.INSTANCE, "/signup", (List) null, 2, (Object) null), loginbody, null, 2, null);
                req.getHeaders().put("Content-Type", "application/json");
                RequestsKt.responseJson(req, new Function3<Request, Response, Result<? extends Json, ? extends FuelError>, Unit>() { // from class: org.owasp.mstgkotlin.RegisterActivity$onCreate$2.1
                    {
                        super(3);
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Request request, Response response, Result<? extends Json, ? extends FuelError> result) {
                        invoke2(request, response, (Result<Json, FuelError>) result);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(@NotNull Request request, @NotNull Response response, @NotNull Result<Json, FuelError> result) {
                        Toast $receiver$iv;
                        Intrinsics.checkParameterIsNotNull(request, "request");
                        Intrinsics.checkParameterIsNotNull(response, "response");
                        Intrinsics.checkParameterIsNotNull(result, "result");
                        if (result instanceof Result.Success) {
                            $receiver$iv = Toast.makeText(RegisterActivity.this, message$iv, 1);
                            $receiver$iv.show();
                            Intrinsics.checkExpressionValueIsNotNull($receiver$iv, "Toast\n        .makeText(…         show()\n        }");
                        } else {
                            if (!(result instanceof Result.Failure)) {
                                throw new NoWhenBranchMatchedException();
                            }
                            $receiver$iv = Toast.makeText(RegisterActivity.this, message$iv, 1);
                            $receiver$iv.show();
                            Intrinsics.checkExpressionValueIsNotNull($receiver$iv, "Toast\n        .makeText(…         show()\n        }");
                        }
                        if (Intrinsics.areEqual(result, $receiver$iv)) {
                        }
                        progressbar.setVisibility(8);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean validate(CharSequence email, CharSequence password, CharSequence cpassword) {
        if (!isValidEmail(email)) {
            Toast $receiver$iv = Toast.makeText(this, message$iv, 0);
            $receiver$iv.show();
            Intrinsics.checkExpressionValueIsNotNull($receiver$iv, "Toast\n        .makeText(…         show()\n        }");
            return false;
        }
        if (isMatchingPassword(password, cpassword)) {
            return true;
        }
        Toast $receiver$iv2 = Toast.makeText(this, message$iv, 0);
        $receiver$iv2.show();
        Intrinsics.checkExpressionValueIsNotNull($receiver$iv2, "Toast\n        .makeText(…         show()\n        }");
        return false;
    }

    public final boolean isValidEmail(@NotNull CharSequence target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public final boolean isMatchingPassword(@NotNull CharSequence target1, @NotNull CharSequence target2) {
        Intrinsics.checkParameterIsNotNull(target1, "target1");
        Intrinsics.checkParameterIsNotNull(target2, "target2");
        return (TextUtils.isEmpty(target1) || TextUtils.isEmpty(target2) || !target1.toString().equals(target2.toString())) ? false : true;
    }
}
