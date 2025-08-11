package kotlinx.serialization;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.internal.PlatformKt;
import kotlinx.serialization.internal.Platform_commonKt;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SerializersJvm.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a\u0018\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a)\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0002\b\u000b\u001a\u0015\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r*\u00020\u0004H\u0002¢\u0006\u0002\b\u000e\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a+\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0002\b\u0010\u001a\u001c\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007\u001a-\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001*\u00020\u00072\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0002\b\u0013¨\u0006\u0014"}, d2 = {"serializer", "Lkotlinx/serialization/KSerializer;", "", "type", "Ljava/lang/reflect/Type;", "serializerOrNull", "genericArraySerializer", "Lkotlinx/serialization/modules/SerializersModule;", "Ljava/lang/reflect/GenericArrayType;", "failOnMissingTypeArgSerializer", "", "genericArraySerializer$SerializersKt__SerializersJvmKt", "kclass", "Lkotlin/reflect/KClass;", "kclass$SerializersKt__SerializersJvmKt", "serializerByJavaTypeImpl", "serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt", "typeSerializer", "Ljava/lang/Class;", "typeSerializer$SerializersKt__SerializersJvmKt", "kotlinx-serialization-core"}, k = 5, mv = {1, 5, 1}, xi = 48, xs = "kotlinx/serialization/SerializersKt")
/* loaded from: classes.dex */
public final /* synthetic */ class SerializersKt__SerializersJvmKt {
    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializer(Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return SerializersKt.serializer(SerializersModuleKt.getEmptySerializersModule(), type);
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializerOrNull(Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        return SerializersKt.serializerOrNull(SerializersModuleKt.getEmptySerializersModule(), type);
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializer(SerializersModule $this$serializer, Type type) {
        Intrinsics.checkNotNullParameter($this$serializer, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        KSerializer<Object> serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt = serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt($this$serializer, type, true);
        if (serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt != null) {
            return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt;
        }
        Platform_commonKt.serializerNotRegistered(kclass$SerializersKt__SerializersJvmKt(type));
        throw new KotlinNothingValueException();
    }

    @ExperimentalSerializationApi
    public static final KSerializer<Object> serializerOrNull(SerializersModule $this$serializerOrNull, Type type) {
        Intrinsics.checkNotNullParameter($this$serializerOrNull, "<this>");
        Intrinsics.checkNotNullParameter(type, "type");
        return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt($this$serializerOrNull, type, false);
    }

    static /* synthetic */ KSerializer serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt$default(SerializersModule serializersModule, Type type, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(serializersModule, type, z);
    }

    private static final KSerializer<Object> serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt(SerializersModule $this$serializerByJavaTypeImpl, Type type, boolean failOnMissingTypeArgSerializer) {
        List argsSerializers;
        if (type instanceof GenericArrayType) {
            return genericArraySerializer$SerializersKt__SerializersJvmKt($this$serializerByJavaTypeImpl, (GenericArrayType) type, failOnMissingTypeArgSerializer);
        }
        if (type instanceof Class) {
            return typeSerializer$SerializersKt__SerializersJvmKt($this$serializerByJavaTypeImpl, (Class) type, failOnMissingTypeArgSerializer);
        }
        if (!(type instanceof ParameterizedType)) {
            if (!(type instanceof WildcardType)) {
                throw new IllegalArgumentException("typeToken should be an instance of Class<?>, GenericArray, ParametrizedType or WildcardType, but actual type is " + type + ' ' + Reflection.getOrCreateKotlinClass(type.getClass()));
            }
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "type.upperBounds");
            Object first = ArraysKt.first(upperBounds);
            Intrinsics.checkNotNullExpressionValue(first, "type.upperBounds.first()");
            return serializerByJavaTypeImpl$SerializersKt__SerializersJvmKt$default($this$serializerByJavaTypeImpl, (Type) first, false, 2, null);
        }
        Type rawType = ((ParameterizedType) type).getRawType();
        if (rawType == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<*>");
        }
        Class rootClass = (Class) rawType;
        Type[] args = ((ParameterizedType) type).getActualTypeArguments();
        Intrinsics.checkNotNullExpressionValue(args, "args");
        if (failOnMissingTypeArgSerializer) {
            Collection destination$iv$iv = new ArrayList(args.length);
            for (Type it : args) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                destination$iv$iv.add(SerializersKt.serializer($this$serializerByJavaTypeImpl, it));
            }
            argsSerializers = (List) destination$iv$iv;
        } else {
            Collection destination$iv$iv2 = new ArrayList(args.length);
            for (Type it2 : args) {
                Intrinsics.checkNotNullExpressionValue(it2, "it");
                KSerializer<Object> serializerOrNull = SerializersKt.serializerOrNull($this$serializerByJavaTypeImpl, it2);
                if (serializerOrNull == null) {
                    return null;
                }
                destination$iv$iv2.add(serializerOrNull);
            }
            argsSerializers = (List) destination$iv$iv2;
        }
        if (Set.class.isAssignableFrom(rootClass)) {
            return BuiltinSerializersKt.SetSerializer((KSerializer) argsSerializers.get(0));
        }
        if (List.class.isAssignableFrom(rootClass) || Collection.class.isAssignableFrom(rootClass)) {
            return BuiltinSerializersKt.ListSerializer((KSerializer) argsSerializers.get(0));
        }
        if (Map.class.isAssignableFrom(rootClass)) {
            return BuiltinSerializersKt.MapSerializer((KSerializer) argsSerializers.get(0), (KSerializer) argsSerializers.get(1));
        }
        if (Map.Entry.class.isAssignableFrom(rootClass)) {
            return BuiltinSerializersKt.MapEntrySerializer((KSerializer) argsSerializers.get(0), (KSerializer) argsSerializers.get(1));
        }
        if (Pair.class.isAssignableFrom(rootClass)) {
            return BuiltinSerializersKt.PairSerializer((KSerializer) argsSerializers.get(0), (KSerializer) argsSerializers.get(1));
        }
        if (Triple.class.isAssignableFrom(rootClass)) {
            return BuiltinSerializersKt.TripleSerializer((KSerializer) argsSerializers.get(0), (KSerializer) argsSerializers.get(1), (KSerializer) argsSerializers.get(2));
        }
        Iterable $this$map$iv = argsSerializers;
        Collection destination$iv$iv3 = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            destination$iv$iv3.add((KSerializer) item$iv$iv);
        }
        List varargs = (List) destination$iv$iv3;
        KClass kotlinClass = JvmClassMappingKt.getKotlinClass(rootClass);
        List $this$toTypedArray$iv = varargs;
        Object[] array = $this$toTypedArray$iv.toArray(new KSerializer[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        KSerializer[] kSerializerArr = (KSerializer[]) array;
        KSerializer<Object> constructSerializerForGivenTypeArgs = PlatformKt.constructSerializerForGivenTypeArgs(kotlinClass, (KSerializer[]) Arrays.copyOf(kSerializerArr, kSerializerArr.length));
        KSerializer<Object> kSerializer = constructSerializerForGivenTypeArgs instanceof KSerializer ? constructSerializerForGivenTypeArgs : null;
        return kSerializer == null ? SerializersKt.reflectiveOrContextual($this$serializerByJavaTypeImpl, JvmClassMappingKt.getKotlinClass(rootClass), varargs) : kSerializer;
    }

    private static final KSerializer<Object> typeSerializer$SerializersKt__SerializersJvmKt(SerializersModule $this$typeSerializer, Class<?> cls, boolean failOnMissingTypeArgSerializer) {
        KSerializer s;
        if (cls.isArray() && !cls.getComponentType().isPrimitive()) {
            Class eType = cls.getComponentType();
            Intrinsics.checkNotNullExpressionValue(eType, "type.componentType");
            Class cls2 = eType;
            if (failOnMissingTypeArgSerializer) {
                s = SerializersKt.serializer($this$typeSerializer, cls2);
            } else {
                s = SerializersKt.serializerOrNull($this$typeSerializer, cls2);
                if (s == null) {
                    return null;
                }
            }
            return BuiltinSerializersKt.ArraySerializer(JvmClassMappingKt.getKotlinClass(eType), s);
        }
        return SerializersKt.reflectiveOrContextual($this$typeSerializer, JvmClassMappingKt.getKotlinClass(cls), CollectionsKt.emptyList());
    }

    private static final KSerializer<Object> genericArraySerializer$SerializersKt__SerializersJvmKt(SerializersModule $this$genericArraySerializer, GenericArrayType type, boolean failOnMissingTypeArgSerializer) {
        Type type2;
        KSerializer serializer;
        KClass kclass;
        Type it = type.getGenericComponentType();
        if (it instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) it).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "it.upperBounds");
            type2 = (Type) ArraysKt.first(upperBounds);
        } else {
            type2 = it;
        }
        Type eType = type2;
        Intrinsics.checkNotNullExpressionValue(eType, "eType");
        if (failOnMissingTypeArgSerializer) {
            serializer = SerializersKt.serializer($this$genericArraySerializer, eType);
        } else {
            serializer = SerializersKt.serializerOrNull($this$genericArraySerializer, eType);
            if (serializer == null) {
                return null;
            }
        }
        if (eType instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) eType).getRawType();
            if (rawType == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Class<*>");
            }
            kclass = JvmClassMappingKt.getKotlinClass((Class) rawType);
        } else {
            if (!(eType instanceof KClass)) {
                throw new IllegalStateException(Intrinsics.stringPlus("unsupported type in GenericArray: ", Reflection.getOrCreateKotlinClass(eType.getClass())));
            }
            kclass = (KClass) eType;
        }
        return BuiltinSerializersKt.ArraySerializer(kclass, serializer);
    }

    private static final KClass<?> kclass$SerializersKt__SerializersJvmKt(Type $this$kclass) {
        if ($this$kclass instanceof KClass) {
            return (KClass) $this$kclass;
        }
        if ($this$kclass instanceof Class) {
            return JvmClassMappingKt.getKotlinClass((Class) $this$kclass);
        }
        if ($this$kclass instanceof ParameterizedType) {
            Type rawType = ((ParameterizedType) $this$kclass).getRawType();
            Intrinsics.checkNotNullExpressionValue(rawType, "it.rawType");
            return kclass$SerializersKt__SerializersJvmKt(rawType);
        }
        if ($this$kclass instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) $this$kclass).getUpperBounds();
            Intrinsics.checkNotNullExpressionValue(upperBounds, "it.upperBounds");
            Object first = ArraysKt.first(upperBounds);
            Intrinsics.checkNotNullExpressionValue(first, "it.upperBounds.first()");
            return kclass$SerializersKt__SerializersJvmKt((Type) first);
        }
        if ($this$kclass instanceof GenericArrayType) {
            Type genericComponentType = ((GenericArrayType) $this$kclass).getGenericComponentType();
            Intrinsics.checkNotNullExpressionValue(genericComponentType, "it.genericComponentType");
            return kclass$SerializersKt__SerializersJvmKt(genericComponentType);
        }
        throw new IllegalArgumentException("typeToken should be an instance of Class<?>, GenericArray, ParametrizedType or WildcardType, but actual type is " + $this$kclass + ' ' + Reflection.getOrCreateKotlinClass($this$kclass.getClass()));
    }
}
