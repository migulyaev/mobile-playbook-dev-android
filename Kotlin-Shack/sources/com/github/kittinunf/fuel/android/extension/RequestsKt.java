package com.github.kittinunf.fuel.android.extension;

import com.github.kittinunf.fuel.android.core.Json;
import com.github.kittinunf.fuel.core.Deserializable;
import com.github.kittinunf.fuel.core.DeserializableKt;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.github.kittinunf.result.Result;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

/* compiled from: Requests.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\u001a(\u0010\u0003\u001a \u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u00070\u0004*\u00020\u0005\u001a6\u0010\u0003\u001a\u00020\u0005*\u00020\u00052*\u0010\t\u001a&\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0006\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0004\u0012\u00020\u000b0\n\u001a\u0018\u0010\u0003\u001a\u00020\u0005*\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\f¨\u0006\r"}, d2 = {"jsonDeserializer", "Lcom/github/kittinunf/fuel/core/Deserializable;", "Lcom/github/kittinunf/fuel/android/core/Json;", "responseJson", "Lkotlin/Triple;", "Lcom/github/kittinunf/fuel/core/Request;", "Lcom/github/kittinunf/fuel/core/Response;", "Lcom/github/kittinunf/result/Result;", "Lcom/github/kittinunf/fuel/core/FuelError;", "handler", "Lkotlin/Function3;", "", "Lcom/github/kittinunf/fuel/core/Handler;", "fuel-android_release"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class RequestsKt {
    @NotNull
    public static final Request responseJson(@NotNull Request receiver, @NotNull Function3<? super Request, ? super Response, ? super Result<Json, FuelError>, Unit> handler) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(receiver, jsonDeserializer(), handler);
    }

    @NotNull
    public static final Request responseJson(@NotNull Request receiver, @NotNull Handler<? super Json> handler) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        return DeserializableKt.response(receiver, jsonDeserializer(), handler);
    }

    @NotNull
    public static final Triple<Request, Response, Result<Json, FuelError>> responseJson(@NotNull Request receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return DeserializableKt.response(receiver, jsonDeserializer());
    }

    @NotNull
    public static final Deserializable<Json> jsonDeserializer() {
        return new Deserializable<Json>() { // from class: com.github.kittinunf.fuel.android.extension.RequestsKt$jsonDeserializer$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.github.kittinunf.fuel.core.Deserializable
            @NotNull
            public Json deserialize(@NotNull Response response) {
                Intrinsics.checkParameterIsNotNull(response, "response");
                return new Json(new String(response.getData(), Charsets.UTF_8));
            }
        };
    }
}
