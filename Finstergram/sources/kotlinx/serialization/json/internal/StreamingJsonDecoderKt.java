package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* compiled from: StreamingJsonDecoder.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0017\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\u0007H\u0082\b¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"parseString", "T", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "expectedType", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/serialization/json/internal/AbstractJsonLexer;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-serialization-json"}, k = 2, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class StreamingJsonDecoderKt {
    private static final <T> T parseString(JsonReader $this$parseString, String expectedType, Function1<? super String, ? extends T> function1) {
        String input = $this$parseString.consumeStringLenient();
        try {
            return function1.invoke(input);
        } catch (IllegalArgumentException e) {
            JsonReader.fail$default($this$parseString, "Failed to parse type '" + expectedType + "' for input '" + input + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }
}
