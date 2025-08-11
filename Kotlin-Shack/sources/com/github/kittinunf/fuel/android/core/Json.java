package com.github.kittinunf.fuel.android.core;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Json.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/github/kittinunf/fuel/android/core/Json;", "", "content", "", "(Ljava/lang/String;)V", "getContent", "()Ljava/lang/String;", "array", "Lorg/json/JSONArray;", "obj", "Lorg/json/JSONObject;", "fuel-android_release"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class Json {

    @NotNull
    private final String content;

    public Json(@NotNull String content) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        this.content = content;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }

    @NotNull
    public final JSONObject obj() {
        return new JSONObject(this.content);
    }

    @NotNull
    public final JSONArray array() {
        return new JSONArray(this.content);
    }
}
