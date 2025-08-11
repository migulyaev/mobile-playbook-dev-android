package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscriber;

/* loaded from: classes.dex */
public final class FlowableRangeLong extends Flowable<Long> {
    final long end;
    final long start;

    public FlowableRangeLong(long start, long count) {
        this.start = start;
        this.end = start + count;
    }

    @Override // io.reactivex.Flowable
    public void subscribeActual(Subscriber<? super Long> s) {
        if (s instanceof ConditionalSubscriber) {
            s.onSubscribe(new RangeConditionalSubscription((ConditionalSubscriber) s, this.start, this.end));
        } else {
            s.onSubscribe(new RangeSubscription(s, this.start, this.end));
        }
    }

    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Long> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final long end;
        long index;

        abstract void fastPath();

        abstract void slowPath(long j);

        BaseRangeSubscription(long index, long end) {
            this.index = index;
            this.end = end;
        }

        @Override // io.reactivex.internal.fuseable.QueueFuseable
        public final int requestFusion(int mode) {
            return mode & 1;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        @Nullable
        public final Long poll() {
            long i = this.index;
            if (i == this.end) {
                return null;
            }
            this.index = i + 1;
            return Long.valueOf(i);
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public final boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // io.reactivex.internal.fuseable.SimpleQueue
        public final void clear() {
            this.index = this.end;
        }

        @Override // org.reactivestreams.Subscription
        public final void request(long n) {
            if (SubscriptionHelper.validate(n) && BackpressureHelper.add(this, n) == 0) {
                if (n == LongCompanionObject.MAX_VALUE) {
                    fastPath();
                } else {
                    slowPath(n);
                }
            }
        }

        @Override // org.reactivestreams.Subscription
        public final void cancel() {
            this.cancelled = true;
        }
    }

    static final class RangeSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final Subscriber<? super Long> actual;

        RangeSubscription(Subscriber<? super Long> actual, long index, long end) {
            super(index, end);
            this.actual = actual;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void fastPath() {
            long f = this.end;
            Subscriber<? super Long> a = this.actual;
            for (long i = this.index; i != f; i++) {
                if (this.cancelled) {
                    return;
                }
                a.onNext(Long.valueOf(i));
            }
            if (this.cancelled) {
                return;
            }
            a.onComplete();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void slowPath(long r) {
            long i = 0;
            long f = this.end;
            long i2 = this.index;
            Subscriber<? super Long> a = this.actual;
            while (true) {
                if (i == r || i2 == f) {
                    if (i2 == f) {
                        if (!this.cancelled) {
                            a.onComplete();
                            return;
                        }
                        return;
                    }
                    r = get();
                    if (i == r) {
                        this.index = i2;
                        r = addAndGet(-i);
                        if (r == 0) {
                            return;
                        } else {
                            i = 0;
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (this.cancelled) {
                        return;
                    }
                    a.onNext(Long.valueOf(i2));
                    long e = i + 1;
                    long e2 = i2 + 1;
                    i2 = e2;
                    i = e;
                }
            }
        }
    }

    static final class RangeConditionalSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final ConditionalSubscriber<? super Long> actual;

        RangeConditionalSubscription(ConditionalSubscriber<? super Long> actual, long index, long end) {
            super(index, end);
            this.actual = actual;
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void fastPath() {
            long f = this.end;
            ConditionalSubscriber<? super Long> a = this.actual;
            for (long i = this.index; i != f; i++) {
                if (this.cancelled) {
                    return;
                }
                a.tryOnNext(Long.valueOf(i));
            }
            if (this.cancelled) {
                return;
            }
            a.onComplete();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableRangeLong.BaseRangeSubscription
        void slowPath(long r) {
            long e = 0;
            long f = this.end;
            long i = this.index;
            ConditionalSubscriber<? super Long> a = this.actual;
            while (true) {
                if (e == r || i == f) {
                    if (i == f) {
                        if (!this.cancelled) {
                            a.onComplete();
                            return;
                        }
                        return;
                    }
                    r = get();
                    if (e == r) {
                        this.index = i;
                        r = addAndGet(-e);
                        if (r == 0) {
                            return;
                        } else {
                            e = 0;
                        }
                    } else {
                        continue;
                    }
                } else {
                    if (this.cancelled) {
                        return;
                    }
                    if (a.tryOnNext(Long.valueOf(i))) {
                        e++;
                    }
                    long e2 = i + 1;
                    i = e2;
                }
            }
        }
    }
}
