package com.github.kittinunf.fuel.core;

import com.github.kittinunf.fuel.core.requests.AsyncTaskRequest;
import com.github.kittinunf.result.Result;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Deserializable.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aO\u0010\u0000\u001a \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u00060\u00040\u0001\"\b\b\u0000\u0010\u0005*\u00020\u0007\"\u000e\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\u00050\t*\u00020\u00022\u0006\u0010\n\u001a\u0002H\b¢\u0006\u0002\u0010\u000b\u001as\u0010\u0000\u001a\u00020\u0002\"\b\b\u0000\u0010\u0005*\u00020\u0007\"\u000e\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\u00050\t*\u00020\u00022\u0006\u0010\n\u001a\u0002H\b2\u001e\u0010\f\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000e0\r2\u001e\u0010\u000f\u001a\u001a\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000e0\rH\u0002¢\u0006\u0002\u0010\u0010\u001a]\u0010\u0000\u001a\u00020\u0002\"\b\b\u0000\u0010\u0005*\u00020\u0007\"\u000e\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\u00050\t*\u00020\u00022\u0006\u0010\n\u001a\u0002H\b2*\u0010\u0011\u001a&\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\u0010\u0012\u001a?\u0010\u0000\u001a\u00020\u0002\"\b\b\u0000\u0010\u0005*\u00020\u0007\"\u000e\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\u00050\t*\u00020\u00022\u0006\u0010\n\u001a\u0002H\b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0013¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"response", "Lkotlin/Triple;", "Lcom/github/kittinunf/fuel/core/Request;", "Lcom/github/kittinunf/fuel/core/Response;", "Lcom/github/kittinunf/result/Result;", "T", "Lcom/github/kittinunf/fuel/core/FuelError;", "", "U", "Lcom/github/kittinunf/fuel/core/Deserializable;", "deserializable", "(Lcom/github/kittinunf/fuel/core/Request;Lcom/github/kittinunf/fuel/core/Deserializable;)Lkotlin/Triple;", "success", "Lkotlin/Function3;", "", "failure", "(Lcom/github/kittinunf/fuel/core/Request;Lcom/github/kittinunf/fuel/core/Deserializable;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;)Lcom/github/kittinunf/fuel/core/Request;", "handler", "(Lcom/github/kittinunf/fuel/core/Request;Lcom/github/kittinunf/fuel/core/Deserializable;Lkotlin/jvm/functions/Function3;)Lcom/github/kittinunf/fuel/core/Request;", "Lcom/github/kittinunf/fuel/core/Handler;", "(Lcom/github/kittinunf/fuel/core/Request;Lcom/github/kittinunf/fuel/core/Deserializable;Lcom/github/kittinunf/fuel/core/Handler;)Lcom/github/kittinunf/fuel/core/Request;", "fuel"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class DeserializableKt {
    @NotNull
    public static final <T, U extends Deserializable<? extends T>> Request response(@NotNull final Request receiver, @NotNull U deserializable, @NotNull final Function3<? super Request, ? super Response, ? super Result<? extends T, FuelError>, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(deserializable, "deserializable");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        response(receiver, deserializable, new Function3<Request, Response, T, Unit>() { // from class: com.github.kittinunf.fuel.core.DeserializableKt$response$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Request request, Response response, Object obj) {
                invoke2(request, response, (Response) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Request $noName_0, @NotNull Response response, @NotNull T value) {
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(response, "response");
                Intrinsics.checkParameterIsNotNull(value, "value");
                handler.invoke(Request.this, response, new Result.Success(value));
            }
        }, new Function3<Request, Response, FuelError, Unit>() { // from class: com.github.kittinunf.fuel.core.DeserializableKt$response$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Request request, Response response, FuelError fuelError) {
                invoke2(request, response, fuelError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Request $noName_0, @NotNull Response response, @NotNull FuelError error) {
                Intrinsics.checkParameterIsNotNull($noName_0, "<anonymous parameter 0>");
                Intrinsics.checkParameterIsNotNull(response, "response");
                Intrinsics.checkParameterIsNotNull(error, "error");
                handler.invoke(Request.this, response, new Result.Failure(error));
            }
        });
        return receiver;
    }

    @NotNull
    public static final <T, U extends Deserializable<? extends T>> Request response(@NotNull Request receiver, @NotNull U deserializable, @NotNull final Handler<? super T> handler) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(deserializable, "deserializable");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        response(receiver, deserializable, new Function3<Request, Response, T, Unit>() { // from class: com.github.kittinunf.fuel.core.DeserializableKt$response$3
            {
                super(3);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Request request, Response response, Object obj) {
                invoke2(request, response, (Response) obj);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Request request, @NotNull Response response, @NotNull T value) {
                Intrinsics.checkParameterIsNotNull(request, "request");
                Intrinsics.checkParameterIsNotNull(response, "response");
                Intrinsics.checkParameterIsNotNull(value, "value");
                Handler.this.success(request, response, value);
            }
        }, new Function3<Request, Response, FuelError, Unit>() { // from class: com.github.kittinunf.fuel.core.DeserializableKt$response$4
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(Request request, Response response, FuelError fuelError) {
                invoke2(request, response, fuelError);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull Request request, @NotNull Response response, @NotNull FuelError error) {
                Intrinsics.checkParameterIsNotNull(request, "request");
                Intrinsics.checkParameterIsNotNull(response, "response");
                Intrinsics.checkParameterIsNotNull(error, "error");
                Handler.this.failure(request, response, error);
            }
        });
        return receiver;
    }

    private static final <T, U extends Deserializable<? extends T>> Request response(@NotNull final Request $receiver, U u, Function3<? super Request, ? super Response, ? super T, Unit> function3, final Function3<? super Request, ? super Response, ? super FuelError, Unit> function32) {
        AsyncTaskRequest asyncRequest = new AsyncTaskRequest($receiver.getTaskRequest$fuel());
        asyncRequest.setSuccessCallback(new DeserializableKt$response$5($receiver, u, function3, function32));
        asyncRequest.setFailureCallback(new Function2<FuelError, Response, Unit>() { // from class: com.github.kittinunf.fuel.core.DeserializableKt$response$6
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(FuelError fuelError, Response response) {
                invoke2(fuelError, response);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(@NotNull final FuelError error, @NotNull final Response response) {
                Intrinsics.checkParameterIsNotNull(error, "error");
                Intrinsics.checkParameterIsNotNull(response, "response");
                Request.this.callback(new Function0<Unit>() { // from class: com.github.kittinunf.fuel.core.DeserializableKt$response$6.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        function32.invoke(Request.this, response, error);
                    }
                });
            }
        });
        $receiver.submit(asyncRequest);
        return $receiver;
    }

    @NotNull
    public static final <T, U extends Deserializable<? extends T>> Triple<Request, Response, Result<T, FuelError>> response(@NotNull Request receiver, @NotNull U deserializable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(deserializable, "deserializable");
        try {
            Response response = receiver.getTaskRequest$fuel().call();
            return new Triple<>(receiver, response, new Result.Success(deserializable.deserialize(response)));
        } catch (FuelError error) {
            return new Triple<>(receiver, error.getResponse(), Result.INSTANCE.error(error));
        }
    }
}
