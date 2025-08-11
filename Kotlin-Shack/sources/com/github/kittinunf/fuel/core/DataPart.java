package com.github.kittinunf.fuel.core;

import java.io.File;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: DataPart.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/github/kittinunf/fuel/core/DataPart;", "", "file", "Ljava/io/File;", "name", "", "type", "(Ljava/io/File;Ljava/lang/String;Ljava/lang/String;)V", "getFile", "()Ljava/io/File;", "getName", "()Ljava/lang/String;", "getType", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final /* data */ class DataPart {

    @NotNull
    private final File file;

    @NotNull
    private final String name;

    @NotNull
    private final String type;

    @NotNull
    public static /* bridge */ /* synthetic */ DataPart copy$default(DataPart dataPart, File file, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            file = dataPart.file;
        }
        if ((i & 2) != 0) {
            str = dataPart.name;
        }
        if ((i & 4) != 0) {
            str2 = dataPart.type;
        }
        return dataPart.copy(file, str, str2);
    }

    @NotNull
    /* renamed from: component1, reason: from getter */
    public final File getFile() {
        return this.file;
    }

    @NotNull
    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    @NotNull
    /* renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    @NotNull
    public final DataPart copy(@NotNull File file, @NotNull String name, @NotNull String type) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new DataPart(file, name, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DataPart)) {
            return false;
        }
        DataPart dataPart = (DataPart) other;
        return Intrinsics.areEqual(this.file, dataPart.file) && Intrinsics.areEqual(this.name, dataPart.name) && Intrinsics.areEqual(this.type, dataPart.type);
    }

    public int hashCode() {
        File file = this.file;
        int hashCode = (file != null ? file.hashCode() : 0) * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.type;
        return hashCode2 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "DataPart(file=" + this.file + ", name=" + this.name + ", type=" + this.type + ")";
    }

    public DataPart(@NotNull File file, @NotNull String name, @NotNull String type) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.file = file;
        this.name = name;
        this.type = type;
    }

    @NotNull
    public final File getFile() {
        return this.file;
    }

    public /* synthetic */ DataPart(File file, String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i & 2) != 0 ? FilesKt.getNameWithoutExtension(file) : str, (i & 4) != 0 ? "" : str2);
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getType() {
        return this.type;
    }
}
