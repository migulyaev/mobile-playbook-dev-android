package de.adorsys.android.securestoragelibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

/* loaded from: classes.dex */
public class SecurePreferences {
    private static final String KEY_SHARED_PREFERENCES_NAME = "SecurePreferences";

    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    private static void clearAllSecureValues(@NonNull Context context) {
        context.getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, 0).edit().clear().commit();
    }

    public static void clearAllValues(@NonNull Context context) {
        if (a.a()) {
            a.b(context);
        }
        clearAllSecureValues(context);
    }

    public static boolean getBooleanValue(@NonNull String str, @NonNull Context context, boolean z) {
        return Boolean.parseBoolean(getStringValue(str, context, String.valueOf(z)));
    }

    public static float getFloatValue(@NonNull String str, @NonNull Context context, float f) {
        return Float.parseFloat(getStringValue(str, context, String.valueOf(f)));
    }

    public static float getIntValue(@NonNull String str, @NonNull Context context, int i) {
        return Integer.parseInt(getStringValue(str, context, String.valueOf(i)));
    }

    public static float getLongValue(@NonNull String str, @NonNull Context context, long j) {
        return Long.parseLong(getStringValue(str, context, String.valueOf(j)));
    }

    @Nullable
    private static String getSecureValue(@NonNull String str, @NonNull Context context) {
        return context.getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, 0).getString(str, null);
    }

    @Nullable
    public static String getStringValue(@NonNull String str, @NonNull Context context, @Nullable String str2) {
        String secureValue = getSecureValue(str, context);
        try {
            return !TextUtils.isEmpty(secureValue) ? a.b(context, secureValue) : str2;
        } catch (CryptoException e) {
            return str2;
        }
    }

    @SuppressLint({"ApplySharedPref"})
    private static void removeSecureValue(@NonNull String str, @NonNull Context context) {
        context.getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, 0).edit().remove(str).commit();
    }

    public static void removeValue(@NonNull String str, @NonNull Context context) {
        removeSecureValue(str, context);
    }

    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    private static void setSecureValue(@NonNull String str, @NonNull String str2, @NonNull Context context) {
        context.getSharedPreferences(KEY_SHARED_PREFERENCES_NAME, 0).edit().putString(str, str2).commit();
    }

    @RequiresApi(18)
    public static void setValue(@NonNull String str, float f, @NonNull Context context) {
        setValue(str, String.valueOf(f), context);
    }

    @RequiresApi(18)
    public static void setValue(@NonNull String str, int i, @NonNull Context context) {
        setValue(str, String.valueOf(i), context);
    }

    @RequiresApi(18)
    public static void setValue(@NonNull String str, long j, @NonNull Context context) {
        setValue(str, String.valueOf(j), context);
    }

    @RequiresApi(18)
    public static void setValue(@NonNull String str, @NonNull String str2, @NonNull Context context) {
        if (!a.a()) {
            a.a(context);
        }
        String a = a.a(context, str2);
        if (TextUtils.isEmpty(a)) {
            throw new CryptoException(context.getString(R.string.message_problem_encryption), null);
        }
        setSecureValue(str, a, context);
    }

    @RequiresApi(18)
    public static void setValue(@NonNull String str, boolean z, @NonNull Context context) {
        setValue(str, String.valueOf(z), context);
    }
}
