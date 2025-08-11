package kotlinx.serialization.json.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.viewbinding.BuildConfig;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: JsonStringBuilder.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0010\u0018\u00002\u00020\u0001B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\u0013J \u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0007H\u0002J\u0018\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0014J\b\u0010\u001d\u001a\u00020\rH\u0016J\b\u0010\u001e\u001a\u00020\u0013H\u0016R\u0012\u0010\u0003\u001a\u00020\u00048\u0004@\u0004X\u0085\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u001f"}, d2 = {"Lkotlinx/serialization/json/internal/JsonStringBuilder;", "", "()V", "array", "", "([C)V", "size", "", "getSize", "()I", "setSize", "(I)V", "append", "", "ch", "", "value", "", TypedValues.Custom.S_STRING, "", "appendQuoted", "appendStringSlowPath", "firstEscapedChar", "currentSize", "ensureAdditionalCapacity", "expected", "ensureTotalCapacity", "oldSize", "additional", BuildConfig.BUILD_TYPE, "toString", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public class JsonStringBuilder {
    protected char[] array;
    private int size;

    public JsonStringBuilder(char[] array) {
        Intrinsics.checkNotNullParameter(array, "array");
        this.array = array;
    }

    public JsonStringBuilder() {
        this(CharArrayPool.INSTANCE.take());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int getSize() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setSize(int i) {
        this.size = i;
    }

    public final void append(long value) {
        append(String.valueOf(value));
    }

    public final void append(char ch) {
        ensureAdditionalCapacity(1);
        char[] cArr = this.array;
        int i = this.size;
        this.size = i + 1;
        cArr[i] = ch;
    }

    public final void append(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        int length = string.length();
        ensureAdditionalCapacity(length);
        string.getChars(0, string.length(), this.array, this.size);
        this.size += length;
    }

    public final void appendQuoted(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        ensureAdditionalCapacity(string.length() + 2);
        char[] arr = this.array;
        int sz = this.size;
        int sz2 = sz + 1;
        arr[sz] = '\"';
        int length = string.length();
        string.getChars(0, length, arr, sz2);
        int i = sz2 + length;
        if (sz2 < i) {
            int i2 = sz2;
            do {
                int i3 = i2;
                i2++;
                char c = arr[i3];
                if (c < StringOpsKt.getESCAPE_MARKERS().length && StringOpsKt.getESCAPE_MARKERS()[c] != 0) {
                    appendStringSlowPath(i3 - sz2, i3, string);
                    return;
                }
            } while (i2 < i);
        }
        int sz3 = sz2 + length;
        arr[sz3] = '\"';
        this.size = sz3 + 1;
    }

    private final void appendStringSlowPath(int firstEscapedChar, int currentSize, String string) {
        int sz = currentSize;
        int length = string.length();
        if (firstEscapedChar < length) {
            int i = firstEscapedChar;
            do {
                int i2 = i;
                i++;
                int sz2 = ensureTotalCapacity(sz, 2);
                int ch = string.charAt(i2);
                if (ch < StringOpsKt.getESCAPE_MARKERS().length) {
                    byte marker = StringOpsKt.getESCAPE_MARKERS()[ch];
                    if (marker == 0) {
                        this.array[sz2] = (char) ch;
                        sz = sz2 + 1;
                    } else if (marker == 1) {
                        String escapedString = StringOpsKt.getESCAPE_STRINGS()[ch];
                        Intrinsics.checkNotNull(escapedString);
                        int sz3 = ensureTotalCapacity(sz2, escapedString.length());
                        char[] cArr = this.array;
                        int length2 = escapedString.length();
                        if (escapedString == null) {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                        escapedString.getChars(0, length2, cArr, sz3);
                        sz = sz3 + escapedString.length();
                        this.size = sz;
                    } else {
                        char[] cArr2 = this.array;
                        cArr2[sz2] = AbstractJsonLexerKt.STRING_ESC;
                        cArr2[sz2 + 1] = (char) marker;
                        sz = sz2 + 2;
                        this.size = sz;
                    }
                } else {
                    this.array[sz2] = (char) ch;
                    sz = sz2 + 1;
                }
            } while (i < length);
        }
        int sz4 = ensureTotalCapacity(sz, 1);
        this.array[sz4] = '\"';
        this.size = sz4 + 1;
    }

    public String toString() {
        return new String(this.array, 0, this.size);
    }

    private final void ensureAdditionalCapacity(int expected) {
        ensureTotalCapacity(this.size, expected);
    }

    protected int ensureTotalCapacity(int oldSize, int additional) {
        int newSize = oldSize + additional;
        char[] cArr = this.array;
        if (cArr.length <= newSize) {
            char[] copyOf = Arrays.copyOf(cArr, RangesKt.coerceAtLeast(newSize, oldSize * 2));
            Intrinsics.checkNotNullExpressionValue(copyOf, "java.util.Arrays.copyOf(this, newSize)");
            this.array = copyOf;
        }
        return oldSize;
    }

    public void release() {
        CharArrayPool.INSTANCE.release(this.array);
    }
}
