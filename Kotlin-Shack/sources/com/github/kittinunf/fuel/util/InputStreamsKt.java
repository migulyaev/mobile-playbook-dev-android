package com.github.kittinunf.fuel.util;

import android.support.v4.app.NotificationCompat;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: InputStreams.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u001a4\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0000¨\u0006\n"}, d2 = {"copyTo", "", "Ljava/io/InputStream;", "out", "Ljava/io/OutputStream;", "bufferSize", "", NotificationCompat.CATEGORY_PROGRESS, "Lkotlin/Function1;", "", "fuel"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class InputStreamsKt {
    public static /* bridge */ /* synthetic */ long copyTo$default(InputStream inputStream, OutputStream outputStream, int i, Function1 function1, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(inputStream, outputStream, i, function1);
    }

    public static final long copyTo(@NotNull InputStream receiver, @NotNull OutputStream out, int bufferSize, @Nullable Function1<? super Long, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(out, "out");
        long bytesCopied = 0;
        byte[] buffer = new byte[bufferSize];
        int bytes = receiver.read(buffer);
        while (bytes >= 0) {
            out.write(buffer, 0, bytes);
            long bytesCopied2 = bytesCopied + bytes;
            if (function1 != null) {
                function1.invoke(Long.valueOf(bytesCopied2));
            }
            bytes = receiver.read(buffer);
            bytesCopied = bytesCopied2;
        }
        return bytesCopied;
    }
}
