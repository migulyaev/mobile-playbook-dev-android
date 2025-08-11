package android.support.v4.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.os.Process;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TypefaceCompatUtil {
    private static final String CACHE_FILE_PREFIX = ".font";
    private static final String TAG = "TypefaceCompatUtil";

    private TypefaceCompatUtil() {
    }

    public static File getTempFile(Context context) {
        String prefix = CACHE_FILE_PREFIX + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(context.getCacheDir(), prefix + i);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[Catch: IOException -> 0x0037, SYNTHETIC, TRY_LEAVE, TryCatch #1 {IOException -> 0x0037, blocks: (B:3:0x0001, B:7:0x0019, B:22:0x002a, B:19:0x0033, B:26:0x002f, B:20:0x0036), top: B:2:0x0001, inners: #2 }] */
    @android.support.annotation.RequiresApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.nio.ByteBuffer mmap(java.io.File r10) {
        /*
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.io.IOException -> L37
            r1.<init>(r10)     // Catch: java.io.IOException -> L37
            java.nio.channels.FileChannel r2 = r1.getChannel()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            long r7 = r2.size()     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            java.nio.channels.FileChannel$MapMode r4 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            r5 = 0
            r3 = r2
            java.nio.MappedByteBuffer r3 = r3.map(r4, r5, r7)     // Catch: java.lang.Throwable -> L1d java.lang.Throwable -> L20
            if (r1 == 0) goto L1c
            r1.close()     // Catch: java.io.IOException -> L37
        L1c:
            return r3
        L1d:
            r2 = move-exception
            r3 = r0
            goto L26
        L20:
            r2 = move-exception
            throw r2     // Catch: java.lang.Throwable -> L22
        L22:
            r3 = move-exception
            r9 = r3
            r3 = r2
            r2 = r9
        L26:
            if (r1 == 0) goto L36
            if (r3 == 0) goto L33
            r1.close()     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L37
            goto L36
        L2e:
            r4 = move-exception
            r3.addSuppressed(r4)     // Catch: java.io.IOException -> L37
            goto L36
        L33:
            r1.close()     // Catch: java.io.IOException -> L37
        L36:
            throw r2     // Catch: java.io.IOException -> L37
        L37:
            r1 = move-exception
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(java.io.File):java.nio.ByteBuffer");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[Catch: all -> 0x004b, Throwable -> 0x004e, SYNTHETIC, TRY_LEAVE, TryCatch #4 {Throwable -> 0x004e, blocks: (B:5:0x000b, B:9:0x0028, B:19:0x0047, B:26:0x0043, B:20:0x004a), top: B:4:0x000b }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:52:? A[Catch: IOException -> 0x0065, SYNTHETIC, TRY_LEAVE, TryCatch #3 {IOException -> 0x0065, blocks: (B:3:0x0005, B:11:0x002d, B:46:0x0058, B:43:0x0061, B:50:0x005d, B:44:0x0064), top: B:2:0x0005, inners: #2 }] */
    @android.support.annotation.RequiresApi(19)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.nio.ByteBuffer mmap(android.content.Context r12, android.os.CancellationSignal r13, android.net.Uri r14) {
        /*
            android.content.ContentResolver r0 = r12.getContentResolver()
            r1 = 0
            java.lang.String r2 = "r"
            android.os.ParcelFileDescriptor r2 = r0.openFileDescriptor(r14, r2, r13)     // Catch: java.io.IOException -> L65
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4e
            java.io.FileDescriptor r4 = r2.getFileDescriptor()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4e
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4e
            java.nio.channels.FileChannel r4 = r3.getChannel()     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L34
            long r9 = r4.size()     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L34
            java.nio.channels.FileChannel$MapMode r6 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L34
            r7 = 0
            r5 = r4
            java.nio.MappedByteBuffer r5 = r5.map(r6, r7, r9)     // Catch: java.lang.Throwable -> L31 java.lang.Throwable -> L34
            if (r3 == 0) goto L2b
            r3.close()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4e
        L2b:
            if (r2 == 0) goto L30
            r2.close()     // Catch: java.io.IOException -> L65
        L30:
            return r5
        L31:
            r4 = move-exception
            r5 = r1
            goto L3a
        L34:
            r4 = move-exception
            throw r4     // Catch: java.lang.Throwable -> L36
        L36:
            r5 = move-exception
            r11 = r5
            r5 = r4
            r4 = r11
        L3a:
            if (r3 == 0) goto L4a
            if (r5 == 0) goto L47
            r3.close()     // Catch: java.lang.Throwable -> L42 java.lang.Throwable -> L4b
            goto L4a
        L42:
            r6 = move-exception
            r5.addSuppressed(r6)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4e
            goto L4a
        L47:
            r3.close()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4e
        L4a:
            throw r4     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4e
        L4b:
            r3 = move-exception
            r4 = r1
            goto L54
        L4e:
            r3 = move-exception
            throw r3     // Catch: java.lang.Throwable -> L50
        L50:
            r4 = move-exception
            r11 = r4
            r4 = r3
            r3 = r11
        L54:
            if (r2 == 0) goto L64
            if (r4 == 0) goto L61
            r2.close()     // Catch: java.lang.Throwable -> L5c java.io.IOException -> L65
            goto L64
        L5c:
            r5 = move-exception
            r4.addSuppressed(r5)     // Catch: java.io.IOException -> L65
            goto L64
        L61:
            r2.close()     // Catch: java.io.IOException -> L65
        L64:
            throw r3     // Catch: java.io.IOException -> L65
        L65:
            r2 = move-exception
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatUtil.mmap(android.content.Context, android.os.CancellationSignal, android.net.Uri):java.nio.ByteBuffer");
    }

    @RequiresApi(19)
    public static ByteBuffer copyToDirectBuffer(Context context, Resources res, int id) {
        File tmpFile = getTempFile(context);
        ByteBuffer byteBuffer = null;
        if (tmpFile == null) {
            return null;
        }
        try {
            if (copyToFile(tmpFile, res, id)) {
                byteBuffer = mmap(tmpFile);
            }
            return byteBuffer;
        } finally {
            tmpFile.delete();
        }
    }

    public static boolean copyToFile(File file, InputStream is) {
        FileOutputStream os = null;
        boolean z = false;
        try {
            try {
                os = new FileOutputStream(file, false);
                byte[] buffer = new byte[1024];
                while (true) {
                    int readLen = is.read(buffer);
                    if (readLen == -1) {
                        break;
                    }
                    os.write(buffer, 0, readLen);
                }
                z = true;
            } catch (IOException e) {
                Log.e(TAG, "Error copying resource contents to temp file: " + e.getMessage());
            }
            return z;
        } finally {
            closeQuietly(os);
        }
    }

    public static boolean copyToFile(File file, Resources res, int id) {
        InputStream is = null;
        try {
            is = res.openRawResource(id);
            return copyToFile(file, is);
        } finally {
            closeQuietly(is);
        }
    }

    public static void closeQuietly(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException e) {
            }
        }
    }
}
