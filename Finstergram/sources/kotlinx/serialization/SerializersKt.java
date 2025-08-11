package kotlinx.serialization;

import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import kotlin.reflect.KType;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"kotlinx/serialization/SerializersKt__SerializersJvmKt", "kotlinx/serialization/SerializersKt__SerializersKt"}, k = 4, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class SerializersKt {
    public static final <T> KSerializer<T> reflectiveOrContextual(SerializersModule $this$reflectiveOrContextual, KClass<T> kClass, List<? extends KSerializer<Object>> list) {
        return SerializersKt__SerializersKt.reflectiveOrContextual($this$reflectiveOrContextual, kClass, list);
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializer(Type type) {
        return SerializersKt__SerializersJvmKt.serializer(type);
    }

    @InternalSerializationApi
    public static final <T> KSerializer<T> serializer(KClass<T> kClass) {
        return SerializersKt__SerializersKt.serializer(kClass);
    }

    public static final KSerializer<Object> serializer(KType type) {
        return SerializersKt__SerializersKt.serializer(type);
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializer(SerializersModule $this$serializer, Type type) {
        return SerializersKt__SerializersJvmKt.serializer($this$serializer, type);
    }

    public static final KSerializer<Object> serializer(SerializersModule $this$serializer, KType type) {
        return SerializersKt__SerializersKt.serializer($this$serializer, type);
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializerOrNull(Type type) {
        return SerializersKt__SerializersJvmKt.serializerOrNull(type);
    }

    @InternalSerializationApi
    public static final <T> KSerializer<T> serializerOrNull(KClass<T> kClass) {
        return SerializersKt__SerializersKt.serializerOrNull(kClass);
    }

    public static final KSerializer<Object> serializerOrNull(KType type) {
        return SerializersKt__SerializersKt.serializerOrNull(type);
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializerOrNull(SerializersModule $this$serializerOrNull, Type type) {
        return SerializersKt__SerializersJvmKt.serializerOrNull($this$serializerOrNull, type);
    }

    public static final KSerializer<Object> serializerOrNull(SerializersModule $this$serializerOrNull, KType type) {
        return SerializersKt__SerializersKt.serializerOrNull($this$serializerOrNull, type);
    }
}
