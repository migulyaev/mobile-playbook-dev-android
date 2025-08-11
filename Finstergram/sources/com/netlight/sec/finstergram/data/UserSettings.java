package com.netlight.sec.finstergram.data;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.modules.SerializersModule;

/* compiled from: UserSettings.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B)\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tB\u001b\b\u0002\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ!\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014HÇ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/netlight/sec/finstergram/data/UserSettings;", "", "seen1", "", "darkMode", "", "requirePassword", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(IZZLkotlinx/serialization/internal/SerializationConstructorMarker;)V", "(ZZ)V", "getDarkMode", "()Z", "getRequirePassword", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "$serializer", "Companion", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
@Serializable
/* loaded from: classes7.dex */
public final class UserSettings {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String FINSTERGRAM_PREFS = "FinstergramPreferences";
    private static final String FINSTERGRAM_SETTINGS_PREF_KEY = "UserSettings";
    public static UserSettings instance;
    private final boolean darkMode;
    private final boolean requirePassword;

    public /* synthetic */ UserSettings(boolean z, boolean z2, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, z2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ UserSettings(int seen1, boolean darkMode, boolean requirePassword, SerializationConstructorMarker serializationConstructorMarker) {
        if ((seen1 & 0) != 0) {
            PluginExceptionsKt.throwMissingFieldException(seen1, 0, UserSettings$$serializer.INSTANCE.getDescriptor());
        }
        if ((seen1 & 1) == 0) {
            this.darkMode = false;
        } else {
            this.darkMode = darkMode;
        }
        if ((seen1 & 2) == 0) {
            this.requirePassword = true;
        } else {
            this.requirePassword = requirePassword;
        }
        INSTANCE.setInstance(this);
    }

    private UserSettings(boolean darkMode, boolean requirePassword) {
        this.darkMode = darkMode;
        this.requirePassword = requirePassword;
        INSTANCE.setInstance(this);
    }

    @JvmStatic
    public static final void write$Self(UserSettings self, CompositeEncoder output, SerialDescriptor serialDesc) {
        boolean z;
        Intrinsics.checkNotNullParameter(self, "self");
        Intrinsics.checkNotNullParameter(output, "output");
        Intrinsics.checkNotNullParameter(serialDesc, "serialDesc");
        boolean z2 = false;
        if (output.shouldEncodeElementDefault(serialDesc, 0)) {
            z = true;
        } else {
            z = self.darkMode;
        }
        if (z) {
            output.encodeBooleanElement(serialDesc, 0, self.darkMode);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 1)) {
            z2 = true;
        } else if (!self.requirePassword) {
            z2 = true;
        }
        if (z2) {
            output.encodeBooleanElement(serialDesc, 1, self.requirePassword);
        }
    }

    /* synthetic */ UserSettings(boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? true : z2);
    }

    public final boolean getDarkMode() {
        return this.darkMode;
    }

    public final boolean getRequirePassword() {
        return this.requirePassword;
    }

    /* compiled from: UserSettings.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0012HÆ\u0001J\"\u0010\u0013\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/netlight/sec/finstergram/data/UserSettings$Companion;", "", "()V", "FINSTERGRAM_PREFS", "", "FINSTERGRAM_SETTINGS_PREF_KEY", "instance", "Lcom/netlight/sec/finstergram/data/UserSettings;", "getInstance", "()Lcom/netlight/sec/finstergram/data/UserSettings;", "setInstance", "(Lcom/netlight/sec/finstergram/data/UserSettings;)V", "load", "", "context", "Landroid/content/Context;", "reset", "serializer", "Lkotlinx/serialization/KSerializer;", "store", "darkMode", "", "requirePassword", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final KSerializer<UserSettings> serializer() {
            return UserSettings$$serializer.INSTANCE;
        }

        public final UserSettings getInstance() {
            UserSettings userSettings = UserSettings.instance;
            if (userSettings != null) {
                return userSettings;
            }
            Intrinsics.throwUninitializedPropertyAccessException("instance");
            return null;
        }

        public final void setInstance(UserSettings userSettings) {
            Intrinsics.checkNotNullParameter(userSettings, "<set-?>");
            UserSettings.instance = userSettings;
        }

        public final void load(Context context) {
            UserSettings userSettings;
            Intrinsics.checkNotNullParameter(context, "context");
            boolean z = false;
            SharedPreferences sharedPrefs = context.getSharedPreferences(UserSettings.FINSTERGRAM_PREFS, 0);
            DefaultConstructorMarker defaultConstructorMarker = null;
            String json = sharedPrefs.getString(UserSettings.FINSTERGRAM_SETTINGS_PREF_KEY, null);
            if (json != null) {
                StringFormat $this$decodeFromString$iv = Json.INSTANCE;
                SerializersModule $this$serializer$iv$iv = $this$decodeFromString$iv.getSerializersModule();
                userSettings = (UserSettings) $this$decodeFromString$iv.decodeFromString(SerializersKt.serializer($this$serializer$iv$iv, Reflection.typeOf(UserSettings.class)), json);
            } else {
                userSettings = null;
            }
            UserSettings settings = userSettings;
            if (settings == null) {
                new UserSettings(z, z, 3, defaultConstructorMarker);
            }
        }

        public static /* synthetic */ void store$default(Companion companion, Context context, boolean z, boolean z2, int i, Object obj) {
            if ((i & 2) != 0) {
                z = companion.getInstance().getDarkMode();
            }
            if ((i & 4) != 0) {
                z2 = companion.getInstance().getRequirePassword();
            }
            companion.store(context, z, z2);
        }

        public final void store(Context context, boolean darkMode, boolean requirePassword) {
            Intrinsics.checkNotNullParameter(context, "context");
            UserSettings newSettings = new UserSettings(darkMode, requirePassword, null);
            StringFormat $this$encodeToString$iv = Json.INSTANCE;
            SerializersModule $this$serializer$iv$iv = $this$encodeToString$iv.getSerializersModule();
            String json = $this$encodeToString$iv.encodeToString(SerializersKt.serializer($this$serializer$iv$iv, Reflection.typeOf(UserSettings.class)), newSettings);
            SharedPreferences sharedPrefs = context.getSharedPreferences(UserSettings.FINSTERGRAM_PREFS, 0);
            SharedPreferences.Editor editor = sharedPrefs.edit();
            editor.putString(UserSettings.FINSTERGRAM_SETTINGS_PREF_KEY, json);
            editor.apply();
        }

        public final void reset(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            boolean z = false;
            UserSettings defaultSettings = new UserSettings(z, z, 3, (DefaultConstructorMarker) null);
            store(context, defaultSettings.getDarkMode(), defaultSettings.getRequirePassword());
        }
    }
}
