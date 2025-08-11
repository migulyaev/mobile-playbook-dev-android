package com.github.kittinunf.fuel.core.interceptors;

import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: LoggingInterceptors.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00010\u0001\u001a0\u0010\u0003\u001a&\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00040\u0001\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00040\u00010\u0001\"\u0004\b\u0000\u0010\u0004\u001a\u0018\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006¨\u0006\b"}, d2 = {"cUrlLoggingRequestInterceptor", "Lkotlin/Function1;", "Lcom/github/kittinunf/fuel/core/Request;", "loggingRequestInterceptor", "T", "loggingResponseInterceptor", "Lkotlin/Function2;", "Lcom/github/kittinunf/fuel/core/Response;", "fuel"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class LoggingInterceptorsKt {
    @NotNull
    public static final <T> Function1<Function1<? super T, ? extends T>, Function1<T, T>> loggingRequestInterceptor() {
        return new Function1<Function1<? super T, ? extends T>, Function1<? super T, ? extends T>>() { // from class: com.github.kittinunf.fuel.core.interceptors.LoggingInterceptorsKt$loggingRequestInterceptor$1
            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Function1<T, T> invoke(@NotNull final Function1<? super T, ? extends T> next) {
                Intrinsics.checkParameterIsNotNull(next, "next");
                return new Function1<T, T>() { // from class: com.github.kittinunf.fuel.core.interceptors.LoggingInterceptorsKt$loggingRequestInterceptor$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public final T invoke(T t) {
                        System.out.println((Object) String.valueOf(t));
                        return (T) Function1.this.invoke(t);
                    }
                };
            }
        };
    }

    @NotNull
    public static final Function1<Function1<? super Request, Request>, Function1<Request, Request>> cUrlLoggingRequestInterceptor() {
        return new Function1<Function1<? super Request, ? extends Request>, Function1<? super Request, ? extends Request>>() { // from class: com.github.kittinunf.fuel.core.interceptors.LoggingInterceptorsKt$cUrlLoggingRequestInterceptor$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Function1<? super Request, ? extends Request> invoke(Function1<? super Request, ? extends Request> function1) {
                return invoke2((Function1<? super Request, Request>) function1);
            }

            @NotNull
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Function1<Request, Request> invoke2(@NotNull final Function1<? super Request, Request> next) {
                Intrinsics.checkParameterIsNotNull(next, "next");
                return new Function1<Request, Request>() { // from class: com.github.kittinunf.fuel.core.interceptors.LoggingInterceptorsKt$cUrlLoggingRequestInterceptor$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @NotNull
                    public final Request invoke(@NotNull Request r) {
                        Intrinsics.checkParameterIsNotNull(r, "r");
                        System.out.println((Object) r.cUrlString());
                        return (Request) Function1.this.invoke(r);
                    }
                };
            }
        };
    }

    @NotNull
    public static final Function2<Request, Response, Response> loggingResponseInterceptor() {
        return new Function2<Request, Response, Response>() { // from class: com.github.kittinunf.fuel.core.interceptors.LoggingInterceptorsKt$loggingResponseInterceptor$1
            @Override // kotlin.jvm.functions.Function2
            @NotNull
            public final Response invoke(@NotNull Request request, @NotNull Response response) {
                Intrinsics.checkParameterIsNotNull(request, "request");
                Intrinsics.checkParameterIsNotNull(response, "response");
                System.out.println((Object) request.toString());
                System.out.println((Object) response.toString());
                return response;
            }
        };
    }
}
