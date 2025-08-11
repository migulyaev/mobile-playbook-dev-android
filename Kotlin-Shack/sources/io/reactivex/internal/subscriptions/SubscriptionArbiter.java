package io.reactivex.internal.subscriptions;

import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.internal.LongCompanionObject;
import org.reactivestreams.Subscription;

/* loaded from: classes.dex */
public class SubscriptionArbiter extends AtomicInteger implements Subscription {
    private static final long serialVersionUID = -2189523197179400958L;
    Subscription actual;
    volatile boolean cancelled;
    long requested;
    protected boolean unbounded;
    final AtomicReference<Subscription> missedSubscription = new AtomicReference<>();
    final AtomicLong missedRequested = new AtomicLong();
    final AtomicLong missedProduced = new AtomicLong();

    public final void setSubscription(Subscription s) {
        if (this.cancelled) {
            s.cancel();
            return;
        }
        ObjectHelper.requireNonNull(s, "s is null");
        if (get() == 0 && compareAndSet(0, 1)) {
            Subscription a = this.actual;
            if (a != null) {
                a.cancel();
            }
            this.actual = s;
            long r = this.requested;
            if (decrementAndGet() != 0) {
                drainLoop();
            }
            if (r != 0) {
                s.request(r);
                return;
            }
            return;
        }
        Subscription a2 = this.missedSubscription.getAndSet(s);
        if (a2 != null) {
            a2.cancel();
        }
        drain();
    }

    @Override // org.reactivestreams.Subscription
    public final void request(long n) {
        if (!SubscriptionHelper.validate(n) || this.unbounded) {
            return;
        }
        if (get() == 0 && compareAndSet(0, 1)) {
            long r = this.requested;
            if (r != LongCompanionObject.MAX_VALUE) {
                long r2 = BackpressureHelper.addCap(r, n);
                this.requested = r2;
                if (r2 == LongCompanionObject.MAX_VALUE) {
                    this.unbounded = true;
                }
            }
            Subscription a = this.actual;
            if (decrementAndGet() != 0) {
                drainLoop();
            }
            if (a != null) {
                a.request(n);
                return;
            }
            return;
        }
        BackpressureHelper.add(this.missedRequested, n);
        drain();
    }

    public final void produced(long n) {
        if (this.unbounded) {
            return;
        }
        if (get() == 0 && compareAndSet(0, 1)) {
            long r = this.requested;
            if (r != LongCompanionObject.MAX_VALUE) {
                long u = r - n;
                if (u < 0) {
                    SubscriptionHelper.reportMoreProduced(u);
                    u = 0;
                }
                this.requested = u;
            }
            if (decrementAndGet() == 0) {
                return;
            }
            drainLoop();
            return;
        }
        BackpressureHelper.add(this.missedProduced, n);
        drain();
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            drain();
        }
    }

    final void drain() {
        if (getAndIncrement() != 0) {
            return;
        }
        drainLoop();
    }

    final void drainLoop() {
        int missed;
        long v;
        long requestAmount = 0;
        Subscription subscription = null;
        int missed2 = 1;
        Subscription requestTarget = null;
        while (true) {
            Subscription ms = this.missedSubscription.get();
            if (ms != null) {
                ms = this.missedSubscription.getAndSet(subscription);
            }
            long mr = this.missedRequested.get();
            if (mr != 0) {
                mr = this.missedRequested.getAndSet(0L);
            }
            long mp = this.missedProduced.get();
            if (mp != 0) {
                mp = this.missedProduced.getAndSet(0L);
            }
            Subscription a = this.actual;
            if (this.cancelled) {
                if (a != null) {
                    a.cancel();
                    this.actual = subscription;
                }
                if (ms != null) {
                    ms.cancel();
                }
                missed = missed2;
            } else {
                long r = this.requested;
                if (r != LongCompanionObject.MAX_VALUE) {
                    long u = BackpressureHelper.addCap(r, mr);
                    if (u != LongCompanionObject.MAX_VALUE) {
                        missed = missed2;
                        v = u - mp;
                        if (v < 0) {
                            SubscriptionHelper.reportMoreProduced(v);
                            v = 0;
                        }
                    } else {
                        missed = missed2;
                        v = u;
                    }
                    r = v;
                    this.requested = r;
                } else {
                    missed = missed2;
                }
                if (ms != null) {
                    if (a != null) {
                        a.cancel();
                    }
                    this.actual = ms;
                    if (r != 0) {
                        requestAmount = BackpressureHelper.addCap(requestAmount, r);
                        requestTarget = ms;
                    }
                } else if (a != null && mr != 0) {
                    requestAmount = BackpressureHelper.addCap(requestAmount, mr);
                    requestTarget = a;
                }
            }
            missed2 = addAndGet(-missed);
            if (missed2 == 0) {
                break;
            } else {
                subscription = null;
            }
        }
        if (requestAmount != 0) {
            requestTarget.request(requestAmount);
        }
    }

    public final boolean isUnbounded() {
        return this.unbounded;
    }

    public final boolean isCancelled() {
        return this.cancelled;
    }
}
