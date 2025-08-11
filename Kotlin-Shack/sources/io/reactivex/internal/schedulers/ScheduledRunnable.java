package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

/* loaded from: classes.dex */
public final class ScheduledRunnable extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, Disposable {
    static final int FUTURE_INDEX = 1;
    static final int PARENT_INDEX = 0;
    static final int THREAD_INDEX = 2;
    private static final long serialVersionUID = -6120223772001106981L;
    final Runnable actual;
    static final Object PARENT_DISPOSED = new Object();
    static final Object SYNC_DISPOSED = new Object();
    static final Object ASYNC_DISPOSED = new Object();
    static final Object DONE = new Object();

    public ScheduledRunnable(Runnable actual, DisposableContainer parent) {
        super(3);
        this.actual = actual;
        lazySet(0, parent);
    }

    @Override // java.util.concurrent.Callable
    public Object call() {
        run();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v20 */
    @Override // java.lang.Runnable
    public void run() {
        Object obj;
        Object obj2;
        int i = 2;
        i = 2;
        lazySet(2, Thread.currentThread());
        Object obj3 = null;
        try {
            try {
                this.actual.run();
            } catch (Throwable th) {
                RxJavaPlugins.onError(th);
            }
            if (obj3 != i && compareAndSet(0, obj3, DONE) && obj3 != null) {
                ((DisposableContainer) obj3).delete(this);
            }
            do {
                obj2 = get(1);
                if (obj2 == SYNC_DISPOSED || obj2 == ASYNC_DISPOSED) {
                    return;
                }
            } while (!compareAndSet(1, obj2, DONE));
        } finally {
            lazySet(i, obj3);
            Object obj4 = get(0);
            if (obj4 != PARENT_DISPOSED && compareAndSet(0, obj4, DONE) && obj4 != null) {
                ((DisposableContainer) obj4).delete(this);
            }
            do {
                obj = get(1);
                if (obj == SYNC_DISPOSED || obj == ASYNC_DISPOSED) {
                    break;
                }
            } while (!compareAndSet(1, obj, DONE));
        }
    }

    public void setFuture(Future<?> f) {
        Object o;
        do {
            o = get(1);
            if (o == DONE) {
                return;
            }
            if (o == SYNC_DISPOSED) {
                f.cancel(false);
                return;
            } else if (o == ASYNC_DISPOSED) {
                f.cancel(true);
                return;
            }
        } while (!compareAndSet(1, o, f));
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        Object o;
        while (true) {
            Object o2 = get(1);
            if (o2 == DONE || o2 == SYNC_DISPOSED || o2 == ASYNC_DISPOSED) {
                break;
            }
            boolean async = get(2) != Thread.currentThread();
            if (compareAndSet(1, o2, async ? ASYNC_DISPOSED : SYNC_DISPOSED)) {
                if (o2 != null) {
                    ((Future) o2).cancel(async);
                }
            }
        }
        do {
            o = get(0);
            if (o == DONE || o == PARENT_DISPOSED || o == null) {
                return;
            }
        } while (!compareAndSet(0, o, PARENT_DISPOSED));
        ((DisposableContainer) o).delete(this);
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        Object o = get(0);
        return o == PARENT_DISPOSED || o == DONE;
    }
}
