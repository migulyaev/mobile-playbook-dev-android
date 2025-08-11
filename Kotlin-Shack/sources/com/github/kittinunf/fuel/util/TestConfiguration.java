package com.github.kittinunf.fuel.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: TestConfiguration.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0017\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u000e\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003J\u000e\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\rJ\t\u0010\u0017\u001a\u00020\u0006HÆ\u0003J0\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0010\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000f¨\u0006\u001f"}, d2 = {"Lcom/github/kittinunf/fuel/util/TestConfiguration;", "", "timeout", "", "timeoutRead", "blocking", "", "(Ljava/lang/Integer;Ljava/lang/Integer;Z)V", "getBlocking", "()Z", "setBlocking", "(Z)V", "getTimeout", "()Ljava/lang/Integer;", "setTimeout", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getTimeoutRead", "setTimeoutRead", "coerceTimeout", "coerceTimeoutRead", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Z)Lcom/github/kittinunf/fuel/util/TestConfiguration;", "equals", "other", "hashCode", "toString", "", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final /* data */ class TestConfiguration {
    private boolean blocking;

    @Nullable
    private Integer timeout;

    @Nullable
    private Integer timeoutRead;

    public TestConfiguration() {
        this(null, null, false, 7, null);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ TestConfiguration copy$default(TestConfiguration testConfiguration, Integer num, Integer num2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            num = testConfiguration.timeout;
        }
        if ((i & 2) != 0) {
            num2 = testConfiguration.timeoutRead;
        }
        if ((i & 4) != 0) {
            z = testConfiguration.blocking;
        }
        return testConfiguration.copy(num, num2, z);
    }

    @Nullable
    /* renamed from: component1, reason: from getter */
    public final Integer getTimeout() {
        return this.timeout;
    }

    @Nullable
    /* renamed from: component2, reason: from getter */
    public final Integer getTimeoutRead() {
        return this.timeoutRead;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getBlocking() {
        return this.blocking;
    }

    @NotNull
    public final TestConfiguration copy(@Nullable Integer timeout, @Nullable Integer timeoutRead, boolean blocking) {
        return new TestConfiguration(timeout, timeoutRead, blocking);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof TestConfiguration) {
            TestConfiguration testConfiguration = (TestConfiguration) other;
            if (Intrinsics.areEqual(this.timeout, testConfiguration.timeout) && Intrinsics.areEqual(this.timeoutRead, testConfiguration.timeoutRead)) {
                if (this.blocking == testConfiguration.blocking) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Integer num = this.timeout;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        Integer num2 = this.timeoutRead;
        int hashCode2 = (hashCode + (num2 != null ? num2.hashCode() : 0)) * 31;
        boolean z = this.blocking;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "TestConfiguration(timeout=" + this.timeout + ", timeoutRead=" + this.timeoutRead + ", blocking=" + this.blocking + ")";
    }

    public TestConfiguration(@Nullable Integer timeout, @Nullable Integer timeoutRead, boolean blocking) {
        this.timeout = timeout;
        this.timeoutRead = timeoutRead;
        this.blocking = blocking;
    }

    public /* synthetic */ TestConfiguration(Integer num, Integer num2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (Integer) null : num2, (i & 4) != 0 ? true : z);
    }

    @Nullable
    public final Integer getTimeout() {
        return this.timeout;
    }

    public final void setTimeout(@Nullable Integer num) {
        this.timeout = num;
    }

    @Nullable
    public final Integer getTimeoutRead() {
        return this.timeoutRead;
    }

    public final void setTimeoutRead(@Nullable Integer num) {
        this.timeoutRead = num;
    }

    public final boolean getBlocking() {
        return this.blocking;
    }

    public final void setBlocking(boolean z) {
        this.blocking = z;
    }

    public final int coerceTimeout(int timeout) {
        Integer num = this.timeout;
        if (num == null) {
            return timeout;
        }
        int it = num.intValue();
        if (it == -1) {
            return Integer.MAX_VALUE;
        }
        return it;
    }

    public final int coerceTimeoutRead(int timeout) {
        Integer num = this.timeoutRead;
        if (num == null) {
            return timeout;
        }
        int it = num.intValue();
        if (it == -1) {
            return Integer.MAX_VALUE;
        }
        return it;
    }
}
