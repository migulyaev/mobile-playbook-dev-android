package com.github.kittinunf.fuel.rx;

import com.github.kittinunf.fuel.core.Deserializable;
import com.github.kittinunf.fuel.core.DeserializableKt;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.github.kittinunf.fuel.core.deserializers.ByteArrayDeserializer;
import com.github.kittinunf.fuel.core.deserializers.StringDeserializer;
import com.github.kittinunf.result.Result;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.functions.Cancellable;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* compiled from: RxFuel.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u0005\u001a4\u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00040\u00020\u0001\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\n\u001a(\u0010\u000b\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\f0\u0001*\u00020\u0005\u001aB\u0010\u000b\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00040\u00020\f0\u0001\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0002\u001a@\u0010\u000e\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00040\u00020\f0\u0001\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\n\u001a2\u0010\u000f\u001a \u0012\u001c\u0012\u001a\u0012\u0004\u0012\u00020\r\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00040\u00020\f0\u0001*\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u001a6\u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u00040\u00020\u0001\"\b\b\u0000\u0010\u0007*\u00020\b*\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0002\u001a&\u0010\u0014\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00040\u00020\u0001*\u00020\u00052\b\b\u0002\u0010\u0011\u001a\u00020\u0012¨\u0006\u0015"}, d2 = {"rx_bytes", "Lio/reactivex/Single;", "Lcom/github/kittinunf/result/Result;", "", "Lcom/github/kittinunf/fuel/core/FuelError;", "Lcom/github/kittinunf/fuel/core/Request;", "rx_object", "T", "", "deserializable", "Lcom/github/kittinunf/fuel/core/Deserializable;", "rx_response", "Lkotlin/Pair;", "Lcom/github/kittinunf/fuel/core/Response;", "rx_responseObject", "rx_responseString", "", "charset", "Ljava/nio/charset/Charset;", "rx_result", "rx_string", "fuel-rxjava"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class RxFuelKt {
    @NotNull
    public static final Single<Pair<Response, Result<byte[], FuelError>>> rx_response(@NotNull Request receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return rx_response(receiver, new ByteArrayDeserializer());
    }

    @NotNull
    public static final Single<Pair<Response, Result<String, FuelError>>> rx_responseString(@NotNull Request receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return rx_response(receiver, new StringDeserializer(charset));
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Single rx_responseString$default(Request request, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return rx_responseString(request, charset);
    }

    @NotNull
    public static final <T> Single<Pair<Response, Result<T, FuelError>>> rx_responseObject(@NotNull Request receiver, @NotNull Deserializable<? extends T> deserializable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(deserializable, "deserializable");
        return rx_response(receiver, deserializable);
    }

    private static final <T> Single<Pair<Response, Result<T, FuelError>>> rx_response(@NotNull final Request $receiver, final Deserializable<? extends T> deserializable) {
        Single<Pair<Response, Result<T, FuelError>>> create = Single.create(new SingleOnSubscribe<T>() { // from class: com.github.kittinunf.fuel.rx.RxFuelKt$rx_response$1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(@NotNull SingleEmitter<Pair<Response, Result<T, FuelError>>> emitter) {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                Triple response = DeserializableKt.response(Request.this, deserializable);
                Response response2 = (Response) response.component2();
                Result result = (Result) response.component3();
                emitter.onSuccess(TuplesKt.to(response2, result));
                emitter.setCancellable(new Cancellable() { // from class: com.github.kittinunf.fuel.rx.RxFuelKt$rx_response$1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        Request.this.cancel();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(create, "Single.create { emitter …this.cancel() }\n        }");
        return create;
    }

    @NotNull
    public static final Single<Result<byte[], FuelError>> rx_bytes(@NotNull Request receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return rx_result(receiver, new ByteArrayDeserializer());
    }

    @NotNull
    public static final Single<Result<String, FuelError>> rx_string(@NotNull Request receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, "charset");
        return rx_result(receiver, new StringDeserializer(charset));
    }

    @NotNull
    public static /* bridge */ /* synthetic */ Single rx_string$default(Request request, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return rx_string(request, charset);
    }

    @NotNull
    public static final <T> Single<Result<T, FuelError>> rx_object(@NotNull Request receiver, @NotNull Deserializable<? extends T> deserializable) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(deserializable, "deserializable");
        return rx_result(receiver, deserializable);
    }

    private static final <T> Single<Result<T, FuelError>> rx_result(@NotNull final Request $receiver, final Deserializable<? extends T> deserializable) {
        Single<Result<T, FuelError>> create = Single.create(new SingleOnSubscribe<T>() { // from class: com.github.kittinunf.fuel.rx.RxFuelKt$rx_result$1
            @Override // io.reactivex.SingleOnSubscribe
            public final void subscribe(@NotNull SingleEmitter<Result<T, FuelError>> emitter) {
                Intrinsics.checkParameterIsNotNull(emitter, "emitter");
                Result result = (Result) DeserializableKt.response(Request.this, deserializable).component3();
                emitter.onSuccess(result);
                emitter.setCancellable(new Cancellable() { // from class: com.github.kittinunf.fuel.rx.RxFuelKt$rx_result$1.1
                    @Override // io.reactivex.functions.Cancellable
                    public final void cancel() {
                        Request.this.cancel();
                    }
                });
            }
        });
        Intrinsics.checkExpressionValueIsNotNull(create, "Single.create { emitter …this.cancel() }\n        }");
        return create;
    }
}
