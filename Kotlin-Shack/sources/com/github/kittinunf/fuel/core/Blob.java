package com.github.kittinunf.fuel.core;

import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Blob.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/github/kittinunf/fuel/core/Blob;", "", "name", "", "length", "", "inputStream", "Lkotlin/Function0;", "Ljava/io/InputStream;", "(Ljava/lang/String;JLkotlin/jvm/functions/Function0;)V", "getInputStream", "()Lkotlin/jvm/functions/Function0;", "getLength", "()J", "getName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final /* data */ class Blob {

    @NotNull
    private final Function0<InputStream> inputStream;
    private final long length;

    @NotNull
    private final String name;

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static /* bridge */ /* synthetic */ Blob copy$default(Blob blob, String str, long j, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            str = blob.name;
        }
        if ((i & 2) != 0) {
            j = blob.length;
        }
        if ((i & 4) != 0) {
            function0 = blob.inputStream;
        }
        return blob.copy(str, j, function0);
    }

    @NotNull
    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final long getLength() {
        return this.length;
    }

    @NotNull
    public final Function0<InputStream> component3() {
        return this.inputStream;
    }

    @NotNull
    public final Blob copy(@NotNull String name, long length, @NotNull Function0<? extends InputStream> inputStream) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        return new Blob(name, length, inputStream);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Blob) {
            Blob blob = (Blob) other;
            if (Intrinsics.areEqual(this.name, blob.name)) {
                if ((this.length == blob.length) && Intrinsics.areEqual(this.inputStream, blob.inputStream)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = str != null ? str.hashCode() : 0;
        long j = this.length;
        int i = ((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31;
        Function0<InputStream> function0 = this.inputStream;
        return i + (function0 != null ? function0.hashCode() : 0);
    }

    public String toString() {
        return "Blob(name=" + this.name + ", length=" + this.length + ", inputStream=" + this.inputStream + ")";
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Blob(@NotNull String name, long length, @NotNull Function0<? extends InputStream> inputStream) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        this.name = name;
        this.length = length;
        this.inputStream = inputStream;
    }

    public /* synthetic */ Blob(String str, long j, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, j, function0);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    public final long getLength() {
        return this.length;
    }

    @NotNull
    public final Function0<InputStream> getInputStream() {
        return this.inputStream;
    }
}
