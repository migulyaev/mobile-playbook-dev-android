package android.support.v4.graphics;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.FontResourcesParserCompat;
import android.util.Log;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
    private static final String ABORT_CREATION_METHOD = "abortCreation";
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
    private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
    private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
    private static final String FREEZE_METHOD = "freeze";
    private static final int RESOLVE_BY_FONT_TABLE = -1;
    private static final String TAG = "TypefaceCompatApi26Impl";
    private static final Method sAbortCreation;
    private static final Method sAddFontFromAssetManager;
    private static final Method sAddFontFromBuffer;
    private static final Method sCreateFromFamiliesWithDefault;
    private static final Class sFontFamily;
    private static final Constructor sFontFamilyCtor;
    private static final Method sFreeze;

    static {
        Constructor fontFamilyCtor;
        Constructor constructor;
        Constructor constructor2;
        Class fontFamilyClass;
        Constructor fontFamilyCtor2;
        Method addFontMethod;
        Method createFromFamiliesWithDefaultMethod;
        Method freezeMethod;
        Method abortCreationMethod;
        Method createFromFamiliesWithDefaultMethod2;
        Method abortCreationMethod2;
        Method createFromFamiliesWithDefaultMethod3 = null;
        try {
            fontFamilyClass = Class.forName(FONT_FAMILY_CLASS);
            try {
                fontFamilyCtor2 = fontFamilyClass.getConstructor(new Class[0]);
                try {
                    addFontMethod = fontFamilyClass.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
                } catch (ClassNotFoundException | NoSuchMethodException e) {
                    e = e;
                    constructor = null;
                    constructor2 = constructor;
                    Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
                    fontFamilyClass = null;
                    fontFamilyCtor2 = null;
                    addFontMethod = null;
                    createFromFamiliesWithDefaultMethod = null;
                    freezeMethod = null;
                    abortCreationMethod = null;
                    createFromFamiliesWithDefaultMethod2 = null;
                    sFontFamilyCtor = fontFamilyCtor2;
                    sFontFamily = fontFamilyClass;
                    sAddFontFromAssetManager = addFontMethod;
                    sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
                    sFreeze = freezeMethod;
                    sAbortCreation = abortCreationMethod;
                    sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
                }
            } catch (ClassNotFoundException | NoSuchMethodException e2) {
                e = e2;
                fontFamilyCtor = null;
                constructor = fontFamilyCtor;
                constructor2 = constructor;
                Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
                fontFamilyClass = null;
                fontFamilyCtor2 = null;
                addFontMethod = null;
                createFromFamiliesWithDefaultMethod = null;
                freezeMethod = null;
                abortCreationMethod = null;
                createFromFamiliesWithDefaultMethod2 = null;
                sFontFamilyCtor = fontFamilyCtor2;
                sFontFamily = fontFamilyClass;
                sAddFontFromAssetManager = addFontMethod;
                sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
                sFreeze = freezeMethod;
                sAbortCreation = abortCreationMethod;
                sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
            }
        } catch (ClassNotFoundException | NoSuchMethodException e3) {
            e = e3;
            fontFamilyCtor = null;
        }
        try {
            Method addFromBufferMethod = fontFamilyClass.getMethod(ADD_FONT_FROM_BUFFER_METHOD, ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
            try {
                freezeMethod = fontFamilyClass.getMethod(FREEZE_METHOD, new Class[0]);
                try {
                    abortCreationMethod = fontFamilyClass.getMethod(ABORT_CREATION_METHOD, new Class[0]);
                    try {
                        Object familyArray = Array.newInstance((Class<?>) fontFamilyClass, 1);
                        createFromFamiliesWithDefaultMethod3 = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, familyArray.getClass(), Integer.TYPE, Integer.TYPE);
                        createFromFamiliesWithDefaultMethod3.setAccessible(true);
                        createFromFamiliesWithDefaultMethod2 = createFromFamiliesWithDefaultMethod3;
                        createFromFamiliesWithDefaultMethod = addFromBufferMethod;
                    } catch (ClassNotFoundException | NoSuchMethodException e4) {
                        e = e4;
                        Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
                        fontFamilyClass = null;
                        fontFamilyCtor2 = null;
                        addFontMethod = null;
                        createFromFamiliesWithDefaultMethod = null;
                        freezeMethod = null;
                        abortCreationMethod = null;
                        createFromFamiliesWithDefaultMethod2 = null;
                        sFontFamilyCtor = fontFamilyCtor2;
                        sFontFamily = fontFamilyClass;
                        sAddFontFromAssetManager = addFontMethod;
                        sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
                        sFreeze = freezeMethod;
                        sAbortCreation = abortCreationMethod;
                        sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
                    }
                } catch (ClassNotFoundException | NoSuchMethodException e5) {
                    e = e5;
                    abortCreationMethod2 = null;
                    Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
                    fontFamilyClass = null;
                    fontFamilyCtor2 = null;
                    addFontMethod = null;
                    createFromFamiliesWithDefaultMethod = null;
                    freezeMethod = null;
                    abortCreationMethod = null;
                    createFromFamiliesWithDefaultMethod2 = null;
                    sFontFamilyCtor = fontFamilyCtor2;
                    sFontFamily = fontFamilyClass;
                    sAddFontFromAssetManager = addFontMethod;
                    sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
                    sFreeze = freezeMethod;
                    sAbortCreation = abortCreationMethod;
                    sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
                }
            } catch (ClassNotFoundException | NoSuchMethodException e6) {
                e = e6;
                abortCreationMethod2 = null;
            }
        } catch (ClassNotFoundException | NoSuchMethodException e7) {
            e = e7;
            constructor2 = null;
            Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            fontFamilyClass = null;
            fontFamilyCtor2 = null;
            addFontMethod = null;
            createFromFamiliesWithDefaultMethod = null;
            freezeMethod = null;
            abortCreationMethod = null;
            createFromFamiliesWithDefaultMethod2 = null;
            sFontFamilyCtor = fontFamilyCtor2;
            sFontFamily = fontFamilyClass;
            sAddFontFromAssetManager = addFontMethod;
            sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
            sFreeze = freezeMethod;
            sAbortCreation = abortCreationMethod;
            sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
        }
        sFontFamilyCtor = fontFamilyCtor2;
        sFontFamily = fontFamilyClass;
        sAddFontFromAssetManager = addFontMethod;
        sAddFontFromBuffer = createFromFamiliesWithDefaultMethod;
        sFreeze = freezeMethod;
        sAbortCreation = abortCreationMethod;
        sCreateFromFamiliesWithDefault = createFromFamiliesWithDefaultMethod2;
    }

    private static boolean isFontFamilyPrivateAPIAvailable() {
        if (sAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods.Fallback to legacy implementation.");
        }
        return sAddFontFromAssetManager != null;
    }

    private static Object newFamily() {
        try {
            return sFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromAssetManager(Context context, Object family, String fileName, int ttcIndex, int weight, int style) {
        try {
            Boolean result = (Boolean) sAddFontFromAssetManager.invoke(family, context.getAssets(), fileName, 0, false, Integer.valueOf(ttcIndex), Integer.valueOf(weight), Integer.valueOf(style), null);
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean addFontFromBuffer(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
        try {
            Boolean result = (Boolean) sAddFontFromBuffer.invoke(family, buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Integer.valueOf(style));
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static Typeface createFromFamiliesWithDefault(Object family) {
        try {
            Object familyArray = Array.newInstance((Class<?>) sFontFamily, 1);
            Array.set(familyArray, 0, family);
            return (Typeface) sCreateFromFamiliesWithDefault.invoke(null, familyArray, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean freeze(Object family) {
        try {
            Boolean result = (Boolean) sFreeze.invoke(family, new Object[0]);
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean abortCreation(Object family) {
        try {
            Boolean result = (Boolean) sAbortCreation.invoke(family, new Object[0]);
            return result.booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamilyFilesResourceEntry, Resources resources, int i) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, fontFamilyFilesResourceEntry, resources, i);
        }
        Object newFamily = newFamily();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry : fontFamilyFilesResourceEntry.getEntries()) {
            if (!addFontFromAssetManager(context, newFamily, fontFileResourceEntry.getFileName(), 0, fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic() ? 1 : 0)) {
                abortCreation(newFamily);
                return null;
            }
        }
        if (freeze(newFamily)) {
            return createFromFamiliesWithDefault(newFamily);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[Catch: IOException -> 0x0066, SYNTHETIC, TRY_LEAVE, TryCatch #3 {IOException -> 0x0066, blocks: (B:9:0x001d, B:13:0x0047, B:28:0x0059, B:25:0x0062, B:32:0x005e, B:26:0x0065), top: B:8:0x001d, inners: #1 }] */
    @Override // android.support.v4.graphics.TypefaceCompatApi21Impl, android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r18, @android.support.annotation.Nullable android.os.CancellationSignal r19, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r20, int r21) {
        /*
            r17 = this;
            r1 = r19
            r2 = r20
            r3 = 1
            r4 = 0
            int r5 = r2.length
            if (r5 >= r3) goto La
            return r4
        La:
            boolean r3 = isFontFamilyPrivateAPIAvailable()
            if (r3 != 0) goto L69
            r3 = r17
            r5 = r21
            android.support.v4.provider.FontsContractCompat$FontInfo r6 = r3.findBestInfo(r2, r5)
            android.content.ContentResolver r7 = r18.getContentResolver()
            android.net.Uri r8 = r6.getUri()     // Catch: java.io.IOException -> L66
            java.lang.String r9 = "r"
            android.os.ParcelFileDescriptor r8 = r7.openFileDescriptor(r8, r9, r1)     // Catch: java.io.IOException -> L66
            android.graphics.Typeface$Builder r9 = new android.graphics.Typeface$Builder     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4f
            java.io.FileDescriptor r10 = r8.getFileDescriptor()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4f
            r9.<init>(r10)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4f
            int r10 = r6.getWeight()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4f
            android.graphics.Typeface$Builder r9 = r9.setWeight(r10)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4f
            boolean r10 = r6.isItalic()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4f
            android.graphics.Typeface$Builder r9 = r9.setItalic(r10)     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4f
            android.graphics.Typeface r9 = r9.build()     // Catch: java.lang.Throwable -> L4b java.lang.Throwable -> L4f
            if (r8 == 0) goto L4a
            r8.close()     // Catch: java.io.IOException -> L66
        L4a:
            return r9
        L4b:
            r0 = move-exception
            r9 = r0
            r10 = r4
            goto L55
        L4f:
            r0 = move-exception
            r9 = r0
            throw r9     // Catch: java.lang.Throwable -> L52
        L52:
            r0 = move-exception
            r10 = r9
            r9 = r0
        L55:
            if (r8 == 0) goto L65
            if (r10 == 0) goto L62
            r8.close()     // Catch: java.lang.Throwable -> L5d java.io.IOException -> L66
            goto L65
        L5d:
            r0 = move-exception
            r10.addSuppressed(r0)     // Catch: java.io.IOException -> L66
            goto L65
        L62:
            r8.close()     // Catch: java.io.IOException -> L66
        L65:
            throw r9     // Catch: java.io.IOException -> L66
        L66:
            r0 = move-exception
            r8 = r0
            return r4
        L69:
            r3 = r17
            r5 = r21
            r6 = r18
            java.util.Map r7 = android.support.v4.provider.FontsContractCompat.prepareFontData(r6, r2, r1)
            java.lang.Object r8 = newFamily()
            r9 = 0
            int r10 = r2.length
            r11 = 0
        L7a:
            if (r11 >= r10) goto La9
            r12 = r2[r11]
            android.net.Uri r13 = r12.getUri()
            java.lang.Object r13 = r7.get(r13)
            java.nio.ByteBuffer r13 = (java.nio.ByteBuffer) r13
            if (r13 != 0) goto L8b
            goto La5
        L8b:
            int r14 = r12.getTtcIndex()
            int r15 = r12.getWeight()
            boolean r4 = r12.isItalic()
            boolean r4 = addFontFromBuffer(r8, r13, r14, r15, r4)
            if (r4 != 0) goto La3
            abortCreation(r8)
            r10 = 0
            return r10
        La3:
            r4 = 1
            r9 = r4
        La5:
            int r11 = r11 + 1
            r4 = 0
            goto L7a
        La9:
            if (r9 != 0) goto Lb0
            abortCreation(r8)
            r4 = 0
            return r4
        Lb0:
            r4 = 0
            boolean r10 = freeze(r8)
            if (r10 != 0) goto Lb8
            return r4
        Lb8:
            android.graphics.Typeface r4 = createFromFamiliesWithDefault(r8)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl, android.support.v4.graphics.TypefaceCompat.TypefaceCompatImpl
    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, id, path, style);
        }
        Object fontFamily = newFamily();
        if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1)) {
            abortCreation(fontFamily);
            return null;
        }
        if (freeze(fontFamily)) {
            return createFromFamiliesWithDefault(fontFamily);
        }
        return null;
    }
}
