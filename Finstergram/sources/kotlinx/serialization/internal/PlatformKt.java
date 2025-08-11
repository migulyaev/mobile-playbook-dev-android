package kotlinx.serialization.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Polymorphic;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.Serializable;

/* compiled from: Platform.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0018\n\u0002\b\u0005\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0000\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001aO\u0010\u0006\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\"\u0010\u0007\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00010\b\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0002¢\u0006\u0002\u0010\t\u001a\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00030\rH\u0000\u001a\u0012\u0010\u000e\u001a\u0004\u0018\u00010\u0003*\u0006\u0012\u0002\b\u00030\u0005H\u0002\u001a$\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\rH\u0000\u001aM\u0010\u0010\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\r2\"\u0010\u0007\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00010\b\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001H\u0000¢\u0006\u0002\u0010\u0011\u001a$\u0010\u0012\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a&\u0010\u0013\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0080\b¢\u0006\u0002\u0010\u0016\u001a\u0015\u0010\u0013\u001a\u00020\u000b*\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0080\b\u001a$\u0010\u0018\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\rH\u0002\u001a\u0018\u0010\u0019\u001a\u00020\u000b*\u00020\u00032\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\rH\u0000\u001a\u001c\u0010\u001b\u001a\u00020\u000b\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u0005H\u0002\u001a\u0010\u0010\u001c\u001a\u00020\u001d*\u0006\u0012\u0002\b\u00030\rH\u0000\u001a$\u0010\u001e\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\rH\u0002\u001aK\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H 0\b\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\n\b\u0001\u0010 *\u0004\u0018\u0001H\u0002*\u0012\u0012\u0004\u0012\u0002H 0!j\b\u0012\u0004\u0012\u0002H `\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00020\rH\u0000¢\u0006\u0002\u0010$¨\u0006%"}, d2 = {"findObjectSerializer", "Lkotlinx/serialization/KSerializer;", "T", "", "jClass", "Ljava/lang/Class;", "invokeSerializerOnCompanion", "args", "", "(Ljava/lang/Class;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "isReferenceArray", "", "rootClass", "Lkotlin/reflect/KClass;", "companionOrNull", "compiledSerializerImpl", "constructSerializerForGivenTypeArgs", "(Lkotlin/reflect/KClass;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "createEnumSerializer", "getChecked", "index", "", "([Ljava/lang/Object;I)Ljava/lang/Object;", "", "interfaceSerializer", "isInstanceOf", "kclass", "isNotAnnotated", "platformSpecificSerializerNotRegistered", "", "polymorphicSerializer", "toNativeArrayImpl", "E", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "eClass", "(Ljava/util/ArrayList;Lkotlin/reflect/KClass;)[Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class PlatformKt {
    public static final <T> T getChecked(T[] tArr, int index) {
        Intrinsics.checkNotNullParameter(tArr, "<this>");
        return tArr[index];
    }

    public static final boolean getChecked(boolean[] $this$getChecked, int index) {
        Intrinsics.checkNotNullParameter($this$getChecked, "<this>");
        return $this$getChecked[index];
    }

    public static final <T> KSerializer<T> compiledSerializerImpl(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return constructSerializerForGivenTypeArgs(kClass, new KSerializer[0]);
    }

    public static final <T, E extends T> E[] toNativeArrayImpl(ArrayList<E> arrayList, KClass<T> eClass) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        Intrinsics.checkNotNullParameter(eClass, "eClass");
        Object newInstance = Array.newInstance((Class<?>) JvmClassMappingKt.getJavaClass((KClass) eClass), arrayList.size());
        if (newInstance == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<E of kotlinx.serialization.internal.PlatformKt.toNativeArrayImpl>");
        }
        E[] eArr = (E[]) arrayList.toArray((Object[]) newInstance);
        Intrinsics.checkNotNullExpressionValue(eArr, "toArray(java.lang.reflec….java, size) as Array<E>)");
        return eArr;
    }

    public static final Void platformSpecificSerializerNotRegistered(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x008a A[Catch: NoSuchFieldException -> 0x008e, TRY_LEAVE, TryCatch #0 {NoSuchFieldException -> 0x008e, blocks: (B:19:0x0040, B:21:0x0052, B:29:0x0073, B:32:0x0086, B:34:0x008a, B:41:0x0079, B:44:0x0082, B:26:0x006a), top: B:18:0x0040 }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0095 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final <T> kotlinx.serialization.KSerializer<T> constructSerializerForGivenTypeArgs(kotlin.reflect.KClass<T> r14, kotlinx.serialization.KSerializer<java.lang.Object>... r15) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r0)
            java.lang.String r0 = "args"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r0)
            java.lang.Class r0 = kotlin.jvm.JvmClassMappingKt.getJavaClass(r14)
            boolean r1 = r0.isEnum()
            if (r1 == 0) goto L1f
            boolean r1 = isNotAnnotated(r0)
            if (r1 == 0) goto L1f
            kotlinx.serialization.KSerializer r1 = createEnumSerializer(r0)
            return r1
        L1f:
            boolean r1 = r0.isInterface()
            if (r1 == 0) goto L2a
            kotlinx.serialization.KSerializer r1 = interfaceSerializer(r14)
            return r1
        L2a:
            int r1 = r15.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r15, r1)
            kotlinx.serialization.KSerializer[] r1 = (kotlinx.serialization.KSerializer[]) r1
            kotlinx.serialization.KSerializer r1 = invokeSerializerOnCompanion(r0, r1)
            if (r1 == 0) goto L38
            return r1
        L38:
            kotlinx.serialization.KSerializer r2 = findObjectSerializer(r0)
            if (r2 != 0) goto L9b
        L3f:
            r2 = 0
            java.lang.Class[] r3 = r0.getDeclaredClasses()     // Catch: java.lang.NoSuchFieldException -> L8e
            java.lang.String r4 = "jClass.declaredClasses"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)     // Catch: java.lang.NoSuchFieldException -> L8e
            java.lang.Object[] r3 = (java.lang.Object[]) r3     // Catch: java.lang.NoSuchFieldException -> L8e
            r4 = 0
            r5 = 0
            r6 = 0
            int r7 = r3.length     // Catch: java.lang.NoSuchFieldException -> L8e
            r8 = 0
        L50:
            if (r8 >= r7) goto L6e
            r9 = r3[r8]     // Catch: java.lang.NoSuchFieldException -> L8e
            r10 = r9
            java.lang.Class r10 = (java.lang.Class) r10     // Catch: java.lang.NoSuchFieldException -> L8e
            r11 = 0
            java.lang.String r12 = r10.getSimpleName()     // Catch: java.lang.NoSuchFieldException -> L8e
            java.lang.String r13 = "$serializer"
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r13)     // Catch: java.lang.NoSuchFieldException -> L8e
            if (r12 == 0) goto L6a
            if (r6 == 0) goto L68
            r5 = r2
            goto L73
        L68:
            r5 = r9
            r6 = 1
        L6a:
            int r8 = r8 + 1
            goto L50
        L6e:
            if (r6 != 0) goto L72
            r5 = r2
            goto L73
        L72:
        L73:
            java.lang.Class r5 = (java.lang.Class) r5     // Catch: java.lang.NoSuchFieldException -> L8e
            if (r5 != 0) goto L79
        L77:
            r3 = r2
            goto L86
        L79:
            java.lang.String r3 = "INSTANCE"
            java.lang.reflect.Field r3 = r5.getField(r3)     // Catch: java.lang.NoSuchFieldException -> L8e
            if (r3 != 0) goto L82
            goto L77
        L82:
            java.lang.Object r3 = r3.get(r2)     // Catch: java.lang.NoSuchFieldException -> L8e
        L86:
            boolean r4 = r3 instanceof kotlinx.serialization.KSerializer     // Catch: java.lang.NoSuchFieldException -> L8e
            if (r4 == 0) goto L92
            kotlinx.serialization.KSerializer r3 = (kotlinx.serialization.KSerializer) r3     // Catch: java.lang.NoSuchFieldException -> L8e
            r2 = r3
            goto L92
        L8e:
            r3 = move-exception
            r4 = r2
            kotlinx.serialization.KSerializer r4 = (kotlinx.serialization.KSerializer) r4
        L92:
            if (r2 == 0) goto L96
            return r2
        L96:
            kotlinx.serialization.KSerializer r3 = polymorphicSerializer(r14)
            return r3
        L9b:
            r3 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.PlatformKt.constructSerializerForGivenTypeArgs(kotlin.reflect.KClass, kotlinx.serialization.KSerializer[]):kotlinx.serialization.KSerializer");
    }

    private static final <T> boolean isNotAnnotated(Class<T> cls) {
        return cls.getAnnotation(Serializable.class) == null && cls.getAnnotation(Polymorphic.class) == null;
    }

    private static final <T> KSerializer<T> polymorphicSerializer(KClass<T> kClass) {
        Class jClass = JvmClassMappingKt.getJavaClass((KClass) kClass);
        if (jClass.getAnnotation(Polymorphic.class) != null) {
            return new PolymorphicSerializer(kClass);
        }
        Serializable serializable = (Serializable) jClass.getAnnotation(Serializable.class);
        if (serializable != null && Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return new PolymorphicSerializer(kClass);
        }
        return null;
    }

    private static final <T> KSerializer<T> interfaceSerializer(KClass<T> kClass) {
        Serializable serializable = (Serializable) JvmClassMappingKt.getJavaClass((KClass) kClass).getAnnotation(Serializable.class);
        if (serializable == null || Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return new PolymorphicSerializer(kClass);
        }
        return null;
    }

    private static final <T> KSerializer<T> invokeSerializerOnCompanion(Class<?> cls, KSerializer<Object>... kSerializerArr) {
        Class[] clsArr;
        Object companion = companionOrNull(cls);
        if (companion == null) {
            return null;
        }
        try {
            if (!(kSerializerArr.length == 0)) {
                int length = kSerializerArr.length;
                Class[] clsArr2 = new Class[length];
                for (int i = 0; i < length; i++) {
                    clsArr2[i] = KSerializer.class;
                }
                clsArr = clsArr2;
            } else {
                clsArr = new Class[0];
            }
            Class[] types = clsArr;
            Object invoke = companion.getClass().getDeclaredMethod("serializer", (Class[]) Arrays.copyOf(types, types.length)).invoke(companion, Arrays.copyOf(kSerializerArr, kSerializerArr.length));
            if (invoke instanceof KSerializer) {
                return (KSerializer) invoke;
            }
            return null;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause == null) {
                throw e2;
            }
            String message = cause.getMessage();
            if (message == null) {
                message = e2.getMessage();
            }
            throw new InvocationTargetException(cause, message);
        }
    }

    private static final Object companionOrNull(Class<?> cls) {
        try {
            Field companion = cls.getDeclaredField("Companion");
            companion.setAccessible(true);
            return companion.get(null);
        } catch (Throwable th) {
            return null;
        }
    }

    private static final <T> KSerializer<T> createEnumSerializer(Class<T> cls) {
        Object[] constants = cls.getEnumConstants();
        String canonicalName = cls.getCanonicalName();
        Intrinsics.checkNotNullExpressionValue(canonicalName, "canonicalName");
        if (constants == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<out kotlin.Enum<*>>");
        }
        EnumSerializer enumSerializer = new EnumSerializer(canonicalName, (Enum[]) constants);
        if (enumSerializer instanceof KSerializer) {
            return enumSerializer;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ac A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0049 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final <T> kotlinx.serialization.KSerializer<T> findObjectSerializer(java.lang.Class<T> r16) {
        /*
            Method dump skipped, instructions count: 201
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.PlatformKt.findObjectSerializer(java.lang.Class):kotlinx.serialization.KSerializer");
    }

    public static final boolean isInstanceOf(Object $this$isInstanceOf, KClass<?> kclass) {
        Intrinsics.checkNotNullParameter($this$isInstanceOf, "<this>");
        Intrinsics.checkNotNullParameter(kclass, "kclass");
        return JvmClassMappingKt.getJavaObjectType(kclass).isInstance($this$isInstanceOf);
    }

    public static final boolean isReferenceArray(KClass<Object> rootClass) {
        Intrinsics.checkNotNullParameter(rootClass, "rootClass");
        return JvmClassMappingKt.getJavaClass((KClass) rootClass).isArray();
    }
}
