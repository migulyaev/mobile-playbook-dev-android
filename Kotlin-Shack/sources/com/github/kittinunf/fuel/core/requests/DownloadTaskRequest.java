package com.github.kittinunf.fuel.core.requests;

import android.support.v4.app.NotificationCompat;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.github.kittinunf.fuel.util.InputStreamsKt;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: DownloadTaskRequest.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0013\u001a\u00020\u0007H\u0016R,\u0010\u0005\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR.\u0010\u000e\u001a\u0016\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u000b\"\u0004\b\u0012\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/github/kittinunf/fuel/core/requests/DownloadTaskRequest;", "Lcom/github/kittinunf/fuel/core/requests/TaskRequest;", "request", "Lcom/github/kittinunf/fuel/core/Request;", "(Lcom/github/kittinunf/fuel/core/Request;)V", "destinationCallback", "Lkotlin/Function2;", "Lcom/github/kittinunf/fuel/core/Response;", "Ljava/net/URL;", "Ljava/io/File;", "getDestinationCallback", "()Lkotlin/jvm/functions/Function2;", "setDestinationCallback", "(Lkotlin/jvm/functions/Function2;)V", "progressCallback", "", "", "getProgressCallback", "setProgressCallback", NotificationCompat.CATEGORY_CALL, "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class DownloadTaskRequest extends TaskRequest {

    @NotNull
    public Function2<? super Response, ? super URL, ? extends File> destinationCallback;

    @Nullable
    private Function2<? super Long, ? super Long, Unit> progressCallback;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DownloadTaskRequest(@NotNull Request request) {
        super(request);
        Intrinsics.checkParameterIsNotNull(request, "request");
    }

    @Nullable
    public final Function2<Long, Long, Unit> getProgressCallback() {
        return this.progressCallback;
    }

    public final void setProgressCallback(@Nullable Function2<? super Long, ? super Long, Unit> function2) {
        this.progressCallback = function2;
    }

    @NotNull
    public final Function2<Response, URL, File> getDestinationCallback() {
        Function2 function2 = this.destinationCallback;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("destinationCallback");
        }
        return function2;
    }

    public final void setDestinationCallback(@NotNull Function2<? super Response, ? super URL, ? extends File> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.destinationCallback = function2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.github.kittinunf.fuel.core.requests.TaskRequest, java.util.concurrent.Callable
    @NotNull
    public Response call() {
        final Response response = super.call();
        Function2<? super Response, ? super URL, ? extends File> function2 = this.destinationCallback;
        if (function2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("destinationCallback");
        }
        File file = function2.invoke(response, getRequest().getUrl());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        Throwable th = (Throwable) null;
        try {
            FileOutputStream it = fileOutputStream;
            InputStreamsKt.copyTo(response.getDataStream(), it, 1024, new Function1<Long, Unit>() { // from class: com.github.kittinunf.fuel.core.requests.DownloadTaskRequest$call$$inlined$use$lambda$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                    invoke(l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(long readBytes) {
                    Function2<Long, Long, Unit> progressCallback = DownloadTaskRequest.this.getProgressCallback();
                    if (progressCallback != null) {
                        progressCallback.invoke(Long.valueOf(readBytes), Long.valueOf(response.getContentLength()));
                    }
                }
            });
            return response;
        } finally {
            CloseableKt.closeFinally(fileOutputStream, th);
        }
    }
}
