package org.jetbrains.anko;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import java.io.Serializable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ContextUtils.kt */
@Metadata(bv = {1, 0, 1}, d1 = {"\u0000z\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a;\u00100\u001a\u0002012.\u00102\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u0001060403\"\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u00010604¢\u0006\u0002\u00107\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00012\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010<\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020=2\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010>\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00042\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010?\u001a(\u00108\u001a\u0002H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00122\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010@\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00012\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010<\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020=2\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010>\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00042\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010?\u001a*\u0010A\u001a\u0004\u0018\u0001H9\"\n\b\u0000\u00109\u0018\u0001*\u00020\u0012*\u00020\u00122\b\b\u0001\u0010:\u001a\u00020;H\u0086\b¢\u0006\u0002\u0010@\u001aL\u0010B\u001a\u0002H9\"\b\b\u0000\u00109*\u00020\u0004*\u0002H92.\u00102\u001a\u0018\u0012\u0014\b\u0001\u0012\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u0001060403\"\u0010\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u00010604H\u0086\b¢\u0006\u0002\u0010C\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003\"\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0005\"\u001a\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0016\u0010\u000b\u001a\u00020\f*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"\u001a\u0010\u000b\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010\"\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u00018Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0016\u0010\u0015\u001a\u00020\r*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017\"\u0016\u0010\u0015\u001a\u00020\r*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018\"\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\u00048Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0016\u0010\u0019\u001a\u00020\u001a*\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001d\"\u001a\u0010\u0019\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001e\"\u0016\u0010\u001f\u001a\u00020 *\u00020\r8Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u001a\u0010\u001f\u001a\u00020 *\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b!\u0010#\"\u0016\u0010$\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b&\u0010'\"\u0016\u0010(\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b)\u0010'\"\u0016\u0010*\u001a\u00020%*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b+\u0010'\"\u001a\u0010,\u001a\u00020-*\u0006\u0012\u0002\b\u00030\b8Æ\u0002¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u0006D"}, d2 = {"act", "Landroid/app/Activity;", "getAct", "(Landroid/app/Activity;)Landroid/app/Activity;", "Landroid/app/Fragment;", "(Landroid/app/Fragment;)Landroid/app/Activity;", "assets", "Landroid/content/res/AssetManager;", "Lorg/jetbrains/anko/AnkoContext;", "getAssets", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/AssetManager;", "configuration", "Landroid/content/res/Configuration;", "Landroid/content/Context;", "getConfiguration", "(Landroid/content/Context;)Landroid/content/res/Configuration;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/Configuration;", "contentView", "Landroid/view/View;", "getContentView", "(Landroid/app/Activity;)Landroid/view/View;", "ctx", "getCtx", "(Landroid/app/Fragment;)Landroid/content/Context;", "(Landroid/content/Context;)Landroid/content/Context;", "defaultSharedPreferences", "Landroid/content/SharedPreferences;", "getDefaultSharedPreferences", "(Landroid/app/Fragment;)Landroid/content/SharedPreferences;", "(Landroid/content/Context;)Landroid/content/SharedPreferences;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/SharedPreferences;", "displayMetrics", "Landroid/util/DisplayMetrics;", "getDisplayMetrics", "(Landroid/content/Context;)Landroid/util/DisplayMetrics;", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/util/DisplayMetrics;", "landscape", "", "getLandscape", "(Landroid/content/res/Configuration;)Z", "long", "getLong", "portrait", "getPortrait", "resources", "Landroid/content/res/Resources;", "getResources", "(Lorg/jetbrains/anko/AnkoContext;)Landroid/content/res/Resources;", "bundleOf", "Landroid/os/Bundle;", "params", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "find", "T", "id", "", "(Landroid/app/Activity;I)Landroid/view/View;", "Landroid/app/Dialog;", "(Landroid/app/Dialog;I)Landroid/view/View;", "(Landroid/app/Fragment;I)Landroid/view/View;", "(Landroid/view/View;I)Landroid/view/View;", "findOptional", "withArguments", "(Landroid/app/Fragment;[Lkotlin/Pair;)Landroid/app/Fragment;", "commons_release"}, k = 2, mv = {1, 1, 5})
/* loaded from: classes.dex */
public final class ContextUtilsKt {
    @NotNull
    public static final Resources getResources(@NotNull AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Resources resources = receiver.getCtx().getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "ctx.resources");
        return resources;
    }

    @NotNull
    public static final AssetManager getAssets(@NotNull AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        AssetManager assets = receiver.getCtx().getAssets();
        Intrinsics.checkExpressionValueIsNotNull(assets, "ctx.assets");
        return assets;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(receiver.getCtx());
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDefaultSharedPreferences(ctx)");
        return defaultSharedPreferences;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull Context receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(receiver);
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…ltSharedPreferences(this)");
        return defaultSharedPreferences;
    }

    @NotNull
    public static final SharedPreferences getDefaultSharedPreferences(@NotNull Fragment receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(receiver.getActivity());
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "PreferenceManager.getDef…aredPreferences(activity)");
        return defaultSharedPreferences;
    }

    @NotNull
    public static final Activity getAct(@NotNull Fragment receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Activity activity = receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        return activity;
    }

    @NotNull
    public static final Context getCtx(@NotNull Fragment receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Activity activity = receiver.getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity, "activity");
        return activity;
    }

    @NotNull
    public static final Context getCtx(@NotNull Context receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver;
    }

    @NotNull
    public static final Activity getAct(@NotNull Activity receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver;
    }

    @Nullable
    public static final View getContentView(@NotNull Activity receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        View findViewById = receiver.findViewById(R.id.content);
        if (!(findViewById instanceof ViewGroup)) {
            findViewById = null;
        }
        ViewGroup viewGroup = (ViewGroup) findViewById;
        if (viewGroup != null) {
            return viewGroup.getChildAt(0);
        }
        return null;
    }

    private static final <T extends View> T find(@NotNull View $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(1, "T");
        return (T) findViewById;
    }

    private static final <T extends View> T find(@NotNull Activity $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(1, "T");
        return (T) findViewById;
    }

    private static final <T extends View> T find(@NotNull Fragment $receiver, @IdRes int id) {
        View view = $receiver.getView();
        View findViewById = view != null ? view.findViewById(id) : null;
        Intrinsics.reifiedOperationMarker(1, "T");
        return (T) findViewById;
    }

    private static final <T extends View> T find(@NotNull Dialog $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(1, "T");
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(@NotNull View $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(2, "T");
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(@NotNull Activity $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(2, "T");
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(@NotNull Fragment $receiver, @IdRes int id) {
        View view = $receiver.getView();
        View findViewById = view != null ? view.findViewById(id) : null;
        Intrinsics.reifiedOperationMarker(2, "T");
        return (T) findViewById;
    }

    private static final <T extends View> T findOptional(@NotNull Dialog $receiver, @IdRes int id) {
        View findViewById = $receiver.findViewById(id);
        Intrinsics.reifiedOperationMarker(2, "T");
        return (T) findViewById;
    }

    @NotNull
    public static final <T extends Fragment> T withArguments(@NotNull T receiver, @NotNull Pair<String, ? extends Object>... params) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(params, "params");
        receiver.setArguments(bundleOf((Pair[]) Arrays.copyOf(params, params.length)));
        return receiver;
    }

    @NotNull
    public static final Bundle bundleOf(@NotNull Pair<String, ? extends Object>... params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Bundle b = new Bundle();
        for (Pair<String, ? extends Object> pair : params) {
            String k = pair.component1();
            Object v = pair.component2();
            if (Intrinsics.areEqual(v, (Object) null)) {
                b.putSerializable(k, null);
            } else if (v instanceof Boolean) {
                b.putBoolean(k, ((Boolean) v).booleanValue());
            } else if (v instanceof Byte) {
                b.putByte(k, ((Number) v).byteValue());
            } else if (v instanceof Character) {
                b.putChar(k, ((Character) v).charValue());
            } else if (v instanceof Short) {
                b.putShort(k, ((Number) v).shortValue());
            } else if (v instanceof Integer) {
                b.putInt(k, ((Number) v).intValue());
            } else if (v instanceof Long) {
                b.putLong(k, ((Number) v).longValue());
            } else if (v instanceof Float) {
                b.putFloat(k, ((Number) v).floatValue());
            } else if (v instanceof Double) {
                b.putDouble(k, ((Number) v).doubleValue());
            } else if (v instanceof String) {
                b.putString(k, (String) v);
            } else if (v instanceof CharSequence) {
                b.putCharSequence(k, (CharSequence) v);
            } else if (v instanceof Parcelable) {
                b.putParcelable(k, (Parcelable) v);
            } else if (v instanceof Serializable) {
                b.putSerializable(k, (Serializable) v);
            } else if (v instanceof boolean[]) {
                b.putBooleanArray(k, (boolean[]) v);
            } else if (v instanceof byte[]) {
                b.putByteArray(k, (byte[]) v);
            } else if (v instanceof char[]) {
                b.putCharArray(k, (char[]) v);
            } else if (v instanceof double[]) {
                b.putDoubleArray(k, (double[]) v);
            } else if (v instanceof float[]) {
                b.putFloatArray(k, (float[]) v);
            } else if (v instanceof int[]) {
                b.putIntArray(k, (int[]) v);
            } else if (v instanceof long[]) {
                b.putLongArray(k, (long[]) v);
            } else if (v instanceof Object[]) {
                if (((Object[]) v) instanceof Parcelable[]) {
                    if (v == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out android.os.Parcelable>");
                    }
                    b.putParcelableArray(k, (Parcelable[]) v);
                } else if (((Object[]) v) instanceof CharSequence[]) {
                    if (v == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out kotlin.CharSequence>");
                    }
                    b.putCharSequenceArray(k, (CharSequence[]) v);
                } else {
                    if (!(((Object[]) v) instanceof String[])) {
                        throw new AnkoException("Unsupported bundle component (" + ((Object[]) v).getClass() + ")");
                    }
                    if (v == null) {
                        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<out kotlin.String>");
                    }
                    b.putStringArray(k, (String[]) v);
                }
            } else if (v instanceof short[]) {
                b.putShortArray(k, (short[]) v);
            } else {
                if (!(v instanceof Bundle)) {
                    throw new AnkoException("Unsupported bundle component (" + v.getClass() + ")");
                }
                b.putBundle(k, (Bundle) v);
            }
        }
        return b;
    }

    @NotNull
    public static final DisplayMetrics getDisplayMetrics(@NotNull Context receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        DisplayMetrics displayMetrics = receiver.getResources().getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(displayMetrics, "resources.displayMetrics");
        return displayMetrics;
    }

    @NotNull
    public static final Configuration getConfiguration(@NotNull Context receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Configuration configuration = receiver.getResources().getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(configuration, "resources.configuration");
        return configuration;
    }

    @NotNull
    public static final DisplayMetrics getDisplayMetrics(@NotNull AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        DisplayMetrics displayMetrics = receiver.getCtx().getResources().getDisplayMetrics();
        Intrinsics.checkExpressionValueIsNotNull(displayMetrics, "ctx.resources.displayMetrics");
        return displayMetrics;
    }

    @NotNull
    public static final Configuration getConfiguration(@NotNull AnkoContext<?> receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Configuration configuration = receiver.getCtx().getResources().getConfiguration();
        Intrinsics.checkExpressionValueIsNotNull(configuration, "ctx.resources.configuration");
        return configuration;
    }

    public static final boolean getPortrait(@NotNull Configuration receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.orientation == 1;
    }

    public static final boolean getLandscape(@NotNull Configuration receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return receiver.orientation == 2;
    }

    public static final boolean getLong(@NotNull Configuration receiver) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return (receiver.screenLayout & 32) != 0;
    }
}
