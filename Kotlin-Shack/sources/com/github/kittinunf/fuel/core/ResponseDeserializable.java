package com.github.kittinunf.fuel.core;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Deserializable.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J\u0015\u0010\u0004\u001a\u00028\u00002\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¢\u0006\u0002\u0010\u0007J\u0017\u0010\u0004\u001a\u0004\u0018\u00018\u00002\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\nJ\u0017\u0010\u0004\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000b\u001a\u00020\fH\u0016¢\u0006\u0002\u0010\rJ\u0017\u0010\u0004\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010J\u0017\u0010\u0004\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"Lcom/github/kittinunf/fuel/core/ResponseDeserializable;", "T", "", "Lcom/github/kittinunf/fuel/core/Deserializable;", "deserialize", "response", "Lcom/github/kittinunf/fuel/core/Response;", "(Lcom/github/kittinunf/fuel/core/Response;)Ljava/lang/Object;", "inputStream", "Ljava/io/InputStream;", "(Ljava/io/InputStream;)Ljava/lang/Object;", "reader", "Ljava/io/Reader;", "(Ljava/io/Reader;)Ljava/lang/Object;", "bytes", "", "([B)Ljava/lang/Object;", "content", "", "(Ljava/lang/String;)Ljava/lang/Object;", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public interface ResponseDeserializable<T> extends Deserializable<T> {
    @Override // com.github.kittinunf.fuel.core.Deserializable
    @NotNull
    T deserialize(@NotNull Response response);

    @Nullable
    T deserialize(@NotNull InputStream inputStream);

    @Nullable
    T deserialize(@NotNull Reader reader);

    @Nullable
    T deserialize(@NotNull String content);

    @Nullable
    T deserialize(@NotNull byte[] bytes);

    /* compiled from: Deserializable.kt */
    @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 9})
    public static final class DefaultImpls {
        @NotNull
        public static <T> T deserialize(@NotNull ResponseDeserializable<? extends T> responseDeserializable, Response response) {
            Intrinsics.checkParameterIsNotNull(response, "response");
            try {
                T deserialize = responseDeserializable.deserialize(response.getDataStream());
                if (deserialize == null) {
                    deserialize = responseDeserializable.deserialize(new InputStreamReader(response.getDataStream(), Charsets.UTF_8));
                }
                if (deserialize == null) {
                    deserialize = responseDeserializable.deserialize(response.getData());
                }
                if (deserialize == null) {
                    deserialize = responseDeserializable.deserialize(new String(response.getData(), Charsets.UTF_8));
                }
                if (deserialize != null) {
                    return deserialize;
                }
                throw new IllegalStateException("One of deserialize(ByteArray) or deserialize(InputStream) or deserialize(Reader) or deserialize(String) must be implemented");
            } finally {
                response.getDataStream().close();
            }
        }

        @Nullable
        public static <T> T deserialize(@NotNull ResponseDeserializable<? extends T> responseDeserializable, byte[] bytes) {
            Intrinsics.checkParameterIsNotNull(bytes, "bytes");
            return null;
        }

        @Nullable
        public static <T> T deserialize(@NotNull ResponseDeserializable<? extends T> responseDeserializable, InputStream inputStream) {
            Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
            return null;
        }

        @Nullable
        public static <T> T deserialize(@NotNull ResponseDeserializable<? extends T> responseDeserializable, Reader reader) {
            Intrinsics.checkParameterIsNotNull(reader, "reader");
            return null;
        }

        @Nullable
        public static <T> T deserialize(@NotNull ResponseDeserializable<? extends T> responseDeserializable, String content) {
            Intrinsics.checkParameterIsNotNull(content, "content");
            return null;
        }
    }
}
