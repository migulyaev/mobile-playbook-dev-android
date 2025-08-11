package com.github.kittinunf.fuel.core;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.Request;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jasypt.digest.StandardStringDigester;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Encoding.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\u001e\b\u0002\u0010\t\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0018\u00010\n¢\u0006\u0002\u0010\rJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0005H\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0003H\u0002J&\u0010'\u001a\u00020\u00052\u001c\u0010(\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0018\u00010\nH\u0002R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R<\u0010\u0012\u001a0\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0005\u0012\u001a\u0012\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0018\u00010\n\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R'\u0010\t\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000b\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001b\u0010\u0019\u001a\u00020\u00148VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u000f¨\u0006)"}, d2 = {"Lcom/github/kittinunf/fuel/core/Encoding;", "Lcom/github/kittinunf/fuel/Fuel$RequestConvertible;", "httpMethod", "Lcom/github/kittinunf/fuel/core/Method;", "urlString", "", "requestType", "Lcom/github/kittinunf/fuel/core/Request$Type;", "baseUrlString", "parameters", "", "Lkotlin/Pair;", "", "(Lcom/github/kittinunf/fuel/core/Method;Ljava/lang/String;Lcom/github/kittinunf/fuel/core/Request$Type;Ljava/lang/String;Ljava/util/List;)V", "getBaseUrlString", "()Ljava/lang/String;", "defaultHeaders", "", "encoder", "Lkotlin/Function3;", "Lcom/github/kittinunf/fuel/core/Request;", "getHttpMethod", "()Lcom/github/kittinunf/fuel/core/Method;", "getParameters", "()Ljava/util/List;", "request", "getRequest", "()Lcom/github/kittinunf/fuel/core/Request;", "request$delegate", "Lkotlin/Lazy;", "getRequestType", "()Lcom/github/kittinunf/fuel/core/Request$Type;", "getUrlString", "createUrl", "Ljava/net/URL;", "path", "encodeParameterInUrl", "", "method", "queryFromParameters", "params", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class Encoding implements Fuel.RequestConvertible {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(Encoding.class), "request", "getRequest()Lcom/github/kittinunf/fuel/core/Request;"))};

    @Nullable
    private final String baseUrlString;
    private final Map<String, String> defaultHeaders;
    private final Function3<Method, String, List<? extends Pair<String, ? extends Object>>, Request> encoder;

    @NotNull
    private final Method httpMethod;

    @Nullable
    private final List<Pair<String, Object>> parameters;

    /* renamed from: request$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy request;

    @NotNull
    private final Request.Type requestType;

    @NotNull
    private final String urlString;

    @Override // com.github.kittinunf.fuel.Fuel.RequestConvertible
    @NotNull
    public Request getRequest() {
        Lazy lazy = this.request;
        KProperty kProperty = $$delegatedProperties[0];
        return (Request) lazy.getValue();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Encoding(@NotNull Method httpMethod, @NotNull String urlString, @NotNull Request.Type requestType, @Nullable String baseUrlString, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(httpMethod, "httpMethod");
        Intrinsics.checkParameterIsNotNull(urlString, "urlString");
        Intrinsics.checkParameterIsNotNull(requestType, "requestType");
        this.httpMethod = httpMethod;
        this.urlString = urlString;
        this.requestType = requestType;
        this.baseUrlString = baseUrlString;
        this.parameters = list;
        this.encoder = (Function3) new Function3<Method, String, List<? extends Pair<? extends String, ? extends Object>>, Request>() { // from class: com.github.kittinunf.fuel.core.Encoding$encoder$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Request invoke(Method method, String str, List<? extends Pair<? extends String, ? extends Object>> list2) {
                return invoke2(method, str, (List<? extends Pair<String, ? extends Object>>) list2);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @NotNull
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Request invoke2(@NotNull Method method, @NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> list2) {
                Map map;
                boolean encodeParameterInUrl;
                URL createUrl;
                String queryParamString;
                Intrinsics.checkParameterIsNotNull(method, "method");
                Intrinsics.checkParameterIsNotNull(path, "path");
                String modifiedPath = path;
                String str = (String) null;
                map = Encoding.this.defaultHeaders;
                Map headerPairs = MapsKt.toMutableMap(map);
                encodeParameterInUrl = Encoding.this.encodeParameterInUrl(method);
                if (encodeParameterInUrl) {
                    String querySign = "";
                    queryParamString = Encoding.this.queryFromParameters(list2);
                    if ((queryParamString.length() > 0) && path.length() > 0) {
                        querySign = StringsKt.last(path) == '?' ? "" : "?";
                    }
                    modifiedPath = modifiedPath + querySign + queryParamString;
                } else if (Intrinsics.areEqual(Encoding.this.getRequestType(), Request.Type.UPLOAD)) {
                    String boundary = Long.toString(System.currentTimeMillis(), CharsKt.checkRadix(16));
                    Intrinsics.checkExpressionValueIsNotNull(boundary, "java.lang.Long.toString(this, checkRadix(radix))");
                    Pair pair = TuplesKt.to("Content-Type", "multipart/form-data; boundary=" + boundary);
                    headerPairs.put(pair.getFirst(), pair.getSecond());
                } else {
                    Pair pair2 = TuplesKt.to("Content-Type", "application/x-www-form-urlencoded");
                    headerPairs.put(pair2.getFirst(), pair2.getSecond());
                    str = Encoding.this.queryFromParameters(list2);
                }
                String modifiedPath2 = modifiedPath;
                String str2 = str;
                createUrl = Encoding.this.createUrl(modifiedPath2);
                Request request = new Request(method, modifiedPath2, createUrl, Encoding.this.getRequestType(), null, list2 != null ? list2 : CollectionsKt.emptyList(), null, null, null, 0, 0, 2000, null);
                request.header$fuel(headerPairs, false);
                if (str2 != null) {
                    if (str2 == null) {
                        Intrinsics.throwNpe();
                    }
                    Request.body$default(request, str2, null, 2, null);
                }
                return request;
            }
        };
        this.request = LazyKt.lazy(new Function0<Request>() { // from class: com.github.kittinunf.fuel.core.Encoding$request$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final Request invoke() {
                Function3 function3;
                function3 = Encoding.this.encoder;
                return (Request) function3.invoke(Encoding.this.getHttpMethod(), Encoding.this.getUrlString(), Encoding.this.getParameters());
            }
        });
        this.defaultHeaders = MapsKt.mapOf(TuplesKt.to("Accept-Encoding", "compress;q=0.5, gzip;q=1.0"));
    }

    @NotNull
    public final Method getHttpMethod() {
        return this.httpMethod;
    }

    @NotNull
    public final String getUrlString() {
        return this.urlString;
    }

    public /* synthetic */ Encoding(Method method, String str, Request.Type type, String str2, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(method, str, (i & 4) != 0 ? Request.Type.REQUEST : type, (i & 8) != 0 ? (String) null : str2, (i & 16) != 0 ? (List) null : list);
    }

    @NotNull
    public final Request.Type getRequestType() {
        return this.requestType;
    }

    @Nullable
    public final String getBaseUrlString() {
        return this.baseUrlString;
    }

    @Nullable
    public final List<Pair<String, Object>> getParameters() {
        return this.parameters;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final URL createUrl(String path) {
        String str;
        URL url;
        URI uri;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            String str2 = this.baseUrlString;
            if (StringsKt.startsWith$default((CharSequence) path, '/', false, 2, (Object) null) || (path.length() == 0)) {
                str = path;
            } else {
                str = String.valueOf('/') + path;
            }
            url = new URL(Intrinsics.stringPlus(str2, str));
        }
        try {
            uri = url.toURI();
        } catch (URISyntaxException e2) {
            uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
        }
        return new URL(uri.toASCIIString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean encodeParameterInUrl(Method method) {
        switch (method) {
            case GET:
            case DELETE:
            case HEAD:
                return true;
            default:
                return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String queryFromParameters(List<? extends Pair<String, ? extends Object>> params) {
        Iterable $receiver$iv = params != null ? params : CollectionsKt.emptyList();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $receiver$iv) {
            Pair it = (Pair) element$iv$iv;
            if (!(it.getSecond() == null)) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        Iterable $receiver$iv2 = (List) destination$iv$iv;
        Iterable $receiver$iv3 = $receiver$iv2;
        Collection destination$iv$iv2 = new ArrayList(CollectionsKt.collectionSizeOrDefault($receiver$iv3, 10));
        for (Object item$iv$iv : $receiver$iv3) {
            Pair $key_value = (Pair) item$iv$iv;
            String key = (String) $key_value.component1();
            Object value = $key_value.component2();
            destination$iv$iv2.add(TuplesKt.to(URLEncoder.encode(key, StandardStringDigester.MESSAGE_CHARSET), URLEncoder.encode("" + value, StandardStringDigester.MESSAGE_CHARSET)));
        }
        Iterable $receiver$iv4 = (List) destination$iv$iv2;
        return CollectionsKt.joinToString$default($receiver$iv4, "&", null, null, 0, null, new Function1<Pair<? extends String, ? extends String>, String>() { // from class: com.github.kittinunf.fuel.core.Encoding$queryFromParameters$3
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ String invoke(Pair<? extends String, ? extends String> pair) {
                return invoke2((Pair<String, String>) pair);
            }

            @NotNull
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final String invoke2(@NotNull Pair<String, String> pair) {
                Intrinsics.checkParameterIsNotNull(pair, "<name for destructuring parameter 0>");
                String key2 = pair.component1();
                String key3 = key2;
                String value2 = pair.component2();
                return "" + key3 + '=' + value2;
            }
        }, 30, null);
    }
}
