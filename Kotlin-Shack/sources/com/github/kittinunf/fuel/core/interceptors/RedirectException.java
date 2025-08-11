package com.github.kittinunf.fuel.core.interceptors;

import kotlin.Metadata;

/* compiled from: RedirectionInterceptor.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/github/kittinunf/fuel/core/interceptors/RedirectException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class RedirectException extends Exception {
    public RedirectException() {
        super("Redirection fail, not found URL to redirect to");
    }
}
