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
    private static final String ABORT_CREATION_METHOD;
    private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD;
    private static final String ADD_FONT_FROM_BUFFER_METHOD;
    private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD;
    private static final String DEFAULT_FAMILY;
    private static final String FONT_FAMILY_CLASS;
    private static final String FREEZE_METHOD;
    private static final int RESOLVE_BY_FONT_TABLE;
    private static final String TAG;
    protected final Method mAbortCreation;
    protected final Method mAddFontFromAssetManager;
    protected final Method mAddFontFromBuffer;
    protected final Method mCreateFromFamiliesWithDefault;
    protected final Class mFontFamily;
    protected final Constructor mFontFamilyCtor;
    protected final Method mFreeze;

    public TypefaceCompatApi26Impl() {
        Method createFromFamiliesWithDefault;
        Method abortCreation;
        Method freeze;
        Method addFontFromBuffer;
        Constructor fontFamilyCtor;
        Class fontFamily;
        Method createFromFamiliesWithDefault2;
        try {
            Class fontFamily2 = obtainFontFamily();
            Constructor fontFamilyCtor2 = obtainFontFamilyCtor(fontFamily2);
            Method addFontFromAssetManager = obtainAddFontFromAssetManagerMethod(fontFamily2);
            Method addFontFromBuffer2 = obtainAddFontFromBufferMethod(fontFamily2);
            Method freeze2 = obtainFreezeMethod(fontFamily2);
            Method abortCreation2 = obtainAbortCreationMethod(fontFamily2);
            fontFamily = fontFamily2;
            createFromFamiliesWithDefault2 = obtainCreateFromFamiliesWithDefaultMethod(fontFamily2);
            createFromFamiliesWithDefault = abortCreation2;
            abortCreation = freeze2;
            freeze = addFontFromBuffer2;
            addFontFromBuffer = addFontFromAssetManager;
            fontFamilyCtor = fontFamilyCtor2;
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            Log.e(TAG, "Unable to collect necessary methods for class " + e.getClass().getName(), e);
            fontFamily = null;
            fontFamilyCtor = null;
            addFontFromBuffer = null;
            freeze = null;
            abortCreation = null;
            createFromFamiliesWithDefault = null;
            createFromFamiliesWithDefault2 = null;
        }
        this.mFontFamily = fontFamily;
        this.mFontFamilyCtor = fontFamilyCtor;
        this.mAddFontFromAssetManager = addFontFromBuffer;
        this.mAddFontFromBuffer = freeze;
        this.mFreeze = abortCreation;
        this.mAbortCreation = createFromFamiliesWithDefault;
        this.mCreateFromFamiliesWithDefault = createFromFamiliesWithDefault2;
    }

    private boolean isFontFamilyPrivateAPIAvailable() {
        if (this.mAddFontFromAssetManager == null) {
            Log.w(TAG, "Unable to collect necessary private methods. Fallback to legacy implementation.");
        }
        return this.mAddFontFromAssetManager != null;
    }

    private Object newFamily() {
        try {
            return this.mFontFamilyCtor.newInstance(new Object[0]);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean addFontFromAssetManager(Context context, Object family, String fileName, int ttcIndex, int weight, int style, @Nullable FontVariationAxis[] axes) {
        try {
            return ((Boolean) this.mAddFontFromAssetManager.invoke(family, context.getAssets(), fileName, 0, false, Integer.valueOf(ttcIndex), Integer.valueOf(weight), Integer.valueOf(style), axes)).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean addFontFromBuffer(Object family, ByteBuffer buffer, int ttcIndex, int weight, int style) {
        try {
            return ((Boolean) this.mAddFontFromBuffer.invoke(family, buffer, Integer.valueOf(ttcIndex), null, Integer.valueOf(weight), Integer.valueOf(style))).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    protected Typeface createFromFamiliesWithDefault(Object family) {
        try {
            Object familyArray = Array.newInstance(this.mFontFamily, 1);
            Array.set(familyArray, 0, family);
            return (Typeface) this.mCreateFromFamiliesWithDefault.invoke(null, familyArray, -1, -1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean freeze(Object family) {
        try {
            return ((Boolean) this.mFreeze.invoke(family, new Object[0])).booleanValue();
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private void abortCreation(Object family) {
        try {
            this.mAbortCreation.invoke(family, new Object[0]);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl
    public Typeface createFromFontFamilyFilesResourceEntry(Context context, FontResourcesParserCompat.FontFamilyFilesResourceEntry entry, Resources resources, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromFontFamilyFilesResourceEntry(context, entry, resources, style);
        }
        Object fontFamily = newFamily();
        FontResourcesParserCompat.FontFileResourceEntry[] entries = entry.getEntries();
        for (FontResourcesParserCompat.FontFileResourceEntry fontFile : entries) {
            if (!addFontFromAssetManager(context, fontFamily, fontFile.getFileName(), fontFile.getTtcIndex(), fontFile.getWeight(), fontFile.isItalic() ? 1 : 0, FontVariationAxis.fromFontVariationSettings(fontFile.getVariationSettings()))) {
                abortCreation(fontFamily);
                return null;
            }
        }
        if (!freeze(fontFamily)) {
            return null;
        }
        return createFromFamiliesWithDefault(fontFamily);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0060  */
    @Override // android.support.v4.graphics.TypefaceCompatApi21Impl, android.support.v4.graphics.TypefaceCompatBaseImpl
    /* Code decompiled incorrectly, please refer to instructions dump */
    public android.graphics.Typeface createFromFontInfo(android.content.Context r22, @android.support.annotation.Nullable android.os.CancellationSignal r23, @android.support.annotation.NonNull android.support.v4.provider.FontsContractCompat.FontInfo[] r24, int r25) {
        /*
            r21 = this;
            r7 = r21
            r8 = r23
            r9 = r24
            r10 = r25
            int r1 = r9.length
            r2 = 1
            r11 = 0
            if (r1 >= r2) goto L_0x000e
            return r11
        L_0x000e:
            boolean r1 = r21.isFontFamilyPrivateAPIAvailable()
            if (r1 != 0) goto L_0x0072
            android.support.v4.provider.FontsContractCompat$FontInfo r1 = r7.findBestInfo(r9, r10)
            android.content.ContentResolver r2 = r22.getContentResolver()
            android.net.Uri r3 = r1.getUri()     // Catch: IOException -> 0x006f
            java.lang.String r4 = "r"
            android.os.ParcelFileDescriptor r3 = r2.openFileDescriptor(r3, r4, r8)     // Catch: IOException -> 0x006f
            if (r3 != 0) goto L_0x0031
            if (r3 == 0) goto L_0x0030
            r3.close()     // Catch: IOException -> 0x006f
        L_0x0030:
            return r11
        L_0x0031:
            android.graphics.Typeface$Builder r4 = new android.graphics.Typeface$Builder     // Catch: Throwable -> 0x0058, all -> 0x0054
            java.io.FileDescriptor r5 = r3.getFileDescriptor()     // Catch: Throwable -> 0x0058, all -> 0x0054
            r4.<init>(r5)     // Catch: Throwable -> 0x0058, all -> 0x0054
            int r5 = r1.getWeight()     // Catch: Throwable -> 0x0058, all -> 0x0054
            android.graphics.Typeface$Builder r4 = r4.setWeight(r5)     // Catch: Throwable -> 0x0058, all -> 0x0054
            boolean r5 = r1.isItalic()     // Catch: Throwable -> 0x0058, all -> 0x0054
            android.graphics.Typeface$Builder r4 = r4.setItalic(r5)     // Catch: Throwable -> 0x0058, all -> 0x0054
            android.graphics.Typeface r4 = r4.build()     // Catch: Throwable -> 0x0058, all -> 0x0054
            if (r3 == 0) goto L_0x0053
            r3.close()     // Catch: IOException -> 0x006f
        L_0x0053:
            return r4
        L_0x0054:
            r0 = move-exception
            r4 = r0
            r5 = r11
            goto L_0x005e
        L_0x0058:
            r0 = move-exception
            r4 = r0
            throw r4     // Catch: all -> 0x005b
        L_0x005b:
            r0 = move-exception
            r5 = r4
            r4 = r0
        L_0x005e:
            if (r3 == 0) goto L_0x006e
            if (r5 == 0) goto L_0x006b
            r3.close()     // Catch: Throwable -> 0x0066, IOException -> 0x006f
            goto L_0x006e
        L_0x0066:
            r0 = move-exception
            r5.addSuppressed(r0)     // Catch: IOException -> 0x006f
            goto L_0x006e
        L_0x006b:
            r3.close()     // Catch: IOException -> 0x006f
        L_0x006e:
            throw r4     // Catch: IOException -> 0x006f
        L_0x006f:
            r0 = move-exception
            r3 = r0
            return r11
        L_0x0072:
            r12 = r22
            java.util.Map r13 = android.support.v4.provider.FontsContractCompat.prepareFontData(r12, r9, r8)
            java.lang.Object r14 = r21.newFamily()
            r1 = 0
            int r15 = r9.length
            r2 = 0
            r16 = r1
            r6 = 0
        L_0x0082:
            if (r6 >= r15) goto L_0x00c1
            r5 = r9[r6]
            android.net.Uri r1 = r5.getUri()
            java.lang.Object r1 = r13.get(r1)
            r17 = r1
            java.nio.ByteBuffer r17 = (java.nio.ByteBuffer) r17
            if (r17 != 0) goto L_0x0098
            r18 = r6
            goto L_0x00be
        L_0x0098:
            int r4 = r5.getTtcIndex()
            int r18 = r5.getWeight()
            boolean r19 = r5.isItalic()
            r1 = r7
            r2 = r14
            r3 = r17
            r20 = r5
            r5 = r18
            r18 = r6
            r6 = r19
            boolean r1 = r1.addFontFromBuffer(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x00bb
            r7.abortCreation(r14)
            return r11
        L_0x00bb:
            r1 = 1
            r16 = r1
        L_0x00be:
            int r6 = r18 + 1
            goto L_0x0082
        L_0x00c1:
            if (r16 != 0) goto L_0x00c7
            r7.abortCreation(r14)
            return r11
        L_0x00c7:
            boolean r1 = r7.freeze(r14)
            if (r1 != 0) goto L_0x00ce
            return r11
        L_0x00ce:
            android.graphics.Typeface r1 = r7.createFromFamiliesWithDefault(r14)
            android.graphics.Typeface r2 = android.graphics.Typeface.create(r1, r10)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.graphics.TypefaceCompatApi26Impl.createFromFontInfo(android.content.Context, android.os.CancellationSignal, android.support.v4.provider.FontsContractCompat$FontInfo[], int):android.graphics.Typeface");
    }

    @Override // android.support.v4.graphics.TypefaceCompatBaseImpl
    @Nullable
    public Typeface createFromResourcesFontFile(Context context, Resources resources, int id, String path, int style) {
        if (!isFontFamilyPrivateAPIAvailable()) {
            return super.createFromResourcesFontFile(context, resources, id, path, style);
        }
        Object fontFamily = newFamily();
        if (!addFontFromAssetManager(context, fontFamily, path, 0, -1, -1, null)) {
            abortCreation(fontFamily);
            return null;
        } else if (!freeze(fontFamily)) {
            return null;
        } else {
            return createFromFamiliesWithDefault(fontFamily);
        }
    }

    protected Class obtainFontFamily() throws ClassNotFoundException {
        return Class.forName(FONT_FAMILY_CLASS);
    }

    protected Constructor obtainFontFamilyCtor(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getConstructor(new Class[0]);
    }

    protected Method obtainAddFontFromAssetManagerMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(ADD_FONT_FROM_ASSET_MANAGER_METHOD, AssetManager.class, String.class, Integer.TYPE, Boolean.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, FontVariationAxis[].class);
    }

    protected Method obtainAddFontFromBufferMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(ADD_FONT_FROM_BUFFER_METHOD, ByteBuffer.class, Integer.TYPE, FontVariationAxis[].class, Integer.TYPE, Integer.TYPE);
    }

    protected Method obtainFreezeMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(FREEZE_METHOD, new Class[0]);
    }

    protected Method obtainAbortCreationMethod(Class fontFamily) throws NoSuchMethodException {
        return fontFamily.getMethod(ABORT_CREATION_METHOD, new Class[0]);
    }

    protected Method obtainCreateFromFamiliesWithDefaultMethod(Class fontFamily) throws NoSuchMethodException {
        Method m = Typeface.class.getDeclaredMethod(CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD, Array.newInstance(fontFamily, 1).getClass(), Integer.TYPE, Integer.TYPE);
        m.setAccessible(true);
        return m;
    }
}
