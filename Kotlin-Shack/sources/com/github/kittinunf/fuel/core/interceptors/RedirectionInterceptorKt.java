package com.github.kittinunf.fuel.core.interceptors;

import com.github.kittinunf.fuel.core.Encoding;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.FuelManager;
import com.github.kittinunf.fuel.core.Method;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: RedirectionInterceptor.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a>\u0010\u0000\u001a2\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"redirectResponseInterceptor", "Lkotlin/Function1;", "Lkotlin/Function2;", "Lcom/github/kittinunf/fuel/core/Request;", "Lcom/github/kittinunf/fuel/core/Response;", "manager", "Lcom/github/kittinunf/fuel/core/FuelManager;", "fuel"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class RedirectionInterceptorKt {
    @NotNull
    public static final Function1<Function2<? super Request, ? super Response, Response>, Function2<Request, Response, Response>> redirectResponseInterceptor(@NotNull final FuelManager manager) {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        return (Function1) new Function1<Function2<? super Request, ? super Response, ? extends Response>, Function2<? super Request, ? super Response, ? extends Response>>() { // from class: com.github.kittinunf.fuel.core.interceptors.RedirectionInterceptorKt$redirectResponseInterceptor$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Function2<? super Request, ? super Response, ? extends Response> invoke(Function2<? super Request, ? super Response, ? extends Response> function2) {
                return invoke2((Function2<? super Request, ? super Response, Response>) function2);
            }

            @NotNull
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Function2<Request, Response, Response> invoke2(@NotNull final Function2<? super Request, ? super Response, Response> next) {
                Intrinsics.checkParameterIsNotNull(next, "next");
                return new Function2<Request, Response, Response>() { // from class: com.github.kittinunf.fuel.core.interceptors.RedirectionInterceptorKt$redirectResponseInterceptor$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    @NotNull
                    public final Response invoke(@NotNull Request request, @NotNull Response response) {
                        String url;
                        Intrinsics.checkParameterIsNotNull(request, "request");
                        Intrinsics.checkParameterIsNotNull(response, "response");
                        if (response.getStatusCode() == 301 || response.getStatusCode() == 302 || response.getStatusCode() == 307) {
                            List redirectedUrl = response.getHeaders().get("Location");
                            if (redirectedUrl != null && !redirectedUrl.isEmpty()) {
                                Method method = request.getMethod();
                                try {
                                    url = new URL(redirectedUrl.get(0)).toString();
                                } catch (MalformedURLException e) {
                                    url = new URL(request.getUrl(), redirectedUrl.get(0)).toString();
                                }
                                Intrinsics.checkExpressionValueIsNotNull(url, "try {\n                  …                        }");
                                Encoding encoding = new Encoding(method, url, null, null, null, 28, null);
                                return (Response) next.invoke(request, FuelManager.this.request(encoding).response().getSecond());
                            }
                            FuelError error = new FuelError(new RedirectException(), response.getData(), response);
                            throw error;
                        }
                        return (Response) next.invoke(request, response);
                    }
                };
            }
        };
    }
}
