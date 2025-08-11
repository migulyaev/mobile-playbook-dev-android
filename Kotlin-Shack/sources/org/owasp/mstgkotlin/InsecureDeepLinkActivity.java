package org.owasp.mstgkotlin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

/* compiled from: InsecureDeepLinkActivity.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0014¨\u0006\u0007"}, d2 = {"Lorg/owasp/mstgkotlin/InsecureDeepLinkActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class InsecureDeepLinkActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_insecure_deep_link);
        String url = "http://github.com/OWASP/owasp-mstg/";
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, "intent");
        Uri data = intent.getData();
        if (data != null) {
            String queryParameter = data.getQueryParameter("data");
            Intrinsics.checkExpressionValueIsNotNull(queryParameter, "data.getQueryParameter(\"data\")");
            url = queryParameter;
        }
        SharedPreferences prefs = getSharedPreferences("InsecurePreferences", 0);
        String token = prefs.getString("auth_token", "");
        Intrinsics.checkExpressionValueIsNotNull(token, "prefs.getString(AuthToken, token)");
        WebView wv_secureWebView = (WebView) findViewById(R.id.deeplink_webview);
        Intrinsics.checkExpressionValueIsNotNull(wv_secureWebView, "wv_secureWebView");
        wv_secureWebView.setVerticalScrollBarEnabled(true);
        wv_secureWebView.setHorizontalScrollBarEnabled(true);
        HashMap extraHeaders = new HashMap();
        extraHeaders.put("Authorization", token);
        wv_secureWebView.loadUrl(url, extraHeaders);
    }
}
