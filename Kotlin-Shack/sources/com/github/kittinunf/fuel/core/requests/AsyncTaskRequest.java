package com.github.kittinunf.fuel.core.requests;

import android.support.v4.app.NotificationCompat;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Response;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AsyncTaskRequest.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J\b\u0010\u0013\u001a\u00020\u0007H\u0016J\b\u0010\u0014\u001a\u00020\u0007H\u0002R.\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR(\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/github/kittinunf/fuel/core/requests/AsyncTaskRequest;", "Lcom/github/kittinunf/fuel/core/requests/TaskRequest;", "task", "(Lcom/github/kittinunf/fuel/core/requests/TaskRequest;)V", "failureCallback", "Lkotlin/Function2;", "Lcom/github/kittinunf/fuel/core/FuelError;", "Lcom/github/kittinunf/fuel/core/Response;", "", "getFailureCallback", "()Lkotlin/jvm/functions/Function2;", "setFailureCallback", "(Lkotlin/jvm/functions/Function2;)V", "successCallback", "Lkotlin/Function1;", "getSuccessCallback", "()Lkotlin/jvm/functions/Function1;", "setSuccessCallback", "(Lkotlin/jvm/functions/Function1;)V", NotificationCompat.CATEGORY_CALL, "errorResponse", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class AsyncTaskRequest extends TaskRequest {

    @Nullable
    private Function2<? super FuelError, ? super Response, Unit> failureCallback;

    @Nullable
    private Function1<? super Response, Unit> successCallback;
    private final TaskRequest task;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AsyncTaskRequest(@NotNull TaskRequest task) {
        super(task.getRequest());
        Intrinsics.checkParameterIsNotNull(task, "task");
        this.task = task;
    }

    @Nullable
    public final Function1<Response, Unit> getSuccessCallback() {
        return this.successCallback;
    }

    public final void setSuccessCallback(@Nullable Function1<? super Response, Unit> function1) {
        this.successCallback = function1;
    }

    @Nullable
    public final Function2<FuelError, Response, Unit> getFailureCallback() {
        return this.failureCallback;
    }

    public final void setFailureCallback(@Nullable Function2<? super FuelError, ? super Response, Unit> function2) {
        this.failureCallback = function2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.kittinunf.fuel.core.requests.TaskRequest, java.util.concurrent.Callable
    @NotNull
    public Response call() {
        try {
            Response $receiver = this.task.call();
            Function1<? super Response, Unit> function1 = this.successCallback;
            if (function1 != null) {
                function1.invoke($receiver);
                return $receiver;
            }
            return $receiver;
        } catch (FuelError error) {
            Function2<? super FuelError, ? super Response, Unit> function2 = this.failureCallback;
            if (function2 != null) {
                function2.invoke(error, error.getResponse());
            }
            return errorResponse();
        } catch (Exception ex) {
            FuelError error2 = new FuelError(ex, null, null, 6, null);
            Response response = errorResponse();
            Function2<? super FuelError, ? super Response, Unit> function22 = this.failureCallback;
            if (function22 != null) {
                function22.invoke(error2, response);
            }
            return response;
        }
    }

    private final Response errorResponse() {
        return new Response(getRequest().getUrl(), 0, null, null, 0L, null, 62, null);
    }
}
