package kotlinx.serialization.internal;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* compiled from: Platform.common.kt */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\u001a\u0012\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007*\u00020\u0002H\u0000\u001a\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\n\"\u0004\b\u0000\u0010\u000b*\u0006\u0012\u0002\b\u00030\nH\u0081\b\u001a\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\f\"\u0004\b\u0000\u0010\u000b*\u0006\u0012\u0002\b\u00030\fH\u0081\b\u001a\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r\"\u0004\b\u0000\u0010\u000b*\u0006\u0012\u0002\b\u00030\rH\u0081\b\u001a\u001f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u000fH\u0000¢\u0006\u0002\u0010\u0010\u001a6\u0010\u0011\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\u0013*\b\u0012\u0004\u0012\u0002H\u000b0\u00142\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\u00130\u0016H\u0080\bø\u0001\u0000\u001a\u0012\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018*\u00020\u001aH\u0000\u001a\u0010\u0010\u001b\u001a\u00020\u001c*\u0006\u0012\u0002\b\u00030\u0018H\u0000\"\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0002X\u0083\u0004¢\u0006\n\n\u0002\u0010\u0005\u0012\u0004\b\u0003\u0010\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"EMPTY_DESCRIPTOR_ARRAY", "", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getEMPTY_DESCRIPTOR_ARRAY$annotations", "()V", "[Lkotlinx/serialization/descriptors/SerialDescriptor;", "cachedSerialNames", "", "", "cast", "Lkotlinx/serialization/DeserializationStrategy;", "T", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/SerializationStrategy;", "compactArray", "", "(Ljava/util/List;)[Lkotlinx/serialization/descriptors/SerialDescriptor;", "elementsHashCodeBy", "", "K", "", "selector", "Lkotlin/Function1;", "kclass", "Lkotlin/reflect/KClass;", "", "Lkotlin/reflect/KType;", "serializerNotRegistered", "", "kotlinx-serialization-core"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class Platform_commonKt {
    private static final SerialDescriptor[] EMPTY_DESCRIPTOR_ARRAY = new SerialDescriptor[0];

    private static /* synthetic */ void getEMPTY_DESCRIPTOR_ARRAY$annotations() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0033, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
    
        if (r2 > 0) goto L8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0021, code lost:
    
        r3 = r1;
        r1 = r1 + 1;
        r0.add(r6.getElementName(r3));
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x002e, code lost:
    
        if (r1 < r2) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.util.Set<java.lang.String> cachedSerialNames(kotlinx.serialization.descriptors.SerialDescriptor r6) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            boolean r0 = r6 instanceof kotlinx.serialization.internal.CachedNames
            if (r0 == 0) goto L11
            r0 = r6
            kotlinx.serialization.internal.CachedNames r0 = (kotlinx.serialization.internal.CachedNames) r0
            java.util.Set r0 = r0.getSerialNames()
            return r0
        L11:
            java.util.HashSet r0 = new java.util.HashSet
            int r1 = r6.getElementsCount()
            r0.<init>(r1)
            r1 = 0
            int r2 = r6.getElementsCount()
            if (r2 <= 0) goto L30
        L21:
            r3 = r1
            int r1 = r1 + 1
            r4 = r0
            java.util.Collection r4 = (java.util.Collection) r4
            java.lang.String r5 = r6.getElementName(r3)
            r4.add(r5)
            if (r1 < r2) goto L21
        L30:
            r1 = r0
            java.util.Set r1 = (java.util.Set) r1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.Platform_commonKt.cachedSerialNames(kotlinx.serialization.descriptors.SerialDescriptor):java.util.Set");
    }

    public static final SerialDescriptor[] compactArray(List<? extends SerialDescriptor> list) {
        List<? extends SerialDescriptor> list2 = list;
        SerialDescriptor[] serialDescriptorArr = null;
        Collection collection = !(list2 == null || list2.isEmpty()) ? list : null;
        if (collection != null) {
            Collection $this$toTypedArray$iv = collection;
            Object[] array = $this$toTypedArray$iv.toArray(new SerialDescriptor[0]);
            if (array == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T>");
            }
            serialDescriptorArr = (SerialDescriptor[]) array;
        }
        return serialDescriptorArr == null ? EMPTY_DESCRIPTOR_ARRAY : serialDescriptorArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> KSerializer<T> cast(KSerializer<?> kSerializer) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        return kSerializer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> SerializationStrategy<T> cast(SerializationStrategy<?> serializationStrategy) {
        Intrinsics.checkNotNullParameter(serializationStrategy, "<this>");
        return serializationStrategy;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> DeserializationStrategy<T> cast(DeserializationStrategy<?> deserializationStrategy) {
        Intrinsics.checkNotNullParameter(deserializationStrategy, "<this>");
        return deserializationStrategy;
    }

    public static final Void serializerNotRegistered(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        throw new SerializationException("Serializer for class '" + ((Object) kClass.getSimpleName()) + "' is not found.\nMark the class as @Serializable or provide the serializer explicitly.");
    }

    public static final KClass<Object> kclass(KType $this$kclass) {
        Intrinsics.checkNotNullParameter($this$kclass, "<this>");
        KClassifier t = $this$kclass.getClassifier();
        if (t instanceof KClass) {
            return (KClass) t;
        }
        throw new IllegalStateException(Intrinsics.stringPlus("Only KClass supported as classifier, got ", t).toString());
    }

    public static final <T, K> int elementsHashCodeBy(Iterable<? extends T> iterable, Function1<? super T, ? extends K> selector) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(selector, "selector");
        int accumulator$iv = 1;
        for (Object element$iv : iterable) {
            int hash = accumulator$iv;
            int i = hash * 31;
            K invoke = selector.invoke(element$iv);
            accumulator$iv = i + (invoke != null ? invoke.hashCode() : 0);
        }
        return accumulator$iv;
    }
}
