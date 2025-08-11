package com.github.kittinunf.fuel;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.Method;
import com.github.kittinunf.fuel.core.Request;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Fuel.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u001e\b\u0002\u0010\u0003\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\u0000\u001a\u00020\u0001*\u00020\u00062\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00022\u001e\b\u0002\u0010\u0003\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00062\u001e\b\u0002\u0010\u0003\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\n\u001a\u00020\u0001*\u00020\u00022\u001e\b\u0002\u0010\u0003\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\n\u001a\u00020\u0001*\u00020\u00062\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u001e\b\u0002\u0010\u0003\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\u000b\u001a\u00020\u0001*\u00020\u00062\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\f\u001a\u00020\u0001*\u00020\u00022\u001e\b\u0002\u0010\u0003\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\f\u001a\u00020\u0001*\u00020\u00062\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\r\u001a\u00020\u0001*\u00020\u00022\u001e\b\u0002\u0010\u0003\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\r\u001a\u00020\u0001*\u00020\u00062\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\u000e\u001a\u00020\u0001*\u00020\u00022\u001e\b\u0002\u0010\u0003\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a,\u0010\u000e\u001a\u00020\u0001*\u00020\u00062\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a6\u0010\u000f\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007\u001a6\u0010\u000f\u001a\u00020\u0001*\u00020\u00062\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u001e\b\u0002\u0010\b\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0005\u0018\u00010\u0004H\u0007¨\u0006\u0012"}, d2 = {"httpDelete", "Lcom/github/kittinunf/fuel/core/Request;", "Lcom/github/kittinunf/fuel/Fuel$PathStringConvertible;", "parameter", "", "Lkotlin/Pair;", "", "", "parameters", "httpDownload", "httpGet", "httpHead", "httpPatch", "httpPost", "httpPut", "httpUpload", "method", "Lcom/github/kittinunf/fuel/core/Method;", "fuel"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class FuelKt {
    @JvmOverloads
    @NotNull
    public static final Request httpDelete(@NotNull Fuel.PathStringConvertible pathStringConvertible) {
        return httpDelete$default(pathStringConvertible, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpDelete(@NotNull String str) {
        return httpDelete$default(str, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpDownload(@NotNull Fuel.PathStringConvertible pathStringConvertible) {
        return httpDownload$default(pathStringConvertible, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpDownload(@NotNull String str) {
        return httpDownload$default(str, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpGet(@NotNull Fuel.PathStringConvertible pathStringConvertible) {
        return httpGet$default(pathStringConvertible, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpGet(@NotNull String str) {
        return httpGet$default(str, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpHead(@NotNull Fuel.PathStringConvertible pathStringConvertible) {
        return httpHead$default(pathStringConvertible, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpHead(@NotNull String str) {
        return httpHead$default(str, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPatch(@NotNull Fuel.PathStringConvertible pathStringConvertible) {
        return httpPatch$default(pathStringConvertible, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPatch(@NotNull String str) {
        return httpPatch$default(str, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPost(@NotNull Fuel.PathStringConvertible pathStringConvertible) {
        return httpPost$default(pathStringConvertible, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPost(@NotNull String str) {
        return httpPost$default(str, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPut(@NotNull Fuel.PathStringConvertible pathStringConvertible) {
        return httpPut$default(pathStringConvertible, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPut(@NotNull String str) {
        return httpPut$default(str, (List) null, 1, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpUpload(@NotNull Fuel.PathStringConvertible pathStringConvertible) {
        return httpUpload$default(pathStringConvertible, (Method) null, (List) null, 3, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpUpload(@NotNull Fuel.PathStringConvertible pathStringConvertible, @NotNull Method method) {
        return httpUpload$default(pathStringConvertible, method, (List) null, 2, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpUpload(@NotNull String str) {
        return httpUpload$default(str, (Method) null, (List) null, 3, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpUpload(@NotNull String str, @NotNull Method method) {
        return httpUpload$default(str, method, (List) null, 2, (Object) null);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpGet(@NotNull String receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.get(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpGet$default(String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpGet(str, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpGet(@NotNull Fuel.PathStringConvertible receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.get(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpGet$default(Fuel.PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpGet(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPost(@NotNull String receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.post(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpPost$default(String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpPost(str, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpPost$default(Fuel.PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpPost(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPost(@NotNull Fuel.PathStringConvertible receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.post(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPut(@NotNull String receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.put(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpPut$default(String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpPut(str, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPut(@NotNull Fuel.PathStringConvertible receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.put(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpPut$default(Fuel.PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpPut(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPatch(@NotNull String receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.patch(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpPatch$default(String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpPatch(str, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpPatch$default(Fuel.PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpPatch(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpPatch(@NotNull Fuel.PathStringConvertible receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.patch(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpDelete(@NotNull String receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.delete(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpDelete$default(String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpDelete(str, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpDelete$default(Fuel.PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpDelete(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpDelete(@NotNull Fuel.PathStringConvertible receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.delete(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpDownload(@NotNull String receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.download(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpDownload$default(String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpDownload(str, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpDownload$default(Fuel.PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpDownload(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpDownload(@NotNull Fuel.PathStringConvertible receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.download(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpUpload$default(String str, Method method, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            method = Method.POST;
        }
        if ((i & 2) != 0) {
            list = (List) null;
        }
        return httpUpload(str, method, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpUpload(@NotNull String receiver, @NotNull Method method, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(method, "method");
        return Fuel.INSTANCE.upload(receiver, method, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpUpload$default(Fuel.PathStringConvertible pathStringConvertible, Method method, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            method = Method.POST;
        }
        if ((i & 2) != 0) {
            list = (List) null;
        }
        return httpUpload(pathStringConvertible, method, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpUpload(@NotNull Fuel.PathStringConvertible receiver, @NotNull Method method, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(method, "method");
        return Fuel.INSTANCE.upload(receiver, method, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpHead$default(Fuel.PathStringConvertible pathStringConvertible, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpHead(pathStringConvertible, (List<? extends Pair<String, ? extends Object>>) list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpHead(@NotNull Fuel.PathStringConvertible receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.head(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static final Request httpHead(@NotNull String receiver, @Nullable List<? extends Pair<String, ? extends Object>> list) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        return Fuel.INSTANCE.head(receiver, list);
    }

    @JvmOverloads
    @NotNull
    public static /* bridge */ /* synthetic */ Request httpHead$default(String str, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = (List) null;
        }
        return httpHead(str, (List<? extends Pair<String, ? extends Object>>) list);
    }
}
