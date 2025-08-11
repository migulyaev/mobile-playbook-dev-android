package com.github.kittinunf.fuel.util;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.properties.ReadWriteProperty;
import org.jetbrains.annotations.NotNull;

/* compiled from: Delegates.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a*\u0010\u0000\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u0005H\u0000¨\u0006\u0006"}, d2 = {"readWriteLazy", "Lkotlin/properties/ReadWriteProperty;", "", "T", "initializer", "Lkotlin/Function0;", "fuel"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class DelegatesKt {
    @NotNull
    public static final <T> ReadWriteProperty<Object, T> readWriteLazy(@NotNull Function0<? extends T> initializer) {
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        return new ReadWriteLazyVal(initializer);
    }
}
