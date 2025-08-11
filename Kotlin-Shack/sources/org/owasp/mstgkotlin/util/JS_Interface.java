package org.owasp.mstgkotlin.util;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: JS_Interface.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/owasp/mstgkotlin/util/JS_Interface;", "", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "returnString", "", "showToast", "", "toast", "app_debug"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class JS_Interface {
    private Context mContext;

    public JS_Interface(@NotNull Context mContext) {
        Intrinsics.checkParameterIsNotNull(mContext, "mContext");
        this.mContext = mContext;
    }

    @JavascriptInterface
    @NotNull
    public final String returnString() {
        return "<strong>This is a Secret String only obtainable via JavaScript</strong>";
    }

    @JavascriptInterface
    public final void showToast(@NotNull String toast) {
        Intrinsics.checkParameterIsNotNull(toast, "toast");
        Toast.makeText(this.mContext, toast, 1).show();
    }
}
