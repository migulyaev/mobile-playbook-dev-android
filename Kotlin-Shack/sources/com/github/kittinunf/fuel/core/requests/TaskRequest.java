package com.github.kittinunf.fuel.core.requests;

import android.support.v4.app.NotificationCompat;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import java.io.InterruptedIOException;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TaskRequest.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\b\u0010\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u000f\u001a\u00020\u0002H\u0016R(\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\u00020\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0010"}, d2 = {"Lcom/github/kittinunf/fuel/core/requests/TaskRequest;", "Ljava/util/concurrent/Callable;", "Lcom/github/kittinunf/fuel/core/Response;", "request", "Lcom/github/kittinunf/fuel/core/Request;", "(Lcom/github/kittinunf/fuel/core/Request;)V", "interruptCallback", "Lkotlin/Function1;", "", "getInterruptCallback", "()Lkotlin/jvm/functions/Function1;", "setInterruptCallback", "(Lkotlin/jvm/functions/Function1;)V", "getRequest$fuel", "()Lcom/github/kittinunf/fuel/core/Request;", NotificationCompat.CATEGORY_CALL, "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public class TaskRequest implements Callable<Response> {

    @Nullable
    private Function1<? super Request, Unit> interruptCallback;

    @NotNull
    private final Request request;

    public TaskRequest(@NotNull Request request) {
        Intrinsics.checkParameterIsNotNull(request, "request");
        this.request = request;
    }

    @NotNull
    /* renamed from: getRequest$fuel, reason: from getter */
    public final Request getRequest() {
        return this.request;
    }

    @Nullable
    public final Function1<Request, Unit> getInterruptCallback() {
        return this.interruptCallback;
    }

    public final void setInterruptCallback(@Nullable Function1<? super Request, Unit> function1) {
        this.interruptCallback = function1;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.concurrent.Callable
    @NotNull
    public Response call() {
        Function1<? super Request, Unit> function1;
        Request modifiedRequest;
        try {
            Function1<Request, Request> requestInterceptor$fuel = this.request.getRequestInterceptor$fuel();
            if (requestInterceptor$fuel == null || (modifiedRequest = requestInterceptor$fuel.invoke(this.request)) == null) {
                modifiedRequest = this.request;
            }
            Response response = this.request.getClient$fuel().executeRequest(modifiedRequest);
            Function2<Request, Response, Response> responseInterceptor$fuel = this.request.getResponseInterceptor$fuel();
            if (responseInterceptor$fuel == null) {
                return response;
            }
            Response invoke = responseInterceptor$fuel.invoke(modifiedRequest, response);
            if (invoke != null) {
                return invoke;
            }
            return response;
        } catch (FuelError error) {
            Exception exception = error.getException();
            if (!(exception instanceof InterruptedIOException)) {
                exception = null;
            }
            if (((InterruptedIOException) exception) != null && (function1 = this.interruptCallback) != null) {
                function1.invoke(this.request);
            }
            throw error;
        }
    }
}
