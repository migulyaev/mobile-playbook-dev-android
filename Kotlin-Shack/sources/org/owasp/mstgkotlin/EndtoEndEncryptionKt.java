package org.owasp.mstgkotlin;

import com.scottyab.aescrypt.AESCrypt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: EndtoEndEncryption.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001¨\u0006\u0003"}, d2 = {"encrypt", "", "text", "app_debug"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class EndtoEndEncryptionKt {
    @NotNull
    public static final String encrypt(@NotNull String text) {
        Intrinsics.checkParameterIsNotNull(text, "text");
        String encryptedValue = AESCrypt.encrypt("ASDFGHJKLASDFGHJ", text);
        Intrinsics.checkExpressionValueIsNotNull(encryptedValue, "encryptedValue");
        return encryptedValue;
    }
}
