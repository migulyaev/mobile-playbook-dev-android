package com.github.kittinunf.fuel.core;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: FuelError.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B%\u0012\n\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0003\u001a\u00060\u0001j\u0002`\u0002¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0011"}, d2 = {"Lcom/github/kittinunf/fuel/core/FuelError;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "errorData", "", "response", "Lcom/github/kittinunf/fuel/core/Response;", "(Ljava/lang/Exception;[BLcom/github/kittinunf/fuel/core/Response;)V", "getErrorData", "()[B", "getException", "()Ljava/lang/Exception;", "getResponse", "()Lcom/github/kittinunf/fuel/core/Response;", "toString", "", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class FuelError extends Exception {

    @NotNull
    private final byte[] errorData;

    @NotNull
    private final Exception exception;

    @NotNull
    private final Response response;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FuelError(@NotNull Exception exception, @NotNull byte[] errorData, @NotNull Response response) {
        super(exception);
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        Intrinsics.checkParameterIsNotNull(errorData, "errorData");
        Intrinsics.checkParameterIsNotNull(response, "response");
        this.exception = exception;
        this.errorData = errorData;
        this.response = response;
    }

    public /* synthetic */ FuelError(Exception exc, byte[] bArr, Response response, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(exc, (i & 2) != 0 ? new byte[0] : bArr, (i & 4) != 0 ? Response.INSTANCE.error() : response);
    }

    @NotNull
    public final byte[] getErrorData() {
        return this.errorData;
    }

    @NotNull
    public final Exception getException() {
        return this.exception;
    }

    @NotNull
    public final Response getResponse() {
        return this.response;
    }

    @Override // java.lang.Throwable
    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append(this.exception.getClass().getCanonicalName());
        sb.append(": ");
        String message = this.exception.getMessage();
        if (message == null) {
            message = "<no message>";
        }
        sb.append(message);
        return sb.toString();
    }
}
