package io.reactivex.internal.disposables;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;

/* loaded from: classes.dex */
public final class ObserverFullArbiter<T> extends FullArbiterPad1 implements Disposable {
    final Observer<? super T> actual;
    volatile boolean cancelled;
    final SpscLinkedArrayQueue<Object> queue;
    Disposable resource;
    volatile Disposable s = EmptyDisposable.INSTANCE;

    public ObserverFullArbiter(Observer<? super T> actual, Disposable resource, int capacity) {
        this.actual = actual;
        this.resource = resource;
        this.queue = new SpscLinkedArrayQueue<>(capacity);
    }

    @Override // io.reactivex.disposables.Disposable
    public void dispose() {
        if (!this.cancelled) {
            this.cancelled = true;
            disposeResource();
        }
    }

    @Override // io.reactivex.disposables.Disposable
    public boolean isDisposed() {
        Disposable d = this.resource;
        return d != null ? d.isDisposed() : this.cancelled;
    }

    void disposeResource() {
        Disposable d = this.resource;
        this.resource = null;
        if (d != null) {
            d.dispose();
        }
    }

    public boolean setDisposable(Disposable s) {
        if (this.cancelled) {
            return false;
        }
        this.queue.offer(this.s, NotificationLite.disposable(s));
        drain();
        return true;
    }

    public boolean onNext(T value, Disposable s) {
        if (this.cancelled) {
            return false;
        }
        this.queue.offer(s, NotificationLite.next(value));
        drain();
        return true;
    }

    public void onError(Throwable value, Disposable s) {
        if (this.cancelled) {
            RxJavaPlugins.onError(value);
        } else {
            this.queue.offer(s, NotificationLite.error(value));
            drain();
        }
    }

    public void onComplete(Disposable s) {
        this.queue.offer(s, NotificationLite.complete());
        drain();
    }

    void drain() {
        if (this.wip.getAndIncrement() != 0) {
            return;
        }
        int i = 1;
        SpscLinkedArrayQueue<Object> spscLinkedArrayQueue = this.queue;
        Observer<? super T> observer = this.actual;
        while (true) {
            Object poll = spscLinkedArrayQueue.poll();
            if (poll != null) {
                Object poll2 = spscLinkedArrayQueue.poll();
                if (poll == this.s) {
                    if (NotificationLite.isDisposable(poll2)) {
                        Disposable disposable = NotificationLite.getDisposable(poll2);
                        this.s.dispose();
                        if (!this.cancelled) {
                            this.s = disposable;
                        } else {
                            disposable.dispose();
                        }
                    } else if (NotificationLite.isError(poll2)) {
                        spscLinkedArrayQueue.clear();
                        disposeResource();
                        Throwable error = NotificationLite.getError(poll2);
                        if (!this.cancelled) {
                            this.cancelled = true;
                            observer.onError(error);
                        } else {
                            RxJavaPlugins.onError(error);
                        }
                    } else if (NotificationLite.isComplete(poll2)) {
                        spscLinkedArrayQueue.clear();
                        disposeResource();
                        if (!this.cancelled) {
                            this.cancelled = true;
                            observer.onComplete();
                        }
                    } else {
                        observer.onNext((Object) NotificationLite.getValue(poll2));
                    }
                }
            } else {
                i = this.wip.addAndGet(-i);
                if (i == 0) {
                    return;
                }
            }
        }
    }
}
