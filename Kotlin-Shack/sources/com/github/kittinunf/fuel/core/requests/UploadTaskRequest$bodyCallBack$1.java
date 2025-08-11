package com.github.kittinunf.fuel.core.requests;

import com.github.kittinunf.fuel.core.Blob;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.util.InputStreamsKt;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: UploadTaskRequest.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0007"}, d2 = {"<no name provided>", "", "request", "Lcom/github/kittinunf/fuel/core/Request;", "outputStream", "Ljava/io/OutputStream;", "totalLength", "invoke"}, k = 3, mv = {1, 1, 9})
/* loaded from: classes.dex */
final class UploadTaskRequest$bodyCallBack$1 extends Lambda implements Function3<Request, OutputStream, Long, Long> {
    final /* synthetic */ UploadTaskRequest this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    UploadTaskRequest$bodyCallBack$1(UploadTaskRequest uploadTaskRequest) {
        super(3);
        this.this$0 = uploadTaskRequest;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Long invoke(Request request, OutputStream outputStream, Long l) {
        return Long.valueOf(invoke(request, outputStream, l.longValue()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long invoke(@NotNull final Request request, @Nullable final OutputStream outputStream, final long totalLength) {
        String str;
        String str2;
        String str3;
        String name;
        String str4;
        Function0 inputStream;
        Throwable th;
        UploadTaskRequest$bodyCallBack$1 uploadTaskRequest$bodyCallBack$1 = this;
        Intrinsics.checkParameterIsNotNull(request, "request");
        final Ref.LongRef contentLength = new Ref.LongRef();
        contentLength.element = 0L;
        Iterable files = uploadTaskRequest$bodyCallBack$1.this$0.getSourceCallback().invoke(request, request.getUrl());
        Iterable $receiver$iv = files;
        int index$iv = 0;
        Iterator<Blob> it = $receiver$iv.iterator();
        Object data = null;
        int $i$a$1$forEachIndexed = 0;
        String name2 = null;
        while (it.hasNext()) {
            Object item$iv = it.next();
            int index$iv2 = index$iv + 1;
            Blob blob = (Blob) item$iv;
            int $i$a$1$forEachIndexed2 = $i$a$1$forEachIndexed;
            String name3 = blob.getName();
            long length = blob.getLength();
            Function0 inputStream2 = blob.component3();
            String postFix = CollectionsKt.count(files) == 1 ? "" : "" + (index$iv + 1);
            List<String> names = request.getNames();
            String fieldName = (index$iv < 0 || index$iv > CollectionsKt.getLastIndex(names)) ? request.getName() + postFix : names.get(index$iv);
            Iterator<Blob> it2 = it;
            final Iterable files2 = files;
            long j = contentLength.element;
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            str3 = uploadTaskRequest$bodyCallBack$1.this$0.boundary;
            sb.append(str3);
            Iterable $receiver$iv2 = $receiver$iv;
            contentLength.element = j + UploadTaskRequestKt.write(outputStream, sb.toString());
            contentLength.element += UploadTaskRequestKt.writeln(outputStream);
            contentLength.element = contentLength.element + UploadTaskRequestKt.write(outputStream, "Content-Disposition: form-data; name=\"" + fieldName + "\"; filename=\"" + name3 + Typography.quote);
            contentLength.element = contentLength.element + ((long) UploadTaskRequestKt.writeln(outputStream));
            long j2 = contentLength.element;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Content-Type: ");
            List<String> mediaTypes = request.getMediaTypes();
            sb2.append((index$iv < 0 || index$iv > CollectionsKt.getLastIndex(mediaTypes)) ? UploadTaskRequestKt.guessContentType(name3) : mediaTypes.get(index$iv));
            contentLength.element = j2 + UploadTaskRequestKt.write(outputStream, sb2.toString());
            contentLength.element += UploadTaskRequestKt.writeln(outputStream);
            contentLength.element += UploadTaskRequestKt.writeln(outputStream);
            if (outputStream != null) {
                InputStream invoke = inputStream2.invoke();
                Throwable th2 = (Throwable) null;
                try {
                    InputStream it3 = invoke;
                    inputStream = inputStream2;
                    name = name3;
                    str4 = blob;
                    try {
                        InputStreamsKt.copyTo(it3, outputStream, 1024, new Function1<Long, Unit>() { // from class: com.github.kittinunf.fuel.core.requests.UploadTaskRequest$bodyCallBack$1$$special$$inlined$apply$lambda$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Long l) {
                                invoke(l.longValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(long writtenBytes) {
                                Function2<Long, Long, Unit> progressCallback = this.this$0.getProgressCallback();
                                if (progressCallback != null) {
                                    progressCallback.invoke(Long.valueOf(contentLength.element + writtenBytes), Long.valueOf(totalLength));
                                }
                            }
                        });
                        CloseableKt.closeFinally(invoke, th2);
                    } catch (Throwable th3) {
                        th2 = th3;
                        try {
                            throw th2;
                        } catch (Throwable th4) {
                            th = th4;
                            th = th;
                            CloseableKt.closeFinally(invoke, th2);
                            throw th;
                        }
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                }
            } else {
                name = name3;
                str4 = blob;
                inputStream = inputStream2;
            }
            contentLength.element += length;
            contentLength.element += UploadTaskRequestKt.writeln(outputStream);
            uploadTaskRequest$bodyCallBack$1 = this;
            index$iv = index$iv2;
            $i$a$1$forEachIndexed = $i$a$1$forEachIndexed2;
            files = files2;
            it = it2;
            $receiver$iv = $receiver$iv2;
            name2 = str4;
        }
        Iterable $receiver$iv3 = request.getParameters();
        int $i$a$2$forEach = 0;
        int $i$f$forEach = 0;
        for (Iterator it4 = $receiver$iv3.iterator(); it4.hasNext(); it4 = it4) {
            Object element$iv = it4.next();
            Pair $name_data = (Pair) element$iv;
            String name4 = (String) $name_data.component1();
            Object data2 = $name_data.component2();
            long j3 = contentLength.element;
            StringBuilder sb3 = new StringBuilder();
            sb3.append("--");
            int i = $i$a$2$forEach;
            str2 = this.this$0.boundary;
            sb3.append(str2);
            contentLength.element = j3 + UploadTaskRequestKt.write(outputStream, sb3.toString());
            contentLength.element += UploadTaskRequestKt.writeln(outputStream);
            contentLength.element = contentLength.element + UploadTaskRequestKt.write(outputStream, "Content-Disposition: form-data; name=\"" + name4 + Typography.quote);
            contentLength.element = contentLength.element + ((long) UploadTaskRequestKt.writeln(outputStream));
            contentLength.element = contentLength.element + ((long) UploadTaskRequestKt.write(outputStream, "Content-Type: text/plain"));
            contentLength.element = contentLength.element + ((long) UploadTaskRequestKt.writeln(outputStream));
            contentLength.element = contentLength.element + ((long) UploadTaskRequestKt.writeln(outputStream));
            contentLength.element += UploadTaskRequestKt.write(outputStream, String.valueOf(data2));
            contentLength.element += UploadTaskRequestKt.writeln(outputStream);
            name2 = name4;
            data = data2;
            $receiver$iv3 = $receiver$iv3;
            $i$a$2$forEach = i;
            $i$f$forEach = $i$f$forEach;
        }
        long j4 = contentLength.element;
        StringBuilder sb4 = new StringBuilder();
        sb4.append("--");
        str = this.this$0.boundary;
        sb4.append(str);
        sb4.append("--");
        contentLength.element = j4 + UploadTaskRequestKt.write(outputStream, sb4.toString());
        contentLength.element += UploadTaskRequestKt.writeln(outputStream);
        Function2<Long, Long, Unit> progressCallback = this.this$0.getProgressCallback();
        if (progressCallback != null) {
            progressCallback.invoke(Long.valueOf(contentLength.element), Long.valueOf(totalLength));
        }
        return contentLength.element;
    }
}
