package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes.dex */
public class ConstraintWidgetContainer extends WidgetContainer {
    private static final int CHAIN_FIRST = 0;
    private static final int CHAIN_FIRST_VISIBLE = 2;
    private static final int CHAIN_LAST = 1;
    private static final int CHAIN_LAST_VISIBLE = 3;
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_LAYOUT = false;
    private static final boolean DEBUG_OPTIMIZE = false;
    private static final int FLAG_CHAIN_DANGLING = 1;
    private static final int FLAG_CHAIN_OPTIMIZE = 0;
    private static final int FLAG_RECOMPUTE_BOUNDS = 2;
    private static final int MAX_ITERATIONS = 8;
    public static final int OPTIMIZATION_ALL = 2;
    public static final int OPTIMIZATION_BASIC = 4;
    public static final int OPTIMIZATION_CHAIN = 8;
    public static final int OPTIMIZATION_NONE = 1;
    private static final boolean USE_THREAD = false;
    private boolean[] flags;
    protected LinearSystem mBackgroundSystem;
    private ConstraintWidget[] mChainEnds;
    private boolean mHeightMeasuredTooSmall;
    private ConstraintWidget[] mHorizontalChainsArray;
    private int mHorizontalChainsSize;
    private ConstraintWidget[] mMatchConstraintsChainedWidgets;
    private int mOptimizationLevel;
    int mPaddingBottom;
    int mPaddingLeft;
    int mPaddingRight;
    int mPaddingTop;
    private Snapshot mSnapshot;
    protected LinearSystem mSystem;
    private ConstraintWidget[] mVerticalChainsArray;
    private int mVerticalChainsSize;
    private boolean mWidthMeasuredTooSmall;
    int mWrapHeight;
    int mWrapWidth;
    private static final boolean USE_SNAPSHOT = true;
    static boolean ALLOW_ROOT_GROUP = USE_SNAPSHOT;

    public ConstraintWidgetContainer() {
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public ConstraintWidgetContainer(int width, int height) {
        super(width, height);
        this.mSystem = new LinearSystem();
        this.mBackgroundSystem = null;
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
        this.mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
        this.mVerticalChainsArray = new ConstraintWidget[4];
        this.mHorizontalChainsArray = new ConstraintWidget[4];
        this.mOptimizationLevel = 2;
        this.flags = new boolean[3];
        this.mChainEnds = new ConstraintWidget[4];
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
    }

    public void setOptimizationLevel(int value) {
        this.mOptimizationLevel = value;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public String getType() {
        return "ConstraintLayout";
    }

    @Override // android.support.constraint.solver.widgets.WidgetContainer, android.support.constraint.solver.widgets.ConstraintWidget
    public void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingRight = 0;
        this.mPaddingTop = 0;
        this.mPaddingBottom = 0;
        super.reset();
    }

    public boolean isWidthMeasuredTooSmall() {
        return this.mWidthMeasuredTooSmall;
    }

    public boolean isHeightMeasuredTooSmall() {
        return this.mHeightMeasuredTooSmall;
    }

    public static ConstraintWidgetContainer createContainer(ConstraintWidgetContainer container, String name, ArrayList<ConstraintWidget> widgets, int padding) {
        Rectangle bounds = getBounds(widgets);
        if (bounds.width == 0 || bounds.height == 0) {
            return null;
        }
        if (padding > 0) {
            int maxPadding = Math.min(bounds.x, bounds.y);
            if (padding > maxPadding) {
                padding = maxPadding;
            }
            bounds.grow(padding, padding);
        }
        container.setOrigin(bounds.x, bounds.y);
        container.setDimension(bounds.width, bounds.height);
        container.setDebugName(name);
        ConstraintWidget parent = widgets.get(0).getParent();
        int widgetsSize = widgets.size();
        for (int i = 0; i < widgetsSize; i++) {
            ConstraintWidget widget = widgets.get(i);
            if (widget.getParent() == parent) {
                container.add(widget);
                widget.setX(widget.getX() - bounds.x);
                widget.setY(widget.getY() - bounds.y);
            }
        }
        return container;
    }

    public boolean addChildrenToSolver(LinearSystem system, int group) {
        addToSolver(system, group);
        int count = this.mChildren.size();
        boolean setMatchParent = false;
        int i = 0;
        if (this.mOptimizationLevel != 2 && this.mOptimizationLevel != 4) {
            setMatchParent = USE_SNAPSHOT;
        } else if (optimize(system)) {
            return false;
        }
        while (true) {
            int i2 = i;
            if (i2 >= count) {
                break;
            }
            ConstraintWidget widget = this.mChildren.get(i2);
            if (widget instanceof ConstraintWidgetContainer) {
                ConstraintWidget.DimensionBehaviour horizontalBehaviour = widget.mHorizontalDimensionBehaviour;
                ConstraintWidget.DimensionBehaviour verticalBehaviour = widget.mVerticalDimensionBehaviour;
                if (horizontalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                if (verticalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
                }
                widget.addToSolver(system, group);
                if (horizontalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setHorizontalDimensionBehaviour(horizontalBehaviour);
                }
                if (verticalBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    widget.setVerticalDimensionBehaviour(verticalBehaviour);
                }
            } else {
                if (setMatchParent) {
                    Optimizer.checkMatchParent(this, system, widget);
                }
                widget.addToSolver(system, group);
            }
            i = i2 + 1;
        }
        if (this.mHorizontalChainsSize > 0) {
            applyHorizontalChain(system);
        }
        if (this.mVerticalChainsSize <= 0) {
            return USE_SNAPSHOT;
        }
        applyVerticalChain(system);
        return USE_SNAPSHOT;
    }

    private boolean optimize(LinearSystem system) {
        int count = this.mChildren.size();
        boolean done = false;
        int dv = 0;
        int dv2 = 0;
        int n = 0;
        for (int i = 0; i < count; i++) {
            ConstraintWidget widget = this.mChildren.get(i);
            widget.mHorizontalResolution = -1;
            widget.mVerticalResolution = -1;
            if (widget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                widget.mHorizontalResolution = 1;
                widget.mVerticalResolution = 1;
            }
        }
        while (!done) {
            int prev = dv;
            int preh = dv2;
            n++;
            int dh = 0;
            int dv3 = 0;
            for (int dv4 = 0; dv4 < count; dv4++) {
                ConstraintWidget widget2 = this.mChildren.get(dv4);
                if (widget2.mHorizontalResolution == -1) {
                    if (this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        widget2.mHorizontalResolution = 1;
                    } else {
                        Optimizer.checkHorizontalSimpleDependency(this, system, widget2);
                    }
                }
                if (widget2.mVerticalResolution == -1) {
                    if (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                        widget2.mVerticalResolution = 1;
                    } else {
                        Optimizer.checkVerticalSimpleDependency(this, system, widget2);
                    }
                }
                if (widget2.mVerticalResolution == -1) {
                    dv3++;
                }
                if (widget2.mHorizontalResolution == -1) {
                    dh++;
                }
            }
            if (dv3 == 0 && dh == 0) {
                done = USE_SNAPSHOT;
            } else if (prev == dv3 && preh == dh) {
                done = USE_SNAPSHOT;
            }
            dv = dv3;
            dv2 = dh;
        }
        int sv = 0;
        int sv2 = 0;
        for (int sh = 0; sh < count; sh++) {
            ConstraintWidget widget3 = this.mChildren.get(sh);
            if (widget3.mHorizontalResolution == 1 || widget3.mHorizontalResolution == -1) {
                sv2++;
            }
            if (widget3.mVerticalResolution == 1 || widget3.mVerticalResolution == -1) {
                sv++;
            }
        }
        if (sv2 == 0 && sv == 0) {
            return USE_SNAPSHOT;
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0524  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0527  */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v67 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void applyHorizontalChain(android.support.constraint.solver.LinearSystem r41) {
        /*
            Method dump skipped, instructions count: 1464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.applyHorizontalChain(android.support.constraint.solver.LinearSystem):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:58:0x054a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x054d  */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v70 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void applyVerticalChain(android.support.constraint.solver.LinearSystem r43) {
        /*
            Method dump skipped, instructions count: 1502
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.applyVerticalChain(android.support.constraint.solver.LinearSystem):void");
    }

    public void updateChildrenFromSolver(LinearSystem system, int group, boolean[] flags) {
        flags[2] = false;
        updateFromSolver(system, group);
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            ConstraintWidget widget = this.mChildren.get(i);
            widget.updateFromSolver(system, group);
            if (widget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.getWidth() < widget.getWrapWidth()) {
                flags[2] = USE_SNAPSHOT;
            }
            if (widget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.getHeight() < widget.getWrapHeight()) {
                flags[2] = USE_SNAPSHOT;
            }
        }
    }

    public void setPadding(int left, int top, int right, int bottom) {
        this.mPaddingLeft = left;
        this.mPaddingTop = top;
        this.mPaddingRight = right;
        this.mPaddingBottom = bottom;
    }

    @Override // android.support.constraint.solver.widgets.WidgetContainer
    public void layout() {
        boolean needsSolving;
        int prex = this.mX;
        int prey = this.mY;
        int prew = Math.max(0, getWidth());
        int preh = Math.max(0, getHeight());
        this.mWidthMeasuredTooSmall = false;
        this.mHeightMeasuredTooSmall = false;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            setX(this.mPaddingLeft);
            setY(this.mPaddingTop);
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        boolean wrap_override = false;
        ConstraintWidget.DimensionBehaviour originalVerticalDimensionBehaviour = this.mVerticalDimensionBehaviour;
        ConstraintWidget.DimensionBehaviour originalHorizontalDimensionBehaviour = this.mHorizontalDimensionBehaviour;
        if (this.mOptimizationLevel == 2 && (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT || this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)) {
            findWrapSize(this.mChildren, this.flags);
            wrap_override = this.flags[0];
            if (prew > 0 && preh > 0 && (this.mWrapWidth > prew || this.mWrapHeight > preh)) {
                wrap_override = false;
            }
            if (wrap_override) {
                if (this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    if (prew <= 0 || prew >= this.mWrapWidth) {
                        setWidth(Math.max(this.mMinWidth, this.mWrapWidth));
                    } else {
                        this.mWidthMeasuredTooSmall = USE_SNAPSHOT;
                        setWidth(prew);
                    }
                }
                if (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) {
                    this.mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    if (preh <= 0 || preh >= this.mWrapHeight) {
                        setHeight(Math.max(this.mMinHeight, this.mWrapHeight));
                    } else {
                        this.mHeightMeasuredTooSmall = USE_SNAPSHOT;
                        setHeight(preh);
                    }
                }
            }
        }
        resetChains();
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            ConstraintWidget widget = this.mChildren.get(i);
            if (widget instanceof WidgetContainer) {
                ((WidgetContainer) widget).layout();
            }
        }
        boolean needsSolving2 = USE_SNAPSHOT;
        boolean wrap_override2 = wrap_override;
        int countSolve = 0;
        while (needsSolving2) {
            countSolve++;
            try {
                this.mSystem.reset();
                needsSolving2 = addChildrenToSolver(this.mSystem, Integer.MAX_VALUE);
                if (needsSolving2) {
                    this.mSystem.minimize();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (needsSolving2) {
                updateChildrenFromSolver(this.mSystem, Integer.MAX_VALUE, this.flags);
            } else {
                updateFromSolver(this.mSystem, Integer.MAX_VALUE);
                int i2 = 0;
                while (true) {
                    if (i2 >= count) {
                        break;
                    }
                    ConstraintWidget widget2 = this.mChildren.get(i2);
                    if (widget2.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget2.getWidth() < widget2.getWrapWidth()) {
                        this.flags[2] = USE_SNAPSHOT;
                        break;
                    } else if (widget2.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT || widget2.getHeight() >= widget2.getWrapHeight()) {
                        i2++;
                    } else {
                        this.flags[2] = USE_SNAPSHOT;
                        break;
                    }
                }
            }
            boolean needsSolving3 = false;
            if (countSolve >= 8 || !this.flags[2]) {
                needsSolving = false;
            } else {
                int maxY = 0;
                int maxX = 0;
                int maxX2 = 0;
                while (maxX2 < count) {
                    ConstraintWidget widget3 = this.mChildren.get(maxX2);
                    maxX = Math.max(maxX, widget3.mX + widget3.getWidth());
                    maxY = Math.max(maxY, widget3.mY + widget3.getHeight());
                    maxX2++;
                    needsSolving3 = needsSolving3;
                }
                needsSolving = needsSolving3;
                int maxX3 = Math.max(this.mMinWidth, maxX);
                int maxY2 = Math.max(this.mMinHeight, maxY);
                if (originalHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && getWidth() < maxX3) {
                    setWidth(maxX3);
                    this.mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    wrap_override2 = USE_SNAPSHOT;
                    needsSolving = true;
                }
                if (originalVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && getHeight() < maxY2) {
                    setHeight(maxY2);
                    this.mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
                    wrap_override2 = USE_SNAPSHOT;
                    needsSolving = true;
                }
            }
            int width = Math.max(this.mMinWidth, getWidth());
            if (width > getWidth()) {
                setWidth(width);
                this.mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                wrap_override2 = USE_SNAPSHOT;
                needsSolving = USE_SNAPSHOT;
            }
            int height = Math.max(this.mMinHeight, getHeight());
            if (height > getHeight()) {
                setHeight(height);
                this.mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                wrap_override2 = USE_SNAPSHOT;
                needsSolving = USE_SNAPSHOT;
            }
            if (!wrap_override2) {
                if (this.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && prew > 0 && getWidth() > prew) {
                    this.mWidthMeasuredTooSmall = USE_SNAPSHOT;
                    wrap_override2 = USE_SNAPSHOT;
                    this.mHorizontalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    setWidth(prew);
                    needsSolving = USE_SNAPSHOT;
                }
                if (this.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT && preh > 0 && getHeight() > preh) {
                    this.mHeightMeasuredTooSmall = USE_SNAPSHOT;
                    this.mVerticalDimensionBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
                    setHeight(preh);
                    wrap_override2 = true;
                    needsSolving2 = true;
                }
            }
            needsSolving2 = needsSolving;
        }
        if (this.mParent != null) {
            int width2 = Math.max(this.mMinWidth, getWidth());
            int height2 = Math.max(this.mMinHeight, getHeight());
            this.mSnapshot.applyTo(this);
            setWidth(this.mPaddingLeft + width2 + this.mPaddingRight);
            setHeight(this.mPaddingTop + height2 + this.mPaddingBottom);
        } else {
            this.mX = prex;
            this.mY = prey;
        }
        if (wrap_override2) {
            this.mHorizontalDimensionBehaviour = originalHorizontalDimensionBehaviour;
            this.mVerticalDimensionBehaviour = originalVerticalDimensionBehaviour;
        }
        resetSolverVariables(this.mSystem.getCache());
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    static int setGroup(ConstraintAnchor anchor, int group) {
        int oldGroup = anchor.mGroup;
        if (anchor.mOwner.getParent() == null) {
            return group;
        }
        if (oldGroup <= group) {
            return oldGroup;
        }
        anchor.mGroup = group;
        ConstraintAnchor opposite = anchor.getOpposite();
        ConstraintAnchor target = anchor.mTarget;
        int group2 = opposite != null ? setGroup(opposite, group) : group;
        int group3 = target != null ? setGroup(target, group2) : group2;
        int group4 = opposite != null ? setGroup(opposite, group3) : group3;
        anchor.mGroup = group4;
        return group4;
    }

    public int layoutFindGroupsSimple() {
        int size = this.mChildren.size();
        for (int j = 0; j < size; j++) {
            ConstraintWidget widget = this.mChildren.get(j);
            widget.mLeft.mGroup = 0;
            widget.mRight.mGroup = 0;
            widget.mTop.mGroup = 1;
            widget.mBottom.mGroup = 1;
            widget.mBaseline.mGroup = 1;
        }
        return 2;
    }

    public void findHorizontalWrapRecursive(ConstraintWidget widget, boolean[] flags) {
        boolean z = false;
        if (widget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mDimensionRatio > 0.0f) {
            flags[0] = false;
            return;
        }
        int w = widget.getOptimizerWrapWidth();
        if (widget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mDimensionRatio > 0.0f) {
            flags[0] = false;
            return;
        }
        int distToRight = w;
        int distToLeft = w;
        ConstraintWidget leftWidget = null;
        ConstraintWidget rightWidget = null;
        widget.mHorizontalWrapVisited = USE_SNAPSHOT;
        if (widget instanceof Guideline) {
            Guideline guideline = (Guideline) widget;
            if (guideline.getOrientation() == 1) {
                distToLeft = 0;
                distToRight = 0;
                if (guideline.getRelativeBegin() != -1) {
                    distToLeft = guideline.getRelativeBegin();
                } else if (guideline.getRelativeEnd() != -1) {
                    distToRight = guideline.getRelativeEnd();
                }
            }
        } else if (!widget.mRight.isConnected() && !widget.mLeft.isConnected()) {
            distToLeft += widget.getX();
        } else {
            if (widget.mRight.mTarget != null && widget.mLeft.mTarget != null && (widget.mRight.mTarget == widget.mLeft.mTarget || (widget.mRight.mTarget.mOwner == widget.mLeft.mTarget.mOwner && widget.mRight.mTarget.mOwner != widget.mParent))) {
                flags[0] = false;
                return;
            }
            if (widget.mRight.mTarget != null) {
                rightWidget = widget.mRight.mTarget.mOwner;
                distToRight += widget.mRight.getMargin();
                if (!rightWidget.isRoot() && !rightWidget.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(rightWidget, flags);
                }
            }
            if (widget.mLeft.mTarget != null) {
                leftWidget = widget.mLeft.mTarget.mOwner;
                distToLeft += widget.mLeft.getMargin();
                if (!leftWidget.isRoot() && !leftWidget.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(leftWidget, flags);
                }
            }
            if (widget.mRight.mTarget != null && !rightWidget.isRoot()) {
                if (widget.mRight.mTarget.mType == ConstraintAnchor.Type.RIGHT) {
                    distToRight += rightWidget.mDistToRight - rightWidget.getOptimizerWrapWidth();
                } else if (widget.mRight.mTarget.getType() == ConstraintAnchor.Type.LEFT) {
                    distToRight += rightWidget.mDistToRight;
                }
                widget.mRightHasCentered = rightWidget.mRightHasCentered || !(rightWidget.mLeft.mTarget == null || rightWidget.mRight.mTarget == null || rightWidget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                if (widget.mRightHasCentered && (rightWidget.mLeft.mTarget == null || rightWidget.mLeft.mTarget.mOwner != widget)) {
                    distToRight += distToRight - rightWidget.mDistToRight;
                }
            }
            if (widget.mLeft.mTarget != null && !leftWidget.isRoot()) {
                if (widget.mLeft.mTarget.getType() == ConstraintAnchor.Type.LEFT) {
                    distToLeft += leftWidget.mDistToLeft - leftWidget.getOptimizerWrapWidth();
                } else if (widget.mLeft.mTarget.getType() == ConstraintAnchor.Type.RIGHT) {
                    distToLeft += leftWidget.mDistToLeft;
                }
                if (leftWidget.mLeftHasCentered || (leftWidget.mLeft.mTarget != null && leftWidget.mRight.mTarget != null && leftWidget.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)) {
                    z = true;
                }
                widget.mLeftHasCentered = z;
                if (widget.mLeftHasCentered && (leftWidget.mRight.mTarget == null || leftWidget.mRight.mTarget.mOwner != widget)) {
                    distToLeft += distToLeft - leftWidget.mDistToLeft;
                }
            }
        }
        if (widget.getVisibility() == 8) {
            distToLeft -= widget.mWidth;
            distToRight -= widget.mWidth;
        }
        widget.mDistToLeft = distToLeft;
        widget.mDistToRight = distToRight;
    }

    public void findVerticalWrapRecursive(ConstraintWidget widget, boolean[] flags) {
        boolean z = false;
        if (widget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mHorizontalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT && widget.mDimensionRatio > 0.0f) {
            flags[0] = false;
            return;
        }
        int h = widget.getOptimizerWrapHeight();
        int distToTop = h;
        int distToBottom = h;
        ConstraintWidget topWidget = null;
        ConstraintWidget bottomWidget = null;
        widget.mVerticalWrapVisited = USE_SNAPSHOT;
        if (widget instanceof Guideline) {
            Guideline guideline = (Guideline) widget;
            if (guideline.getOrientation() == 0) {
                distToTop = 0;
                distToBottom = 0;
                if (guideline.getRelativeBegin() != -1) {
                    distToTop = guideline.getRelativeBegin();
                } else if (guideline.getRelativeEnd() != -1) {
                    distToBottom = guideline.getRelativeEnd();
                }
            }
        } else if (widget.mBaseline.mTarget == null && widget.mTop.mTarget == null && widget.mBottom.mTarget == null) {
            distToTop += widget.getY();
        } else {
            if (widget.mBottom.mTarget != null && widget.mTop.mTarget != null && (widget.mBottom.mTarget == widget.mTop.mTarget || (widget.mBottom.mTarget.mOwner == widget.mTop.mTarget.mOwner && widget.mBottom.mTarget.mOwner != widget.mParent))) {
                flags[0] = false;
                return;
            }
            if (widget.mBaseline.isConnected()) {
                ConstraintWidget baseLineWidget = widget.mBaseline.mTarget.getOwner();
                if (!baseLineWidget.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(baseLineWidget, flags);
                }
                int distToTop2 = Math.max((baseLineWidget.mDistToTop - baseLineWidget.mHeight) + h, h);
                int distToBottom2 = Math.max((baseLineWidget.mDistToBottom - baseLineWidget.mHeight) + h, h);
                if (widget.getVisibility() == 8) {
                    distToTop2 -= widget.mHeight;
                    distToBottom2 -= widget.mHeight;
                }
                widget.mDistToTop = distToTop2;
                widget.mDistToBottom = distToBottom2;
                return;
            }
            if (widget.mTop.isConnected()) {
                topWidget = widget.mTop.mTarget.getOwner();
                distToTop += widget.mTop.getMargin();
                if (!topWidget.isRoot() && !topWidget.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(topWidget, flags);
                }
            }
            if (widget.mBottom.isConnected()) {
                bottomWidget = widget.mBottom.mTarget.getOwner();
                distToBottom += widget.mBottom.getMargin();
                if (!bottomWidget.isRoot() && !bottomWidget.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(bottomWidget, flags);
                }
            }
            if (widget.mTop.mTarget != null && !topWidget.isRoot()) {
                if (widget.mTop.mTarget.getType() == ConstraintAnchor.Type.TOP) {
                    distToTop += topWidget.mDistToTop - topWidget.getOptimizerWrapHeight();
                } else if (widget.mTop.mTarget.getType() == ConstraintAnchor.Type.BOTTOM) {
                    distToTop += topWidget.mDistToTop;
                }
                widget.mTopHasCentered = topWidget.mTopHasCentered || !(topWidget.mTop.mTarget == null || topWidget.mTop.mTarget.mOwner == widget || topWidget.mBottom.mTarget == null || topWidget.mBottom.mTarget.mOwner == widget || topWidget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT);
                if (widget.mTopHasCentered && (topWidget.mBottom.mTarget == null || topWidget.mBottom.mTarget.mOwner != widget)) {
                    distToTop += distToTop - topWidget.mDistToTop;
                }
            }
            if (widget.mBottom.mTarget != null && !bottomWidget.isRoot()) {
                if (widget.mBottom.mTarget.getType() == ConstraintAnchor.Type.BOTTOM) {
                    distToBottom += bottomWidget.mDistToBottom - bottomWidget.getOptimizerWrapHeight();
                } else if (widget.mBottom.mTarget.getType() == ConstraintAnchor.Type.TOP) {
                    distToBottom += bottomWidget.mDistToBottom;
                }
                if (bottomWidget.mBottomHasCentered || (bottomWidget.mTop.mTarget != null && bottomWidget.mTop.mTarget.mOwner != widget && bottomWidget.mBottom.mTarget != null && bottomWidget.mBottom.mTarget.mOwner != widget && bottomWidget.mVerticalDimensionBehaviour != ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT)) {
                    z = true;
                }
                widget.mBottomHasCentered = z;
                if (widget.mBottomHasCentered && (bottomWidget.mTop.mTarget == null || bottomWidget.mTop.mTarget.mOwner != widget)) {
                    distToBottom += distToBottom - bottomWidget.mDistToBottom;
                }
            }
        }
        if (widget.getVisibility() == 8) {
            distToTop -= widget.mHeight;
            distToBottom -= widget.mHeight;
        }
        widget.mDistToTop = distToTop;
        widget.mDistToBottom = distToBottom;
    }

    public void findWrapSize(ArrayList<ConstraintWidget> children, boolean[] flags) {
        int maxLeftDist = 0;
        int maxRightDist = 0;
        int maxConnectWidth = 0;
        int size = children.size();
        char c = 0;
        flags[0] = USE_SNAPSHOT;
        int maxConnectHeight = 0;
        int maxBottomDist = 0;
        int maxTopDist = 0;
        int maxTopDist2 = 0;
        while (maxTopDist2 < size) {
            ConstraintWidget widget = children.get(maxTopDist2);
            if (!widget.isRoot()) {
                if (!widget.mHorizontalWrapVisited) {
                    findHorizontalWrapRecursive(widget, flags);
                }
                if (!widget.mVerticalWrapVisited) {
                    findVerticalWrapRecursive(widget, flags);
                }
                if (!flags[c]) {
                    return;
                }
                int connectWidth = (widget.mDistToLeft + widget.mDistToRight) - widget.getWidth();
                int connectHeight = (widget.mDistToTop + widget.mDistToBottom) - widget.getHeight();
                if (widget.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                    connectWidth = widget.getWidth() + widget.mLeft.mMargin + widget.mRight.mMargin;
                }
                if (widget.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_PARENT) {
                    connectHeight = widget.getHeight() + widget.mTop.mMargin + widget.mBottom.mMargin;
                }
                if (widget.getVisibility() == 8) {
                    connectWidth = 0;
                    connectHeight = 0;
                }
                maxLeftDist = Math.max(maxLeftDist, widget.mDistToLeft);
                maxRightDist = Math.max(maxRightDist, widget.mDistToRight);
                maxBottomDist = Math.max(maxBottomDist, widget.mDistToBottom);
                maxTopDist = Math.max(maxTopDist, widget.mDistToTop);
                maxConnectWidth = Math.max(maxConnectWidth, connectWidth);
                maxConnectHeight = Math.max(maxConnectHeight, connectHeight);
            }
            maxTopDist2++;
            c = 0;
        }
        int max = Math.max(maxLeftDist, maxRightDist);
        this.mWrapWidth = Math.max(this.mMinWidth, Math.max(max, maxConnectWidth));
        int max2 = Math.max(maxTopDist, maxBottomDist);
        this.mWrapHeight = Math.max(this.mMinHeight, Math.max(max2, maxConnectHeight));
        for (int j = 0; j < size; j++) {
            ConstraintWidget child = children.get(j);
            child.mHorizontalWrapVisited = false;
            child.mVerticalWrapVisited = false;
            child.mLeftHasCentered = false;
            child.mRightHasCentered = false;
            child.mTopHasCentered = false;
            child.mBottomHasCentered = false;
        }
    }

    public int layoutFindGroups() {
        int index;
        int i = 0;
        ConstraintAnchor.Type[] dir = {ConstraintAnchor.Type.LEFT, ConstraintAnchor.Type.RIGHT, ConstraintAnchor.Type.TOP, ConstraintAnchor.Type.BASELINE, ConstraintAnchor.Type.BOTTOM};
        int size = this.mChildren.size();
        int label = 1;
        for (int label2 = 0; label2 < size; label2++) {
            ConstraintWidget widget = this.mChildren.get(label2);
            ConstraintAnchor anchor = widget.mLeft;
            if (anchor.mTarget == null) {
                anchor.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(anchor, label) == label) {
                label++;
            }
            ConstraintAnchor anchor2 = widget.mTop;
            if (anchor2.mTarget == null) {
                anchor2.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(anchor2, label) == label) {
                label++;
            }
            ConstraintAnchor anchor3 = widget.mRight;
            if (anchor3.mTarget == null) {
                anchor3.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(anchor3, label) == label) {
                label++;
            }
            ConstraintAnchor anchor4 = widget.mBottom;
            if (anchor4.mTarget == null) {
                anchor4.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(anchor4, label) == label) {
                label++;
            }
            ConstraintAnchor anchor5 = widget.mBaseline;
            if (anchor5.mTarget == null) {
                anchor5.mGroup = Integer.MAX_VALUE;
            } else if (setGroup(anchor5, label) == label) {
                label++;
            }
        }
        int count = 0;
        int count2 = 1;
        int j = 0;
        while (count2 != 0) {
            count2 = 0;
            count++;
            int fix = j;
            int fix2 = i;
            while (fix2 < size) {
                ConstraintWidget widget2 = this.mChildren.get(fix2);
                int fix3 = fix;
                int fix4 = count2;
                for (int i2 = i; i2 < dir.length; i2++) {
                    ConstraintAnchor.Type type = dir[i2];
                    ConstraintAnchor anchor6 = null;
                    switch (type) {
                        case LEFT:
                            anchor6 = widget2.mLeft;
                            break;
                        case TOP:
                            anchor6 = widget2.mTop;
                            break;
                        case RIGHT:
                            anchor6 = widget2.mRight;
                            break;
                        case BOTTOM:
                            anchor6 = widget2.mBottom;
                            break;
                        case BASELINE:
                            anchor6 = widget2.mBaseline;
                            break;
                    }
                    ConstraintAnchor target = anchor6.mTarget;
                    if (target != null) {
                        if (target.mOwner.getParent() != null && target.mGroup != anchor6.mGroup) {
                            int i3 = anchor6.mGroup > target.mGroup ? target.mGroup : anchor6.mGroup;
                            anchor6.mGroup = i3;
                            target.mGroup = i3;
                            fix3++;
                            fix4 = 1;
                        }
                        ConstraintAnchor opposite = target.getOpposite();
                        if (opposite != null && opposite.mGroup != anchor6.mGroup) {
                            int i4 = anchor6.mGroup > opposite.mGroup ? opposite.mGroup : anchor6.mGroup;
                            anchor6.mGroup = i4;
                            opposite.mGroup = i4;
                            fix3++;
                            fix4 = 1;
                        }
                    }
                }
                fix2++;
                count2 = fix4;
                fix = fix3;
                i = 0;
            }
            j = fix;
        }
        int index2 = 0;
        int[] table = new int[(this.mChildren.size() * dir.length) + 1];
        Arrays.fill(table, -1);
        int j2 = 0;
        while (true) {
            int j3 = j2;
            if (j3 >= size) {
                return index2;
            }
            ConstraintWidget widget3 = this.mChildren.get(j3);
            ConstraintAnchor anchor7 = widget3.mLeft;
            if (anchor7.mGroup != Integer.MAX_VALUE) {
                int g = anchor7.mGroup;
                if (table[g] == -1) {
                    table[g] = index2;
                    index2++;
                }
                int index3 = table[g];
                anchor7.mGroup = index3;
            }
            ConstraintAnchor anchor8 = widget3.mTop;
            if (anchor8.mGroup != Integer.MAX_VALUE) {
                int g2 = anchor8.mGroup;
                if (table[g2] == -1) {
                    table[g2] = index2;
                    index2++;
                }
                int index4 = table[g2];
                anchor8.mGroup = index4;
            }
            ConstraintAnchor anchor9 = widget3.mRight;
            if (anchor9.mGroup != Integer.MAX_VALUE) {
                int g3 = anchor9.mGroup;
                if (table[g3] == -1) {
                    table[g3] = index2;
                    index2++;
                }
                int index5 = table[g3];
                anchor9.mGroup = index5;
            }
            ConstraintAnchor anchor10 = widget3.mBottom;
            if (anchor10.mGroup != Integer.MAX_VALUE) {
                int g4 = anchor10.mGroup;
                if (table[g4] == -1) {
                    table[g4] = index2;
                    index2++;
                }
                int index6 = table[g4];
                anchor10.mGroup = index6;
            }
            ConstraintAnchor anchor11 = widget3.mBaseline;
            if (anchor11.mGroup != Integer.MAX_VALUE) {
                int g5 = anchor11.mGroup;
                if (table[g5] == -1) {
                    index = index2 + 1;
                    table[g5] = index2;
                } else {
                    index = index2;
                }
                int index7 = table[g5];
                anchor11.mGroup = index7;
                index2 = index;
            }
            j2 = j3 + 1;
        }
    }

    public void layoutWithGroup(int numOfGroups) {
        int prex = this.mX;
        int prey = this.mY;
        if (this.mParent != null) {
            if (this.mSnapshot == null) {
                this.mSnapshot = new Snapshot(this);
            }
            this.mSnapshot.updateFrom(this);
            this.mX = 0;
            this.mY = 0;
            resetAnchors();
            resetSolverVariables(this.mSystem.getCache());
        } else {
            this.mX = 0;
            this.mY = 0;
        }
        int count = this.mChildren.size();
        for (int i = 0; i < count; i++) {
            ConstraintWidget widget = this.mChildren.get(i);
            if (widget instanceof WidgetContainer) {
                ((WidgetContainer) widget).layout();
            }
        }
        this.mLeft.mGroup = 0;
        this.mRight.mGroup = 0;
        this.mTop.mGroup = 1;
        this.mBottom.mGroup = 1;
        this.mSystem.reset();
        for (int i2 = 0; i2 < numOfGroups; i2++) {
            try {
                addToSolver(this.mSystem, i2);
                this.mSystem.minimize();
                updateFromSolver(this.mSystem, i2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateFromSolver(this.mSystem, -2);
        }
        if (this.mParent != null) {
            int width = getWidth();
            int height = getHeight();
            this.mSnapshot.applyTo(this);
            setWidth(width);
            setHeight(height);
        } else {
            this.mX = prex;
            this.mY = prey;
        }
        if (this == getRootConstraintContainer()) {
            updateDrawPosition();
        }
    }

    public boolean handlesInternalConstraints() {
        return false;
    }

    public ArrayList<Guideline> getVerticalGuidelines() {
        ArrayList<Guideline> guidelines = new ArrayList<>();
        int mChildrenSize = this.mChildren.size();
        for (int i = 0; i < mChildrenSize; i++) {
            ConstraintWidget widget = this.mChildren.get(i);
            if (widget instanceof Guideline) {
                Guideline guideline = (Guideline) widget;
                if (guideline.getOrientation() == 1) {
                    guidelines.add(guideline);
                }
            }
        }
        return guidelines;
    }

    public ArrayList<Guideline> getHorizontalGuidelines() {
        ArrayList<Guideline> guidelines = new ArrayList<>();
        int mChildrenSize = this.mChildren.size();
        for (int i = 0; i < mChildrenSize; i++) {
            ConstraintWidget widget = this.mChildren.get(i);
            if (widget instanceof Guideline) {
                Guideline guideline = (Guideline) widget;
                if (guideline.getOrientation() == 0) {
                    guidelines.add(guideline);
                }
            }
        }
        return guidelines;
    }

    public LinearSystem getSystem() {
        return this.mSystem;
    }

    private void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    void addChain(ConstraintWidget constraintWidget, int type) {
        ConstraintWidget widget = constraintWidget;
        if (type == 0) {
            while (widget.mLeft.mTarget != null && widget.mLeft.mTarget.mOwner.mRight.mTarget != null && widget.mLeft.mTarget.mOwner.mRight.mTarget == widget.mLeft && widget.mLeft.mTarget.mOwner != widget) {
                widget = widget.mLeft.mTarget.mOwner;
            }
            addHorizontalChain(widget);
            return;
        }
        if (type == 1) {
            while (widget.mTop.mTarget != null && widget.mTop.mTarget.mOwner.mBottom.mTarget != null && widget.mTop.mTarget.mOwner.mBottom.mTarget == widget.mTop && widget.mTop.mTarget.mOwner != widget) {
                widget = widget.mTop.mTarget.mOwner;
            }
            addVerticalChain(widget);
        }
    }

    private void addHorizontalChain(ConstraintWidget widget) {
        for (int i = 0; i < this.mHorizontalChainsSize; i++) {
            if (this.mHorizontalChainsArray[i] == widget) {
                return;
            }
        }
        int i2 = this.mHorizontalChainsSize;
        if (i2 + 1 >= this.mHorizontalChainsArray.length) {
            this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2);
        }
        this.mHorizontalChainsArray[this.mHorizontalChainsSize] = widget;
        this.mHorizontalChainsSize++;
    }

    private void addVerticalChain(ConstraintWidget widget) {
        for (int i = 0; i < this.mVerticalChainsSize; i++) {
            if (this.mVerticalChainsArray[i] == widget) {
                return;
            }
        }
        int i2 = this.mVerticalChainsSize;
        if (i2 + 1 >= this.mVerticalChainsArray.length) {
            this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2);
        }
        this.mVerticalChainsArray[this.mVerticalChainsSize] = widget;
        this.mVerticalChainsSize++;
    }

    private int countMatchConstraintsChainedWidgets(LinearSystem system, ConstraintWidget[] chainEnds, ConstraintWidget widget, int direction, boolean[] flags) {
        char c;
        ConstraintWidget last;
        char c2;
        ConstraintWidget widget2 = widget;
        flags[0] = USE_SNAPSHOT;
        flags[1] = false;
        ConstraintWidget constraintWidget = null;
        chainEnds[0] = null;
        chainEnds[2] = null;
        chainEnds[1] = null;
        chainEnds[3] = null;
        int i = 5;
        int i2 = 8;
        if (direction == 0) {
            boolean fixedPosition = USE_SNAPSHOT;
            if (widget2.mLeft.mTarget != null && widget2.mLeft.mTarget.mOwner != this) {
                fixedPosition = false;
            }
            widget2.mHorizontalNextWidget = null;
            ConstraintWidget firstVisible = null;
            if (widget.getVisibility() != 8) {
                firstVisible = widget2;
            }
            int count = 0;
            ConstraintWidget firstVisible2 = firstVisible;
            ConstraintWidget last2 = null;
            while (widget2.mRight.mTarget != null) {
                widget2.mHorizontalNextWidget = constraintWidget;
                if (widget2.getVisibility() != 8) {
                    if (firstVisible2 == null) {
                        firstVisible2 = widget2;
                    }
                    if (firstVisible != null && firstVisible != widget2) {
                        firstVisible.mHorizontalNextWidget = widget2;
                    }
                    ConstraintWidget lastVisible = widget2;
                    firstVisible = lastVisible;
                } else {
                    system.addEquality(widget2.mLeft.mSolverVariable, widget2.mLeft.mTarget.mSolverVariable, 0, 5);
                    system.addEquality(widget2.mRight.mSolverVariable, widget2.mLeft.mSolverVariable, 0, 5);
                }
                if (widget2.getVisibility() != 8 && widget2.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    if (widget2.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                        flags[0] = false;
                    }
                    if (widget2.mDimensionRatio <= 0.0f) {
                        flags[0] = false;
                        if (count + 1 >= this.mMatchConstraintsChainedWidgets.length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                        }
                        this.mMatchConstraintsChainedWidgets[count] = widget2;
                        count++;
                    }
                }
                if (widget2.mRight.mTarget.mOwner.mLeft.mTarget == null || widget2.mRight.mTarget.mOwner.mLeft.mTarget.mOwner != widget2 || widget2.mRight.mTarget.mOwner == widget2) {
                    break;
                }
                widget2 = widget2.mRight.mTarget.mOwner;
                last2 = widget2;
                constraintWidget = null;
            }
            if (widget2.mRight.mTarget != null && widget2.mRight.mTarget.mOwner != this) {
                fixedPosition = false;
            }
            if (widget2.mLeft.mTarget != null) {
                last = last2;
                if (last.mRight.mTarget != null) {
                    c2 = 1;
                    widget2.mHorizontalChainFixedPosition = fixedPosition;
                    last.mHorizontalNextWidget = null;
                    chainEnds[0] = widget2;
                    chainEnds[2] = firstVisible2;
                    chainEnds[c2] = last;
                    chainEnds[3] = firstVisible;
                    return count;
                }
            } else {
                last = last2;
            }
            c2 = 1;
            flags[1] = USE_SNAPSHOT;
            widget2.mHorizontalChainFixedPosition = fixedPosition;
            last.mHorizontalNextWidget = null;
            chainEnds[0] = widget2;
            chainEnds[2] = firstVisible2;
            chainEnds[c2] = last;
            chainEnds[3] = firstVisible;
            return count;
        }
        boolean fixedPosition2 = USE_SNAPSHOT;
        if (widget2.mTop.mTarget != null && widget2.mTop.mTarget.mOwner != this) {
            fixedPosition2 = false;
        }
        widget2.mVerticalNextWidget = null;
        ConstraintWidget firstVisible3 = null;
        if (widget.getVisibility() != 8) {
            firstVisible3 = widget2;
        }
        ConstraintWidget last3 = null;
        int count2 = 0;
        ConstraintWidget firstVisible4 = firstVisible3;
        while (widget2.mBottom.mTarget != null) {
            widget2.mVerticalNextWidget = null;
            if (widget2.getVisibility() != i2) {
                if (firstVisible4 == null) {
                    firstVisible4 = widget2;
                }
                if (firstVisible3 != null && firstVisible3 != widget2) {
                    firstVisible3.mVerticalNextWidget = widget2;
                }
                firstVisible3 = widget2;
            } else {
                system.addEquality(widget2.mTop.mSolverVariable, widget2.mTop.mTarget.mSolverVariable, 0, i);
                system.addEquality(widget2.mBottom.mSolverVariable, widget2.mTop.mSolverVariable, 0, i);
            }
            i2 = 8;
            if (widget2.getVisibility() != 8 && widget2.mVerticalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                if (widget2.mHorizontalDimensionBehaviour == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) {
                    flags[0] = false;
                }
                if (widget2.mDimensionRatio <= 0.0f) {
                    flags[0] = false;
                    if (count2 + 1 >= this.mMatchConstraintsChainedWidgets.length) {
                        this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(this.mMatchConstraintsChainedWidgets, this.mMatchConstraintsChainedWidgets.length * 2);
                    }
                    this.mMatchConstraintsChainedWidgets[count2] = widget2;
                    count2++;
                }
            }
            if (widget2.mBottom.mTarget.mOwner.mTop.mTarget == null || widget2.mBottom.mTarget.mOwner.mTop.mTarget.mOwner != widget2 || widget2.mBottom.mTarget.mOwner == widget2) {
                break;
            }
            widget2 = widget2.mBottom.mTarget.mOwner;
            last3 = widget2;
            i = 5;
        }
        if (widget2.mBottom.mTarget != null && widget2.mBottom.mTarget.mOwner != this) {
            fixedPosition2 = false;
        }
        if (widget2.mTop.mTarget == null || last3.mBottom.mTarget == null) {
            c = 1;
            flags[1] = USE_SNAPSHOT;
        } else {
            c = 1;
        }
        widget2.mVerticalChainFixedPosition = fixedPosition2;
        last3.mVerticalNextWidget = null;
        chainEnds[0] = widget2;
        chainEnds[2] = firstVisible4;
        chainEnds[c] = last3;
        chainEnds[3] = firstVisible3;
        return count2;
    }
}
