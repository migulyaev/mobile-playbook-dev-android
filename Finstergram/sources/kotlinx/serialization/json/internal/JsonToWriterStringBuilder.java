package kotlinx.serialization.json.internal;

import androidx.viewbinding.BuildConfig;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.Charsets;

/* compiled from: JsonToWriterStringBuilder.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006B\r\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bH\u0014J\u0012\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000bH\u0002J\b\u0010\u0011\u001a\u00020\u000fH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lkotlinx/serialization/json/internal/JsonToWriterStringBuilder;", "Lkotlinx/serialization/json/internal/JsonStringBuilder;", "os", "Ljava/io/OutputStream;", "charset", "Ljava/nio/charset/Charset;", "(Ljava/io/OutputStream;Ljava/nio/charset/Charset;)V", "writer", "Ljava/io/Writer;", "(Ljava/io/Writer;)V", "ensureTotalCapacity", "", "oldSize", "additional", "flush", "", "sz", BuildConfig.BUILD_TYPE, "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class JsonToWriterStringBuilder extends JsonStringBuilder {
    private final Writer writer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonToWriterStringBuilder(Writer writer) {
        super(new char[16384]);
        Intrinsics.checkNotNullParameter(writer, "writer");
        this.writer = writer;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public JsonToWriterStringBuilder(java.io.OutputStream r4, java.nio.charset.Charset r5) {
        /*
            r3 = this;
            java.lang.String r0 = "os"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "charset"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter
            r0.<init>(r4, r5)
            java.io.Writer r0 = (java.io.Writer) r0
            boolean r1 = r0 instanceof java.io.BufferedWriter
            if (r1 == 0) goto L18
            java.io.BufferedWriter r0 = (java.io.BufferedWriter) r0
            goto L20
        L18:
            java.io.BufferedWriter r1 = new java.io.BufferedWriter
            r2 = 262144(0x40000, float:3.67342E-40)
            r1.<init>(r0, r2)
            r0 = r1
        L20:
            java.io.Writer r0 = (java.io.Writer) r0
            r3.<init>(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonToWriterStringBuilder.<init>(java.io.OutputStream, java.nio.charset.Charset):void");
    }

    public /* synthetic */ JsonToWriterStringBuilder(OutputStream outputStream, Charset charset, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(outputStream, (i & 2) != 0 ? Charsets.UTF_8 : charset);
    }

    @Override // kotlinx.serialization.json.internal.JsonStringBuilder
    protected int ensureTotalCapacity(int oldSize, int additional) {
        int requiredSize = oldSize + additional;
        int currentSize = this.array.length;
        if (currentSize <= requiredSize) {
            flush(oldSize);
            if (additional > currentSize) {
                this.array = new char[RangesKt.coerceAtLeast(requiredSize, currentSize * 2)];
                return 0;
            }
            return 0;
        }
        return oldSize;
    }

    static /* synthetic */ void flush$default(JsonToWriterStringBuilder jsonToWriterStringBuilder, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = jsonToWriterStringBuilder.getSize();
        }
        jsonToWriterStringBuilder.flush(i);
    }

    private final void flush(int sz) {
        this.writer.write(this.array, 0, sz);
        setSize(0);
    }

    @Override // kotlinx.serialization.json.internal.JsonStringBuilder
    public void release() {
        flush$default(this, 0, 1, null);
        this.writer.flush();
    }
}
