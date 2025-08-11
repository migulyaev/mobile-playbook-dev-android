package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

/* compiled from: JsonNamesMap.kt */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u0018\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002*\u00020\nH\u0000\u001a\u001c\u0010\u000b\u001a\u00020\u0004*\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0000\u001a\u001c\u0010\u000f\u001a\u00020\u0004*\u00020\n2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0000\u001aF\u0010\u0010\u001a\u00020\u0011*\u00020\r2\u0006\u0010\u0012\u001a\u00020\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00110\u00142\u000e\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00142\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014H\u0080\bø\u0001\u0000\".\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0018"}, d2 = {"JsonAlternativeNamesKey", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "", "", "", "getJsonAlternativeNamesKey$annotations", "()V", "getJsonAlternativeNamesKey", "()Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "buildAlternativeNamesMap", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getJsonNameIndex", "json", "Lkotlinx/serialization/json/Json;", "name", "getJsonNameIndexOrThrow", "tryCoerceValue", "", "elementDescriptor", "peekNull", "Lkotlin/Function0;", "peekString", "onEnumCoercing", "", "kotlinx-serialization-json"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class JsonNamesMapKt {
    private static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonAlternativeNamesKey = new DescriptorSchemaCache.Key<>();

    public static /* synthetic */ void getJsonAlternativeNamesKey$annotations() {
    }

    public static final DescriptorSchemaCache.Key<Map<String, Integer>> getJsonAlternativeNamesKey() {
        return JsonAlternativeNamesKey;
    }

    private static final void buildAlternativeNamesMap$putOrThrow(Map<String, Integer> map, SerialDescriptor $this_buildAlternativeNamesMap, String name, int index) {
        if (map.containsKey(name)) {
            throw new JsonException("The suggested name '" + name + "' for property " + $this_buildAlternativeNamesMap.getElementName(index) + " is already one of the names for property " + $this_buildAlternativeNamesMap.getElementName(((Number) MapsKt.getValue(map, name)).intValue()) + " in " + $this_buildAlternativeNamesMap);
        }
        map.put(name, Integer.valueOf(index));
    }

    public static final Map<String, Integer> buildAlternativeNamesMap(SerialDescriptor $this$buildAlternativeNamesMap) {
        String[] strArr;
        Intrinsics.checkNotNullParameter($this$buildAlternativeNamesMap, "<this>");
        Map<String, Integer> map = null;
        int elementsCount = $this$buildAlternativeNamesMap.getElementsCount();
        if (elementsCount > 0) {
            int i = 0;
            do {
                int i2 = i;
                i++;
                Iterable $this$filterIsInstance$iv = $this$buildAlternativeNamesMap.getElementAnnotations(i2);
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filterIsInstance$iv) {
                    if (element$iv$iv instanceof JsonNames) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                JsonNames jsonNames = (JsonNames) CollectionsKt.singleOrNull((List) destination$iv$iv);
                if (jsonNames != null && (strArr = jsonNames.get_names()) != null) {
                    for (String str : strArr) {
                        if (map == null) {
                            map = CreateMapForCacheKt.createMapForCache($this$buildAlternativeNamesMap.getElementsCount());
                        }
                        Intrinsics.checkNotNull(map);
                        buildAlternativeNamesMap$putOrThrow(map, $this$buildAlternativeNamesMap, str, i2);
                    }
                }
            } while (i < elementsCount);
        }
        return map == null ? MapsKt.emptyMap() : map;
    }

    public static final int getJsonNameIndex(SerialDescriptor $this$getJsonNameIndex, Json json, String name) {
        Intrinsics.checkNotNullParameter($this$getJsonNameIndex, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        int index = $this$getJsonNameIndex.getElementIndex(name);
        if (index != -3 || !json.getConfiguration().getUseAlternativeNames()) {
            return index;
        }
        Map alternativeNamesMap = (Map) JsonSchemaCacheKt.getSchemaCache(json).getOrPut($this$getJsonNameIndex, JsonAlternativeNamesKey, new JsonNamesMapKt$getJsonNameIndex$alternativeNamesMap$1($this$getJsonNameIndex));
        Integer num = (Integer) alternativeNamesMap.get(name);
        if (num == null) {
            return -3;
        }
        return num.intValue();
    }

    public static final int getJsonNameIndexOrThrow(SerialDescriptor $this$getJsonNameIndexOrThrow, Json json, String name) {
        Intrinsics.checkNotNullParameter($this$getJsonNameIndexOrThrow, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        int index = getJsonNameIndex($this$getJsonNameIndexOrThrow, json, name);
        if (index == -3) {
            throw new SerializationException($this$getJsonNameIndexOrThrow.getSerialName() + " does not contain element with name '" + name + '\'');
        }
        return index;
    }

    public static /* synthetic */ boolean tryCoerceValue$default(Json $this$tryCoerceValue_u24default, SerialDescriptor elementDescriptor, Function0 peekNull, Function0 peekString, Function0 onEnumCoercing, int i, Object obj) {
        String enumValue;
        if ((i & 8) != 0) {
            Function0 onEnumCoercing2 = new Function0<Unit>() { // from class: kotlinx.serialization.json.internal.JsonNamesMapKt$tryCoerceValue$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
            onEnumCoercing = onEnumCoercing2;
        }
        Intrinsics.checkNotNullParameter($this$tryCoerceValue_u24default, "<this>");
        Intrinsics.checkNotNullParameter(elementDescriptor, "elementDescriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        if (!elementDescriptor.isNullable() && ((Boolean) peekNull.invoke()).booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || (enumValue = (String) peekString.invoke()) == null) {
            return false;
        }
        int enumIndex = getJsonNameIndex(elementDescriptor, $this$tryCoerceValue_u24default, enumValue);
        if (enumIndex == -3) {
            onEnumCoercing.invoke();
            return true;
        }
        return false;
    }

    public static final boolean tryCoerceValue(Json $this$tryCoerceValue, SerialDescriptor elementDescriptor, Function0<Boolean> peekNull, Function0<String> peekString, Function0<Unit> onEnumCoercing) {
        String enumValue;
        Intrinsics.checkNotNullParameter($this$tryCoerceValue, "<this>");
        Intrinsics.checkNotNullParameter(elementDescriptor, "elementDescriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        if (!elementDescriptor.isNullable() && peekNull.invoke().booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || (enumValue = peekString.invoke()) == null) {
            return false;
        }
        int enumIndex = getJsonNameIndex(elementDescriptor, $this$tryCoerceValue, enumValue);
        if (enumIndex == -3) {
            onEnumCoercing.invoke();
            return true;
        }
        return false;
    }
}
