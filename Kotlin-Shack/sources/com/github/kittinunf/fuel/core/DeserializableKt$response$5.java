package com.github.kittinunf.fuel.core;

import com.github.kittinunf.result.Result;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

/* compiled from: Deserializable.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u000e\b\u0001\u0010\u0004*\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "T", "", "U", "Lcom/github/kittinunf/fuel/core/Deserializable;", "response", "Lcom/github/kittinunf/fuel/core/Response;", "invoke"}, k = 3, mv = {1, 1, 9})
/* loaded from: classes.dex */
final class DeserializableKt$response$5 extends Lambda implements Function1<Response, Unit> {
    final /* synthetic */ Deserializable $deserializable;
    final /* synthetic */ Function3 $failure;
    final /* synthetic */ Function3 $success;
    final /* synthetic */ Request receiver$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DeserializableKt$response$5(Request request, Deserializable deserializable, Function3 function3, Function3 function32) {
        super(1);
        this.receiver$0 = request;
        this.$deserializable = deserializable;
        this.$success = function3;
        this.$failure = function32;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Response response) {
        invoke2(response);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(@NotNull final Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        final Result deliverable = Result.INSTANCE.of(new Function0<T>() { // from class: com.github.kittinunf.fuel.core.DeserializableKt$response$5$deliverable$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            @NotNull
            public final T invoke() {
                return (T) DeserializableKt$response$5.this.$deserializable.deserialize(response);
            }
        });
        this.receiver$0.callback(new Function0<Unit>() { // from class: com.github.kittinunf.fuel.core.DeserializableKt$response$5.1
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
                Result this_$iv = deliverable;
                if (this_$iv instanceof Result.Success) {
                    Object it = ((Result.Success) this_$iv).getValue();
                    DeserializableKt$response$5.this.$success.invoke(DeserializableKt$response$5.this.receiver$0, response, it);
                } else {
                    if (this_$iv instanceof Result.Failure) {
                        Exception it2 = ((Result.Failure) this_$iv).getError();
                        DeserializableKt$response$5.this.$failure.invoke(DeserializableKt$response$5.this.receiver$0, response, new FuelError(it2, null, null, 6, null));
                        return;
                    }
                    throw new NoWhenBranchMatchedException();
                }
            }
        });
    }
}
