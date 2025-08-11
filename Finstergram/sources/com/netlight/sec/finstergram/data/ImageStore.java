package com.netlight.sec.finstergram.data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import com.netlight.sec.finstergram.service.ImageStoreService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageStore.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\u0004J\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0004J\b\u0010\r\u001a\u00020\u0004H\u0002J \u0010\u000e\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00040\u00100\u000f2\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/netlight/sec/finstergram/data/ImageStore;", "", "()V", "IMAGES_DIR", "", "deleteAllImages", "", "context", "Landroid/content/Context;", "deleteAllImagesSync", "deleteImage", "imagePath", "deleteImageSync", "generateImageName", "loadImages", "", "Lkotlin/Pair;", "Landroid/graphics/Bitmap;", "storeImage", "imageUri", "Landroid/net/Uri;", "storeImageSync", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes7.dex */
public final class ImageStore {
    private static final String IMAGES_DIR = "my_images";
    public static final ImageStore INSTANCE = new ImageStore();

    private ImageStore() {
    }

    public final List<Pair<Bitmap, String>> loadImages(Context context) {
        Pair pair;
        File[] fileArr;
        Intrinsics.checkNotNullParameter(context, "context");
        File folder = new File(context.getFilesDir(), IMAGES_DIR);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File[] listFiles = folder.listFiles();
        if (listFiles == null) {
            return CollectionsKt.emptyList();
        }
        Collection destination$iv$iv = new ArrayList();
        int i = 0;
        int length = listFiles.length;
        while (i < length) {
            File file = listFiles[i];
            File folder2 = folder;
            Bitmap image = BitmapFactory.decodeFile(file.getAbsolutePath());
            if (image == null) {
                pair = null;
                fileArr = listFiles;
            } else {
                fileArr = listFiles;
                pair = new Pair(image, file.getAbsolutePath());
            }
            if (pair != null) {
                destination$iv$iv.add(pair);
            }
            i++;
            listFiles = fileArr;
            folder = folder2;
        }
        return (List) destination$iv$iv;
    }

    public final void storeImage(Context context, Uri imageUri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageUri, "imageUri");
        Intent intent = new Intent(ImageStoreService.INTENT_ACTION_FINSTERGRAM_IMG_SERVICE);
        intent.putExtra(ImageStoreService.INTENT_EXTRA_COMMAND, "STORE_IMAGE");
        intent.setData(imageUri);
        context.getApplicationContext().sendBroadcast(intent);
    }

    public final void storeImageSync(Context context, Uri imageUri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageUri, "imageUri");
        String destinationPath = context.getFilesDir().getAbsolutePath() + "/my_images/" + generateImageName();
        InputStream inputStream = context.getContentResolver().openInputStream(imageUri);
        FileOutputStream outputStream = new FileOutputStream(new File(destinationPath));
        byte[] buffer = new byte[1024];
        while (true) {
            Intrinsics.checkNotNull(inputStream);
            int it = inputStream.read(buffer);
            if (it != -1) {
                outputStream.write(buffer, 0, it);
            } else {
                inputStream.close();
                outputStream.close();
                return;
            }
        }
    }

    private final String generateImageName() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String format = currentDateTime.format(formatter);
        Intrinsics.checkNotNullExpressionValue(format, "currentDateTime.format(formatter)");
        return format;
    }

    public final void deleteAllImages(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent intent = new Intent(ImageStoreService.INTENT_ACTION_FINSTERGRAM_IMG_SERVICE);
        intent.putExtra(ImageStoreService.INTENT_EXTRA_COMMAND, "DELETE_ALL");
        context.sendBroadcast(intent);
    }

    public final void deleteAllImagesSync(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        File folder = new File(context.getFilesDir(), IMAGES_DIR);
        File[] listFiles = folder.listFiles();
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            file.delete();
        }
    }

    public final void deleteImage(Context context, String imagePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        Intent intent = new Intent(ImageStoreService.INTENT_ACTION_FINSTERGRAM_IMG_SERVICE);
        intent.putExtra(ImageStoreService.INTENT_EXTRA_COMMAND, "DELETE_IMAGE");
        intent.putExtra(ImageStoreService.INTENT_EXTRA_DESTINATION_PATH, imagePath);
        context.sendBroadcast(intent);
    }

    public final void deleteImageSync(String imagePath) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        File imageFile = new File(imagePath);
        imageFile.delete();
    }
}
