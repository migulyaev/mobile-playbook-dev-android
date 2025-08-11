package com.github.kittinunf.fuel.core;

import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Environment.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/github/kittinunf/fuel/core/DefaultEnvironment;", "Lcom/github/kittinunf/fuel/core/Environment;", "()V", "callbackExecutor", "Ljava/util/concurrent/Executor;", "getCallbackExecutor", "()Ljava/util/concurrent/Executor;", "setCallbackExecutor", "(Ljava/util/concurrent/Executor;)V", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class DefaultEnvironment implements Environment {

    @NotNull
    private Executor callbackExecutor = new Executor() { // from class: com.github.kittinunf.fuel.core.DefaultEnvironment$callbackExecutor$1
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable command) {
            if (command != null) {
                command.run();
            }
        }
    };

    @Override // com.github.kittinunf.fuel.core.Environment
    @NotNull
    public Executor getCallbackExecutor() {
        return this.callbackExecutor;
    }

    @Override // com.github.kittinunf.fuel.core.Environment
    public void setCallbackExecutor(@NotNull Executor executor) {
        Intrinsics.checkParameterIsNotNull(executor, "<set-?>");
        this.callbackExecutor = executor;
    }
}
