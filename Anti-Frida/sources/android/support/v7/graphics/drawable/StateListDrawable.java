package android.support.v7.graphics.drawable;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.appcompat.R;
import android.support.v7.graphics.drawable.DrawableContainer;
import android.util.AttributeSet;
import android.util.StateSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
/* JADX INFO: Access modifiers changed from: package-private */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class StateListDrawable extends DrawableContainer {
    private static final boolean DEBUG = false;
    private static final String TAG = "StateListDrawable";
    private boolean mMutated;
    private StateListState mStateListState;

    StateListDrawable() {
        this(null, null);
    }

    public void addState(int[] stateSet, Drawable drawable) {
        if (drawable != null) {
            this.mStateListState.addStateSet(stateSet, drawable);
            onStateChange(getState());
        }
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    public boolean onStateChange(int[] stateSet) {
        boolean changed = super.onStateChange(stateSet);
        int idx = this.mStateListState.indexOfStateSet(stateSet);
        if (idx < 0) {
            idx = this.mStateListState.indexOfStateSet(StateSet.WILD_CARD);
        }
        return selectDrawable(idx) || changed;
    }

    public void inflate(@NonNull Context context, @NonNull Resources r, @NonNull XmlPullParser parser, @NonNull AttributeSet attrs, @Nullable Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray a = TypedArrayUtils.obtainAttributes(r, theme, attrs, R.styleable.StateListDrawable);
        setVisible(a.getBoolean(R.styleable.StateListDrawable_android_visible, true), true);
        updateStateFromTypedArray(a);
        updateDensity(r);
        a.recycle();
        inflateChildElements(context, r, parser, attrs, theme);
        onStateChange(getState());
    }

    private void updateStateFromTypedArray(TypedArray a) {
        StateListState state = this.mStateListState;
        if (Build.VERSION.SDK_INT >= 21) {
            state.mChangingConfigurations |= a.getChangingConfigurations();
        }
        state.mVariablePadding = a.getBoolean(R.styleable.StateListDrawable_android_variablePadding, state.mVariablePadding);
        state.mConstantSize = a.getBoolean(R.styleable.StateListDrawable_android_constantSize, state.mConstantSize);
        state.mEnterFadeDuration = a.getInt(R.styleable.StateListDrawable_android_enterFadeDuration, state.mEnterFadeDuration);
        state.mExitFadeDuration = a.getInt(R.styleable.StateListDrawable_android_exitFadeDuration, state.mExitFadeDuration);
        state.mDither = a.getBoolean(R.styleable.StateListDrawable_android_dither, state.mDither);
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump */
    private void inflateChildElements(android.content.Context r18, android.content.res.Resources r19, org.xmlpull.v1.XmlPullParser r20, android.util.AttributeSet r21, android.content.res.Resources.Theme r22) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r17 = this;
            r0 = r17
            r1 = r21
            android.support.v7.graphics.drawable.StateListDrawable$StateListState r2 = r0.mStateListState
            int r3 = r20.getDepth()
            r4 = 1
            int r3 = r3 + r4
        L_0x000c:
            int r5 = r20.next()
            r6 = r5
            if (r5 == r4) goto L_0x00a0
            int r5 = r20.getDepth()
            r7 = r5
            if (r5 >= r3) goto L_0x001d
            r5 = 3
            if (r6 == r5) goto L_0x00a0
        L_0x001d:
            r5 = 2
            if (r6 == r5) goto L_0x0029
        L_0x0021:
            r13 = r18
            r9 = r19
            r10 = r22
            goto L_0x009d
        L_0x0029:
            if (r7 > r3) goto L_0x0021
            java.lang.String r8 = r20.getName()
            java.lang.String r9 = "item"
            boolean r8 = r8.equals(r9)
            if (r8 != 0) goto L_0x0038
            goto L_0x0021
        L_0x0038:
            int[] r8 = android.support.v7.appcompat.R.styleable.StateListDrawableItem
            r9 = r19
            r10 = r22
            android.content.res.TypedArray r8 = android.support.v4.content.res.TypedArrayUtils.obtainAttributes(r9, r10, r1, r8)
            r11 = 0
            int r12 = android.support.v7.appcompat.R.styleable.StateListDrawableItem_android_drawable
            r13 = -1
            int r12 = r8.getResourceId(r12, r13)
            if (r12 <= 0) goto L_0x0053
            r13 = r18
            android.graphics.drawable.Drawable r11 = android.support.v7.content.res.AppCompatResources.getDrawable(r13, r12)
            goto L_0x0055
        L_0x0053:
            r13 = r18
        L_0x0055:
            r8.recycle()
            int[] r14 = r0.extractStateSet(r1)
            if (r11 != 0) goto L_0x0099
        L_0x005e:
            int r15 = r20.next()
            r6 = r15
            r4 = 4
            if (r15 != r4) goto L_0x0068
            r4 = 1
            goto L_0x005e
        L_0x0068:
            if (r6 == r5) goto L_0x008a
            org.xmlpull.v1.XmlPullParserException r4 = new org.xmlpull.v1.XmlPullParserException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r15 = r20.getPositionDescription()
            r5.append(r15)
            java.lang.String r15 = ": <item> tag requires a 'drawable' attribute or "
            r5.append(r15)
            java.lang.String r15 = "child tag defining a drawable"
            r5.append(r15)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x008a:
            int r4 = android.os.Build.VERSION.SDK_INT
            r5 = 21
            if (r4 < r5) goto L_0x0095
            android.graphics.drawable.Drawable r11 = android.graphics.drawable.Drawable.createFromXmlInner(r19, r20, r21, r22)
            goto L_0x0099
        L_0x0095:
            android.graphics.drawable.Drawable r11 = android.graphics.drawable.Drawable.createFromXmlInner(r19, r20, r21)
        L_0x0099:
            r2.addStateSet(r14, r11)
        L_0x009d:
            r4 = 1
            goto L_0x000c
        L_0x00a0:
            r13 = r18
            r9 = r19
            r10 = r22
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.graphics.drawable.StateListDrawable.inflateChildElements(android.content.Context, android.content.res.Resources, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet, android.content.res.Resources$Theme):void");
    }

    int[] extractStateSet(AttributeSet attrs) {
        int numAttrs = attrs.getAttributeCount();
        int[] states = new int[numAttrs];
        int j = 0;
        for (int i = 0; i < numAttrs; i++) {
            int stateResId = attrs.getAttributeNameResource(i);
            if (!(stateResId == 0 || stateResId == 16842960 || stateResId == 16843161)) {
                int j2 = j + 1;
                states[j] = attrs.getAttributeBooleanValue(i, false) ? stateResId : -stateResId;
                j = j2;
            }
        }
        return StateSet.trimStateSet(states, j);
    }

    StateListState getStateListState() {
        return this.mStateListState;
    }

    int getStateCount() {
        return this.mStateListState.getChildCount();
    }

    int[] getStateSet(int index) {
        return this.mStateListState.mStateSets[index];
    }

    Drawable getStateDrawable(int index) {
        return this.mStateListState.getChild(index);
    }

    int getStateDrawableIndex(int[] stateSet) {
        return this.mStateListState.indexOfStateSet(stateSet);
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    @NonNull
    public Drawable mutate() {
        if (!this.mMutated && super.mutate() == this) {
            this.mStateListState.mutate();
            this.mMutated = true;
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public StateListState cloneConstantState() {
        return new StateListState(this.mStateListState, this, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public void clearMutated() {
        super.clearMutated();
        this.mMutated = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class StateListState extends DrawableContainer.DrawableContainerState {
        int[][] mStateSets;

        /* JADX INFO: Access modifiers changed from: package-private */
        public StateListState(StateListState orig, StateListDrawable owner, Resources res) {
            super(orig, owner, res);
            if (orig != null) {
                this.mStateSets = orig.mStateSets;
            } else {
                this.mStateSets = new int[getCapacity()];
            }
        }

        @Override // android.support.v7.graphics.drawable.DrawableContainer.DrawableContainerState
        void mutate() {
            int[][] stateSets = new int[this.mStateSets.length];
            for (int i = this.mStateSets.length - 1; i >= 0; i--) {
                stateSets[i] = this.mStateSets[i] != null ? (int[]) this.mStateSets[i].clone() : null;
            }
            this.mStateSets = stateSets;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int addStateSet(int[] stateSet, Drawable drawable) {
            int pos = addChild(drawable);
            this.mStateSets[pos] = stateSet;
            return pos;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public int indexOfStateSet(int[] stateSet) {
            int[][] stateSets = this.mStateSets;
            int count = getChildCount();
            for (int i = 0; i < count; i++) {
                if (StateSet.stateSetMatches(stateSets[i], stateSet)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable() {
            return new StateListDrawable(this, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        @NonNull
        public Drawable newDrawable(Resources res) {
            return new StateListDrawable(this, res);
        }

        @Override // android.support.v7.graphics.drawable.DrawableContainer.DrawableContainerState
        public void growArray(int oldSize, int newSize) {
            super.growArray(oldSize, newSize);
            int[][] newStateSets = new int[newSize];
            System.arraycopy(this.mStateSets, 0, newStateSets, 0, oldSize);
            this.mStateSets = newStateSets;
        }
    }

    @Override // android.support.v7.graphics.drawable.DrawableContainer, android.graphics.drawable.Drawable
    @RequiresApi(21)
    public void applyTheme(@NonNull Resources.Theme theme) {
        super.applyTheme(theme);
        onStateChange(getState());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.graphics.drawable.DrawableContainer
    public void setConstantState(@NonNull DrawableContainer.DrawableContainerState state) {
        super.setConstantState(state);
        if (state instanceof StateListState) {
            this.mStateListState = (StateListState) state;
        }
    }

    StateListDrawable(StateListState state, Resources res) {
        setConstantState(new StateListState(state, this, res));
        onStateChange(getState());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public StateListDrawable(@Nullable StateListState state) {
        if (state != null) {
            setConstantState(state);
        }
    }
}
