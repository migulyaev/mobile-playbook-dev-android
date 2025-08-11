package org.owasp.mstgkotlin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.anko.internals.AnkoInternals;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: MenuActivity.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lorg/owasp/mstgkotlin/MenuActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "goToInsecureDeepLinkActivity", "", "goToInsecureWebViewActivity", "goToSecureWebViewActivity", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "toggleFlag", "", "flag", "tv", "Landroid/widget/TextView;", "app_debug"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class MenuActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_menu);
        final Ref.BooleanRef flag_sharedPref = new Ref.BooleanRef();
        flag_sharedPref.element = false;
        final Ref.BooleanRef flag_webview = new Ref.BooleanRef();
        flag_webview.element = false;
        View findViewById = findViewById(R.id.tvsharedpref);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView tv_sharedPref = (TextView) findViewById;
        View findViewById2 = findViewById(R.id.tvsharedpref_details);
        if (findViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        final TextView tv_sharedPrefDetails = (TextView) findViewById2;
        View findViewById3 = findViewById(R.id.tvwebview);
        if (findViewById3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        TextView tv_webview = (TextView) findViewById3;
        View findViewById4 = findViewById(R.id.tvwebview_details);
        if (findViewById4 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        final TextView tv_webviewDetails = (TextView) findViewById4;
        View findViewById5 = findViewById(R.id.btn_unsafe_webview);
        if (findViewById5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        final Button btn_unsafeWebview = (Button) findViewById5;
        View findViewById6 = findViewById(R.id.btn_safe_webview);
        if (findViewById6 == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        final Button btn_safeWebview = (Button) findViewById6;
        tv_sharedPref.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MenuActivity$onCreate$1
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                flag_sharedPref.element = MenuActivity.this.toggleFlag(flag_sharedPref.element, tv_sharedPrefDetails);
            }
        });
        tv_webview.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MenuActivity$onCreate$2
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                flag_webview.element = MenuActivity.this.toggleFlag(flag_webview.element, tv_webviewDetails);
                if (flag_webview.element) {
                    btn_unsafeWebview.setVisibility(0);
                    btn_safeWebview.setVisibility(0);
                } else {
                    btn_unsafeWebview.setVisibility(8);
                    btn_safeWebview.setVisibility(8);
                }
            }
        });
        btn_safeWebview.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MenuActivity$onCreate$3
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MenuActivity.this.goToSecureWebViewActivity();
            }
        });
        btn_unsafeWebview.setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MenuActivity$onCreate$4
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MenuActivity.this.goToInsecureWebViewActivity();
            }
        });
        ((Button) _$_findCachedViewById(R.id.btn_insecureDeeplink)).setOnClickListener(new View.OnClickListener() { // from class: org.owasp.mstgkotlin.MenuActivity$onCreate$5
            @Override // android.view.View.OnClickListener
            public final void onClick(View it) {
                MenuActivity.this.goToInsecureDeepLinkActivity();
            }
        });
    }

    public final boolean toggleFlag(boolean flag, @NotNull TextView tv) {
        Intrinsics.checkParameterIsNotNull(tv, "tv");
        if (!flag) {
            tv.setVisibility(0);
            return true;
        }
        tv.setVisibility(8);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goToSecureWebViewActivity() {
        Pair[] params$iv = new Pair[0];
        AnkoInternals.internalStartActivity(this, SecureWebViewActivity.class, params$iv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goToInsecureWebViewActivity() {
        Pair[] params$iv = new Pair[0];
        AnkoInternals.internalStartActivity(this, InsecureWebViewActivity.class, params$iv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goToInsecureDeepLinkActivity() {
        Pair[] params$iv = new Pair[0];
        AnkoInternals.internalStartActivity(this, InsecureDeepLinkActivity.class, params$iv);
    }
}
