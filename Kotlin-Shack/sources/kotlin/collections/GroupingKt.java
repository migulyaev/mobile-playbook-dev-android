package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Grouping.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000L\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0005\u001a\u009b\u0001\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b\u001a´\u0001\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\u0087\b¢\u0006\u0002\u0010\u0013\u001a0\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001aI\u0010\u0016\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0016\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u0010H\u0007¢\u0006\u0002\u0010\u0017\u001a¼\u0001\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u000526\u0010\u0019\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001a2K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001bH\u0087\b\u001a|\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u001c\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\b¢\u0006\u0002\u0010\u001d\u001aÕ\u0001\u0010\u001e\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u001026\u0010\u0019\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001a2K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001bH\u0087\b¢\u0006\u0002\u0010\u001f\u001a\u0090\u0001\u0010\u001e\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102\u0006\u0010\u001c\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\u0087\b¢\u0006\u0002\u0010 \u001aW\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\"\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\"0\u00112\u001e\u0010#\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\"0%\u0012\u0004\u0012\u0002H\u00030$H\u0081\b\u001a\u0088\u0001\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H'0\u0001\"\u0004\b\u0000\u0010'\"\b\b\u0001\u0010\u0004*\u0002H'\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H'¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H'0\u001bH\u0087\b\u001a¡\u0001\u0010(\u001a\u0002H\u0010\"\u0004\b\u0000\u0010'\"\b\b\u0001\u0010\u0004*\u0002H'\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H'0\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H'¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H'0\u001bH\u0087\b¢\u0006\u0002\u0010)¨\u0006*"}, d2 = {"aggregate", "", "K", "R", "T", "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "aggregateTo", "M", "", "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "eachCount", "", "eachCountTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "mapValuesInPlace", "V", "f", "Lkotlin/Function1;", "", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class GroupingKt {
    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> aggregate(@NotNull Grouping<T, ? extends K> receiver, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            Object key$iv = receiver.keyOf(next);
            Object accumulator$iv = linkedHashMap.get(key$iv);
            linkedHashMap.put(key$iv, operation.invoke(key$iv, accumulator$iv, next, Boolean.valueOf(accumulator$iv == null && !linkedHashMap.containsKey(key$iv))));
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M aggregateTo(@NotNull Grouping<T, ? extends K> receiver, @NotNull M destination, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            Object key = receiver.keyOf(next);
            Object accumulator = destination.get(key);
            destination.put(key, operation.invoke(key, accumulator, next, Boolean.valueOf(accumulator == null && !destination.containsKey(key))));
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> fold(@NotNull Grouping<T, ? extends K> receiver, @NotNull Function2<? super K, ? super T, ? extends R> function2, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> operation) {
        int $i$f$fold;
        Object key;
        Object e;
        Object obj;
        Function2<? super K, ? super T, ? extends R> initialValueSelector = function2;
        int $i$f$fold2 = 0;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(initialValueSelector, "initialValueSelector");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        boolean z = false;
        Map destination$iv$iv = new LinkedHashMap();
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            K keyOf = receiver.keyOf(next);
            Object accumulator$iv$iv = destination$iv$iv.get(keyOf);
            boolean first = (accumulator$iv$iv != null || destination$iv$iv.containsKey(keyOf)) ? z : true;
            if (first) {
                $i$f$fold = $i$f$fold2;
                key = keyOf;
                e = next;
                obj = initialValueSelector.invoke(key, e);
            } else {
                $i$f$fold = $i$f$fold2;
                key = keyOf;
                e = next;
                obj = accumulator$iv$iv;
            }
            destination$iv$iv.put(keyOf, operation.invoke(key, obj, e));
            $i$f$fold2 = $i$f$fold;
            initialValueSelector = function2;
            z = false;
        }
        return destination$iv$iv;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> receiver, @NotNull M destination, @NotNull Function2<? super K, ? super T, ? extends R> function2, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> operation) {
        Object e;
        Object obj;
        Function2<? super K, ? super T, ? extends R> initialValueSelector = function2;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(initialValueSelector, "initialValueSelector");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        boolean z = false;
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            Object key$iv = receiver.keyOf(next);
            Object accumulator$iv = destination.get(key$iv);
            boolean first = (accumulator$iv != null || destination.containsKey(key$iv)) ? z : true;
            if (first) {
                e = next;
                obj = initialValueSelector.invoke(key$iv, e);
            } else {
                e = next;
                obj = accumulator$iv;
            }
            destination.put(key$iv, operation.invoke(key$iv, obj, e));
            initialValueSelector = function2;
            z = false;
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R> Map<K, R> fold(@NotNull Grouping<T, ? extends K> receiver, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        int $i$f$fold = 0;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        boolean z = false;
        Map destination$iv$iv = new LinkedHashMap();
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            K keyOf = receiver.keyOf(next);
            Object accumulator$iv$iv = destination$iv$iv.get(keyOf);
            boolean first = (accumulator$iv$iv != null || destination$iv$iv.containsKey(keyOf)) ? z : true;
            destination$iv$iv.put(keyOf, operation.invoke(first ? r : accumulator$iv$iv, next));
            $i$f$fold = $i$f$fold;
            z = false;
        }
        return destination$iv$iv;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> receiver, @NotNull M destination, R r, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        boolean z = false;
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            K keyOf = receiver.keyOf(next);
            Object accumulator$iv = destination.get(keyOf);
            boolean first = (accumulator$iv != null || destination.containsKey(keyOf)) ? z : true;
            destination.put(keyOf, operation.invoke(first ? r : accumulator$iv, next));
            z = false;
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <S, T extends S, K> Map<K, S> reduce(@NotNull Grouping<T, ? extends K> receiver, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> operation) {
        int $i$f$aggregate;
        int $i$f$aggregate2 = 0;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        boolean z = false;
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            Object key$iv$iv = receiver.keyOf(next);
            Object accumulator$iv$iv = linkedHashMap.get(key$iv$iv);
            boolean first = (accumulator$iv$iv != null || linkedHashMap.containsKey(key$iv$iv)) ? z : true;
            Object e = next;
            if (first) {
                $i$f$aggregate = $i$f$aggregate2;
            } else {
                $i$f$aggregate = $i$f$aggregate2;
                e = operation.invoke(key$iv$iv, accumulator$iv$iv, e);
            }
            linkedHashMap.put(key$iv$iv, e);
            $i$f$aggregate2 = $i$f$aggregate;
            z = false;
        }
        return linkedHashMap;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <S, T extends S, K, M extends Map<? super K, S>> M reduceTo(@NotNull Grouping<T, ? extends K> receiver, @NotNull M destination, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            T next = sourceIterator.next();
            Object key$iv = receiver.keyOf(next);
            Object accumulator$iv = destination.get(key$iv);
            boolean first = accumulator$iv == null && !destination.containsKey(key$iv);
            Object e = next;
            if (!first) {
                e = operation.invoke(key$iv, accumulator$iv, e);
            }
            destination.put(key$iv, e);
        }
        return destination;
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K> Map<K, Integer> eachCount(@NotNull Grouping<T, ? extends K> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Map destination$iv = new LinkedHashMap();
        Grouping $receiver$iv = receiver;
        boolean z = false;
        Iterator<T> sourceIterator = $receiver$iv.sourceIterator();
        while (sourceIterator.hasNext()) {
            K keyOf = $receiver$iv.keyOf(sourceIterator.next());
            Object accumulator$iv$iv = destination$iv.get(keyOf);
            boolean first$iv = (accumulator$iv$iv != null || destination$iv.containsKey(keyOf)) ? z : true;
            Ref.IntRef acc = (Ref.IntRef) (first$iv ? new Ref.IntRef() : accumulator$iv$iv);
            acc.element++;
            destination$iv.put(keyOf, acc);
            $receiver$iv = $receiver$iv;
            z = false;
        }
        for (Map.Entry it : destination$iv.entrySet()) {
            if (it == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
            TypeIntrinsics.asMutableMapEntry(it).setValue(Integer.valueOf(((Ref.IntRef) it.getValue()).element));
        }
        return TypeIntrinsics.asMutableMap(destination$iv);
    }

    @SinceKotlin(version = "1.1")
    @NotNull
    public static final <T, K, M extends Map<? super K, Integer>> M eachCountTo(@NotNull Grouping<T, ? extends K> receiver, @NotNull M destination) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        boolean z = false;
        Iterator<T> sourceIterator = receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            K keyOf = receiver.keyOf(sourceIterator.next());
            Object accumulator$iv$iv = destination.get(keyOf);
            boolean first$iv = (accumulator$iv$iv != null || destination.containsKey(keyOf)) ? z : true;
            int acc = ((Number) (first$iv ? 0 : accumulator$iv$iv)).intValue();
            destination.put(keyOf, Integer.valueOf(acc + 1));
            z = false;
        }
        return destination;
    }

    @PublishedApi
    @InlineOnly
    private static final <K, V, R> Map<K, R> mapValuesInPlace(@NotNull Map<K, V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Iterable $receiver$iv = map.entrySet();
        for (Object element$iv : $receiver$iv) {
            Map.Entry it = (Map.Entry) element$iv;
            if (it == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
            }
            TypeIntrinsics.asMutableMapEntry(it).setValue(function1.invoke(it));
        }
        if (map == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, R>");
        }
        return TypeIntrinsics.asMutableMap(map);
    }
}
