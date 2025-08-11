package com.github.kittinunf.fuel.util;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.Encoding;
import com.github.kittinunf.fuel.core.Method;
import com.github.kittinunf.fuel.core.Request;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: FuelRouting.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR(\u0010\u000e\u001a\u0018\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010\u0018\u00010\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0005R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/github/kittinunf/fuel/util/FuelRouting;", "Lcom/github/kittinunf/fuel/Fuel$RequestConvertible;", "basePath", "", "getBasePath", "()Ljava/lang/String;", "headers", "", "getHeaders", "()Ljava/util/Map;", "method", "Lcom/github/kittinunf/fuel/core/Method;", "getMethod", "()Lcom/github/kittinunf/fuel/core/Method;", "params", "", "Lkotlin/Pair;", "", "getParams", "()Ljava/util/List;", "path", "getPath", "request", "Lcom/github/kittinunf/fuel/core/Request;", "getRequest", "()Lcom/github/kittinunf/fuel/core/Request;", "fuel"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public interface FuelRouting extends Fuel.RequestConvertible {
    @NotNull
    String getBasePath();

    @Nullable
    Map<String, String> getHeaders();

    @NotNull
    Method getMethod();

    @Nullable
    List<Pair<String, Object>> getParams();

    @NotNull
    String getPath();

    @Override // com.github.kittinunf.fuel.Fuel.RequestConvertible
    @NotNull
    Request getRequest();

    /* compiled from: FuelRouting.kt */
    @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 9})
    public static final class DefaultImpls {
        @NotNull
        public static Request getRequest(FuelRouting $this) {
            Encoding encoder = new Encoding($this.getMethod(), $this.getPath(), null, $this.getBasePath(), $this.getParams(), 4, null);
            return encoder.getRequest().header($this.getHeaders());
        }
    }
}
