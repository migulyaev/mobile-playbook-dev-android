package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Composers.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\n\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\tH\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\nH\u0016J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lkotlinx/serialization/json/internal/ComposerForUnsignedNumbers;", "Lkotlinx/serialization/json/internal/Composer;", "sb", "Lkotlinx/serialization/json/internal/JsonStringBuilder;", "(Lkotlinx/serialization/json/internal/JsonStringBuilder;)V", "print", "", "v", "", "", "", "", "kotlinx-serialization-json"}, k = 1, mv = {1, 5, 1}, xi = 48)
/* loaded from: classes.dex */
public final class ComposerForUnsignedNumbers extends Composer {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposerForUnsignedNumbers(JsonStringBuilder sb) {
        super(sb);
        Intrinsics.checkNotNullParameter(sb, "sb");
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(int v) {
        super.print(UInt.m176toStringimpl(UInt.m131constructorimpl(v)));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(long v) {
        super.print(ULong.m254toStringimpl(ULong.m209constructorimpl(v)));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(byte v) {
        super.print(UByte.m98toStringimpl(UByte.m55constructorimpl(v)));
    }

    @Override // kotlinx.serialization.json.internal.Composer
    public void print(short v) {
        super.print(UShort.m358toStringimpl(UShort.m315constructorimpl(v)));
    }
}
