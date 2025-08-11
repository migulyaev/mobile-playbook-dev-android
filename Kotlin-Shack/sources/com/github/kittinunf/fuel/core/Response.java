package com.github.kittinunf.fuel.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* compiled from: Response.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\u0010 \n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b$\u0018\u0000 62\u00020\u0001:\u00016BQ\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u001a\b\u0002\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0002\u0010\u000fJ'\u0010/\u001a\u00020\u00072\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\tH\u0000¢\u0006\u0002\b0J\u001d\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020\u00072\u0006\u00103\u001a\u00020\u0013H\u0000¢\u0006\u0002\b4J\b\u00105\u001a\u00020\u0007H\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\u00138FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R#\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010\u0011R,\u0010 \u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\n0\t8FX\u0087\u0004¢\u0006\f\u0012\u0004\b!\u0010\u001e\u001a\u0004\b\"\u0010\u001bR\u001a\u0010#\u001a\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b$\u0010\u001e\u001a\u0004\b%\u0010&R\u001a\u0010'\u001a\u00020\u00058FX\u0087\u0004¢\u0006\f\u0012\u0004\b(\u0010\u001e\u001a\u0004\b)\u0010*R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b+\u0010&R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b,\u0010*R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u00067"}, d2 = {"Lcom/github/kittinunf/fuel/core/Response;", "", "url", "Ljava/net/URL;", "statusCode", "", "responseMessage", "", "headers", "", "", "contentLength", "", "dataStream", "Ljava/io/InputStream;", "(Ljava/net/URL;ILjava/lang/String;Ljava/util/Map;JLjava/io/InputStream;)V", "getContentLength", "()J", "data", "", "getData", "()[B", "data$delegate", "Lkotlin/Lazy;", "getDataStream", "()Ljava/io/InputStream;", "getHeaders", "()Ljava/util/Map;", "httpContentLength", "httpContentLength$annotations", "()V", "getHttpContentLength", "httpResponseHeaders", "httpResponseHeaders$annotations", "getHttpResponseHeaders", "httpResponseMessage", "httpResponseMessage$annotations", "getHttpResponseMessage", "()Ljava/lang/String;", "httpStatusCode", "httpStatusCode$annotations", "getHttpStatusCode", "()I", "getResponseMessage", "getStatusCode", "getUrl", "()Ljava/net/URL;", "guessContentType", "guessContentType$fuel", "processBody", "contentType", "bodyData", "processBody$fuel", "toString", "Companion", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class Response {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Response.class), "data", "getData()[B"))};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long contentLength;

    /* renamed from: data$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy data;

    @NotNull
    private final InputStream dataStream;

    @NotNull
    private final Map<String, List<String>> headers;

    @NotNull
    private final String responseMessage;
    private final int statusCode;

    @NotNull
    private final URL url;

    @Deprecated(message = "http naming is deprecated, use 'contentLength' instead", replaceWith = @ReplaceWith(expression = "contentLength", imports = {}))
    public static /* synthetic */ void httpContentLength$annotations() {
    }

    @Deprecated(message = "http naming is deprecated, use 'headers' instead", replaceWith = @ReplaceWith(expression = "headers", imports = {}))
    public static /* synthetic */ void httpResponseHeaders$annotations() {
    }

    @Deprecated(message = "http naming is deprecated, use 'responseMessage' instead", replaceWith = @ReplaceWith(expression = "responseMessage", imports = {}))
    public static /* synthetic */ void httpResponseMessage$annotations() {
    }

    @Deprecated(message = "http naming is deprecated, use 'statusCode' instead", replaceWith = @ReplaceWith(expression = "statusCode", imports = {}))
    public static /* synthetic */ void httpStatusCode$annotations() {
    }

    @NotNull
    public final byte[] getData() {
        Lazy lazy = this.data;
        KProperty kProperty = $$delegatedProperties[0];
        return (byte[]) lazy.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Response(@NotNull URL url, int statusCode, @NotNull String responseMessage, @NotNull Map<String, ? extends List<String>> headers, long contentLength, @NotNull InputStream dataStream) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(responseMessage, "responseMessage");
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        Intrinsics.checkParameterIsNotNull(dataStream, "dataStream");
        this.url = url;
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.headers = headers;
        this.contentLength = contentLength;
        this.dataStream = dataStream;
        this.data = LazyKt.lazy(new Function0<byte[]>() { // from class: com.github.kittinunf.fuel.core.Response$data$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final byte[] invoke() {
                try {
                    return ByteStreamsKt.readBytes$default(Response.this.getDataStream(), 0, 1, null);
                } catch (IOException e) {
                    return new byte[0];
                }
            }
        });
    }

    @NotNull
    public final URL getUrl() {
        return this.url;
    }

    public /* synthetic */ Response(URL url, int i, String str, Map map, long j, InputStream inputStream, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(url, (i2 & 2) != 0 ? -1 : i, (i2 & 4) != 0 ? "" : str, (i2 & 8) != 0 ? MapsKt.emptyMap() : map, (i2 & 16) != 0 ? 0L : j, (i2 & 32) != 0 ? new ByteArrayInputStream(new byte[0]) : inputStream);
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final String getResponseMessage() {
        return this.responseMessage;
    }

    @NotNull
    public final Map<String, List<String>> getHeaders() {
        return this.headers;
    }

    public final long getContentLength() {
        return this.contentLength;
    }

    @NotNull
    public final InputStream getDataStream() {
        return this.dataStream;
    }

    public final long getHttpContentLength() {
        return this.contentLength;
    }

    @NotNull
    /* renamed from: getHttpResponseMessage, reason: from getter */
    public final String getResponseMessage() {
        return this.responseMessage;
    }

    /* renamed from: getHttpStatusCode, reason: from getter */
    public final int getStatusCode() {
        return this.statusCode;
    }

    @NotNull
    public final Map<String, List<String>> getHttpResponseHeaders() {
        return this.headers;
    }

    @NotNull
    public String toString() {
        String contentType = guessContentType$fuel(this.headers);
        String dataString = processBody$fuel(contentType, getData());
        StringBuilder $receiver = new StringBuilder();
        $receiver.append("<-- " + this.statusCode + " (" + this.url + ')');
        Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
        StringsKt.appendln($receiver);
        StringBuilder sb = new StringBuilder();
        sb.append("Response : ");
        sb.append(this.responseMessage);
        $receiver.append(sb.toString());
        Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
        StringsKt.appendln($receiver);
        $receiver.append("Length : " + this.contentLength);
        Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
        StringsKt.appendln($receiver);
        $receiver.append("Body : (" + dataString + ')');
        Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
        StringsKt.appendln($receiver);
        $receiver.append("Headers : (" + this.headers.size() + ')');
        Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
        StringsKt.appendln($receiver);
        for (Map.Entry<String, List<String>> entry : this.headers.entrySet()) {
            String key = entry.getKey();
            List value = entry.getValue();
            $receiver.append("" + key + " : " + value);
            Intrinsics.checkExpressionValueIsNotNull($receiver, "append(value)");
            StringsKt.appendln($receiver);
        }
        String sb2 = $receiver.toString();
        Intrinsics.checkExpressionValueIsNotNull(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    @NotNull
    public final String guessContentType$fuel(@NotNull Map<String, ? extends List<String>> headers) {
        Intrinsics.checkParameterIsNotNull(headers, "headers");
        List<String> list = headers.get("Content-Type");
        String contentTypeFromHeaders = list != null ? (String) CollectionsKt.first((List) list) : null;
        if (contentTypeFromHeaders instanceof String) {
            if (!(contentTypeFromHeaders.length() == 0)) {
                return contentTypeFromHeaders;
            }
        }
        String contentTypeFromStream = URLConnection.guessContentTypeFromStream(new ByteArrayInputStream(getData()));
        String str = contentTypeFromStream;
        if (str == null || str.length() == 0) {
            return "(unknown)";
        }
        Intrinsics.checkExpressionValueIsNotNull(contentTypeFromStream, "contentTypeFromStream");
        return contentTypeFromStream;
    }

    @NotNull
    public final String processBody$fuel(@NotNull String contentType, @NotNull byte[] bodyData) {
        Intrinsics.checkParameterIsNotNull(contentType, "contentType");
        Intrinsics.checkParameterIsNotNull(bodyData, "bodyData");
        if (!(contentType.length() > 0) || (!StringsKt.contains$default((CharSequence) contentType, (CharSequence) "image/", false, 2, (Object) null) && !StringsKt.contains$default((CharSequence) contentType, (CharSequence) "application/octet-stream", false, 2, (Object) null))) {
            if (!(bodyData.length == 0)) {
                return new String(bodyData, Charsets.UTF_8);
            }
            return "(empty)";
        }
        return "" + this.contentLength + " bytes of " + guessContentType$fuel(this.headers);
    }

    /* compiled from: Response.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/github/kittinunf/fuel/core/Response$Companion;", "", "()V", "error", "Lcom/github/kittinunf/fuel/core/Response;", "fuel"}, k = 1, mv = {1, 1, 9})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final Response error() {
            return new Response(new URL("http://."), 0, null, null, 0L, null, 62, null);
        }
    }
}
