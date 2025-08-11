package com.github.kittinunf.fuel.core.requests;

import com.github.kittinunf.fuel.core.Request;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UploadTaskRequest.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u000e\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t\u001a\u0014\u0010\n\u001a\u00020\u0001*\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\u0005\u001a\f\u0010\r\u001a\u00020\u0001*\u0004\u0018\u00010\u000b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"BUFFER_SIZE", "", "CRLF", "", "guessContentType", "", "name", "retrieveBoundaryInfo", "request", "Lcom/github/kittinunf/fuel/core/Request;", "write", "Ljava/io/OutputStream;", "str", "writeln", "fuel"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class UploadTaskRequestKt {
    private static final int BUFFER_SIZE = 1024;
    private static final byte[] CRLF;

    public static final int write(@Nullable OutputStream $receiver, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "str");
        byte[] data = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(data, "(this as java.lang.String).getBytes(charset)");
        if ($receiver != null) {
            $receiver.write(data);
        }
        return data.length;
    }

    public static final int writeln(@Nullable OutputStream $receiver) {
        if ($receiver != null) {
            $receiver.write(CRLF);
        }
        return CRLF.length;
    }

    static {
        byte[] bytes = "\r\n".getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        CRLF = bytes;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String guessContentType(String name) {
        try {
            String guessContentTypeFromName = URLConnection.guessContentTypeFromName(name);
            return guessContentTypeFromName != null ? guessContentTypeFromName : "application/octet-stream";
        } catch (NoClassDefFoundError e) {
            return "application/octet-stream";
        }
    }

    @NotNull
    public static final String retrieveBoundaryInfo(@NotNull Request request) {
        List split$default;
        String str;
        Intrinsics.checkParameterIsNotNull(request, "request");
        String str2 = request.getHeaders().get("Content-Type");
        if (str2 != null && (split$default = StringsKt.split$default((CharSequence) str2, new String[]{"boundary="}, false, 2, 2, (Object) null)) != null && (str = (String) CollectionsKt.getOrNull(split$default, 1)) != null) {
            return str;
        }
        String l = Long.toString(System.currentTimeMillis(), CharsKt.checkRadix(16));
        Intrinsics.checkExpressionValueIsNotNull(l, "java.lang.Long.toString(this, checkRadix(radix))");
        return l;
    }
}
