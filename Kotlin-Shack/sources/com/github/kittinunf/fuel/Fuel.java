package com.github.kittinunf.fuel;

import com.github.kittinunf.fuel.core.FuelManager;
import com.github.kittinunf.fuel.core.Method;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.util.TestConfiguration;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Fuel.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00032\u00020\u0001:\u0003\u0003\u0004\u0005B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0006"}, d2 = {"Lcom/github/kittinunf/fuel/Fuel;", "", "()V", "Companion", "PathStringConvertible", "RequestConvertible", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class Fuel {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static TestConfiguration testConfiguration = new TestConfiguration(null, null, false, 2, null);

    /* compiled from: Fuel.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/github/kittinunf/fuel/Fuel$PathStringConvertible;", "", "path", "", "getPath", "()Ljava/lang/String;", "fuel"}, k = 1, mv = {1, 1, 9})
    public interface PathStringConvertible {
        @NotNull
        String getPath();
    }

    /* compiled from: Fuel.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/github/kittinunf/fuel/Fuel$RequestConvertible;", "", "request", "Lcom/github/kittinunf/fuel/core/Request;", "getRequest", "()Lcom/github/kittinunf/fuel/core/Request;", "fuel"}, k = 1, mv = {1, 1, 9})
    public interface RequestConvertible {
        @NotNull
        Request getRequest();
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request delete(@NotNull PathStringConvertible pathStringConvertible) {
        return Companion.delete$default(INSTANCE, pathStringConvertible, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request delete(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return INSTANCE.delete(convertible, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request delete(@NotNull String str) {
        return Companion.delete$default(INSTANCE, str, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request delete(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return INSTANCE.delete(path, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request download(@NotNull PathStringConvertible pathStringConvertible) {
        return Companion.download$default(INSTANCE, pathStringConvertible, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request download(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return INSTANCE.download(convertible, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request download(@NotNull String str) {
        return Companion.download$default(INSTANCE, str, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request download(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return INSTANCE.download(path, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request get(@NotNull PathStringConvertible pathStringConvertible) {
        return Companion.get$default(INSTANCE, pathStringConvertible, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request get(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return INSTANCE.get(convertible, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request get(@NotNull String str) {
        return Companion.get$default(INSTANCE, str, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request get(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return INSTANCE.get(path, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request head(@NotNull PathStringConvertible pathStringConvertible) {
        return Companion.head$default(INSTANCE, pathStringConvertible, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request head(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return INSTANCE.head(convertible, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request head(@NotNull String str) {
        return Companion.head$default(INSTANCE, str, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request head(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return INSTANCE.head(path, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request patch(@NotNull PathStringConvertible pathStringConvertible) {
        return Companion.patch$default(INSTANCE, pathStringConvertible, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request patch(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return INSTANCE.patch(convertible, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request patch(@NotNull String str) {
        return Companion.patch$default(INSTANCE, str, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request patch(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return INSTANCE.patch(path, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request post(@NotNull PathStringConvertible pathStringConvertible) {
        return Companion.post$default(INSTANCE, pathStringConvertible, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request post(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return INSTANCE.post(convertible, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request post(@NotNull String str) {
        return Companion.post$default(INSTANCE, str, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request post(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return INSTANCE.post(path, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request put(@NotNull PathStringConvertible pathStringConvertible) {
        return Companion.put$default(INSTANCE, pathStringConvertible, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request put(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return INSTANCE.put(convertible, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request put(@NotNull String str) {
        return Companion.put$default(INSTANCE, str, (List) null, 2, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request put(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        return INSTANCE.put(path, list);
    }

    @JvmStatic
    public static final void regularMode() {
        INSTANCE.regularMode();
    }

    @JvmStatic
    @NotNull
    public static final Request request(@NotNull RequestConvertible convertible) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        return INSTANCE.request(convertible);
    }

    @JvmStatic
    @JvmOverloads
    public static final void testMode() {
        Companion.testMode$default(INSTANCE, null, 1, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final void testMode(@NotNull Function1<? super TestConfiguration, Unit> configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "configuration");
        INSTANCE.testMode(configuration);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request upload(@NotNull PathStringConvertible pathStringConvertible) {
        return Companion.upload$default(INSTANCE, pathStringConvertible, (Method) null, (List) null, 6, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request upload(@NotNull PathStringConvertible pathStringConvertible, @NotNull Method method) {
        return Companion.upload$default(INSTANCE, pathStringConvertible, method, (List) null, 4, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request upload(@NotNull PathStringConvertible convertible, @NotNull Method method, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(convertible, "convertible");
        Intrinsics.checkParameterIsNotNull(method, "method");
        return INSTANCE.upload(convertible, method, list);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request upload(@NotNull String str) {
        return Companion.upload$default(INSTANCE, str, (Method) null, (List) null, 6, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request upload(@NotNull String str, @NotNull Method method) {
        return Companion.upload$default(INSTANCE, str, method, (List) null, 4, (Object) null);
    }

    @JvmStatic
    @JvmOverloads
    @NotNull
    public static final Request upload(@NotNull String path, @NotNull Method method, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(method, "method");
        return INSTANCE.upload(path, method, list);
    }

    /* compiled from: Fuel.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J0\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\t\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0012\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0015\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J0\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J\b\u0010\u0018\u001a\u00020\u0019H\u0007J\u0010\u0010\u001a\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u001bH\u0007J8\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u000b\u001a\u00020\f2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0002J8\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0011\u001a\u00020\u00102\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0002J#\u0010\u001e\u001a\u00020\u00192\u0019\b\u0002\u0010\u001f\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00190 ¢\u0006\u0002\b!H\u0007J:\u0010\"\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007J:\u0010\"\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\u001e\b\u0002\u0010\r\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f\u0018\u00010\u000eH\u0007R\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u0006#"}, d2 = {"Lcom/github/kittinunf/fuel/Fuel$Companion;", "", "()V", "testConfiguration", "Lcom/github/kittinunf/fuel/util/TestConfiguration;", "getTestConfiguration$fuel", "()Lcom/github/kittinunf/fuel/util/TestConfiguration;", "setTestConfiguration$fuel", "(Lcom/github/kittinunf/fuel/util/TestConfiguration;)V", "delete", "Lcom/github/kittinunf/fuel/core/Request;", "convertible", "Lcom/github/kittinunf/fuel/Fuel$PathStringConvertible;", "parameters", "", "Lkotlin/Pair;", "", "path", "download", "get", "head", "patch", "post", "put", "regularMode", "", "request", "Lcom/github/kittinunf/fuel/Fuel$RequestConvertible;", "method", "Lcom/github/kittinunf/fuel/core/Method;", "testMode", "configuration", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "upload", "fuel"}, k = 1, mv = {1, 1, 9})
    public static final class Companion {
        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request delete(@NotNull PathStringConvertible pathStringConvertible) {
            return delete$default(this, pathStringConvertible, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request delete(@NotNull String str) {
            return delete$default(this, str, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request download(@NotNull PathStringConvertible pathStringConvertible) {
            return download$default(this, pathStringConvertible, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request download(@NotNull String str) {
            return download$default(this, str, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request get(@NotNull PathStringConvertible pathStringConvertible) {
            return get$default(this, pathStringConvertible, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request get(@NotNull String str) {
            return get$default(this, str, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request head(@NotNull PathStringConvertible pathStringConvertible) {
            return head$default(this, pathStringConvertible, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request head(@NotNull String str) {
            return head$default(this, str, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request patch(@NotNull PathStringConvertible pathStringConvertible) {
            return patch$default(this, pathStringConvertible, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request patch(@NotNull String str) {
            return patch$default(this, str, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request post(@NotNull PathStringConvertible pathStringConvertible) {
            return post$default(this, pathStringConvertible, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request post(@NotNull String str) {
            return post$default(this, str, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request put(@NotNull PathStringConvertible pathStringConvertible) {
            return put$default(this, pathStringConvertible, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request put(@NotNull String str) {
            return put$default(this, str, (List) null, 2, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        public final void testMode() {
            testMode$default(this, null, 1, null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request upload(@NotNull PathStringConvertible pathStringConvertible) {
            return upload$default(this, pathStringConvertible, (Method) null, (List) null, 6, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request upload(@NotNull PathStringConvertible pathStringConvertible, @NotNull Method method) {
            return upload$default(this, pathStringConvertible, method, (List) null, 4, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request upload(@NotNull String str) {
            return upload$default(this, str, (Method) null, (List) null, 6, (Object) null);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request upload(@NotNull String str, @NotNull Method method) {
            return upload$default(this, str, method, (List) null, 4, (Object) null);
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final TestConfiguration getTestConfiguration$fuel() {
            return Fuel.testConfiguration;
        }

        public final void setTestConfiguration$fuel(@NotNull TestConfiguration testConfiguration) {
            Intrinsics.checkParameterIsNotNull(testConfiguration, "<set-?>");
            Fuel.testConfiguration = testConfiguration;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        public static /* bridge */ /* synthetic */ void testMode$default(Companion companion, Function1 function1, int i, Object obj) {
            if ((i & 1) != 0) {
                function1 = new Function1<TestConfiguration, Unit>() { // from class: com.github.kittinunf.fuel.Fuel$Companion$testMode$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(TestConfiguration testConfiguration) {
                        invoke2(testConfiguration);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(@NotNull TestConfiguration receiver) {
                        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    }
                };
            }
            companion.testMode(function1);
        }

        @JvmStatic
        @JvmOverloads
        public final void testMode(@NotNull Function1<? super TestConfiguration, Unit> configuration) {
            Intrinsics.checkParameterIsNotNull(configuration, "configuration");
            TestConfiguration testConfiguration = new TestConfiguration(null, null, false, 7, null);
            configuration.invoke(testConfiguration);
            setTestConfiguration$fuel(testConfiguration);
        }

        @JvmStatic
        public final void regularMode() {
            testMode(new Function1<TestConfiguration, Unit>() { // from class: com.github.kittinunf.fuel.Fuel$Companion$regularMode$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(TestConfiguration testConfiguration) {
                    invoke2(testConfiguration);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(@NotNull TestConfiguration receiver) {
                    Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
                    receiver.setTimeout((Integer) null);
                    receiver.setBlocking(false);
                }
            });
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request get$default(Companion companion, String str, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.get(str, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request get(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            return request(Method.GET, path, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request get$default(Companion companion, PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.get(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request get(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            return request(Method.GET, convertible, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request post$default(Companion companion, String str, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.post(str, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request post(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            return request(Method.POST, path, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request post$default(Companion companion, PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.post(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request post(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            return request(Method.POST, convertible, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request put$default(Companion companion, String str, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.put(str, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request put(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            return request(Method.PUT, path, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request put$default(Companion companion, PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.put(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request put(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            return request(Method.PUT, convertible, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request patch$default(Companion companion, String str, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.patch(str, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request patch(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            return request(Method.PATCH, path, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request patch$default(Companion companion, PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.patch(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request patch(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            return request(Method.PATCH, convertible, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request delete$default(Companion companion, String str, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.delete(str, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request delete(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            return request(Method.DELETE, path, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request delete$default(Companion companion, PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.delete(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request delete(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            return request(Method.DELETE, convertible, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request download$default(Companion companion, String str, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.download(str, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request download(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            return FuelManager.INSTANCE.getInstance().download(path, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request download$default(Companion companion, PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.download(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request download(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            return download(convertible.getPath(), parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request upload$default(Companion companion, String str, Method method, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                method = Method.POST;
            }
            if ((i & 4) != 0) {
                list = (List) null;
            }
            return companion.upload(str, method, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request upload(@NotNull String path, @NotNull Method method, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            Intrinsics.checkParameterIsNotNull(method, "method");
            return FuelManager.INSTANCE.getInstance().upload(path, method, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request upload$default(Companion companion, PathStringConvertible pathStringConvertible, Method method, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                method = Method.POST;
            }
            if ((i & 4) != 0) {
                list = (List) null;
            }
            return companion.upload(pathStringConvertible, method, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request upload(@NotNull PathStringConvertible convertible, @NotNull Method method, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            Intrinsics.checkParameterIsNotNull(method, "method");
            return upload(convertible.getPath(), method, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request head$default(Companion companion, String str, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.head(str, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request head(@NotNull String path, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(path, "path");
            return request(Method.HEAD, path, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @JvmStatic
        @JvmOverloads
        @NotNull
        public static /* bridge */ /* synthetic */ Request head$default(Companion companion, PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
            if ((i & 2) != 0) {
                list = (List) null;
            }
            return companion.head(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
        }

        @JvmStatic
        @JvmOverloads
        @NotNull
        public final Request head(@NotNull PathStringConvertible convertible, @Nullable List<? extends Pair<String, ? extends Object>> parameters) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            return request(Method.HEAD, convertible, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        static /* bridge */ /* synthetic */ Request request$default(Companion companion, Method method, String str, List list, int i, Object obj) {
            if ((i & 4) != 0) {
                list = (List) null;
            }
            return companion.request(method, str, (List<? extends Pair<String, ? extends Object>>) list);
        }

        private final Request request(Method method, String path, List<? extends Pair<String, ? extends Object>> parameters) {
            return FuelManager.INSTANCE.getInstance().request(method, path, parameters);
        }

        /* JADX WARN: Multi-variable type inference failed */
        static /* bridge */ /* synthetic */ Request request$default(Companion companion, Method method, PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
            if ((i & 4) != 0) {
                list = (List) null;
            }
            return companion.request(method, pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
        }

        private final Request request(Method method, PathStringConvertible convertible, List<? extends Pair<String, ? extends Object>> parameters) {
            return request(method, convertible.getPath(), parameters);
        }

        @JvmStatic
        @NotNull
        public final Request request(@NotNull RequestConvertible convertible) {
            Intrinsics.checkParameterIsNotNull(convertible, "convertible");
            return FuelManager.INSTANCE.getInstance().request(convertible);
        }
    }
}
