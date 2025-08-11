package com.github.kittinunf.fuel.core;

import kotlin.Metadata;
import kotlin.TypeCastException;
import org.jetbrains.annotations.NotNull;

/* compiled from: Environment.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0002\u001a\u00020\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"AndroidEnvironmentClass", "", "createEnvironment", "Lcom/github/kittinunf/fuel/core/Environment;", "fuel"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class EnvironmentKt {

    @NotNull
    public static final String AndroidEnvironmentClass = "com.github.kittinunf.fuel.android.util.AndroidEnvironment";

    @NotNull
    public static final Environment createEnvironment() {
        try {
            Object newInstance = Class.forName(AndroidEnvironmentClass).newInstance();
            if (newInstance == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.github.kittinunf.fuel.core.Environment");
            }
            return (Environment) newInstance;
        } catch (ClassNotFoundException e) {
            return new DefaultEnvironment();
        }
    }
}
