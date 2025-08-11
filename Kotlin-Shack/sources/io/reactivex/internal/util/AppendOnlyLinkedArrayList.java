package io.reactivex.internal.util;

import io.reactivex.functions.BiPredicate;
import io.reactivex.functions.Predicate;

/* loaded from: classes.dex */
public class AppendOnlyLinkedArrayList<T> {
    final int capacity;
    final Object[] head;
    int offset;
    Object[] tail;

    public interface NonThrowingPredicate<T> extends Predicate<T> {
        @Override // io.reactivex.functions.Predicate
        boolean test(T t);
    }

    public AppendOnlyLinkedArrayList(int capacity) {
        this.capacity = capacity;
        this.head = new Object[capacity + 1];
        this.tail = this.head;
    }

    public void add(T value) {
        int c = this.capacity;
        int o = this.offset;
        if (o == c) {
            Object[] next = new Object[c + 1];
            this.tail[c] = next;
            this.tail = next;
            o = 0;
        }
        this.tail[o] = value;
        this.offset = o + 1;
    }

    public void setFirst(T value) {
        this.head[0] = value;
    }

    public void forEachWhile(NonThrowingPredicate<? super T> consumer) {
        int i;
        int c = this.capacity;
        for (Object[] a = this.head; a != null; a = (Object[]) a[c]) {
            while (i < c) {
                Object o = a[i];
                i = (o == null || consumer.test(o)) ? 0 : i + 1;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <U> boolean accept(org.reactivestreams.Subscriber<? super U> r6) {
        /*
            r5 = this;
            java.lang.Object[] r0 = r5.head
            int r1 = r5.capacity
        L4:
            r2 = 0
            if (r0 == 0) goto L20
        L8:
            if (r2 >= r1) goto L1a
            r3 = r0[r2]
            if (r3 != 0) goto Lf
            goto L1a
        Lf:
            boolean r4 = io.reactivex.internal.util.NotificationLite.acceptFull(r3, r6)
            if (r4 == 0) goto L17
            r4 = 1
            return r4
        L17:
            int r2 = r2 + 1
            goto L8
        L1a:
            r2 = r0[r1]
            r0 = r2
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            goto L4
        L20:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.util.AppendOnlyLinkedArrayList.accept(org.reactivestreams.Subscriber):boolean");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x001a, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <U> boolean accept(io.reactivex.Observer<? super U> r6) {
        /*
            r5 = this;
            java.lang.Object[] r0 = r5.head
            int r1 = r5.capacity
        L4:
            r2 = 0
            if (r0 == 0) goto L20
        L8:
            if (r2 >= r1) goto L1a
            r3 = r0[r2]
            if (r3 != 0) goto Lf
            goto L1a
        Lf:
            boolean r4 = io.reactivex.internal.util.NotificationLite.acceptFull(r3, r6)
            if (r4 == 0) goto L17
            r4 = 1
            return r4
        L17:
            int r2 = r2 + 1
            goto L8
        L1a:
            r2 = r0[r1]
            r0 = r2
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            goto L4
        L20:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.util.AppendOnlyLinkedArrayList.accept(io.reactivex.Observer):boolean");
    }

    public <S> void forEachWhile(S state, BiPredicate<? super S, ? super T> consumer) throws Exception {
        Object[] a = this.head;
        int c = this.capacity;
        while (true) {
            for (int i = 0; i < c; i++) {
                Object o = a[i];
                if (o == null || consumer.test(state, o)) {
                    return;
                }
            }
            a = (Object[]) a[c];
        }
    }
}
