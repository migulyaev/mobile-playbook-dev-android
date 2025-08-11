package com.github.kittinunf.fuel.toolbox;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.Client;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Method;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: HttpClient.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0018\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/github/kittinunf/fuel/toolbox/HttpClient;", "Lcom/github/kittinunf/fuel/core/Client;", "proxy", "Ljava/net/Proxy;", "(Ljava/net/Proxy;)V", "establishConnection", "Ljava/net/URLConnection;", "request", "Lcom/github/kittinunf/fuel/core/Request;", "executeRequest", "Lcom/github/kittinunf/fuel/core/Response;", "setBodyIfDoOutput", "", "connection", "Ljava/net/HttpURLConnection;", "setDoOutput", "method", "Lcom/github/kittinunf/fuel/core/Method;", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class HttpClient implements Client {
    private final Proxy proxy;

    /* JADX WARN: Multi-variable type inference failed */
    public HttpClient() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public HttpClient(@Nullable Proxy proxy) {
        this.proxy = proxy;
    }

    public /* synthetic */ HttpClient(Proxy proxy, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Proxy) null : proxy);
    }

    @Override // com.github.kittinunf.fuel.core.Client
    @NotNull
    public Response executeRequest(@NotNull Request request) {
        InputStream byteArrayInputStream;
        InputStream inputStream;
        Intrinsics.checkParameterIsNotNull(request, "request");
        URLConnection establishConnection = establishConnection(request);
        if (establishConnection == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
        }
        HttpURLConnection connection = (HttpURLConnection) establishConnection;
        try {
            connection.setConnectTimeout(Fuel.INSTANCE.getTestConfiguration$fuel().coerceTimeout(request.getTimeoutInMillisecond()));
            connection.setReadTimeout(Fuel.INSTANCE.getTestConfiguration$fuel().coerceTimeoutRead(request.getTimeoutReadInMillisecond()));
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod((Intrinsics.areEqual(request.getMethod(), Method.PATCH) ? Method.POST : request.getMethod()).getValue());
            connection.setInstanceFollowRedirects(false);
            for (Map.Entry<String, String> entry : request.getHeaders().entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                connection.setRequestProperty(key, value);
            }
            if (Intrinsics.areEqual(request.getMethod(), Method.PATCH)) {
                connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            }
            setDoOutput(connection, request.getMethod());
            setBodyIfDoOutput(connection, request);
            String contentEncoding = connection.getContentEncoding();
            if (contentEncoding == null) {
                contentEncoding = "";
            }
            URL url = request.getUrl();
            Map $receiver$iv = connection.getHeaderFields();
            Intrinsics.checkExpressionValueIsNotNull($receiver$iv, "connection.headerFields");
            LinkedHashMap result$iv = new LinkedHashMap();
            for (Map.Entry entry$iv : $receiver$iv.entrySet()) {
                String it = entry$iv.getKey();
                String it2 = it != null ? 1 : null;
                if (it2 != null) {
                    result$iv.put(entry$iv.getKey(), entry$iv.getValue());
                }
            }
            LinkedHashMap linkedHashMap = result$iv;
            long contentLength = connection.getContentLength();
            int responseCode = connection.getResponseCode();
            String responseMessage = connection.getResponseMessage();
            if (responseMessage == null) {
                responseMessage = "";
            }
            String str = responseMessage;
            try {
                InputStream stream = connection.getErrorStream();
                if (stream == null) {
                    stream = connection.getInputStream();
                }
                if (StringsKt.compareTo(contentEncoding, "gzip", true) == 0) {
                    stream = new GZIPInputStream(stream);
                }
                byteArrayInputStream = stream;
            } catch (IOException e) {
                try {
                    if (connection.getErrorStream() == null && (inputStream = connection.getInputStream()) != null) {
                        inputStream.close();
                        Unit unit = Unit.INSTANCE;
                    }
                } catch (IOException e2) {
                }
                byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
            }
            Intrinsics.checkExpressionValueIsNotNull(byteArrayInputStream, "try {\n                  …0))\n                    }");
            return new Response(url, responseCode, str, linkedHashMap, contentLength, byteArrayInputStream);
        } catch (Exception exception) {
            throw new FuelError(exception, new byte[0], new Response(request.getUrl(), 0, null, null, 0L, null, 62, null));
        }
    }

    private final URLConnection establishConnection(Request request) {
        URLConnection urlConnection = this.proxy != null ? request.getUrl().openConnection(this.proxy) : request.getUrl().openConnection();
        if (!Intrinsics.areEqual(request.getUrl().getProtocol(), "https")) {
            if (urlConnection == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.net.HttpURLConnection");
            }
            return (HttpURLConnection) urlConnection;
        }
        if (urlConnection == null) {
            throw new TypeCastException("null cannot be cast to non-null type javax.net.ssl.HttpsURLConnection");
        }
        HttpsURLConnection conn = (HttpsURLConnection) urlConnection;
        conn.setSSLSocketFactory(request.getSocketFactory());
        conn.setHostnameVerifier(request.getHostnameVerifier());
        return conn;
    }

    private final void setBodyIfDoOutput(HttpURLConnection connection, Request request) {
        Function3 bodyCallback = request.getBodyCallback();
        if (bodyCallback == null || !connection.getDoOutput()) {
            return;
        }
        long contentLength = bodyCallback.invoke(request, null, 0L).longValue();
        if (Intrinsics.areEqual(request.getType(), Request.Type.UPLOAD)) {
            connection.setFixedLengthStreamingMode((int) contentLength);
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(connection.getOutputStream());
        Throwable th = (Throwable) null;
        try {
            BufferedOutputStream it = bufferedOutputStream;
            bodyCallback.invoke(request, it, Long.valueOf(contentLength)).longValue();
        } finally {
            CloseableKt.closeFinally(bufferedOutputStream, th);
        }
    }

    private final void setDoOutput(HttpURLConnection connection, Method method) {
        switch (method) {
            case GET:
            case DELETE:
            case HEAD:
                connection.setDoOutput(false);
                return;
            case POST:
            case PUT:
            case PATCH:
                connection.setDoOutput(true);
                return;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
