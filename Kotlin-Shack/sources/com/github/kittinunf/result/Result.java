package com.github.kittinunf.result;

import java.lang.Exception;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: Result.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u0012*\n\b\u0000\u0010\u0001 \u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003 \u0001*\u00060\u0004j\u0002`\u00052\u00020\u0002:\u0003\u0012\u0013\u0014B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u0004\u0018\u00018\u0000H¦\u0002¢\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u0004\u0018\u00018\u0001H¦\u0002¢\u0006\u0002\u0010\nJ<\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0002\u0010\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\f0\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\f0\u000eH\u0086\b¢\u0006\u0002\u0010\u0010J\r\u0010\u0011\u001a\u00028\u0000H&¢\u0006\u0002\u0010\b\u0082\u0001\u0002\u0015\u0016¨\u0006\u0017"}, d2 = {"Lcom/github/kittinunf/result/Result;", "V", "", "E", "Ljava/lang/Exception;", "Lkotlin/Exception;", "()V", "component1", "()Ljava/lang/Object;", "component2", "()Ljava/lang/Exception;", "fold", "X", "success", "Lkotlin/Function1;", "failure", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "get", "Companion", "Failure", "Success", "Lcom/github/kittinunf/result/Result$Success;", "Lcom/github/kittinunf/result/Result$Failure;", "result"}, k = 1, mv = {1, 1, 9})
/* loaded from: classes.dex */
public abstract class Result<V, E extends Exception> {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Nullable
    public abstract V component1();

    @Nullable
    public abstract E component2();

    @NotNull
    public abstract V get();

    private Result() {
    }

    public /* synthetic */ Result(DefaultConstructorMarker $constructor_marker) {
        this();
    }

    public final <X> X fold(@NotNull Function1<? super V, ? extends X> success, @NotNull Function1<? super E, ? extends X> failure) {
        Intrinsics.checkParameterIsNotNull(success, "success");
        Intrinsics.checkParameterIsNotNull(failure, "failure");
        if (this instanceof Success) {
            return success.invoke((Object) ((Success) this).getValue());
        }
        if (this instanceof Failure) {
            return failure.invoke(((Failure) this).getError());
        }
        throw new NoWhenBranchMatchedException();
    }

    /* compiled from: Result.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\n\b\u0002\u0010\u0001 \u0001*\u00020\u0002*\u000e\b\u0003\u0010\u0003 \u0001*\u00060\u0004j\u0002`\u00052\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0006B\r\u0012\u0006\u0010\u0007\u001a\u00028\u0002¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u0004\u0018\u00018\u0002H\u0096\u0002¢\u0006\u0002\u0010\nJ\u0010\u0010\r\u001a\u0004\u0018\u00018\u0003H\u0096\u0002¢\u0006\u0002\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\r\u0010\u0012\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0013\u0010\u0007\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, d2 = {"Lcom/github/kittinunf/result/Result$Success;", "V", "", "E", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lcom/github/kittinunf/result/Result;", "value", "(Ljava/lang/Object;)V", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "()Ljava/lang/Exception;", "equals", "", "other", "get", "hashCode", "", "toString", "", "result"}, k = 1, mv = {1, 1, 9})
    public static final class Success<V, E extends Exception> extends Result<V, E> {

        @NotNull
        private final V value;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Success(@NotNull V value) {
            super(null);
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.value = value;
        }

        @NotNull
        public final V getValue() {
            return this.value;
        }

        @Override // com.github.kittinunf.result.Result
        @Nullable
        public V component1() {
            return this.value;
        }

        @Override // com.github.kittinunf.result.Result
        @Nullable
        public E component2() {
            return null;
        }

        @Override // com.github.kittinunf.result.Result
        @NotNull
        public V get() {
            return this.value;
        }

        @NotNull
        public String toString() {
            return "[Success: " + this.value + ']';
        }

        public int hashCode() {
            return this.value.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Success) && Intrinsics.areEqual(this.value, ((Success) other).value);
        }
    }

    /* compiled from: Result.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\n\b\u0002\u0010\u0001 \u0001*\u00020\u0002*\u000e\b\u0003\u0010\u0003 \u0001*\u00060\u0004j\u0002`\u00052\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0006B\r\u0012\u0006\u0010\u0007\u001a\u00028\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u0004\u0018\u00018\u0002H\u0096\u0002¢\u0006\u0002\u0010\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00018\u0003H\u0096\u0002¢\u0006\u0002\u0010\nJ\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002H\u0096\u0002J\r\u0010\u0012\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\rJ\u000b\u0010\u0013\u001a\u00028\u0003¢\u0006\u0002\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016R\u0013\u0010\u0007\u001a\u00028\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lcom/github/kittinunf/result/Result$Failure;", "V", "", "E", "Ljava/lang/Exception;", "Lkotlin/Exception;", "Lcom/github/kittinunf/result/Result;", "error", "(Ljava/lang/Exception;)V", "getError", "()Ljava/lang/Exception;", "Ljava/lang/Exception;", "component1", "()Ljava/lang/Object;", "component2", "equals", "", "other", "get", "getException", "hashCode", "", "toString", "", "result"}, k = 1, mv = {1, 1, 9})
    public static final class Failure<V, E extends Exception> extends Result<V, E> {

        @NotNull
        private final E error;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Failure(@NotNull E error) {
            super(null);
            Intrinsics.checkParameterIsNotNull(error, "error");
            this.error = error;
        }

        @NotNull
        public final E getError() {
            return this.error;
        }

        @Override // com.github.kittinunf.result.Result
        @Nullable
        public V component1() {
            return null;
        }

        @Override // com.github.kittinunf.result.Result
        @Nullable
        public E component2() {
            return this.error;
        }

        @Override // com.github.kittinunf.result.Result
        @NotNull
        public V get() {
            throw this.error;
        }

        @NotNull
        public final E getException() {
            return this.error;
        }

        @NotNull
        public String toString() {
            return "[Failure: " + this.error + ']';
        }

        public int hashCode() {
            return this.error.hashCode();
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof Failure) && Intrinsics.areEqual(this.error, ((Failure) other).error);
        }
    }

    /* compiled from: Result.kt */
    @Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00060\u0004\"\f\b\u0002\u0010\u0006*\u00060\u0007j\u0002`\b2\u0006\u0010\t\u001a\u0002H\u0006¢\u0006\u0002\u0010\nJ.\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u0002H\r\u0012\b\u0012\u00060\u0007j\u0002`\b0\f\"\b\b\u0002\u0010\r*\u00020\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\r0\u000fJC\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u0002H\r\u0012\b\u0012\u00060\u0007j\u0002`\b0\f\"\b\b\u0002\u0010\r*\u00020\u00012\b\u0010\u0010\u001a\u0004\u0018\u0001H\r2\u0012\b\u0002\u0010\u0011\u001a\f\u0012\b\u0012\u00060\u0007j\u0002`\b0\u000f¢\u0006\u0002\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/github/kittinunf/result/Result$Companion;", "", "()V", "error", "Lcom/github/kittinunf/result/Result$Failure;", "", "E", "Ljava/lang/Exception;", "Lkotlin/Exception;", "ex", "(Ljava/lang/Exception;)Lcom/github/kittinunf/result/Result$Failure;", "of", "Lcom/github/kittinunf/result/Result;", "V", "f", "Lkotlin/Function0;", "value", "fail", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Lcom/github/kittinunf/result/Result;", "result"}, k = 1, mv = {1, 1, 9})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker $constructor_marker) {
            this();
        }

        @NotNull
        public final <E extends Exception> Failure error(@NotNull E ex) {
            Intrinsics.checkParameterIsNotNull(ex, "ex");
            return new Failure(ex);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public static /* bridge */ /* synthetic */ Result of$default(Companion companion, Object obj, Function0 function0, int i, Object obj2) {
            if ((i & 2) != 0) {
                function0 = new Function0<Exception>() { // from class: com.github.kittinunf.result.Result$Companion$of$1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    @NotNull
                    public final Exception invoke() {
                        return new Exception();
                    }
                };
            }
            return companion.of(obj, function0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @NotNull
        public final <V> Result<V, Exception> of(@Nullable V value, @NotNull Function0<? extends Exception> fail) {
            Intrinsics.checkParameterIsNotNull(fail, "fail");
            return value != null ? new Success(value) : error(fail.invoke());
        }

        @NotNull
        public final <V> Result<V, Exception> of(@NotNull Function0<? extends V> f) {
            Intrinsics.checkParameterIsNotNull(f, "f");
            try {
                return new Success(f.invoke());
            } catch (Exception ex) {
                return new Failure(ex);
            }
        }
    }
}
