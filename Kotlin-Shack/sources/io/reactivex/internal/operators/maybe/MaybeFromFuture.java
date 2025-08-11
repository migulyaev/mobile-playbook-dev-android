package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: classes.dex */
public final class MaybeFromFuture<T> extends Maybe<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    public MaybeFromFuture(Future<? extends T> future, long timeout, TimeUnit unit) {
        this.future = future;
        this.timeout = timeout;
        this.unit = unit;
    }

    @Override // io.reactivex.Maybe
    protected void subscribeActual(MaybeObserver<? super T> maybeObserver) {
        T t;
        Disposable empty = Disposables.empty();
        maybeObserver.onSubscribe(empty);
        if (!empty.isDisposed()) {
            try {
                if (this.timeout <= 0) {
                    t = this.future.get();
                } else {
                    t = this.future.get(this.timeout, this.unit);
                }
                if (!empty.isDisposed()) {
                    if (t == null) {
                        maybeObserver.onComplete();
                    } else {
                        maybeObserver.onSuccess(t);
                    }
                }
            } catch (InterruptedException e) {
                if (!empty.isDisposed()) {
                    maybeObserver.onError(e);
                }
            } catch (ExecutionException e2) {
                if (!empty.isDisposed()) {
                    maybeObserver.onError(e2.getCause());
                }
            } catch (TimeoutException e3) {
                if (!empty.isDisposed()) {
                    maybeObserver.onError(e3);
                }
            }
        }
    }
}
