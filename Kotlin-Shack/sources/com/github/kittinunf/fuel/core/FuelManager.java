package com.github.kittinunf.fuel.core;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.interceptors.RedirectionInterceptorKt;
import com.github.kittinunf.fuel.core.interceptors.ValidatorInterceptorKt;
import com.github.kittinunf.fuel.toolbox.HttpClient;
import com.github.kittinunf.fuel.util.DelegatesKt;
import com.github.kittinunf.fuel.util.SameThreadExecutorService;
import java.net.Proxy;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.ranges.IntRange;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FuelManager.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 a2\u00020\u0001:\u0001aB\u0005¢\u0006\u0002\u0010\u0002J2\u0010N\u001a\u00020O2*\u0010P\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020C0B\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020C0B0BJ>\u0010Q\u001a\u00020O26\u0010P\u001a2\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020F0E\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020F0E0BJ\b\u0010R\u001a\u00020&H\u0002J.\u0010S\u001a\u00020C2\u0006\u0010T\u001a\u00020\u00052\u001e\b\u0002\u0010U\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f\u0018\u00010\u000bJ\u0006\u0010V\u001a\u00020OJ\u0006\u0010W\u001a\u00020OJ2\u0010X\u001a\u00020O2*\u0010P\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020C0B\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020C0B0BJ>\u0010Y\u001a\u00020O26\u0010P\u001a2\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020F0E\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020F0E0BJ\u000e\u0010Z\u001a\u00020C2\u0006\u0010[\u001a\u00020\\J6\u0010Z\u001a\u00020C2\u0006\u0010]\u001a\u00020^2\u0006\u0010[\u001a\u00020_2\u001e\b\u0002\u0010U\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f\u0018\u00010\u000bJ6\u0010Z\u001a\u00020C2\u0006\u0010]\u001a\u00020^2\u0006\u0010T\u001a\u00020\u00052\u001e\b\u0002\u0010U\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f\u0018\u00010\u000bJ8\u0010`\u001a\u00020C2\u0006\u0010T\u001a\u00020\u00052\b\b\u0002\u0010]\u001a\u00020^2\u001e\b\u0002\u0010U\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f\u0018\u00010\u000bR(\u0010\u0003\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR.\u0010\n\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0016\u001a\u00020\u00178F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR+\u0010 \u001a\u00020\u001f2\u0006\u0010\u0016\u001a\u00020\u001f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b%\u0010\u001e\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R+\u0010'\u001a\u00020&2\u0006\u0010\u0016\u001a\u00020&8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b,\u0010\u001e\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R+\u0010.\u001a\u00020-2\u0006\u0010\u0016\u001a\u00020-8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b3\u0010\u001e\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001c\u00104\u001a\u0004\u0018\u000105X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R8\u0010@\u001a,\u0012(\u0012&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020C0B\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020C0B0B0AX\u0082\u0004¢\u0006\u0002\n\u0000RD\u0010D\u001a8\u00124\u00122\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020F0E\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020C\u0012\u0004\u0012\u00020F\u0012\u0004\u0012\u00020F0E0B0AX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010H\u001a\u00020G2\u0006\u0010\u0016\u001a\u00020G8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bM\u0010\u001e\u001a\u0004\bI\u0010J\"\u0004\bK\u0010L¨\u0006b"}, d2 = {"Lcom/github/kittinunf/fuel/core/FuelManager;", "", "()V", "baseHeaders", "", "", "getBaseHeaders", "()Ljava/util/Map;", "setBaseHeaders", "(Ljava/util/Map;)V", "baseParams", "", "Lkotlin/Pair;", "getBaseParams", "()Ljava/util/List;", "setBaseParams", "(Ljava/util/List;)V", "basePath", "getBasePath", "()Ljava/lang/String;", "setBasePath", "(Ljava/lang/String;)V", "<set-?>", "Ljava/util/concurrent/Executor;", "callbackExecutor", "getCallbackExecutor", "()Ljava/util/concurrent/Executor;", "setCallbackExecutor", "(Ljava/util/concurrent/Executor;)V", "callbackExecutor$delegate", "Lkotlin/properties/ReadWriteProperty;", "Lcom/github/kittinunf/fuel/core/Client;", "client", "getClient", "()Lcom/github/kittinunf/fuel/core/Client;", "setClient", "(Lcom/github/kittinunf/fuel/core/Client;)V", "client$delegate", "Ljava/util/concurrent/ExecutorService;", "executor", "getExecutor", "()Ljava/util/concurrent/ExecutorService;", "setExecutor", "(Ljava/util/concurrent/ExecutorService;)V", "executor$delegate", "Ljavax/net/ssl/HostnameVerifier;", "hostnameVerifier", "getHostnameVerifier", "()Ljavax/net/ssl/HostnameVerifier;", "setHostnameVerifier", "(Ljavax/net/ssl/HostnameVerifier;)V", "hostnameVerifier$delegate", "keystore", "Ljava/security/KeyStore;", "getKeystore", "()Ljava/security/KeyStore;", "setKeystore", "(Ljava/security/KeyStore;)V", "proxy", "Ljava/net/Proxy;", "getProxy", "()Ljava/net/Proxy;", "setProxy", "(Ljava/net/Proxy;)V", "requestInterceptors", "", "Lkotlin/Function1;", "Lcom/github/kittinunf/fuel/core/Request;", "responseInterceptors", "Lkotlin/Function2;", "Lcom/github/kittinunf/fuel/core/Response;", "Ljavax/net/ssl/SSLSocketFactory;", "socketFactory", "getSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "setSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)V", "socketFactory$delegate", "addRequestInterceptor", "", "interceptor", "addResponseInterceptor", "createExecutor", "download", "path", "param", "removeAllRequestInterceptors", "removeAllResponseInterceptors", "removeRequestInterceptor", "removeResponseInterceptor", "request", "convertible", "Lcom/github/kittinunf/fuel/Fuel$RequestConvertible;", "method", "Lcom/github/kittinunf/fuel/core/Method;", "Lcom/github/kittinunf/fuel/Fuel$PathStringConvertible;", "upload", "Companion", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class FuelManager {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(FuelManager.class), "client", "getClient()Lcom/github/kittinunf/fuel/core/Client;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(FuelManager.class), "socketFactory", "getSocketFactory()Ljavax/net/ssl/SSLSocketFactory;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(FuelManager.class), "hostnameVerifier", "getHostnameVerifier()Ljavax/net/ssl/HostnameVerifier;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(FuelManager.class), "executor", "getExecutor()Ljava/util/concurrent/ExecutorService;")), Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(FuelManager.class), "callbackExecutor", "getCallbackExecutor()Ljava/util/concurrent/Executor;"))};

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final ReadWriteProperty instance$delegate = DelegatesKt.readWriteLazy(new Function0<FuelManager>() { // from class: com.github.kittinunf.fuel.core.FuelManager$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final FuelManager invoke() {
            return new FuelManager();
        }
    });

    @Nullable
    private Map<String, String> baseHeaders;

    @Nullable
    private String basePath;

    @Nullable
    private KeyStore keystore;

    @Nullable
    private Proxy proxy;

    /* renamed from: client$delegate, reason: from kotlin metadata */
    @NotNull
    private final ReadWriteProperty client = DelegatesKt.readWriteLazy(new Function0<HttpClient>() { // from class: com.github.kittinunf.fuel.core.FuelManager$client$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final HttpClient invoke() {
            return new HttpClient(FuelManager.this.getProxy());
        }
    });

    @NotNull
    private List<? extends Pair<String, ? extends Object>> baseParams = CollectionsKt.emptyList();

    /* renamed from: socketFactory$delegate, reason: from kotlin metadata */
    @NotNull
    private final ReadWriteProperty socketFactory = DelegatesKt.readWriteLazy(new Function0<SSLSocketFactory>() { // from class: com.github.kittinunf.fuel.core.FuelManager$socketFactory$2
        {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final SSLSocketFactory invoke() {
            KeyStore it = FuelManager.this.getKeystore();
            if (it != null) {
                TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustFactory.init(it);
                SSLContext sslContext = SSLContext.getInstance("SSL");
                Intrinsics.checkExpressionValueIsNotNull(trustFactory, "trustFactory");
                sslContext.init(null, trustFactory.getTrustManagers(), null);
                Intrinsics.checkExpressionValueIsNotNull(sslContext, "sslContext");
                SSLSocketFactory socketFactory = sslContext.getSocketFactory();
                if (socketFactory != null) {
                    return socketFactory;
                }
            }
            return HttpsURLConnection.getDefaultSSLSocketFactory();
        }
    });

    /* renamed from: hostnameVerifier$delegate, reason: from kotlin metadata */
    @NotNull
    private final ReadWriteProperty hostnameVerifier = DelegatesKt.readWriteLazy(new Function0<HostnameVerifier>() { // from class: com.github.kittinunf.fuel.core.FuelManager$hostnameVerifier$2
        @Override // kotlin.jvm.functions.Function0
        public final HostnameVerifier invoke() {
            return HttpsURLConnection.getDefaultHostnameVerifier();
        }
    });

    /* renamed from: executor$delegate, reason: from kotlin metadata */
    @NotNull
    private final ReadWriteProperty executor = DelegatesKt.readWriteLazy(new Function0<ExecutorService>() { // from class: com.github.kittinunf.fuel.core.FuelManager$executor$2
        @Override // kotlin.jvm.functions.Function0
        public final ExecutorService invoke() {
            return Executors.newCachedThreadPool(new ThreadFactory() { // from class: com.github.kittinunf.fuel.core.FuelManager$executor$2.1
                @Override // java.util.concurrent.ThreadFactory
                @NotNull
                public final Thread newThread(Runnable command) {
                    Thread thread = new Thread(command);
                    thread.setPriority(5);
                    thread.setDaemon(true);
                    return thread;
                }
            });
        }
    });
    private final List<Function1<Function1<? super Request, Request>, Function1<Request, Request>>> requestInterceptors = new ArrayList();
    private final List<Function1<Function2<? super Request, ? super Response, Response>, Function2<Request, Response, Response>>> responseInterceptors = CollectionsKt.mutableListOf(RedirectionInterceptorKt.redirectResponseInterceptor(this), ValidatorInterceptorKt.validatorResponseInterceptor(new IntRange(200, 299)));

    /* renamed from: callbackExecutor$delegate, reason: from kotlin metadata */
    @NotNull
    private final ReadWriteProperty callbackExecutor = DelegatesKt.readWriteLazy(new Function0<Executor>() { // from class: com.github.kittinunf.fuel.core.FuelManager$callbackExecutor$2
        @Override // kotlin.jvm.functions.Function0
        @NotNull
        public final Executor invoke() {
            return EnvironmentKt.createEnvironment().getCallbackExecutor();
        }
    });

    @NotNull
    public final Executor getCallbackExecutor() {
        return (Executor) this.callbackExecutor.getValue(this, $$delegatedProperties[4]);
    }

    @NotNull
    public final Client getClient() {
        return (Client) this.client.getValue(this, $$delegatedProperties[0]);
    }

    @NotNull
    public final ExecutorService getExecutor() {
        return (ExecutorService) this.executor.getValue(this, $$delegatedProperties[3]);
    }

    @NotNull
    public final HostnameVerifier getHostnameVerifier() {
        return (HostnameVerifier) this.hostnameVerifier.getValue(this, $$delegatedProperties[2]);
    }

    @NotNull
    public final SSLSocketFactory getSocketFactory() {
        return (SSLSocketFactory) this.socketFactory.getValue(this, $$delegatedProperties[1]);
    }

    public final void setCallbackExecutor(@NotNull Executor executor) {
        Intrinsics.checkParameterIsNotNull(executor, "<set-?>");
        this.callbackExecutor.setValue(this, $$delegatedProperties[4], executor);
    }

    public final void setClient(@NotNull Client client) {
        Intrinsics.checkParameterIsNotNull(client, "<set-?>");
        this.client.setValue(this, $$delegatedProperties[0], client);
    }

    public final void setExecutor(@NotNull ExecutorService executorService) {
        Intrinsics.checkParameterIsNotNull(executorService, "<set-?>");
        this.executor.setValue(this, $$delegatedProperties[3], executorService);
    }

    public final void setHostnameVerifier(@NotNull HostnameVerifier hostnameVerifier) {
        Intrinsics.checkParameterIsNotNull(hostnameVerifier, "<set-?>");
        this.hostnameVerifier.setValue(this, $$delegatedProperties[2], hostnameVerifier);
    }

    public final void setSocketFactory(@NotNull SSLSocketFactory sSLSocketFactory) {
        Intrinsics.checkParameterIsNotNull(sSLSocketFactory, "<set-?>");
        this.socketFactory.setValue(this, $$delegatedProperties[1], sSLSocketFactory);
    }

    @Nullable
    public final Proxy getProxy() {
        return this.proxy;
    }

    public final void setProxy(@Nullable Proxy proxy) {
        this.proxy = proxy;
    }

    @Nullable
    public final String getBasePath() {
        return this.basePath;
    }

    public final void setBasePath(@Nullable String str) {
        this.basePath = str;
    }

    @Nullable
    public final Map<String, String> getBaseHeaders() {
        return this.baseHeaders;
    }

    public final void setBaseHeaders(@Nullable Map<String, String> map) {
        this.baseHeaders = map;
    }

    @NotNull
    public final List<Pair<String, Object>> getBaseParams() {
        return this.baseParams;
    }

    public final void setBaseParams(@NotNull List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.baseParams = list;
    }

    @Nullable
    public final KeyStore getKeystore() {
        return this.keystore;
    }

    public final void setKeystore(@Nullable KeyStore keyStore) {
        this.keystore = keyStore;
    }

    private final ExecutorService createExecutor() {
        return Fuel.INSTANCE.getTestConfiguration$fuel().getBlocking() ? new SameThreadExecutorService() : getExecutor();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* bridge */ /* synthetic */ Request request$default(FuelManager fuelManager, Method method, String str, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            list = (List) null;
        }
        return fuelManager.request(method, str, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @NotNull
    public final Request request(@NotNull Method method, @NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> param) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(path, "path");
        Request request = request(new Encoding(method, path, null, this.basePath, param == null ? this.baseParams : CollectionsKt.plus((Collection) this.baseParams, (Iterable) param), 4, null).getRequest());
        request.setClient$fuel(getClient());
        Map<String, String> headers = request.getHeaders();
        Map<String, String> map = this.baseHeaders;
        if (map == null) {
            map = MapsKt.emptyMap();
        }
        headers.putAll(map);
        request.setSocketFactory$fuel(getSocketFactory());
        request.setHostnameVerifier$fuel(getHostnameVerifier());
        request.setExecutor$fuel(createExecutor());
        request.setCallbackExecutor$fuel(getCallbackExecutor());
        List $receiver$iv = this.requestInterceptors;
        Object initial$iv = new Function1<Request, Request>() { // from class: com.github.kittinunf.fuel.core.FuelManager$request$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Request invoke(@NotNull Request r) {
                Intrinsics.checkParameterIsNotNull(r, "r");
                return r;
            }
        };
        Object accumulator$iv = initial$iv;
        if (!$receiver$iv.isEmpty()) {
            ListIterator iterator$iv = $receiver$iv.listIterator($receiver$iv.size());
            while (iterator$iv.hasPrevious()) {
                Function1 acc = (Function1) accumulator$iv;
                Function1<Function1<? super Request, Request>, Function1<Request, Request>> f = iterator$iv.previous();
                accumulator$iv = (Function1) f.invoke(acc);
            }
        }
        request.setRequestInterceptor$fuel((Function1) accumulator$iv);
        List $receiver$iv2 = this.responseInterceptors;
        Object initial$iv2 = new Function2<Request, Response, Response>() { // from class: com.github.kittinunf.fuel.core.FuelManager$request$3
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final Response invoke(@NotNull Request $noName_0, @NotNull Response res) {
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(res, "res");
                return res;
            }
        };
        Object accumulator$iv2 = initial$iv2;
        if (!$receiver$iv2.isEmpty()) {
            ListIterator iterator$iv2 = $receiver$iv2.listIterator($receiver$iv2.size());
            while (iterator$iv2.hasPrevious()) {
                Function2 acc2 = (Function2) accumulator$iv2;
                Function1<Function2<? super Request, ? super Response, Response>, Function2<Request, Response, Response>> f2 = iterator$iv2.previous();
                accumulator$iv2 = (Function2) f2.invoke(acc2);
            }
        }
        request.setResponseInterceptor$fuel((Function2) accumulator$iv2);
        return request;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* bridge */ /* synthetic */ Request request$default(FuelManager fuelManager, Method method, Fuel.PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
        if ((i & 4) != 0) {
            list = (List) null;
        }
        return fuelManager.request(method, pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @NotNull
    public final Request request(@NotNull Method method, @NotNull Fuel.PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> param) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return request(method, convertible.getPath(), param);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* bridge */ /* synthetic */ Request download$default(FuelManager fuelManager, String str, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = (List) null;
        }
        return fuelManager.download(str, list);
    }

    @NotNull
    public final Request download(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> param) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Request request = new Encoding(Method.GET, path, Request.Type.DOWNLOAD, this.basePath, param == null ? this.baseParams : CollectionsKt.plus((Collection) this.baseParams, (Iterable) param)).getRequest();
        request.setClient$fuel(getClient());
        Map<String, String> headers = request.getHeaders();
        Map<String, String> map = this.baseHeaders;
        if (map == null) {
            map = MapsKt.emptyMap();
        }
        headers.putAll(map);
        request.setSocketFactory$fuel(getSocketFactory());
        request.setHostnameVerifier$fuel(getHostnameVerifier());
        request.setExecutor$fuel(createExecutor());
        request.setCallbackExecutor$fuel(getCallbackExecutor());
        List $receiver$iv = this.requestInterceptors;
        Object initial$iv = new Function1<Request, Request>() { // from class: com.github.kittinunf.fuel.core.FuelManager$download$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Request invoke(@NotNull Request r) {
                Intrinsics.checkParameterIsNotNull(r, "r");
                return r;
            }
        };
        Object accumulator$iv = initial$iv;
        if (!$receiver$iv.isEmpty()) {
            ListIterator iterator$iv = $receiver$iv.listIterator($receiver$iv.size());
            while (iterator$iv.hasPrevious()) {
                Function1 acc = (Function1) accumulator$iv;
                Function1<Function1<? super Request, Request>, Function1<Request, Request>> f = iterator$iv.previous();
                accumulator$iv = (Function1) f.invoke(acc);
            }
        }
        request.setRequestInterceptor$fuel((Function1) accumulator$iv);
        List $receiver$iv2 = this.responseInterceptors;
        Object initial$iv2 = new Function2<Request, Response, Response>() { // from class: com.github.kittinunf.fuel.core.FuelManager$download$3
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final Response invoke(@NotNull Request $noName_0, @NotNull Response res) {
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(res, "res");
                return res;
            }
        };
        Object accumulator$iv2 = initial$iv2;
        if (!$receiver$iv2.isEmpty()) {
            ListIterator iterator$iv2 = $receiver$iv2.listIterator($receiver$iv2.size());
            while (iterator$iv2.hasPrevious()) {
                Function2 acc2 = (Function2) accumulator$iv2;
                Function1<Function2<? super Request, ? super Response, Response>, Function2<Request, Response, Response>> f2 = iterator$iv2.previous();
                accumulator$iv2 = (Function2) f2.invoke(acc2);
            }
        }
        request.setResponseInterceptor$fuel((Function2) accumulator$iv2);
        return request;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* bridge */ /* synthetic */ Request upload$default(FuelManager fuelManager, String str, Method method, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            method = Method.POST;
        }
        if ((i & 4) != 0) {
            list = (List) null;
        }
        return fuelManager.upload(str, method, list);
    }

    @NotNull
    public final Request upload(@NotNull String path, @NotNull Method method, @Nullable List<? extends Pair<String, ? extends Object>> param) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(method, "method");
        Request request = new Encoding(method, path, Request.Type.UPLOAD, this.basePath, param == null ? this.baseParams : CollectionsKt.plus((Collection) this.baseParams, (Iterable) param)).getRequest();
        request.setClient$fuel(getClient());
        Map<String, String> headers = request.getHeaders();
        Map<String, String> map = this.baseHeaders;
        if (map == null) {
            map = MapsKt.emptyMap();
        }
        headers.putAll(map);
        request.setSocketFactory$fuel(getSocketFactory());
        request.setHostnameVerifier$fuel(getHostnameVerifier());
        request.setExecutor$fuel(createExecutor());
        request.setCallbackExecutor$fuel(getCallbackExecutor());
        List $receiver$iv = this.requestInterceptors;
        Object initial$iv = new Function1<Request, Request>() { // from class: com.github.kittinunf.fuel.core.FuelManager$upload$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Request invoke(@NotNull Request r) {
                Intrinsics.checkParameterIsNotNull(r, "r");
                return r;
            }
        };
        Object accumulator$iv = initial$iv;
        if (!$receiver$iv.isEmpty()) {
            ListIterator iterator$iv = $receiver$iv.listIterator($receiver$iv.size());
            while (iterator$iv.hasPrevious()) {
                Function1 acc = (Function1) accumulator$iv;
                Function1<Function1<? super Request, Request>, Function1<Request, Request>> f = iterator$iv.previous();
                accumulator$iv = (Function1) f.invoke(acc);
            }
        }
        request.setRequestInterceptor$fuel((Function1) accumulator$iv);
        List $receiver$iv2 = this.responseInterceptors;
        Object initial$iv2 = new Function2<Request, Response, Response>() { // from class: com.github.kittinunf.fuel.core.FuelManager$upload$3
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final Response invoke(@NotNull Request $noName_0, @NotNull Response res) {
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(res, "res");
                return res;
            }
        };
        Object accumulator$iv2 = initial$iv2;
        if (!$receiver$iv2.isEmpty()) {
            ListIterator iterator$iv2 = $receiver$iv2.listIterator($receiver$iv2.size());
            while (iterator$iv2.hasPrevious()) {
                Function2 acc2 = (Function2) accumulator$iv2;
                Function1<Function2<? super Request, ? super Response, Response>, Function2<Request, Response, Response>> f2 = iterator$iv2.previous();
                accumulator$iv2 = (Function2) f2.invoke(acc2);
            }
        }
        request.setResponseInterceptor$fuel((Function2) accumulator$iv2);
        return request;
    }

    @NotNull
    public final Request request(@NotNull Fuel.RequestConvertible convertible) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        Request request = convertible.getRequest();
        request.setClient$fuel(getClient());
        Map<String, String> headers = request.getHeaders();
        Map<String, String> map = this.baseHeaders;
        if (map == null) {
            map = MapsKt.emptyMap();
        }
        headers.putAll(map);
        request.setSocketFactory$fuel(getSocketFactory());
        request.setHostnameVerifier$fuel(getHostnameVerifier());
        request.setExecutor$fuel(createExecutor());
        request.setCallbackExecutor$fuel(getCallbackExecutor());
        List $receiver$iv = this.requestInterceptors;
        Object initial$iv = new Function1<Request, Request>() { // from class: com.github.kittinunf.fuel.core.FuelManager$request$5
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Request invoke(@NotNull Request r) {
                Intrinsics.checkParameterIsNotNull(r, "r");
                return r;
            }
        };
        Object accumulator$iv = initial$iv;
        if (!$receiver$iv.isEmpty()) {
            ListIterator iterator$iv = $receiver$iv.listIterator($receiver$iv.size());
            while (iterator$iv.hasPrevious()) {
                Function1 acc = (Function1) accumulator$iv;
                Function1<Function1<? super Request, Request>, Function1<Request, Request>> f = iterator$iv.previous();
                accumulator$iv = (Function1) f.invoke(acc);
            }
        }
        request.setRequestInterceptor$fuel((Function1) accumulator$iv);
        List $receiver$iv2 = this.responseInterceptors;
        Object initial$iv2 = new Function2<Request, Response, Response>() { // from class: com.github.kittinunf.fuel.core.FuelManager$request$7
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final Response invoke(@NotNull Request $noName_0, @NotNull Response res) {
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(res, "res");
                return res;
            }
        };
        Object accumulator$iv2 = initial$iv2;
        if (!$receiver$iv2.isEmpty()) {
            ListIterator iterator$iv2 = $receiver$iv2.listIterator($receiver$iv2.size());
            while (iterator$iv2.hasPrevious()) {
                Function2 acc2 = (Function2) accumulator$iv2;
                Function1<Function2<? super Request, ? super Response, Response>, Function2<Request, Response, Response>> f2 = iterator$iv2.previous();
                accumulator$iv2 = (Function2) f2.invoke(acc2);
            }
        }
        request.setResponseInterceptor$fuel((Function2) accumulator$iv2);
        return request;
    }

    public final void addRequestInterceptor(@NotNull Function1<? super Function1<? super Request, Request>, ? extends Function1<? super Request, Request>> interceptor) {
        Intrinsics.checkParameterIsNotNull(interceptor, "interceptor");
        this.requestInterceptors.add(interceptor);
    }

    public final void addResponseInterceptor(@NotNull Function1<? super Function2<? super Request, ? super Response, Response>, ? extends Function2<? super Request, ? super Response, Response>> interceptor) {
        Intrinsics.checkParameterIsNotNull(interceptor, "interceptor");
        this.responseInterceptors.add(interceptor);
    }

    public final void removeRequestInterceptor(@NotNull Function1<? super Function1<? super Request, Request>, ? extends Function1<? super Request, Request>> interceptor) {
        Intrinsics.checkParameterIsNotNull(interceptor, "interceptor");
        this.requestInterceptors.remove(interceptor);
    }

    public final void removeResponseInterceptor(@NotNull Function1<? super Function2<? super Request, ? super Response, Response>, ? extends Function2<? super Request, ? super Response, Response>> interceptor) {
        Intrinsics.checkParameterIsNotNull(interceptor, "interceptor");
        this.responseInterceptors.remove(interceptor);
    }

    public final void removeAllRequestInterceptors() {
        this.requestInterceptors.clear();
    }

    public final void removeAllResponseInterceptors() {
        this.responseInterceptors.clear();
    }

    /* compiled from: FuelManager.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R+\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/github/kittinunf/fuel/core/FuelManager$Companion;", "", "()V", "<set-?>", "Lcom/github/kittinunf/fuel/core/FuelManager;", "instance", "getInstance", "()Lcom/github/kittinunf/fuel/core/FuelManager;", "setInstance", "(Lcom/github/kittinunf/fuel/core/FuelManager;)V", "instance$delegate", "Lkotlin/properties/ReadWriteProperty;", "fuel"}, k = 1, mv = {1, 1, 9})
    public static final class Companion {
        static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(Reflection.getOrCreateKotlinClass(Companion.class), "instance", "getInstance()Lcom/github/kittinunf/fuel/core/FuelManager;"))};

        @NotNull
        public final FuelManager getInstance() {
            return (FuelManager) FuelManager.instance$delegate.getValue(this, $$delegatedProperties[0]);
        }

        public final void setInstance(@NotNull FuelManager fuelManager) {
            Intrinsics.checkParameterIsNotNull(fuelManager, "<set-?>");
            FuelManager.instance$delegate.setValue(this, $$delegatedProperties[0], fuelManager);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }
    }
}
