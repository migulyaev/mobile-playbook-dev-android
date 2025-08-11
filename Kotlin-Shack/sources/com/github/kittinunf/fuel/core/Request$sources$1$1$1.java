package com.github.kittinunf.fuel.core;

import java.io.File;
import java.io.FileInputStream;
import kotlin.Metadata;
import kotlin.io.FilesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import org.jetbrains.annotations.NotNull;

/* compiled from: Request.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/FileInputStream;", "invoke"}, k = 3, mv = {1, 1, 9})
/* loaded from: classes.dex */
final class Request$sources$1$1$1 extends FunctionReference implements Function0<FileInputStream> {
    Request$sources$1$1$1(File file) {
        super(0, file);
    }

    @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
    public final String getName() {
        return "inputStream";
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinPackage(FilesKt.class, "fuel");
    }

    @Override // kotlin.jvm.internal.CallableReference
    public final String getSignature() {
        return "inputStream(Ljava/io/File;)Ljava/io/FileInputStream;";
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // kotlin.jvm.functions.Function0
    @NotNull
    public final FileInputStream invoke() {
        return new FileInputStream((File) this.receiver);
    }
}
