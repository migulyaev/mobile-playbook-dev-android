package kotlinx.serialization.json.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: AbstractJsonLexer.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\r\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u0001\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\r\b \u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u0004H\u0014J\b\u0010\u001d\u001a\u00020\u001eH&J\u0006\u0010\u001f\u001a\u00020\u001eJ\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u0004H\u0003J\u0006\u0010!\u001a\u00020\u001eJ\u0018\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0004H\u0002J\b\u0010$\u001a\u00020\rH&J\b\u0010%\u001a\u00020&H&J\u000e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&J\u0010\u0010%\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(H\u0016J\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020\rJ \u0010+\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\u0005J\u0006\u0010,\u001a\u00020\rJ\u0006\u0010-\u001a\u00020\rJ\u0018\u0010.\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010/\u001a\u00020\u001aH\u0016J\u0006\u00100\u001a\u00020\u001aJ\u0015\u00101\u001a\u0002022\u0006\u00103\u001a\u00020&H\u0000¢\u0006\u0002\b4J\u0018\u00101\u001a\u0002022\u0006\u00105\u001a\u00020\r2\b\b\u0002\u00106\u001a\u00020\u0004J\u000e\u00107\u001a\u00020\u001a2\u0006\u00108\u001a\u00020\rJ\u0018\u00109\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002J\u0018\u0010:\u001a\u00020\u00042\u0006\u0010;\u001a\u00020(2\u0006\u0010\u0018\u001a\u00020\u0004H\u0016J\u0006\u0010<\u001a\u00020\u001eJ\u0010\u0010=\u001a\u00020\u001e2\u0006\u0010>\u001a\u00020(H\u0004J\u0006\u0010?\u001a\u00020&J\u0010\u0010@\u001a\u0004\u0018\u00010\r2\u0006\u0010A\u001a\u00020\u001eJ\u0010\u0010B\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u0004H&J1\u0010C\u001a\u00020\u001a2\u0006\u0010D\u001a\u00020\u001e2\b\b\u0002\u00106\u001a\u00020\u00042\f\u00105\u001a\b\u0012\u0004\u0012\u00020\r0EH\u0080\bø\u0001\u0000¢\u0006\u0002\bFJ\u000e\u0010G\u001a\u00020\u001a2\u0006\u0010H\u001a\u00020\u001eJ\b\u0010I\u001a\u00020\u0004H\u0016J\u0018\u0010J\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u0004H\u0016J\b\u0010L\u001a\u00020\rH\u0002J\b\u0010M\u001a\u00020\rH\u0016J\b\u0010N\u001a\u00020\u001eH&J\u0006\u0010O\u001a\u00020\u001eJ\u0010\u0010P\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020(H\u0004J\b\u0010Q\u001a\u00020\u001eH\u0002R\u0012\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000e\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006R"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "", "()V", "currentPosition", "", "escapedString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "getEscapedString", "()Ljava/lang/StringBuilder;", "setEscapedString", "(Ljava/lang/StringBuilder;)V", "peekedString", "", "source", "", "getSource", "()Ljava/lang/CharSequence;", "appendEsc", "startPosition", "appendEscape", "lastPosition", "current", "appendHex", "startPos", "appendRange", "", "fromIndex", "toIndex", "canConsumeValue", "", "consumeBoolean", "start", "consumeBooleanLenient", "consumeBooleanLiteral", "literalSuffix", "consumeKeyString", "consumeNextToken", "", "expected", "", "consumeNumericLiteral", "", "consumeString", "consumeStringLenient", "consumeStringLenientNotNull", "decodedString", "ensureHaveChars", "expectEof", "fail", "", "expectedToken", "fail$kotlinx_serialization_json", "message", "position", "failOnUnknownKey", "key", "fromHexChar", "indexOf", "char", "isNotEof", "isValidValueStart", "c", "peekNextToken", "peekString", "isLenient", "prefetchOrEof", "require", "condition", "Lkotlin/Function0;", "require$kotlinx_serialization_json", "skipElement", "allowLenientStrings", "skipWhitespaces", "substring", "endPos", "takePeeked", "toString", "tryConsumeComma", "tryConsumeNotNull", "unexpectedToken", "wasUnquotedString", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* renamed from: kotlinx.serialization.json.internal.AbstractJsonLexer, reason: from toString */
/* loaded from: classes.dex */
public abstract class JsonReader {
    protected int currentPosition;
    private StringBuilder escapedString = new StringBuilder();
    private String peekedString;

    public abstract boolean canConsumeValue();

    public abstract String consumeKeyString();

    public abstract byte consumeNextToken();

    protected abstract CharSequence getSource();

    public abstract int prefetchOrEof(int position);

    public abstract boolean tryConsumeComma();

    public void ensureHaveChars() {
    }

    public final boolean isNotEof() {
        return peekNextToken() != 10;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isValidValueStart(char c) {
        return !(((c == '}' || c == ']') || c == ':') || c == ',');
    }

    public final void expectEof() {
        byte nextToken = consumeNextToken();
        if (nextToken != 10) {
            fail$default(this, "Expected EOF after parsing, but had " + getSource().charAt(this.currentPosition - 1) + " instead", 0, 2, null);
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final StringBuilder getEscapedString() {
        return this.escapedString;
    }

    protected final void setEscapedString(StringBuilder sb) {
        Intrinsics.checkNotNullParameter(sb, "<set-?>");
        this.escapedString = sb;
    }

    public final byte consumeNextToken(byte expected) {
        byte token = consumeNextToken();
        if (token != expected) {
            fail$kotlinx_serialization_json(expected);
            throw new KotlinNothingValueException();
        }
        return token;
    }

    public void consumeNextToken(char expected) {
        ensureHaveChars();
        CharSequence source = getSource();
        int cpos = this.currentPosition;
        while (true) {
            int cpos2 = prefetchOrEof(cpos);
            if (cpos2 != -1) {
                int cpos3 = cpos2 + 1;
                char c = source.charAt(cpos2);
                if (c != ' ' && c != '\n' && c != '\r' && c != '\t') {
                    this.currentPosition = cpos3;
                    if (c == expected) {
                        return;
                    } else {
                        unexpectedToken(expected);
                    }
                }
                cpos = cpos3;
            } else {
                this.currentPosition = cpos2;
                unexpectedToken(expected);
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void unexpectedToken(char expected) {
        int i = this.currentPosition - 1;
        this.currentPosition = i;
        if (i >= 0 && expected == '\"' && Intrinsics.areEqual(consumeStringLenient(), AbstractJsonLexerKt.NULL)) {
            fail("Expected string literal but 'null' literal was found.\nUse 'coerceInputValues = true' in 'Json {}` builder to coerce nulls to default values.", this.currentPosition - 4);
            throw new KotlinNothingValueException();
        }
        fail$kotlinx_serialization_json(AbstractJsonLexerKt.charToTokenClass(expected));
        throw new KotlinNothingValueException();
    }

    public final Void fail$kotlinx_serialization_json(byte expectedToken) {
        String expected;
        if (expectedToken == 1) {
            expected = "quotation mark '\"'";
        } else if (expectedToken == 4) {
            expected = "comma ','";
        } else if (expectedToken == 5) {
            expected = "semicolon ':'";
        } else if (expectedToken == 6) {
            expected = "start of the object '{'";
        } else if (expectedToken == 7) {
            expected = "end of the object '}'";
        } else if (expectedToken == 8) {
            expected = "start of the array '['";
        } else {
            expected = expectedToken == 9 ? "end of the array ']'" : "valid token";
        }
        String s = (this.currentPosition == getSource().length() || this.currentPosition <= 0) ? "EOF" : String.valueOf(getSource().charAt(this.currentPosition - 1));
        fail("Expected " + expected + ", but had '" + s + "' instead", this.currentPosition - 1);
        throw new KotlinNothingValueException();
    }

    public final byte peekNextToken() {
        CharSequence source = getSource();
        int cpos = this.currentPosition;
        while (true) {
            int cpos2 = prefetchOrEof(cpos);
            if (cpos2 != -1) {
                char ch = source.charAt(cpos2);
                if (ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t') {
                    cpos = cpos2 + 1;
                } else {
                    this.currentPosition = cpos2;
                    return AbstractJsonLexerKt.charToTokenClass(ch);
                }
            } else {
                this.currentPosition = cpos2;
                return (byte) 10;
            }
        }
    }

    public final boolean tryConsumeNotNull() {
        int current = prefetchOrEof(skipWhitespaces());
        int len = getSource().length() - current;
        if (len < 4 || current == -1) {
            return true;
        }
        int i = 0;
        do {
            int i2 = i;
            i++;
            if (AbstractJsonLexerKt.NULL.charAt(i2) != getSource().charAt(current + i2)) {
                return true;
            }
        } while (i <= 3);
        if (len > 4 && AbstractJsonLexerKt.charToTokenClass(getSource().charAt(current + 4)) == 0) {
            return true;
        }
        this.currentPosition = current + 4;
        return false;
    }

    public int skipWhitespaces() {
        int current;
        char c;
        int current2 = this.currentPosition;
        while (true) {
            current = prefetchOrEof(current2);
            if (current != -1 && ((c = getSource().charAt(current)) == ' ' || c == '\n' || c == '\r' || c == '\t')) {
                current2 = current + 1;
            }
        }
        this.currentPosition = current;
        return current;
    }

    public final String peekString(boolean isLenient) {
        String string;
        byte token = peekNextToken();
        if (isLenient) {
            if (token != 1 && token != 0) {
                return null;
            }
            string = consumeStringLenient();
        } else {
            if (token != 1) {
                return null;
            }
            string = consumeString();
        }
        this.peekedString = string;
        return string;
    }

    public int indexOf(char r7, int startPos) {
        return StringsKt.indexOf$default(getSource(), r7, startPos, false, 4, (Object) null);
    }

    public String substring(int startPos, int endPos) {
        return getSource().subSequence(startPos, endPos).toString();
    }

    public final String consumeString() {
        if (this.peekedString != null) {
            return takePeeked();
        }
        return consumeKeyString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String consumeString(CharSequence source, int startPosition, int current) {
        String string;
        Intrinsics.checkNotNullParameter(source, "source");
        int currentPosition = current;
        int lastPosition = startPosition;
        char charAt = source.charAt(currentPosition);
        boolean usedAppend = false;
        while (charAt != '\"') {
            if (charAt == '\\') {
                usedAppend = true;
                currentPosition = prefetchOrEof(appendEscape(lastPosition, currentPosition));
                if (currentPosition == -1) {
                    fail("EOF", currentPosition);
                    throw new KotlinNothingValueException();
                }
                lastPosition = currentPosition;
            } else {
                currentPosition++;
                if (currentPosition >= source.length()) {
                    usedAppend = true;
                    appendRange(lastPosition, currentPosition);
                    currentPosition = prefetchOrEof(currentPosition);
                    if (currentPosition == -1) {
                        fail("EOF", currentPosition);
                        throw new KotlinNothingValueException();
                    }
                    lastPosition = currentPosition;
                } else {
                    continue;
                }
            }
            charAt = source.charAt(currentPosition);
        }
        if (!usedAppend) {
            string = substring(lastPosition, currentPosition);
        } else {
            string = decodedString(lastPosition, currentPosition);
        }
        this.currentPosition = currentPosition + 1;
        return string;
    }

    private final int appendEscape(int lastPosition, int current) {
        appendRange(lastPosition, current);
        return appendEsc(current + 1);
    }

    private final String decodedString(int lastPosition, int currentPosition) {
        appendRange(lastPosition, currentPosition);
        String result = this.escapedString.toString();
        Intrinsics.checkNotNullExpressionValue(result, "escapedString.toString()");
        this.escapedString.setLength(0);
        return result;
    }

    private final String takePeeked() {
        String str = this.peekedString;
        Intrinsics.checkNotNull(str);
        this.peekedString = null;
        return str;
    }

    public final String consumeStringLenientNotNull() {
        String result = consumeStringLenient();
        if (Intrinsics.areEqual(result, AbstractJsonLexerKt.NULL) && wasUnquotedString()) {
            fail$default(this, "Unexpected 'null' value instead of string literal", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        return result;
    }

    private final boolean wasUnquotedString() {
        return getSource().charAt(this.currentPosition - 1) != '\"';
    }

    public final String consumeStringLenient() {
        String result;
        if (this.peekedString != null) {
            return takePeeked();
        }
        int current = skipWhitespaces();
        if (current >= getSource().length() || current == -1) {
            fail("EOF", current);
            throw new KotlinNothingValueException();
        }
        byte token = AbstractJsonLexerKt.charToTokenClass(getSource().charAt(current));
        if (token == 1) {
            return consumeString();
        }
        if (token != 0) {
            fail$default(this, Intrinsics.stringPlus("Expected beginning of the string, but got ", Character.valueOf(getSource().charAt(current))), 0, 2, null);
            throw new KotlinNothingValueException();
        }
        boolean usedAppend = false;
        while (AbstractJsonLexerKt.charToTokenClass(getSource().charAt(current)) == 0) {
            current++;
            if (current >= getSource().length()) {
                usedAppend = true;
                appendRange(this.currentPosition, current);
                int eof = prefetchOrEof(current);
                if (eof == -1) {
                    this.currentPosition = current;
                    return decodedString(0, 0);
                }
                current = eof;
            }
        }
        if (!usedAppend) {
            result = substring(this.currentPosition, current);
        } else {
            result = decodedString(this.currentPosition, current);
        }
        this.currentPosition = current;
        return result;
    }

    protected void appendRange(int fromIndex, int toIndex) {
        this.escapedString.append(getSource(), fromIndex, toIndex);
    }

    private final int appendEsc(int startPosition) {
        int currentPosition = prefetchOrEof(startPosition);
        if (currentPosition == -1) {
            fail$default(this, "Expected escape sequence to continue, got EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int currentPosition2 = currentPosition + 1;
        char currentChar = getSource().charAt(currentPosition);
        if (currentChar == 'u') {
            return appendHex(getSource(), currentPosition2);
        }
        char c = AbstractJsonLexerKt.escapeToChar(currentChar);
        if (c == 0) {
            fail$default(this, "Invalid escaped char '" + currentChar + '\'', 0, 2, null);
            throw new KotlinNothingValueException();
        }
        this.escapedString.append(c);
        return currentPosition2;
    }

    private final int appendHex(CharSequence source, int startPos) {
        if (startPos + 4 < source.length()) {
            this.escapedString.append((char) ((fromHexChar(source, startPos) << 12) + (fromHexChar(source, startPos + 1) << 8) + (fromHexChar(source, startPos + 2) << 4) + fromHexChar(source, startPos + 3)));
            return startPos + 4;
        }
        this.currentPosition = startPos;
        ensureHaveChars();
        if (this.currentPosition + 4 < source.length()) {
            return appendHex(source, this.currentPosition);
        }
        fail$default(this, "Unexpected EOF during unicode escape", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ void require$kotlinx_serialization_json$default(JsonReader jsonReader, boolean condition, int position, Function0 message, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: require");
        }
        if ((i & 2) != 0) {
            position = jsonReader.currentPosition;
        }
        Intrinsics.checkNotNullParameter(message, "message");
        if (!condition) {
            jsonReader.fail((String) message.invoke(), position);
            throw new KotlinNothingValueException();
        }
    }

    public final void require$kotlinx_serialization_json(boolean condition, int position, Function0<String> message) {
        Intrinsics.checkNotNullParameter(message, "message");
        if (!condition) {
            fail(message.invoke(), position);
            throw new KotlinNothingValueException();
        }
    }

    private final int fromHexChar(CharSequence source, int currentPosition) {
        char character = source.charAt(currentPosition);
        if ('0' <= character && character <= '9') {
            return character - '0';
        }
        if ('a' <= character && character <= 'f') {
            return (character - 'a') + 10;
        }
        if ('A' <= character && character <= 'F') {
            return (character - 'A') + 10;
        }
        fail$default(this, "Invalid toHexChar char '" + character + "' in unicode escape", 0, 2, null);
        throw new KotlinNothingValueException();
    }

    public final void skipElement(boolean allowLenientStrings) {
        List tokenStack = new ArrayList();
        byte lastToken = peekNextToken();
        if (lastToken != 8 && lastToken != 6) {
            consumeStringLenient();
            return;
        }
        while (true) {
            byte lastToken2 = peekNextToken();
            boolean z = true;
            if (lastToken2 != 1) {
                if (lastToken2 != 8 && lastToken2 != 6) {
                    z = false;
                }
                if (z) {
                    tokenStack.add(Byte.valueOf(lastToken2));
                } else if (lastToken2 == 9) {
                    if (((Number) CollectionsKt.last(tokenStack)).byteValue() != 8) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found ] instead of }", getSource());
                    }
                    CollectionsKt.removeLast(tokenStack);
                } else if (lastToken2 == 7) {
                    if (((Number) CollectionsKt.last(tokenStack)).byteValue() != 6) {
                        throw JsonExceptionsKt.JsonDecodingException(this.currentPosition, "found } instead of ]", getSource());
                    }
                    CollectionsKt.removeLast(tokenStack);
                } else if (lastToken2 == 10) {
                    fail$default(this, "Unexpected end of input due to malformed JSON during ignoring unknown keys", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                consumeNextToken();
                if (tokenStack.size() == 0) {
                    return;
                }
            } else if (allowLenientStrings) {
                consumeStringLenient();
            } else {
                consumeKeyString();
            }
        }
    }

    public String toString() {
        return "JsonReader(source='" + ((Object) getSource()) + "', currentPosition=" + this.currentPosition + ')';
    }

    public final void failOnUnknownKey(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        String processed = substring(0, this.currentPosition);
        int lastIndexOf = StringsKt.lastIndexOf$default((CharSequence) processed, key, 0, false, 6, (Object) null);
        fail("Encountered an unknown key '" + key + "'.\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.", lastIndexOf);
        throw new KotlinNothingValueException();
    }

    public static /* synthetic */ Void fail$default(JsonReader jsonReader, String str, int i, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: fail");
        }
        if ((i2 & 2) != 0) {
            i = jsonReader.currentPosition;
        }
        return jsonReader.fail(str, i);
    }

    public final Void fail(String message, int position) {
        Intrinsics.checkNotNullParameter(message, "message");
        throw JsonExceptionsKt.JsonDecodingException(position, message, getSource());
    }

    public final long consumeNumericLiteral() {
        boolean hasQuotation;
        long accumulator;
        Object obj;
        int i;
        int i2;
        int current = prefetchOrEof(skipWhitespaces());
        Object obj2 = null;
        int i3 = 2;
        int i4 = 0;
        if (current >= getSource().length() || current == -1) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(current) == '\"') {
            current++;
            if (current == getSource().length()) {
                fail$default(this, "EOF", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            hasQuotation = true;
        } else {
            hasQuotation = false;
        }
        long accumulator2 = 0;
        boolean isNegative = false;
        int start = current;
        int i5 = 1;
        while (true) {
            if (i5 == 0) {
                accumulator = accumulator2;
                break;
            }
            char ch = getSource().charAt(current);
            if (ch == '-') {
                if (current != start) {
                    fail$default(this, "Unexpected symbol '-' in numeric literal", i4, i3, obj2);
                    throw new KotlinNothingValueException();
                }
                isNegative = true;
                current++;
            } else {
                byte token = AbstractJsonLexerKt.charToTokenClass(ch);
                if (token != 0) {
                    accumulator = accumulator2;
                    break;
                }
                current++;
                i5 = current != getSource().length() ? 1 : i4;
                int digit = ch - '0';
                if (((digit < 0 || digit > 9) ? i4 : 1) == 0) {
                    fail$default(this, "Unexpected symbol '" + ch + "' in numeric literal", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                accumulator2 = (10 * accumulator2) - digit;
                if (accumulator2 > 0) {
                    fail$default(this, "Numeric value overflow", 0, 2, null);
                    throw new KotlinNothingValueException();
                }
                obj2 = null;
                i3 = 2;
                i4 = 0;
            }
        }
        if (start == current) {
            obj = null;
            i = 2;
            i2 = 0;
        } else {
            if (!isNegative || start != current - 1) {
                if (hasQuotation) {
                    if (i5 == 0) {
                        fail$default(this, "EOF", 0, 2, null);
                        throw new KotlinNothingValueException();
                    }
                    if (getSource().charAt(current) != '\"') {
                        fail$default(this, "Expected closing quotation mark", 0, 2, null);
                        throw new KotlinNothingValueException();
                    }
                    current++;
                }
                this.currentPosition = current;
                if (isNegative) {
                    return accumulator;
                }
                if (accumulator != Long.MIN_VALUE) {
                    return -accumulator;
                }
                fail$default(this, "Numeric value overflow", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            obj = null;
            i = 2;
            i2 = 0;
        }
        fail$default(this, "Expected numeric literal", i2, i, obj);
        throw new KotlinNothingValueException();
    }

    public final boolean consumeBoolean() {
        return consumeBoolean(skipWhitespaces());
    }

    public final boolean consumeBooleanLenient() {
        boolean hasQuotation;
        int current = skipWhitespaces();
        if (current == getSource().length()) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        if (getSource().charAt(current) == '\"') {
            current++;
            hasQuotation = true;
        } else {
            hasQuotation = false;
        }
        boolean result = consumeBoolean(current);
        if (hasQuotation) {
            if (this.currentPosition == getSource().length()) {
                fail$default(this, "EOF", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            if (getSource().charAt(this.currentPosition) != '\"') {
                fail$default(this, "Expected closing quotation mark", 0, 2, null);
                throw new KotlinNothingValueException();
            }
            this.currentPosition++;
        }
        return result;
    }

    private final boolean consumeBoolean(int start) {
        int current = prefetchOrEof(start);
        if (current >= getSource().length() || current == -1) {
            fail$default(this, "EOF", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int current2 = current + 1;
        int charAt = getSource().charAt(current) | 32;
        if (charAt == 116) {
            consumeBooleanLiteral("rue", current2);
            return true;
        }
        if (charAt == 102) {
            consumeBooleanLiteral("alse", current2);
            return false;
        }
        fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, 2, null);
        throw new KotlinNothingValueException();
    }

    private final void consumeBooleanLiteral(String literalSuffix, int current) {
        if (getSource().length() - current < literalSuffix.length()) {
            fail$default(this, "Unexpected end of boolean literal", 0, 2, null);
            throw new KotlinNothingValueException();
        }
        int length = literalSuffix.length() - 1;
        if (length >= 0) {
            int i = 0;
            do {
                int i2 = i;
                i++;
                char expected = literalSuffix.charAt(i2);
                char actual = getSource().charAt(current + i2);
                if (expected != (actual | ' ')) {
                    fail$default(this, "Expected valid boolean literal prefix, but had '" + consumeStringLenient() + '\'', 0, 2, null);
                    throw new KotlinNothingValueException();
                }
            } while (i <= length);
        }
        this.currentPosition = literalSuffix.length() + current;
    }
}
