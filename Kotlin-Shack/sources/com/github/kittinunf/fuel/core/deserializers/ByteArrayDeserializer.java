package com.github.kittinunf.fuel.core.deserializers;

import com.github.kittinunf.fuel.core.Deserializable;
import com.github.kittinunf.fuel.core.Response;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: ByteArrayDeserializer.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/github/kittinunf/fuel/core/deserializers/ByteArrayDeserializer;", "Lcom/github/kittinunf/fuel/core/Deserializable;", "", "()V", "deserialize", "response", "Lcom/github/kittinunf/fuel/core/Response;", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class ByteArrayDeserializer implements Deserializable<byte[]> {
    @Override // com.github.kittinunf.fuel.core.Deserializable
    @NotNull
    public byte[] deserialize(@NotNull Response response) {
        Intrinsics.checkParameterIsNotNull(response, "response");
        return response.getData();
    }
}
