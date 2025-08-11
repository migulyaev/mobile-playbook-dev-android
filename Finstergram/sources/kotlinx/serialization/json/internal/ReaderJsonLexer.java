package kotlinx.serialization.json.internal;

import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* compiled from: JsonLexerJvm.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\f\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\u0017\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0014H\u0014J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0016J\u0018\u0010 \u001a\u00020\u00142\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0014H\u0016J\u0010\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u0014H\u0016J\u0010\u0010&\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u0014H\u0002J\u0018\u0010(\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\u00142\u0006\u0010)\u001a\u00020\u0014H\u0016J\b\u0010*\u001a\u00020\u001aH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r@VX\u0094\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lkotlinx/serialization/json/internal/ReaderJsonLexer;", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "i", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V", "reader", "Ljava/io/Reader;", "_source", "", "(Ljava/io/Reader;[C)V", "<set-?>", "", "source", "getSource", "()Ljava/lang/CharSequence;", "setSource", "(Ljava/lang/CharSequence;)V", "threshold", "", "appendRange", "", "fromIndex", "toIndex", "canConsumeValue", "", "consumeKeyString", "", "consumeNextToken", "", "ensureHaveChars", "indexOf", "char", "", "startPos", "prefetchOrEof", "position", "preload", "spaceLeft", "substring", "endPos", "tryConsumeComma", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ReaderJsonLexer extends JsonReader {
    private char[] _source;
    private final Reader reader;
    private CharSequence source;
    private int threshold;

    public /* synthetic */ ReaderJsonLexer(Reader reader, char[] cArr, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reader, (i & 2) != 0 ? new char[16384] : cArr);
    }

    public ReaderJsonLexer(Reader reader, char[] _source) {
        Intrinsics.checkNotNullParameter(reader, "reader");
        Intrinsics.checkNotNullParameter(_source, "_source");
        this.reader = reader;
        this._source = _source;
        this.threshold = 128;
        this.source = new ArrayAsSequence(this._source);
        preload(0);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ReaderJsonLexer(java.io.InputStream r4, java.nio.charset.Charset r5) {
        /*
            r3 = this;
            java.lang.String r0 = "i"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.io.InputStreamReader r0 = new java.io.InputStreamReader
            r0.<init>(r4, r5)
            java.io.Reader r0 = (java.io.Reader) r0
            boolean r1 = r0 instanceof java.io.BufferedReader
            if (r1 == 0) goto L18
            java.io.BufferedReader r0 = (java.io.BufferedReader) r0
            goto L20
        L18:
            java.io.BufferedReader r1 = new java.io.BufferedReader
            r2 = 262144(0x40000, float:3.67342E-40)
            r1.<init>(r0, r2)
            r0 = r1
        L20:
            java.io.Reader r0 = (java.io.Reader) r0
            r1 = 2
            r2 = 0
            r3.<init>(r0, r2, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.ReaderJsonLexer.<init>(java.io.InputStream, java.nio.charset.Charset):void");
    }

    public /* synthetic */ ReaderJsonLexer(InputStream inputStream, Charset charset, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(inputStream, (i & 2) != 0 ? Charsets.UTF_8 : charset);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    protected CharSequence getSource() {
        return this.source;
    }

    public void setSource(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "<set-?>");
        this.source = charSequence;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public boolean tryConsumeComma() {
        int current = skipWhitespaces();
        if (current >= getSource().length() || current == -1 || getSource().charAt(current) != ',') {
            return false;
        }
        this.currentPosition++;
        int i = this.currentPosition;
        return true;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public boolean canConsumeValue() {
        ensureHaveChars();
        int current = this.currentPosition;
        while (true) {
            int current2 = prefetchOrEof(current);
            if (current2 != -1) {
                char c = getSource().charAt(current2);
                if (c == ' ' || c == '\n' || c == '\r' || c == '\t') {
                    current = current2 + 1;
                } else {
                    this.currentPosition = current2;
                    return isValidValueStart(c);
                }
            } else {
                this.currentPosition = current2;
                return false;
            }
        }
    }

    private final void preload(int spaceLeft) {
        char[] buffer = this._source;
        System.arraycopy(buffer, this.currentPosition, buffer, 0, spaceLeft);
        int read = spaceLeft;
        int sizeTotal = this._source.length;
        while (true) {
            if (read == sizeTotal) {
                break;
            }
            int actual = this.reader.read(buffer, read, sizeTotal - read);
            if (actual == -1) {
                char[] copyOf = Arrays.copyOf(this._source, read);
                Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
                this._source = copyOf;
                setSource(new ArrayAsSequence(this._source));
                this.threshold = -1;
                break;
            }
            read += actual;
        }
        this.currentPosition = 0;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public int prefetchOrEof(int position) {
        if (position < getSource().length()) {
            return position;
        }
        this.currentPosition = position;
        ensureHaveChars();
        if (this.currentPosition == 0) {
            return getSource().length() == 0 ? -1 : 0;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public byte consumeNextToken() {
        ensureHaveChars();
        CharSequence source = getSource();
        int cpos = this.currentPosition;
        while (true) {
            int cpos2 = prefetchOrEof(cpos);
            if (cpos2 != -1) {
                int cpos3 = cpos2 + 1;
                char ch = source.charAt(cpos2);
                byte tc = AbstractJsonLexerKt.charToTokenClass(ch);
                if (tc != 3) {
                    this.currentPosition = cpos3;
                    return tc;
                }
                cpos = cpos3;
            } else {
                this.currentPosition = cpos2;
                return (byte) 10;
            }
        }
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public void ensureHaveChars() {
        int cur = this.currentPosition;
        int oldSize = this._source.length;
        int spaceLeft = oldSize - cur;
        if (spaceLeft > this.threshold) {
            return;
        }
        preload(spaceLeft);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public String consumeKeyString() {
        consumeNextToken('\"');
        int current = this.currentPosition;
        int closingQuote = indexOf('\"', current);
        if (closingQuote == -1) {
            int current2 = prefetchOrEof(current);
            if (current2 == -1) {
                fail$kotlinx_serialization_json((byte) 1);
                throw new KotlinNothingValueException();
            }
            return consumeString(getSource(), this.currentPosition, current2);
        }
        if (current < closingQuote) {
            int i = current;
            do {
                int i2 = i;
                i++;
                if (getSource().charAt(i2) == '\\') {
                    return consumeString(getSource(), this.currentPosition, i2);
                }
            } while (i < closingQuote);
        }
        this.currentPosition = closingQuote + 1;
        return substring(current, closingQuote);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public int indexOf(char r6, int startPos) {
        char[] src = this._source;
        int length = src.length;
        if (startPos < length) {
            int i = startPos;
            do {
                int i2 = i;
                i++;
                if (src[i2] == r6) {
                    return i2;
                }
            } while (i < length);
            return -1;
        }
        return -1;
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    public String substring(int startPos, int endPos) {
        return new String(this._source, startPos, endPos - startPos);
    }

    @Override // kotlinx.serialization.json.internal.JsonReader
    protected void appendRange(int fromIndex, int toIndex) {
        getEscapedString().append(this._source, fromIndex, toIndex - fromIndex);
    }
}
