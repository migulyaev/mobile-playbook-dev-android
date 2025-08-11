package com.netlight.sec.finstergram.ui.list;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ListItem.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/netlight/sec/finstergram/ui/list/ListItem;", "", "image", "Landroid/graphics/Bitmap;", "fileName", "", "(Landroid/graphics/Bitmap;Ljava/lang/String;)V", "getFileName", "()Ljava/lang/String;", "getImage", "()Landroid/graphics/Bitmap;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes6.dex */
public final class ListItem {
    private final String fileName;
    private final Bitmap image;

    public ListItem(Bitmap image, String fileName) {
        Intrinsics.checkNotNullParameter(image, "image");
        Intrinsics.checkNotNullParameter(fileName, "fileName");
        this.image = image;
        this.fileName = fileName;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final Bitmap getImage() {
        return this.image;
    }
}
