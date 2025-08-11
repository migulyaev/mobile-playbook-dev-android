package org.owasp.mstgkotlin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import org.owasp.mstgkotlin.util.JS_Interface;

/* compiled from: InsecureWebViewActivity.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lorg/owasp/mstgkotlin/InsecureWebViewActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class InsecureWebViewActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_insecure_web_view);
        JS_Interface jsInterface = new JS_Interface(this);
        View findViewById = findViewById(R.id.webview_insecure);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.webkit.WebView");
        }
        WebView wv_insecureWebView = (WebView) findViewById;
        WebSettings settings = wv_insecureWebView.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings, "wv_insecureWebView.settings");
        settings.setJavaScriptEnabled(true);
        WebSettings settings2 = wv_insecureWebView.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings2, "wv_insecureWebView.settings");
        settings2.setAllowFileAccessFromFileURLs(true);
        wv_insecureWebView.setVerticalScrollBarEnabled(true);
        wv_insecureWebView.setHorizontalScrollBarEnabled(true);
        WebSettings settings3 = wv_insecureWebView.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings3, "wv_insecureWebView.settings");
        settings3.setUseWideViewPort(false);
        WebSettings settings4 = wv_insecureWebView.getSettings();
        Intrinsics.checkExpressionValueIsNotNull(settings4, "wv_insecureWebView.settings");
        settings4.setCacheMode(1);
        wv_insecureWebView.setWebChromeClient(new WebChromeClient());
        wv_insecureWebView.addJavascriptInterface(jsInterface, "Android");
        wv_insecureWebView.loadUrl("file:///android_asset/unsafe_content.html");
        Log.d("Checkpoint-jsEnabled", "true");
    }
}
