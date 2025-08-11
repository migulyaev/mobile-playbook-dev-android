package com.github.kittinunf.result;

import com.github.kittinunf.result.Result;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* compiled from: Result.kt */
@Metadata(bv = {1, 0, 2}, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a2\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003*\f\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u00030\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00010\u0006\u001a6\u0010\u0007\u001a\u00020\b\"\f\b\u0000\u0010\t*\u00060\nj\u0002`\u000b*\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\t0\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u00020\b0\u0006\u001aV\u0010\r\u001a\u0018\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000f0\u000e\u0012\u0002\b\u00030\u0004\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u000f*\u00020\u0003*\f\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u00030\u00042\u0016\u0010\u0010\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002H\u000f\u0012\u0002\b\u00030\u00040\u0011\u001ad\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\t0\u0004\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u000f*\u00020\u0003\"\f\b\u0002\u0010\t*\u00060\nj\u0002`\u000b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00042\u001e\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u0002H\u0002\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\t0\u00040\u0006\u001ah\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00150\u0004\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\f\b\u0001\u0010\t*\u00060\nj\u0002`\u000b\"\f\b\u0002\u0010\u0015*\u00060\nj\u0002`\u000b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00042\u001e\u0010\u0013\u001a\u001a\u0012\u0004\u0012\u0002H\t\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00150\u00040\u0006\u001a$\u0010\u0016\u001a\u0004\u0018\u0001H\u0017\"\u0006\b\u0000\u0010\u0017\u0018\u0001*\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0004H\u0086\b¢\u0006\u0002\u0010\u0018\u001a>\u0010\u0019\u001a\u0002H\u0002\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\f\b\u0001\u0010\t*\u00060\nj\u0002`\u000b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00042\u0006\u0010\u001a\u001a\u0002H\u0002H\u0086\u0004¢\u0006\u0002\u0010\u001b\u001aX\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u0002H\t0\u0004\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u000f*\u00020\u0003\"\f\b\u0002\u0010\t*\u00060\nj\u0002`\u000b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u000f0\u0006\u001a\\\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00150\u0004\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\f\b\u0001\u0010\t*\u00060\nj\u0002`\u000b\"\f\b\u0002\u0010\u0015*\u00060\nj\u0002`\u000b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00042\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\u00150\u0006\u001aJ\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u001f\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\f\b\u0001\u0010\t*\u00060\nj\u0002`\u000b*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\t0\u00042\u0006\u0010\u001a\u001a\u0002H\u0002H\u0086\u0004¢\u0006\u0002\u0010 \u001a2\u0010!\u001a\u00020\b\"\b\b\u0000\u0010\u0002*\u00020\u0003*\f\u0012\u0004\u0012\u0002H\u0002\u0012\u0002\b\u00030\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\b0\u0006¨\u0006\""}, d2 = {"any", "", "V", "", "Lcom/github/kittinunf/result/Result;", "predicate", "Lkotlin/Function1;", "failure", "", "E", "Ljava/lang/Exception;", "Lkotlin/Exception;", "f", "fanout", "Lkotlin/Pair;", "U", "other", "Lkotlin/Function0;", "flatMap", "transform", "flatMapError", "E2", "getAs", "X", "(Lcom/github/kittinunf/result/Result;)Ljava/lang/Object;", "getOrElse", "fallback", "(Lcom/github/kittinunf/result/Result;Ljava/lang/Object;)Ljava/lang/Object;", "map", "mapError", "or", "Lcom/github/kittinunf/result/Result$Success;", "(Lcom/github/kittinunf/result/Result;Ljava/lang/Object;)Lcom/github/kittinunf/result/Result$Success;", "success", "result"}, k = 2, mv = {1, 1, 9})
/* loaded from: classes.dex */
public final class ResultKt {
    private static final <X> X getAs(@NotNull Result<?, ?> result) {
        if (result instanceof Result.Success) {
            Object value = ((Result.Success) result).getValue();
            Intrinsics.reifiedOperationMarker(2, "X");
            return (X) value;
        }
        if (!(result instanceof Result.Failure)) {
            throw new NoWhenBranchMatchedException();
        }
        Object error = ((Result.Failure) result).getError();
        Intrinsics.reifiedOperationMarker(2, "X");
        return (X) error;
    }

    public static final <V> void success(@NotNull Result<? extends V, ?> receiver, @NotNull Function1<? super V, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (receiver instanceof Result.Success) {
            f.invoke((Object) ((Result.Success) receiver).getValue());
        } else {
            if (!(receiver instanceof Result.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            ((Result.Failure) receiver).getError();
        }
    }

    public static final <E extends Exception> void failure(@NotNull Result<?, ? extends E> receiver, @NotNull Function1<? super E, Unit> f) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        if (receiver instanceof Result.Success) {
            ((Result.Success) receiver).getValue();
        } else {
            if (!(receiver instanceof Result.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            f.invoke(((Result.Failure) receiver).getError());
        }
    }

    @NotNull
    public static final <V, E extends Exception> Result.Success<V, E> or(@NotNull Result<? extends V, ? extends E> receiver, @NotNull V fallback) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(fallback, "fallback");
        return receiver instanceof Result.Success ? (Result.Success) receiver : new Result.Success<>(fallback);
    }

    @NotNull
    public static final <V, E extends Exception> V getOrElse(@NotNull Result<? extends V, ? extends E> receiver, @NotNull V fallback) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(fallback, "fallback");
        return receiver instanceof Result.Success ? (V) ((Result.Success) receiver).getValue() : fallback;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <V, U, E extends Exception> Result<U, E> map(@NotNull Result<? extends V, ? extends E> receiver, @NotNull Function1<? super V, ? extends U> transform) {
        Result.Failure failure;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        try {
            if (receiver instanceof Result.Success) {
                failure = new Result.Success(transform.invoke((Object) ((Result.Success) receiver).getValue()));
            } else {
                if (!(receiver instanceof Result.Failure)) {
                    throw new NoWhenBranchMatchedException();
                }
                failure = new Result.Failure(((Result.Failure) receiver).getError());
            }
            return failure;
        } catch (Exception e) {
            Result.Companion companion = Result.INSTANCE;
            if (e == null) {
                throw new TypeCastException("null cannot be cast to non-null type E");
            }
            return companion.error(e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @NotNull
    public static final <V, U, E extends Exception> Result<U, E> flatMap(@NotNull Result<? extends V, ? extends E> receiver, @NotNull Function1<? super V, ? extends Result<? extends U, ? extends E>> transform) {
        Result.Failure failure;
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        try {
            if (receiver instanceof Result.Success) {
                failure = transform.invoke((Object) ((Result.Success) receiver).getValue());
            } else {
                if (!(receiver instanceof Result.Failure)) {
                    throw new NoWhenBranchMatchedException();
                }
                failure = new Result.Failure(((Result.Failure) receiver).getError());
            }
            return failure;
        } catch (Exception e) {
            Result.Companion companion = Result.INSTANCE;
            if (e == null) {
                throw new TypeCastException("null cannot be cast to non-null type E");
            }
            return companion.error(e);
        }
    }

    @NotNull
    public static final <V, E extends Exception, E2 extends Exception> Result<V, E2> mapError(@NotNull Result<? extends V, ? extends E> receiver, @NotNull Function1<? super E, ? extends E2> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        if (receiver instanceof Result.Success) {
            return new Result.Success(((Result.Success) receiver).getValue());
        }
        if (receiver instanceof Result.Failure) {
            return new Result.Failure(transform.invoke(((Result.Failure) receiver).getError()));
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public static final <V, E extends Exception, E2 extends Exception> Result<V, E2> flatMapError(@NotNull Result<? extends V, ? extends E> receiver, @NotNull Function1<? super E, ? extends Result<? extends V, ? extends E2>> transform) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        if (receiver instanceof Result.Success) {
            return new Result.Success(((Result.Success) receiver).getValue());
        }
        if (receiver instanceof Result.Failure) {
            return transform.invoke(((Result.Failure) receiver).getError());
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final <V> boolean any(@NotNull Result<? extends V, ?> receiver, @NotNull Function1<? super V, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if (receiver instanceof Result.Success) {
            return predicate.invoke((Object) ((Result.Success) receiver).getValue()).booleanValue();
        }
        if (receiver instanceof Result.Failure) {
            return false;
        }
        throw new NoWhenBranchMatchedException();
    }

    @NotNull
    public static final <V, U> Result<Pair<V, U>, ?> fanout(@NotNull Result<? extends V, ?> receiver, @NotNull final Function0<? extends Result<? extends U, ?>> other) {
        Intrinsics.checkParameterIsNotNull(receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(other, "other");
        return flatMap(receiver, new Function1<V, Result<? extends Pair<? extends V, ? extends U>, ? extends Exception>>() { // from class: com.github.kittinunf.result.ResultKt$fanout$1
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                return invoke((ResultKt$fanout$1<U, V>) obj);
            }

            @Override // kotlin.jvm.functions.Function1
            @NotNull
            public final Result<Pair<V, U>, Exception> invoke(@NotNull final V outer) {
                Intrinsics.checkParameterIsNotNull(outer, "outer");
                return ResultKt.map((Result) Function0.this.invoke(), new Function1<U, Pair<? extends V, ? extends U>>() { // from class: com.github.kittinunf.result.ResultKt$fanout$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        return invoke((AnonymousClass1) obj);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    @NotNull
                    public final Pair<V, U> invoke(@NotNull U it) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        return TuplesKt.to(outer, it);
                    }
                });
            }
        });
    }
}
