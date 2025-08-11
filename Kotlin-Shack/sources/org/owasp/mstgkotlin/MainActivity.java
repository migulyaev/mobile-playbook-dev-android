package org.owasp.mstgkotlin;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
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
import de.adorsys.android.securestoragelibrary.SecurePreferences;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MainActivity.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0002J\b\u0010\u0010\u001a\u00020\u000eH\u0002J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u0013H\u0002J\u0006\u0010\u0015\u001a\u00020\u000eJ\u000e\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018J\u0012\u0010\u0019\u001a\u00020\u00132\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u000eH\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\f¨\u0006\u001d"}, d2 = {"Lorg/owasp/mstgkotlin/MainActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "jwtInsecureFilename", "", "getJwtInsecureFilename", "()Ljava/lang/String;", "prefsInsecure", "Landroid/content/SharedPreferences;", "token", "getToken", "setToken", "(Ljava/lang/String;)V", "checkRootMethod1", "", "checkRootMethod2", "checkRootMethod3", "checkRootMethod4", "goToMenu", "", "goToRegister", "isDeviceRooted", "isValidEmail", "target", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "singleRootDetection", "app_debug"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class MainActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    private SharedPreferences prefsInsecure;

    @NotNull
    private String token = "";

    @NotNull
    private final String jwtInsecureFilename = "InsecurePreferences";

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

    @NotNull
    public final String getToken() {
        return this.token;
    }

    public final void setToken(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.token = str;
    }

    @NotNull
    public final String getJwtInsecureFilename() {
        return this.jwtInsecureFilename;
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View findViewById = findViewById(R.id.etendpoint);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        final EditText endpoint = (EditText) findViewById;
        View findViewById2 = findViewById(R.id.etemail);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        final EditText loginemail = (EditText) findViewById2;
        View findViewById3 = findViewById(R.id.etpassword);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
        }
        final EditText loginpassword = (EditText) findViewById3;
        View findViewById4 = findViewById(R.id.btnregister);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        Button btnregister = (Button) findViewById4;
        View findViewById5 = findViewById(R.id.btnsubmit);
        if (findViewById5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        Button btnsubmit = (Button) findViewById5;
        View findViewById6 = findViewById(R.id.progressBar);
        if (findViewById6 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ProgressBar");
        }
        final ProgressBar progressbar = (ProgressBar) findViewById6;
        View findViewById7 = findViewById(R.id.btn_insecure_rootcheck);
        if (findViewById7 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        Button btn_insecureRootCheck = (Button) findViewById7;
        View findViewById8 = findViewById(R.id.btn_secure_rootcheck);
        if (findViewById8 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        Button btn_secureRootCheck = (Button) findViewById8;
        btn_secureRootCheck.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MainActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                if (MainActivity.this.isDeviceRooted()) {
                    Toast $receiver$iv = Toast.makeText(MainActivity.this, message$iv, 1);
                    $receiver$iv.show();
                    Intrinsics.checkExpressionValueIsNotNull($receiver$iv, "Toast\n        .makeText(…         show()\n        }");
                    return;
                }
                Toast $receiver$iv2 = Toast.makeText(MainActivity.this, message$iv, 1);
                $receiver$iv2.show();
                Intrinsics.checkExpressionValueIsNotNull($receiver$iv2, "Toast\n        .makeText(…         show()\n        }");
            }
        });
        btn_insecureRootCheck.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MainActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                boolean singleRootDetection;
                singleRootDetection = MainActivity.this.singleRootDetection();
                if (singleRootDetection) {
                    Toast $receiver$iv = Toast.makeText(MainActivity.this, message$iv, 1);
                    $receiver$iv.show();
                    Intrinsics.checkExpressionValueIsNotNull($receiver$iv, "Toast\n        .makeText(…         show()\n        }");
                    return;
                }
                Toast $receiver$iv2 = Toast.makeText(MainActivity.this, message$iv, 1);
                $receiver$iv2.show();
                Intrinsics.checkExpressionValueIsNotNull($receiver$iv2, "Toast\n        .makeText(…         show()\n        }");
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MainActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MainActivity.this.goToRegister();
            }
        });
        btnsubmit.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MainActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                FuelManager.INSTANCE.getInstance().setBasePath(endpoint.getText().toString());
                String password = EndtoEndEncryptionKt.encrypt(loginpassword.getText().toString());
                String username = EndtoEndEncryptionKt.encrypt(loginemail.getText().toString());
                String loginbody = "{ \"email\" : \"" + username + "\", \"password\" : \"" + password + "\" }";
                MainActivity mainActivity = MainActivity.this;
                Editable text = loginemail.getText();
                Intrinsics.checkExpressionValueIsNotNull(text, "loginemail.text");
                if (mainActivity.isValidEmail(text)) {
                    progressbar.setVisibility(0);
                    Request req = Request.body$default(Fuel.Companion.post$default(Fuel.INSTANCE, "/auth/login", (List) null, 2, (Object) null), loginbody, null, 2, null);
                    req.getHeaders().put("Content-Type", "application/json");
                    RequestsKt.responseJson(req, new Function3<Request, Response, Result<? extends Json, ? extends FuelError>, Unit>() { // from class: org.owasp.mstgkotlin.MainActivity$onCreate$4.1
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
                            Object obj;
                            SharedPreferences sharedPreferences;
                            Intrinsics.checkParameterIsNotNull(request, "request");
                            Intrinsics.checkParameterIsNotNull(response, "response");
                            Intrinsics.checkParameterIsNotNull(result, "result");
                            int statusCode = response.getStatusCode();
                            if (200 > statusCode || 201 < statusCode) {
                                if (400 <= statusCode && 401 >= statusCode) {
                                    Toast makeText = Toast.makeText(MainActivity.this, "Oh oh, HTTP Status code 4xx! Wrong credentials?", 1);
                                    makeText.show();
                                    Intrinsics.checkExpressionValueIsNotNull(makeText, "Toast\n        .makeText(…         show()\n        }");
                                    progressbar.setVisibility(8);
                                    return;
                                }
                                return;
                            }
                            if (result instanceof Result.Success) {
                                Json json = (Json) ((Result.Success) result).getValue();
                                MainActivity mainActivity2 = MainActivity.this;
                                String string = json.obj().getString("auth_token");
                                Intrinsics.checkExpressionValueIsNotNull(string, "json.obj().getString(\"auth_token\")");
                                mainActivity2.setToken(string);
                                obj = Integer.valueOf(Log.d("token", json.obj().getString("auth_token")));
                            } else {
                                if (!(result instanceof Result.Failure)) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                Toast makeText2 = Toast.makeText(MainActivity.this, "Invalid login credentials", 1);
                                makeText2.show();
                                Intrinsics.checkExpressionValueIsNotNull(makeText2, "Toast\n        .makeText(…         show()\n        }");
                                obj = makeText2;
                            }
                            if (Intrinsics.areEqual(result, obj)) {
                            }
                            progressbar.setVisibility(8);
                            if (TextUtils.isEmpty(MainActivity.this.getToken())) {
                                return;
                            }
                            MainActivity.this.prefsInsecure = MainActivity.this.getSharedPreferences(MainActivity.this.getJwtInsecureFilename(), 0);
                            sharedPreferences = MainActivity.this.prefsInsecure;
                            SharedPreferences.Editor edit = sharedPreferences != null ? sharedPreferences.edit() : null;
                            if (edit != null) {
                                edit.putString("auth_token", MainActivity.this.getToken());
                            }
                            if (edit != null) {
                                edit.apply();
                            }
                            SecurePreferences.setValue("auth_token", MainActivity.this.getToken(), MainActivity.this);
                            MainActivity.this.goToMenu();
                        }
                    });
                    return;
                }
                Toast $receiver$iv = Toast.makeText(MainActivity.this, message$iv, 1);
                $receiver$iv.show();
                Intrinsics.checkExpressionValueIsNotNull($receiver$iv, "Toast\n        .makeText(…         show()\n        }");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goToRegister() {
        Pair[] params$iv = new Pair[0];
        AnkoInternals.internalStartActivity(this, RegisterActivity.class, params$iv);
    }

    public final boolean isValidEmail(@NotNull CharSequence target) {
        Intrinsics.checkParameterIsNotNull(target, "target");
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goToMenu() {
        Pair[] params$iv = new Pair[0];
        AnkoInternals.internalStartActivity(this, MenuActivity.class, params$iv);
    }

    public final boolean isDeviceRooted() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod3() || checkRootMethod4();
    }

    private final boolean checkRootMethod1() {
        String buildTags = Build.TAGS;
        return buildTags != null && StringsKt.contains$default((CharSequence) buildTags, (CharSequence) "test-keys", false, 2, (Object) null);
    }

    private final boolean checkRootMethod2() {
        String[] paths = {"/system/app/Superuser.apk", "/system/etc/init.d/99SuperSUDaemon", "/dev/com.koushikdutta.superuser.daemon/", "/system/xbin/daemonsu", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }

    private final boolean checkRootMethod3() {
        Process process = (Process) null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process != null ? process.getInputStream() : null));
            boolean z = in.readLine() != null;
            if (process != null) {
                process.destroy();
            }
            return z;
        } catch (Throwable th) {
            if (process != null) {
                process.destroy();
            }
            throw th;
        }
    }

    private final boolean checkRootMethod4() {
        Collection emptyList;
        String str = System.getenv("PATH");
        Intrinsics.checkExpressionValueIsNotNull(str, "System.getenv(\"PATH\")");
        List $receiver$iv = new Regex(":").split(str, 0);
        if (!$receiver$iv.isEmpty()) {
            ListIterator iterator$iv = $receiver$iv.listIterator($receiver$iv.size());
            while (iterator$iv.hasPrevious()) {
                String it = iterator$iv.previous();
                String it2 = it.length() == 0 ? 1 : null;
                if (it2 == null) {
                    emptyList = CollectionsKt.take($receiver$iv, iterator$iv.nextIndex() + 1);
                    break;
                }
            }
        }
        emptyList = CollectionsKt.emptyList();
        Collection $receiver$iv2 = emptyList;
        if ($receiver$iv2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
        }
        Object[] array = $receiver$iv2.toArray(new String[0]);
        if (array == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        for (String pathDir : (String[]) array) {
            if (new File(pathDir, "su").exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean singleRootDetection() {
        String[] paths = {"/system/bin/su"};
        for (String path : paths) {
            if (new File(path).exists()) {
                return true;
            }
        }
        return false;
    }
}
