package kotlinx.serialization.json.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.modules.SerializersModule;

/* compiled from: StreamingJsonDecoder.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0010\u0018\u00002\u00020\u00012\u00020\u0002B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0002J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u000fH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\b\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010'\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\nH\u0016J\b\u0010)\u001a\u00020*H\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\nH\u0016J\b\u0010.\u001a\u00020\u000fH\u0016J\b\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020\u000fH\u0002J\b\u00102\u001a\u000203H\u0016J\b\u00104\u001a\u00020\u000fH\u0002J\b\u00105\u001a\u00020\u001dH\u0016J\n\u00106\u001a\u0004\u0018\u000107H\u0016J\u0010\u00108\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\nH\u0002J!\u00109\u001a\u0002H:\"\u0004\b\u0000\u0010:2\f\u0010;\u001a\b\u0012\u0004\u0012\u0002H:0<H\u0016¢\u0006\u0002\u0010=J\b\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u00020AH\u0016J\b\u0010B\u001a\u00020AH\u0002J\u0010\u0010C\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010D\u001a\u00020\u001d2\u0006\u0010E\u001a\u00020AH\u0002J\u0010\u0010F\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0007\u001a\u00020\b8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006G"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "json", "Lkotlinx/serialization/json/Json;", "mode", "Lkotlinx/serialization/json/internal/WriteMode;", "lexer", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;Lkotlinx/serialization/json/internal/AbstractJsonLexer;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "currentIndex", "", "elementMarker", "Lkotlinx/serialization/json/internal/JsonElementMarker;", "getJson", "()Lkotlinx/serialization/json/Json;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "checkLeadingComma", "", "coerceInputValue", "", "index", "decodeBoolean", "decodeByte", "", "decodeChar", "", "decodeDouble", "", "decodeElementIndex", "decodeEnum", "enumDescriptor", "decodeFloat", "", "decodeInline", "Lkotlinx/serialization/encoding/Decoder;", "inlineDescriptor", "decodeInt", "decodeJsonElement", "Lkotlinx/serialization/json/JsonElement;", "decodeListIndex", "decodeLong", "", "decodeMapIndex", "decodeNotNullMark", "decodeNull", "", "decodeObjectIndex", "decodeSerializableValue", "T", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeShort", "", "decodeString", "", "decodeStringKey", "endStructure", "handleUnknown", "key", "skipLeftoverElements", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public class StreamingJsonDecoder extends AbstractDecoder implements JsonDecoder {
    private final JsonConfiguration configuration;
    private int currentIndex;
    private final JsonElementMarker elementMarker;
    private final Json json;
    public final JsonReader lexer;
    private final WriteMode mode;
    private final SerializersModule serializersModule;

    /* compiled from: StreamingJsonDecoder.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WriteMode.values().length];
            iArr[WriteMode.LIST.ordinal()] = 1;
            iArr[WriteMode.MAP.ordinal()] = 2;
            iArr[WriteMode.POLY_OBJ.ordinal()] = 3;
            iArr[WriteMode.OBJ.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public final Json getJson() {
        return this.json;
    }

    public StreamingJsonDecoder(Json json, WriteMode mode, JsonReader lexer, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.json = json;
        this.mode = mode;
        this.lexer = lexer;
        this.serializersModule = json.getSerializersModule();
        this.currentIndex = -1;
        JsonConfiguration configuration = json.getConfiguration();
        this.configuration = configuration;
        this.elementMarker = configuration.getExplicitNulls() ? null : new JsonElementMarker(descriptor);
    }

    @Override // kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return new JsonTreeReader(this.json.getConfiguration(), this.lexer).read();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<T> deserializer) {
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        return (T) PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializer);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        StreamingJsonDecoder streamingJsonDecoder;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        WriteMode newMode = WriteModeKt.switchMode(this.json, descriptor);
        this.lexer.consumeNextToken(newMode.begin);
        checkLeadingComma();
        switch (WhenMappings.$EnumSwitchMapping$0[newMode.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return new StreamingJsonDecoder(this.json, newMode, this.lexer, descriptor);
            default:
                if (this.mode == newMode && this.json.getConfiguration().getExplicitNulls()) {
                    streamingJsonDecoder = this;
                } else {
                    streamingJsonDecoder = new StreamingJsonDecoder(this.json, newMode, this.lexer, descriptor);
                }
                return streamingJsonDecoder;
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (this.json.getConfiguration().getIgnoreUnknownKeys() && descriptor.getElementsCount() == 0) {
            skipLeftoverElements(descriptor);
        }
        this.lexer.consumeNextToken(this.mode.end);
    }

    private final void skipLeftoverElements(SerialDescriptor descriptor) {
        do {
        } while (decodeElementIndex(descriptor) != -1);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        JsonElementMarker jsonElementMarker = this.elementMarker;
        return !(jsonElementMarker == null ? false : jsonElementMarker.getIsUnmarkedNull()) && this.lexer.tryConsumeNotNull();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    private final void checkLeadingComma() {
        if (this.lexer.peekNextToken() == 4) {
            JsonReader.fail$default(this.lexer, "Unexpected leading comma", 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        switch (WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()]) {
            case 2:
                return decodeMapIndex();
            case 3:
            default:
                return decodeListIndex();
            case 4:
                return decodeObjectIndex(descriptor);
        }
    }

    private final int decodeMapIndex() {
        boolean hasComma = false;
        int i = this.currentIndex;
        boolean decodingKey = i % 2 != 0;
        if (decodingKey) {
            if (i != -1) {
                hasComma = this.lexer.tryConsumeComma();
            }
        } else {
            this.lexer.consumeNextToken(AbstractJsonLexerKt.COLON);
        }
        if (this.lexer.canConsumeValue()) {
            if (decodingKey) {
                if (this.currentIndex != -1) {
                    JsonReader this_$iv = this.lexer;
                    int position$iv = this_$iv.currentPosition;
                    if (!hasComma) {
                        this_$iv.fail("Expected comma after the key-value pair", position$iv);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    JsonReader this_$iv2 = this.lexer;
                    boolean condition$iv = !hasComma;
                    int position$iv2 = this_$iv2.currentPosition;
                    if (!condition$iv) {
                        this_$iv2.fail("Unexpected trailing comma", position$iv2);
                        throw new KotlinNothingValueException();
                    }
                }
            }
            int i2 = this.currentIndex + 1;
            this.currentIndex = i2;
            return i2;
        }
        if (!hasComma) {
            return -1;
        }
        JsonReader.fail$default(this.lexer, "Expected '}', but had ',' instead", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    private final boolean coerceInputValue(SerialDescriptor descriptor, int index) {
        String enumValue$iv;
        Json $this$tryCoerceValue$iv = this.json;
        SerialDescriptor elementDescriptor$iv = descriptor.getElementDescriptor(index);
        if (!elementDescriptor$iv.isNullable() && (!this.lexer.tryConsumeNotNull())) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor$iv.getKind(), SerialKind.ENUM.INSTANCE) || (enumValue$iv = this.lexer.peekString(this.configuration.getIsLenient())) == null) {
            return false;
        }
        int enumIndex$iv = JsonNamesMapKt.getJsonNameIndex(elementDescriptor$iv, $this$tryCoerceValue$iv, enumValue$iv);
        if (enumIndex$iv != -3) {
            return false;
        }
        this.lexer.consumeString();
        return true;
    }

    private final int decodeObjectIndex(SerialDescriptor descriptor) {
        int index;
        boolean hasComma = this.lexer.tryConsumeComma();
        while (true) {
            boolean isUnknown = false;
            if (this.lexer.canConsumeValue()) {
                hasComma = false;
                String key = decodeStringKey();
                this.lexer.consumeNextToken(AbstractJsonLexerKt.COLON);
                index = JsonNamesMapKt.getJsonNameIndex(descriptor, this.json, key);
                if (index != -3) {
                    if (!this.configuration.getCoerceInputValues() || !coerceInputValue(descriptor, index)) {
                        break;
                    }
                    hasComma = this.lexer.tryConsumeComma();
                } else {
                    isUnknown = true;
                }
                if (isUnknown) {
                    hasComma = handleUnknown(key);
                }
            } else {
                if (hasComma) {
                    JsonReader.fail$default(this.lexer, "Unexpected trailing comma", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                JsonElementMarker jsonElementMarker = this.elementMarker;
                if (jsonElementMarker == null) {
                    return -1;
                }
                return jsonElementMarker.nextUnmarkedIndex$kotlinx_serialization_json();
            }
        }
        JsonElementMarker jsonElementMarker2 = this.elementMarker;
        if (jsonElementMarker2 != null) {
            jsonElementMarker2.mark$kotlinx_serialization_json(index);
        }
        return index;
    }

    private final boolean handleUnknown(String key) {
        if (this.configuration.getIgnoreUnknownKeys()) {
            this.lexer.skipElement(this.configuration.getIsLenient());
        } else {
            this.lexer.failOnUnknownKey(key);
        }
        return this.lexer.tryConsumeComma();
    }

    private final int decodeListIndex() {
        boolean hasComma = this.lexer.tryConsumeComma();
        if (this.lexer.canConsumeValue()) {
            int i = this.currentIndex;
            if (i != -1 && !hasComma) {
                JsonReader.fail$default(this.lexer, "Expected end of the array or comma", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            int i2 = i + 1;
            this.currentIndex = i2;
            return i2;
        }
        if (!hasComma) {
            return -1;
        }
        JsonReader.fail$default(this.lexer, "Unexpected trailing comma", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        if (this.configuration.getIsLenient()) {
            return this.lexer.consumeBooleanLenient();
        }
        return this.lexer.consumeBoolean();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        long value = this.lexer.consumeNumericLiteral();
        if (value != ((byte) value)) {
            JsonReader.fail$default(this.lexer, "Failed to parse byte for input '" + value + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return (byte) value;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        long value = this.lexer.consumeNumericLiteral();
        if (value != ((short) value)) {
            JsonReader.fail$default(this.lexer, "Failed to parse short for input '" + value + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return (short) value;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        long value = this.lexer.consumeNumericLiteral();
        if (value != ((int) value)) {
            JsonReader.fail$default(this.lexer, "Failed to parse int for input '" + value + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return (int) value;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        JsonReader $this$parseString$iv = this.lexer;
        String input$iv = $this$parseString$iv.consumeStringLenient();
        boolean z = false;
        try {
            float result = Float.parseFloat(input$iv);
            boolean specialFp = this.json.getConfiguration().getAllowSpecialFloatingPointValues();
            if (!specialFp) {
                if (!Float.isInfinite(result) && !Float.isNaN(result)) {
                    z = true;
                }
                if (!z) {
                    JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Float.valueOf(result));
                    throw new KotlinNothingValueException();
                }
            }
            return result;
        } catch (IllegalArgumentException e) {
            JsonReader.fail$default($this$parseString$iv, "Failed to parse type '" + TypedValues.Custom.S_FLOAT + "' for input '" + input$iv + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        JsonReader $this$parseString$iv = this.lexer;
        String input$iv = $this$parseString$iv.consumeStringLenient();
        boolean z = false;
        try {
            double result = Double.parseDouble(input$iv);
            boolean specialFp = this.json.getConfiguration().getAllowSpecialFloatingPointValues();
            if (!specialFp) {
                if (!Double.isInfinite(result) && !Double.isNaN(result)) {
                    z = true;
                }
                if (!z) {
                    JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(result));
                    throw new KotlinNothingValueException();
                }
            }
            return result;
        } catch (IllegalArgumentException e) {
            JsonReader.fail$default($this$parseString$iv, "Failed to parse type 'double' for input '" + input$iv + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        String string = this.lexer.consumeStringLenient();
        if (string.length() != 1) {
            JsonReader.fail$default(this.lexer, "Expected single char, but got '" + string + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return string.charAt(0);
    }

    private final String decodeStringKey() {
        if (this.configuration.getIsLenient()) {
            return this.lexer.consumeStringLenientNotNull();
        }
        return this.lexer.consumeKeyString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        if (this.configuration.getIsLenient()) {
            return this.lexer.consumeStringLenientNotNull();
        }
        return this.lexer.consumeString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        return StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor) ? new JsonDecoderForUnsignedTypes(this.lexer, this.json) : super.decodeInline(inlineDescriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return JsonNamesMapKt.getJsonNameIndexOrThrow(enumDescriptor, this.json, decodeString());
    }
}
