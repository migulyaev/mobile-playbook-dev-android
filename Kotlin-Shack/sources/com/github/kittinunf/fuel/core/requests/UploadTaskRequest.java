package com.github.kittinunf.fuel.core.requests;

import com.github.kittinunf.fuel.core.Blob;
import com.github.kittinunf.fuel.core.Request;
import java.io.OutputStream;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UploadTaskRequest.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R(\u0010\u0005\u001a\u001c\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R2\u0010\u0012\u001a\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\fX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/github/kittinunf/fuel/core/requests/UploadTaskRequest;", "Lcom/github/kittinunf/fuel/core/requests/TaskRequest;", "request", "Lcom/github/kittinunf/fuel/core/Request;", "(Lcom/github/kittinunf/fuel/core/Request;)V", "bodyCallBack", "Lkotlin/Function3;", "Ljava/io/OutputStream;", "", "boundary", "", "progressCallback", "Lkotlin/Function2;", "", "getProgressCallback", "()Lkotlin/jvm/functions/Function2;", "setProgressCallback", "(Lkotlin/jvm/functions/Function2;)V", "sourceCallback", "Ljava/net/URL;", "", "Lcom/github/kittinunf/fuel/core/Blob;", "getSourceCallback", "setSourceCallback", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class UploadTaskRequest extends TaskRequest {
    private Function3<? super Request, ? super OutputStream, ? super Long, Long> bodyCallBack;
    private final String boundary;

    @Nullable
    private Function2<? super Long, ? super Long, Unit> progressCallback;

    @NotNull
    public Function2<? super Request, ? super URL, ? extends Iterable<Blob>> sourceCallback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadTaskRequest(@NotNull Request request) {
        super(request);
        Intrinsics.checkParameterIsNotNull(request, "request");
        this.bodyCallBack = new UploadTaskRequest$bodyCallBack$1(this);
        this.boundary = UploadTaskRequestKt.retrieveBoundaryInfo(request);
        request.setBodyCallback(this.bodyCallBack);
    }

    @Nullable
    public final Function2<Long, Long, Unit> getProgressCallback() {
        return this.progressCallback;
    }

    public final void setProgressCallback(@Nullable Function2<? super Long, ? super Long, Unit> function2) {
        this.progressCallback = function2;
    }

    @NotNull
    public final Function2<Request, URL, Iterable<Blob>> getSourceCallback() {
        Function2 function2 = this.sourceCallback;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("sourceCallback");
        }
        return function2;
    }

    public final void setSourceCallback(@NotNull Function2<? super Request, ? super URL, ? extends Iterable<Blob>> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.sourceCallback = function2;
    }
}
