package com.github.kittinunf.fuel.util;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: SameThreadExecutorService.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0017J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0012H\u0016R\u0012\u0010\u0003\u001a\u00020\u00048\u0002@\u0002X\u0083\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/github/kittinunf/fuel/util/SameThreadExecutorService;", "Ljava/util/concurrent/AbstractExecutorService;", "()V", "terminated", "", "awaitTermination", "theTimeout", "", "theUnit", "Ljava/util/concurrent/TimeUnit;", "execute", "", "theCommand", "Ljava/lang/Runnable;", "isShutdown", "isTerminated", "shutdown", "shutdownNow", "", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class SameThreadExecutorService extends AbstractExecutorService {
    private volatile boolean terminated;

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.terminated = true;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.terminated;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.terminated;
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long theTimeout, @NotNull TimeUnit theUnit) throws InterruptedException {
        Intrinsics.checkParameterIsNotNull(theUnit, "theUnit");
        shutdown();
        return this.terminated;
    }

    @Override // java.util.concurrent.ExecutorService
    @NotNull
    public List<Runnable> shutdownNow() {
        return CollectionsKt.emptyList();
    }

    @Override // java.util.concurrent.Executor
    public void execute(@NotNull Runnable theCommand) {
        Intrinsics.checkParameterIsNotNull(theCommand, "theCommand");
        theCommand.run();
    }
}
