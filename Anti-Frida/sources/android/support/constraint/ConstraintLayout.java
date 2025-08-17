package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.Analyzer;
import android.support.constraint.solver.widgets.ConstraintAnchor;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.ActivityChooserView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.HashMap;
/* loaded from: classes.dex */
public class ConstraintLayout extends ViewGroup {
    static final boolean ALLOWS_EMBEDDED;
    private static final boolean CACHE_MEASURED_DIMENSION;
    private static final boolean DEBUG;
    public static final int DESIGN_INFO_ID;
    private static final String TAG;
    private static final boolean USE_CONSTRAINTS_HELPER;
    public static final String VERSION;
    private Metrics mMetrics;
    SparseArray<View> mChildrenByIds = new SparseArray<>();
    private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<>(4);
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>(100);
    ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMinWidth = 0;
    private int mMinHeight = 0;
    private int mMaxWidth = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private int mMaxHeight = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    private boolean mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    private int mOptimizationLevel = 7;
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds = new HashMap<>();
    private int mLastMeasureWidth = -1;
    private int mLastMeasureHeight = -1;
    int mLastMeasureWidthSize = -1;
    int mLastMeasureHeightSize = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureHeightMode = 0;

    public void setDesignInformation(int type, Object value1, Object value2) {
        if (type == 0 && (value1 instanceof String) && (value2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String name = (String) value1;
            int index = name.indexOf("/");
            if (index != -1) {
                name = name.substring(index + 1);
            }
            this.mDesignIds.put(name, Integer.valueOf(((Integer) value2).intValue()));
        }
    }

    public Object getDesignInformation(int type, Object value) {
        if (type != 0 || !(value instanceof String)) {
            return null;
        }
        String name = (String) value;
        if (this.mDesignIds == null || !this.mDesignIds.containsKey(name)) {
            return null;
        }
        return this.mDesignIds.get(name);
    }

    public ConstraintLayout(Context context) {
        super(context);
        init(null);
    }

    public ConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @Override // android.view.View
    public void setId(int id) {
        this.mChildrenByIds.remove(getId());
        super.setId(id);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(AttributeSet attrs) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            for (int i = 0; i < N; i++) {
                int attr = a.getIndex(i);
                if (attr == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = a.getDimensionPixelOffset(attr, this.mMinWidth);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = a.getDimensionPixelOffset(attr, this.mMinHeight);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = a.getDimensionPixelOffset(attr, this.mMaxWidth);
                } else if (attr == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = a.getDimensionPixelOffset(attr, this.mMaxHeight);
                } else if (attr == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = a.getInt(attr, this.mOptimizationLevel);
                } else if (attr == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int id = a.getResourceId(attr, 0);
                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(getContext(), id);
                    } catch (Resources.NotFoundException e) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = id;
                }
            }
            a.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (Build.VERSION.SDK_INT < 14) {
            onViewAdded(child);
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public void removeView(View view) {
        super.removeView(view);
        if (Build.VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    @Override // android.view.ViewGroup
    public void onViewAdded(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget widget = getViewWidget(view);
        if ((view instanceof Guideline) && !(widget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new Guideline();
            layoutParams.isGuideline = USE_CONSTRAINTS_HELPER;
            ((Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper helper = (ConstraintHelper) view;
            helper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = USE_CONSTRAINTS_HELPER;
            if (!this.mConstraintHelpers.contains(helper)) {
                this.mConstraintHelpers.add(helper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    @Override // android.view.ViewGroup
    public void onViewRemoved(View view) {
        if (Build.VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget widget = getViewWidget(view);
        this.mLayoutWidget.remove(widget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(widget);
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
    }

    public void setMinWidth(int value) {
        if (value != this.mMinWidth) {
            this.mMinWidth = value;
            requestLayout();
        }
    }

    public void setMinHeight(int value) {
        if (value != this.mMinHeight) {
            this.mMinHeight = value;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int value) {
        if (value != this.mMaxWidth) {
            this.mMaxWidth = value;
            requestLayout();
        }
    }

    public void setMaxHeight(int value) {
        if (value != this.mMaxHeight) {
            this.mMaxHeight = value;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private void updateHierarchy() {
        int count = getChildCount();
        boolean recompute = false;
        int i = 0;
        while (true) {
            if (i >= count) {
                break;
            } else if (getChildAt(i).isLayoutRequested()) {
                recompute = USE_CONSTRAINTS_HELPER;
                break;
            } else {
                i++;
            }
        }
        if (recompute) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    /* JADX INFO: Multiple debug info for r5v6 int: [D('resolveGoneRightMargin' int), D('resolvedLeftToRight' int)] */
    /* JADX INFO: Multiple debug info for r8v3 'layoutParams'  android.support.constraint.ConstraintLayout$LayoutParams: [D('child' android.view.View), D('layoutParams' android.support.constraint.ConstraintLayout$LayoutParams)] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x01fb A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    private void setChildrenConstraints() {
        /*
        // Method dump skipped, instructions count: 1139
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.setChildrenConstraints():void");
    }

    private final ConstraintWidget getTargetWidget(int id) {
        if (id == 0) {
            return this.mLayoutWidget;
        }
        View view = this.mChildrenByIds.get(id);
        if (view == null && (view = findViewById(id)) != null && view != this && view.getParent() == this) {
            onViewAdded(view);
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            return null;
        }
        return ((LayoutParams) view.getLayoutParams()).widget;
    }

    private void internalMeasureChildren(int parentWidthSpec, int parentHeightSpec) {
        int baseline;
        int childWidthMeasureSpec;
        int childHeightMeasureSpec;
        ConstraintLayout constraintLayout = this;
        int i = parentWidthSpec;
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        int widgetsCount = getChildCount();
        int i2 = 0;
        while (i2 < widgetsCount) {
            View child = constraintLayout.getChildAt(i2);
            if (child.getVisibility() != 8) {
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                ConstraintWidget widget = params.widget;
                if (!params.isGuideline && !params.isHelper) {
                    widget.setVisibility(child.getVisibility());
                    int width = params.width;
                    int height = params.height;
                    boolean didWrapMeasureWidth = false;
                    boolean didWrapMeasureHeight = false;
                    if ((params.horizontalDimensionFixed || params.verticalDimensionFixed || (!params.horizontalDimensionFixed && params.matchConstraintDefaultWidth == 1) || params.width == -1 || (!params.verticalDimensionFixed && (params.matchConstraintDefaultHeight == 1 || params.height == -1))) ? USE_CONSTRAINTS_HELPER : false) {
                        if (width == 0) {
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, -2);
                            didWrapMeasureWidth = USE_CONSTRAINTS_HELPER;
                        } else if (width == -1) {
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, -1);
                        } else {
                            if (width == -2) {
                                didWrapMeasureWidth = USE_CONSTRAINTS_HELPER;
                            }
                            childWidthMeasureSpec = getChildMeasureSpec(i, widthPadding, width);
                        }
                        if (height == 0) {
                            childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, -2);
                            didWrapMeasureHeight = USE_CONSTRAINTS_HELPER;
                        } else if (height == -1) {
                            childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, -1);
                        } else {
                            if (height == -2) {
                                didWrapMeasureHeight = USE_CONSTRAINTS_HELPER;
                            }
                            childHeightMeasureSpec = getChildMeasureSpec(parentHeightSpec, heightPadding, height);
                        }
                        child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
                        if (constraintLayout.mMetrics != null) {
                            constraintLayout.mMetrics.measures++;
                        }
                        widget.setWidthWrapContent(width == -2 ? USE_CONSTRAINTS_HELPER : false);
                        widget.setHeightWrapContent(height == -2 ? USE_CONSTRAINTS_HELPER : false);
                        width = child.getMeasuredWidth();
                        height = child.getMeasuredHeight();
                    }
                    widget.setWidth(width);
                    widget.setHeight(height);
                    if (didWrapMeasureWidth) {
                        widget.setWrapWidth(width);
                    }
                    if (didWrapMeasureHeight) {
                        widget.setWrapHeight(height);
                    }
                    if (params.needsBaseline && (baseline = child.getBaseline()) != -1) {
                        widget.setBaselineDistance(baseline);
                    }
                }
            }
            i2++;
            constraintLayout = this;
            i = parentWidthSpec;
        }
    }

    private void updatePostMeasures() {
        int widgetsCount = getChildCount();
        for (int i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            if (child instanceof Placeholder) {
                ((Placeholder) child).updatePostMeasure(this);
            }
        }
        int helperCount = this.mConstraintHelpers.size();
        if (helperCount > 0) {
            for (int i2 = 0; i2 < helperCount; i2++) {
                this.mConstraintHelpers.get(i2).updatePostMeasure(this);
            }
        }
    }

    /* JADX INFO: Multiple debug info for r4v15 'heightPadding'  int: [D('heightPadding' int), D('widthPadding' int)] */
    /* JADX INFO: Multiple debug info for r4v17 'heightPadding'  int: [D('heightPadding' int), D('widthPadding' int)] */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02a4  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02e8  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02f3 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump */
    private void internalMeasureDimensions(int r33, int r34) {
        /*
        // Method dump skipped, instructions count: 789
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.internalMeasureDimensions(int, int):void");
    }

    public void fillMetrics(Metrics metrics) {
        this.mMetrics = metrics;
        this.mLayoutWidget.fillMetrics(metrics);
    }

    /* JADX INFO: Multiple debug info for r14v14 android.support.constraint.ConstraintLayout$LayoutParams: [D('startingHeight' int), D('params' android.support.constraint.ConstraintLayout$LayoutParams)] */
    /* JADX INFO: Multiple debug info for r1v28 int: [D('minHeight' int), D('h' int)] */
    /* JADX INFO: Multiple debug info for r5v8 android.view.View: [D('sizeDependentWidgetsCount' int), D('child' android.view.View)] */
    @Override // android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int REMEASURES_B;
        int childState;
        int startingWidth;
        boolean containerWrapWidth;
        int startingWidth2;
        int i;
        int startingWidth3;
        int startingHeight;
        int widthSpec;
        int heightSpec;
        int baseline;
        int i2 = widthMeasureSpec;
        int i3 = heightMeasureSpec;
        System.currentTimeMillis();
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (Build.VERSION.SDK_INT >= 17) {
            this.mLayoutWidget.setRtl(getLayoutDirection() == 1 ? USE_CONSTRAINTS_HELPER : false);
        }
        setSelfDimensionBehaviour(widthMeasureSpec, heightMeasureSpec);
        int startingWidth4 = this.mLayoutWidget.getWidth();
        int startingHeight2 = this.mLayoutWidget.getHeight();
        boolean runAnalyzer = false;
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
            runAnalyzer = USE_CONSTRAINTS_HELPER;
        }
        boolean optimiseDimensions = (this.mOptimizationLevel & 8) == 8 ? USE_CONSTRAINTS_HELPER : false;
        if (optimiseDimensions) {
            this.mLayoutWidget.preOptimize();
            this.mLayoutWidget.optimizeForDimensions(startingWidth4, startingHeight2);
            internalMeasureDimensions(widthMeasureSpec, heightMeasureSpec);
        } else {
            internalMeasureChildren(widthMeasureSpec, heightMeasureSpec);
        }
        updatePostMeasures();
        if (getChildCount() > 0 && runAnalyzer) {
            Analyzer.determineGroups(this.mLayoutWidget);
        }
        if (this.mLayoutWidget.mGroupsWrapOptimized) {
            if (this.mLayoutWidget.mHorizontalWrapOptimized && widthMode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedWidth < widthSize) {
                    this.mLayoutWidget.setWidth(this.mLayoutWidget.mWrapFixedWidth);
                }
                this.mLayoutWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && heightMode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedHeight < heightSize) {
                    this.mLayoutWidget.setHeight(this.mLayoutWidget.mWrapFixedHeight);
                }
                this.mLayoutWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
            }
        }
        int REMEASURES_A = 0;
        if ((this.mOptimizationLevel & 32) == 32) {
            int width = this.mLayoutWidget.getWidth();
            int height = this.mLayoutWidget.getHeight();
            if (this.mLastMeasureWidth == width || widthMode != 1073741824) {
                REMEASURES_B = 0;
            } else {
                REMEASURES_B = 0;
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, width);
            }
            if (this.mLastMeasureHeight != height && heightMode == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, height);
            }
            if (this.mLayoutWidget.mHorizontalWrapOptimized && this.mLayoutWidget.mWrapFixedWidth > widthSize) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, widthSize);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && this.mLayoutWidget.mWrapFixedHeight > heightSize) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, heightSize);
            }
        } else {
            REMEASURES_B = 0;
        }
        if (getChildCount() > 0) {
            solveLinearSystem("First pass");
        }
        int sizeDependentWidgetsCount = this.mVariableDimensionsWidgets.size();
        int heightPadding = getPaddingBottom() + paddingTop;
        int widthPadding = paddingLeft + getPaddingRight();
        if (sizeDependentWidgetsCount > 0) {
            boolean needSolverPass = false;
            boolean containerWrapWidth2 = this.mLayoutWidget.getHorizontalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? USE_CONSTRAINTS_HELPER : false;
            boolean containerWrapHeight = this.mLayoutWidget.getVerticalDimensionBehaviour() == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT ? USE_CONSTRAINTS_HELPER : false;
            childState = 0;
            int minWidth = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
            int minHeight = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
            int i4 = 0;
            while (i4 < sizeDependentWidgetsCount) {
                ConstraintWidget widget = this.mVariableDimensionsWidgets.get(i4);
                View child = (View) widget.getCompanionWidget();
                if (child == null) {
                    i = i4;
                    startingWidth3 = startingWidth4;
                    startingHeight = startingHeight2;
                } else {
                    startingHeight = startingHeight2;
                    LayoutParams params = (LayoutParams) child.getLayoutParams();
                    startingWidth3 = startingWidth4;
                    if (params.isHelper) {
                        i = i4;
                    } else if (params.isGuideline) {
                        i = i4;
                    } else {
                        i = i4;
                        if (child.getVisibility() != 8 && (!optimiseDimensions || !widget.getResolutionWidth().isResolved() || !widget.getResolutionHeight().isResolved())) {
                            if (params.width != -2 || !params.horizontalDimensionFixed) {
                                widthSpec = View.MeasureSpec.makeMeasureSpec(widget.getWidth(), 1073741824);
                            } else {
                                widthSpec = getChildMeasureSpec(i2, widthPadding, params.width);
                            }
                            if (params.height != -2 || !params.verticalDimensionFixed) {
                                heightSpec = View.MeasureSpec.makeMeasureSpec(widget.getHeight(), 1073741824);
                            } else {
                                heightSpec = getChildMeasureSpec(i3, heightPadding, params.height);
                            }
                            child.measure(widthSpec, heightSpec);
                            if (this.mMetrics != null) {
                                this.mMetrics.additionalMeasures++;
                            }
                            REMEASURES_A++;
                            int measuredWidth = child.getMeasuredWidth();
                            int measuredHeight = child.getMeasuredHeight();
                            if (measuredWidth != widget.getWidth()) {
                                widget.setWidth(measuredWidth);
                                if (optimiseDimensions) {
                                    widget.getResolutionWidth().resolve(measuredWidth);
                                }
                                if (containerWrapWidth2 && widget.getRight() > minWidth) {
                                    minWidth = Math.max(minWidth, widget.getRight() + widget.getAnchor(ConstraintAnchor.Type.RIGHT).getMargin());
                                }
                                needSolverPass = USE_CONSTRAINTS_HELPER;
                            }
                            if (measuredHeight != widget.getHeight()) {
                                widget.setHeight(measuredHeight);
                                if (optimiseDimensions) {
                                    widget.getResolutionHeight().resolve(measuredHeight);
                                }
                                if (containerWrapHeight && widget.getBottom() > minHeight) {
                                    minHeight = Math.max(minHeight, widget.getBottom() + widget.getAnchor(ConstraintAnchor.Type.BOTTOM).getMargin());
                                }
                                needSolverPass = USE_CONSTRAINTS_HELPER;
                            }
                            if (!(!params.needsBaseline || (baseline = child.getBaseline()) == -1 || baseline == widget.getBaselineDistance())) {
                                widget.setBaselineDistance(baseline);
                                needSolverPass = USE_CONSTRAINTS_HELPER;
                            }
                            if (Build.VERSION.SDK_INT >= 11) {
                                childState = combineMeasuredStates(childState, child.getMeasuredState());
                            }
                        }
                    }
                }
                i4 = i + 1;
                paddingTop = paddingTop;
                sizeDependentWidgetsCount = sizeDependentWidgetsCount;
                startingHeight2 = startingHeight;
                startingWidth4 = startingWidth3;
                i2 = widthMeasureSpec;
                i3 = heightMeasureSpec;
            }
            int sizeDependentWidgetsCount2 = sizeDependentWidgetsCount;
            if (needSolverPass) {
                startingWidth = startingWidth4;
                this.mLayoutWidget.setWidth(startingWidth);
                this.mLayoutWidget.setHeight(startingHeight2);
                if (optimiseDimensions) {
                    this.mLayoutWidget.solveGraph();
                }
                solveLinearSystem("2nd pass");
                boolean needSolverPass2 = false;
                if (this.mLayoutWidget.getWidth() < minWidth) {
                    this.mLayoutWidget.setWidth(minWidth);
                    needSolverPass2 = USE_CONSTRAINTS_HELPER;
                }
                if (this.mLayoutWidget.getHeight() < minHeight) {
                    this.mLayoutWidget.setHeight(minHeight);
                    needSolverPass2 = USE_CONSTRAINTS_HELPER;
                }
                if (needSolverPass2) {
                    solveLinearSystem("3rd pass");
                }
            } else {
                startingWidth = startingWidth4;
            }
            int i5 = 0;
            while (i5 < sizeDependentWidgetsCount2) {
                ConstraintWidget widget2 = this.mVariableDimensionsWidgets.get(i5);
                View child2 = (View) widget2.getCompanionWidget();
                if (child2 == null) {
                    startingWidth2 = startingWidth;
                } else {
                    startingWidth2 = startingWidth;
                    if (!(child2.getMeasuredWidth() == widget2.getWidth() && child2.getMeasuredHeight() == widget2.getHeight())) {
                        if (widget2.getVisibility() != 8) {
                            containerWrapWidth = containerWrapWidth2;
                            child2.measure(View.MeasureSpec.makeMeasureSpec(widget2.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(widget2.getHeight(), 1073741824));
                            if (this.mMetrics != null) {
                                this.mMetrics.additionalMeasures++;
                            }
                            REMEASURES_B++;
                        } else {
                            containerWrapWidth = containerWrapWidth2;
                        }
                        i5++;
                        sizeDependentWidgetsCount2 = sizeDependentWidgetsCount2;
                        startingWidth = startingWidth2;
                        containerWrapWidth2 = containerWrapWidth;
                    }
                }
                containerWrapWidth = containerWrapWidth2;
                i5++;
                sizeDependentWidgetsCount2 = sizeDependentWidgetsCount2;
                startingWidth = startingWidth2;
                containerWrapWidth2 = containerWrapWidth;
            }
        } else {
            childState = 0;
        }
        int androidLayoutWidth = this.mLayoutWidget.getWidth() + widthPadding;
        int androidLayoutHeight = this.mLayoutWidget.getHeight() + heightPadding;
        if (Build.VERSION.SDK_INT >= 11) {
            int resolvedWidthSize = resolveSizeAndState(androidLayoutWidth, widthMeasureSpec, childState);
            int resolvedHeightSize = resolveSizeAndState(androidLayoutHeight, heightMeasureSpec, childState << 16);
            int resolvedWidthSize2 = resolvedWidthSize & ViewCompat.MEASURED_SIZE_MASK;
            int resolvedHeightSize2 = resolvedHeightSize & ViewCompat.MEASURED_SIZE_MASK;
            int resolvedWidthSize3 = Math.min(this.mMaxWidth, resolvedWidthSize2);
            int resolvedHeightSize3 = Math.min(this.mMaxHeight, resolvedHeightSize2);
            if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
                resolvedWidthSize3 |= 16777216;
            }
            if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
                resolvedHeightSize3 |= 16777216;
            }
            setMeasuredDimension(resolvedWidthSize3, resolvedHeightSize3);
            this.mLastMeasureWidth = resolvedWidthSize3;
            this.mLastMeasureHeight = resolvedHeightSize3;
            return;
        }
        setMeasuredDimension(androidLayoutWidth, androidLayoutHeight);
        this.mLastMeasureWidth = androidLayoutWidth;
        this.mLastMeasureHeight = androidLayoutHeight;
    }

    private void setSelfDimensionBehaviour(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);
        int heightPadding = getPaddingTop() + getPaddingBottom();
        int widthPadding = getPaddingLeft() + getPaddingRight();
        ConstraintWidget.DimensionBehaviour widthBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        ConstraintWidget.DimensionBehaviour heightBehaviour = ConstraintWidget.DimensionBehaviour.FIXED;
        int desiredWidth = 0;
        int desiredHeight = 0;
        getLayoutParams();
        if (widthMode == Integer.MIN_VALUE) {
            widthBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            desiredWidth = widthSize;
        } else if (widthMode == 0) {
            widthBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        } else if (widthMode == 1073741824) {
            desiredWidth = Math.min(this.mMaxWidth, widthSize) - widthPadding;
        }
        if (heightMode == Integer.MIN_VALUE) {
            heightBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
            desiredHeight = heightSize;
        } else if (heightMode == 0) {
            heightBehaviour = ConstraintWidget.DimensionBehaviour.WRAP_CONTENT;
        } else if (heightMode == 1073741824) {
            desiredHeight = Math.min(this.mMaxHeight, heightSize) - heightPadding;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(widthBehaviour);
        this.mLayoutWidget.setWidth(desiredWidth);
        this.mLayoutWidget.setVerticalDimensionBehaviour(heightBehaviour);
        this.mLayoutWidget.setHeight(desiredHeight);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    protected void solveLinearSystem(String reason) {
        this.mLayoutWidget.layout();
        if (this.mMetrics != null) {
            this.mMetrics.resolutions++;
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        View content;
        int widgetsCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i = 0; i < widgetsCount; i++) {
            View child = getChildAt(i);
            LayoutParams params = (LayoutParams) child.getLayoutParams();
            ConstraintWidget widget = params.widget;
            if ((child.getVisibility() != 8 || params.isGuideline || params.isHelper || isInEditMode) && !params.isInPlaceholder) {
                int l = widget.getDrawX();
                int t = widget.getDrawY();
                int r = widget.getWidth() + l;
                int b = widget.getHeight() + t;
                child.layout(l, t, r, b);
                if ((child instanceof Placeholder) && (content = ((Placeholder) child).getContent()) != null) {
                    content.setVisibility(0);
                    content.layout(l, t, r, b);
                }
            }
        }
        int helperCount = this.mConstraintHelpers.size();
        if (helperCount > 0) {
            for (int i2 = 0; i2 < helperCount; i2++) {
                this.mConstraintHelpers.get(i2).updatePostLayout(this);
            }
        }
    }

    public void setOptimizationLevel(int level) {
        this.mLayoutWidget.setOptimizationLevel(level);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override // android.view.ViewGroup
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    @Override // android.view.ViewGroup
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override // android.view.ViewGroup
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet set) {
        this.mConstraintSet = set;
    }

    public View getViewById(int id) {
        return this.mChildrenByIds.get(id);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas canvas) {
        float ow;
        float ch;
        float cw;
        int count;
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int count2 = getChildCount();
            float cw2 = (float) getWidth();
            float ch2 = (float) getHeight();
            float ow2 = 1080.0f;
            char c = 0;
            int i = 0;
            while (i < count2) {
                View child = getChildAt(i);
                if (child.getVisibility() == 8) {
                    count = count2;
                    cw = cw2;
                    ch = ch2;
                    ow = ow2;
                } else {
                    Object tag = child.getTag();
                    if (tag != null && (tag instanceof String)) {
                        String[] split = ((String) tag).split(",");
                        if (split.length == 4) {
                            int x = Integer.parseInt(split[c]);
                            int y = Integer.parseInt(split[1]);
                            int x2 = (int) ((((float) x) / ow2) * cw2);
                            int y2 = (int) ((((float) y) / 1920.0f) * ch2);
                            int w = (int) ((((float) Integer.parseInt(split[2])) / ow2) * cw2);
                            int h = (int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * ch2);
                            Paint paint = new Paint();
                            count = count2;
                            paint.setColor(SupportMenu.CATEGORY_MASK);
                            cw = cw2;
                            ch = ch2;
                            ow = ow2;
                            canvas.drawLine((float) x2, (float) y2, (float) (x2 + w), (float) y2, paint);
                            canvas.drawLine((float) (x2 + w), (float) y2, (float) (x2 + w), (float) (y2 + h), paint);
                            canvas.drawLine((float) (x2 + w), (float) (y2 + h), (float) x2, (float) (y2 + h), paint);
                            canvas.drawLine((float) x2, (float) (y2 + h), (float) x2, (float) y2, paint);
                            paint.setColor(-16711936);
                            canvas.drawLine((float) x2, (float) y2, (float) (x2 + w), (float) (y2 + h), paint);
                            canvas.drawLine((float) x2, (float) (y2 + h), (float) (x2 + w), (float) y2, paint);
                        }
                    }
                    count = count2;
                    cw = cw2;
                    ch = ch2;
                    ow = ow2;
                }
                i++;
                count2 = count;
                cw2 = cw;
                ch2 = ch;
                ow2 = ow;
                c = 0;
            }
        }
    }

    /* loaded from: classes.dex */
    public static class LayoutParams extends ViewGroup.MarginLayoutParams {
        public static final int BASELINE;
        public static final int BOTTOM;
        public static final int CHAIN_PACKED;
        public static final int CHAIN_SPREAD;
        public static final int CHAIN_SPREAD_INSIDE;
        public static final int END;
        public static final int HORIZONTAL;
        public static final int LEFT;
        public static final int MATCH_CONSTRAINT;
        public static final int MATCH_CONSTRAINT_PERCENT;
        public static final int MATCH_CONSTRAINT_SPREAD;
        public static final int MATCH_CONSTRAINT_WRAP;
        public static final int PARENT_ID;
        public static final int RIGHT;
        public static final int START;
        public static final int TOP;
        public static final int UNSET;
        public static final int VERTICAL;
        public int baselineToBaseline;
        public int bottomToBottom;
        public int bottomToTop;
        public float circleAngle;
        public int circleConstraint;
        public int circleRadius;
        public boolean constrainedHeight;
        public boolean constrainedWidth;
        public String dimensionRatio;
        int dimensionRatioSide;
        float dimensionRatioValue;
        public int editorAbsoluteX;
        public int editorAbsoluteY;
        public int endToEnd;
        public int endToStart;
        public int goneBottomMargin;
        public int goneEndMargin;
        public int goneLeftMargin;
        public int goneRightMargin;
        public int goneStartMargin;
        public int goneTopMargin;
        public int guideBegin;
        public int guideEnd;
        public float guidePercent;
        public boolean helped;
        public float horizontalBias;
        public int horizontalChainStyle;
        boolean horizontalDimensionFixed;
        public float horizontalWeight;
        boolean isGuideline;
        boolean isHelper;
        boolean isInPlaceholder;
        public int leftToLeft;
        public int leftToRight;
        public int matchConstraintDefaultHeight;
        public int matchConstraintDefaultWidth;
        public int matchConstraintMaxHeight;
        public int matchConstraintMaxWidth;
        public int matchConstraintMinHeight;
        public int matchConstraintMinWidth;
        public float matchConstraintPercentHeight;
        public float matchConstraintPercentWidth;
        boolean needsBaseline;
        public int orientation;
        int resolveGoneLeftMargin;
        int resolveGoneRightMargin;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias;
        int resolvedLeftToLeft;
        int resolvedLeftToRight;
        int resolvedRightToLeft;
        int resolvedRightToRight;
        public int rightToLeft;
        public int rightToRight;
        public int startToEnd;
        public int startToStart;
        public int topToBottom;
        public int topToTop;
        public float verticalBias;
        public int verticalChainStyle;
        boolean verticalDimensionFixed;
        public float verticalWeight;
        ConstraintWidget widget;

        public void reset() {
            if (this.widget != null) {
                this.widget.reset();
            }
        }

        public LayoutParams(LayoutParams source) {
            super((ViewGroup.MarginLayoutParams) source);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            this.guideBegin = source.guideBegin;
            this.guideEnd = source.guideEnd;
            this.guidePercent = source.guidePercent;
            this.leftToLeft = source.leftToLeft;
            this.leftToRight = source.leftToRight;
            this.rightToLeft = source.rightToLeft;
            this.rightToRight = source.rightToRight;
            this.topToTop = source.topToTop;
            this.topToBottom = source.topToBottom;
            this.bottomToTop = source.bottomToTop;
            this.bottomToBottom = source.bottomToBottom;
            this.baselineToBaseline = source.baselineToBaseline;
            this.circleConstraint = source.circleConstraint;
            this.circleRadius = source.circleRadius;
            this.circleAngle = source.circleAngle;
            this.startToEnd = source.startToEnd;
            this.startToStart = source.startToStart;
            this.endToStart = source.endToStart;
            this.endToEnd = source.endToEnd;
            this.goneLeftMargin = source.goneLeftMargin;
            this.goneTopMargin = source.goneTopMargin;
            this.goneRightMargin = source.goneRightMargin;
            this.goneBottomMargin = source.goneBottomMargin;
            this.goneStartMargin = source.goneStartMargin;
            this.goneEndMargin = source.goneEndMargin;
            this.horizontalBias = source.horizontalBias;
            this.verticalBias = source.verticalBias;
            this.dimensionRatio = source.dimensionRatio;
            this.dimensionRatioValue = source.dimensionRatioValue;
            this.dimensionRatioSide = source.dimensionRatioSide;
            this.horizontalWeight = source.horizontalWeight;
            this.verticalWeight = source.verticalWeight;
            this.horizontalChainStyle = source.horizontalChainStyle;
            this.verticalChainStyle = source.verticalChainStyle;
            this.constrainedWidth = source.constrainedWidth;
            this.constrainedHeight = source.constrainedHeight;
            this.matchConstraintDefaultWidth = source.matchConstraintDefaultWidth;
            this.matchConstraintDefaultHeight = source.matchConstraintDefaultHeight;
            this.matchConstraintMinWidth = source.matchConstraintMinWidth;
            this.matchConstraintMaxWidth = source.matchConstraintMaxWidth;
            this.matchConstraintMinHeight = source.matchConstraintMinHeight;
            this.matchConstraintMaxHeight = source.matchConstraintMaxHeight;
            this.matchConstraintPercentWidth = source.matchConstraintPercentWidth;
            this.matchConstraintPercentHeight = source.matchConstraintPercentHeight;
            this.editorAbsoluteX = source.editorAbsoluteX;
            this.editorAbsoluteY = source.editorAbsoluteY;
            this.orientation = source.orientation;
            this.horizontalDimensionFixed = source.horizontalDimensionFixed;
            this.verticalDimensionFixed = source.verticalDimensionFixed;
            this.needsBaseline = source.needsBaseline;
            this.isGuideline = source.isGuideline;
            this.resolvedLeftToLeft = source.resolvedLeftToLeft;
            this.resolvedLeftToRight = source.resolvedLeftToRight;
            this.resolvedRightToLeft = source.resolvedRightToLeft;
            this.resolvedRightToRight = source.resolvedRightToRight;
            this.resolveGoneLeftMargin = source.resolveGoneLeftMargin;
            this.resolveGoneRightMargin = source.resolveGoneRightMargin;
            this.resolvedHorizontalBias = source.resolvedHorizontalBias;
            this.widget = source.widget;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* loaded from: classes.dex */
        public static class Table {
            public static final int ANDROID_ORIENTATION;
            public static final int LAYOUT_CONSTRAINED_HEIGHT;
            public static final int LAYOUT_CONSTRAINED_WIDTH;
            public static final int LAYOUT_CONSTRAINT_BASELINE_CREATOR;
            public static final int LAYOUT_CONSTRAINT_BASELINE_TO_BASELINE_OF;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_CREATOR;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_BOTTOM_OF;
            public static final int LAYOUT_CONSTRAINT_BOTTOM_TO_TOP_OF;
            public static final int LAYOUT_CONSTRAINT_CIRCLE;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_ANGLE;
            public static final int LAYOUT_CONSTRAINT_CIRCLE_RADIUS;
            public static final int LAYOUT_CONSTRAINT_DIMENSION_RATIO;
            public static final int LAYOUT_CONSTRAINT_END_TO_END_OF;
            public static final int LAYOUT_CONSTRAINT_END_TO_START_OF;
            public static final int LAYOUT_CONSTRAINT_GUIDE_BEGIN;
            public static final int LAYOUT_CONSTRAINT_GUIDE_END;
            public static final int LAYOUT_CONSTRAINT_GUIDE_PERCENT;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_DEFAULT;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MAX;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_MIN;
            public static final int LAYOUT_CONSTRAINT_HEIGHT_PERCENT;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_BIAS;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_CHAINSTYLE;
            public static final int LAYOUT_CONSTRAINT_HORIZONTAL_WEIGHT;
            public static final int LAYOUT_CONSTRAINT_LEFT_CREATOR;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_LEFT_OF;
            public static final int LAYOUT_CONSTRAINT_LEFT_TO_RIGHT_OF;
            public static final int LAYOUT_CONSTRAINT_RIGHT_CREATOR;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_LEFT_OF;
            public static final int LAYOUT_CONSTRAINT_RIGHT_TO_RIGHT_OF;
            public static final int LAYOUT_CONSTRAINT_START_TO_END_OF;
            public static final int LAYOUT_CONSTRAINT_START_TO_START_OF;
            public static final int LAYOUT_CONSTRAINT_TOP_CREATOR;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_BOTTOM_OF;
            public static final int LAYOUT_CONSTRAINT_TOP_TO_TOP_OF;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_BIAS;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE;
            public static final int LAYOUT_CONSTRAINT_VERTICAL_WEIGHT;
            public static final int LAYOUT_CONSTRAINT_WIDTH_DEFAULT;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MAX;
            public static final int LAYOUT_CONSTRAINT_WIDTH_MIN;
            public static final int LAYOUT_CONSTRAINT_WIDTH_PERCENT;
            public static final int LAYOUT_EDITOR_ABSOLUTEX;
            public static final int LAYOUT_EDITOR_ABSOLUTEY;
            public static final int LAYOUT_GONE_MARGIN_BOTTOM;
            public static final int LAYOUT_GONE_MARGIN_END;
            public static final int LAYOUT_GONE_MARGIN_LEFT;
            public static final int LAYOUT_GONE_MARGIN_RIGHT;
            public static final int LAYOUT_GONE_MARGIN_START;
            public static final int LAYOUT_GONE_MARGIN_TOP;
            public static final int UNUSED;
            public static final SparseIntArray map = new SparseIntArray();

            private Table() {
            }

            static {
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
            int commaIndex;
            int i = -1;
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            int i2 = 0;
            this.circleRadius = 0;
            float f = 0.0f;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            int i3 = 1;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
            TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.ConstraintLayout_Layout);
            int N = a.getIndexCount();
            int i4 = 0;
            while (i4 < N) {
                int attr = a.getIndex(i4);
                switch (Table.map.get(attr)) {
                    case 1:
                        this.orientation = a.getInt(attr, this.orientation);
                        break;
                    case 2:
                        this.circleConstraint = a.getResourceId(attr, this.circleConstraint);
                        if (this.circleConstraint != -1) {
                            break;
                        } else {
                            this.circleConstraint = a.getInt(attr, -1);
                            break;
                        }
                    case 3:
                        this.circleRadius = a.getDimensionPixelSize(attr, this.circleRadius);
                        break;
                    case 4:
                        this.circleAngle = a.getFloat(attr, this.circleAngle) % 360.0f;
                        if (this.circleAngle < 0.0f) {
                            this.circleAngle = (360.0f - this.circleAngle) % 360.0f;
                        }
                        break;
                    case 5:
                        this.guideBegin = a.getDimensionPixelOffset(attr, this.guideBegin);
                        break;
                    case 6:
                        this.guideEnd = a.getDimensionPixelOffset(attr, this.guideEnd);
                        break;
                    case 7:
                        this.guidePercent = a.getFloat(attr, this.guidePercent);
                        break;
                    case 8:
                        this.leftToLeft = a.getResourceId(attr, this.leftToLeft);
                        if (this.leftToLeft == -1) {
                            this.leftToLeft = a.getInt(attr, -1);
                        }
                        break;
                    case 9:
                        this.leftToRight = a.getResourceId(attr, this.leftToRight);
                        if (this.leftToRight == -1) {
                            this.leftToRight = a.getInt(attr, -1);
                        }
                        break;
                    case 10:
                        this.rightToLeft = a.getResourceId(attr, this.rightToLeft);
                        if (this.rightToLeft == -1) {
                            this.rightToLeft = a.getInt(attr, -1);
                        }
                        break;
                    case 11:
                        this.rightToRight = a.getResourceId(attr, this.rightToRight);
                        if (this.rightToRight == -1) {
                            this.rightToRight = a.getInt(attr, -1);
                        }
                        break;
                    case 12:
                        this.topToTop = a.getResourceId(attr, this.topToTop);
                        if (this.topToTop == -1) {
                            this.topToTop = a.getInt(attr, -1);
                        }
                        break;
                    case 13:
                        this.topToBottom = a.getResourceId(attr, this.topToBottom);
                        if (this.topToBottom == -1) {
                            this.topToBottom = a.getInt(attr, -1);
                        }
                        break;
                    case 14:
                        this.bottomToTop = a.getResourceId(attr, this.bottomToTop);
                        if (this.bottomToTop == -1) {
                            this.bottomToTop = a.getInt(attr, -1);
                        }
                        break;
                    case 15:
                        this.bottomToBottom = a.getResourceId(attr, this.bottomToBottom);
                        if (this.bottomToBottom == -1) {
                            this.bottomToBottom = a.getInt(attr, -1);
                        }
                        break;
                    case 16:
                        this.baselineToBaseline = a.getResourceId(attr, this.baselineToBaseline);
                        if (this.baselineToBaseline == -1) {
                            this.baselineToBaseline = a.getInt(attr, -1);
                        }
                        break;
                    case 17:
                        this.startToEnd = a.getResourceId(attr, this.startToEnd);
                        if (this.startToEnd == -1) {
                            this.startToEnd = a.getInt(attr, -1);
                        }
                        break;
                    case 18:
                        this.startToStart = a.getResourceId(attr, this.startToStart);
                        if (this.startToStart == -1) {
                            this.startToStart = a.getInt(attr, -1);
                        }
                        break;
                    case 19:
                        this.endToStart = a.getResourceId(attr, this.endToStart);
                        if (this.endToStart == -1) {
                            this.endToStart = a.getInt(attr, -1);
                        }
                        break;
                    case 20:
                        this.endToEnd = a.getResourceId(attr, this.endToEnd);
                        if (this.endToEnd == -1) {
                            this.endToEnd = a.getInt(attr, -1);
                        }
                        break;
                    case 21:
                        this.goneLeftMargin = a.getDimensionPixelSize(attr, this.goneLeftMargin);
                        break;
                    case 22:
                        this.goneTopMargin = a.getDimensionPixelSize(attr, this.goneTopMargin);
                        break;
                    case 23:
                        this.goneRightMargin = a.getDimensionPixelSize(attr, this.goneRightMargin);
                        break;
                    case 24:
                        this.goneBottomMargin = a.getDimensionPixelSize(attr, this.goneBottomMargin);
                        break;
                    case 25:
                        this.goneStartMargin = a.getDimensionPixelSize(attr, this.goneStartMargin);
                        break;
                    case 26:
                        this.goneEndMargin = a.getDimensionPixelSize(attr, this.goneEndMargin);
                        break;
                    case 27:
                        this.constrainedWidth = a.getBoolean(attr, this.constrainedWidth);
                        break;
                    case 28:
                        this.constrainedHeight = a.getBoolean(attr, this.constrainedHeight);
                        break;
                    case 29:
                        this.horizontalBias = a.getFloat(attr, this.horizontalBias);
                        break;
                    case 30:
                        this.verticalBias = a.getFloat(attr, this.verticalBias);
                        break;
                    case 31:
                        this.matchConstraintDefaultWidth = a.getInt(attr, 0);
                        if (this.matchConstraintDefaultWidth == 1) {
                            Log.e(ConstraintLayout.TAG, "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                        }
                        break;
                    case 32:
                        this.matchConstraintDefaultHeight = a.getInt(attr, 0);
                        if (this.matchConstraintDefaultHeight == 1) {
                            Log.e(ConstraintLayout.TAG, "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                        }
                        break;
                    case 33:
                        try {
                            this.matchConstraintMinWidth = a.getDimensionPixelSize(attr, this.matchConstraintMinWidth);
                        } catch (Exception e) {
                            if (a.getInt(attr, this.matchConstraintMinWidth) == -2) {
                                this.matchConstraintMinWidth = -2;
                            }
                        }
                        break;
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = a.getDimensionPixelSize(attr, this.matchConstraintMaxWidth);
                        } catch (Exception e2) {
                            if (a.getInt(attr, this.matchConstraintMaxWidth) == -2) {
                                this.matchConstraintMaxWidth = -2;
                            }
                        }
                        break;
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(0.0f, a.getFloat(attr, this.matchConstraintPercentWidth));
                        break;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = a.getDimensionPixelSize(attr, this.matchConstraintMinHeight);
                        } catch (Exception e3) {
                            if (a.getInt(attr, this.matchConstraintMinHeight) == -2) {
                                this.matchConstraintMinHeight = -2;
                            }
                        }
                        break;
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = a.getDimensionPixelSize(attr, this.matchConstraintMaxHeight);
                        } catch (Exception e4) {
                            if (a.getInt(attr, this.matchConstraintMaxHeight) == -2) {
                                this.matchConstraintMaxHeight = -2;
                            }
                        }
                        break;
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(0.0f, a.getFloat(attr, this.matchConstraintPercentHeight));
                        break;
                    case 44:
                        this.dimensionRatio = a.getString(attr);
                        this.dimensionRatioValue = Float.NaN;
                        this.dimensionRatioSide = i;
                        if (this.dimensionRatio != null) {
                            int len = this.dimensionRatio.length();
                            int commaIndex2 = this.dimensionRatio.indexOf(44);
                            if (commaIndex2 <= 0 || commaIndex2 >= len - 1) {
                                commaIndex = 0;
                            } else {
                                String dimension = this.dimensionRatio.substring(i2, commaIndex2);
                                if (dimension.equalsIgnoreCase("W")) {
                                    this.dimensionRatioSide = i2;
                                } else if (dimension.equalsIgnoreCase("H")) {
                                    this.dimensionRatioSide = i3;
                                }
                                commaIndex = commaIndex2 + 1;
                            }
                            int colonIndex = this.dimensionRatio.indexOf(58);
                            if (colonIndex < 0 || colonIndex >= len - 1) {
                                String r = this.dimensionRatio.substring(commaIndex);
                                if (r.length() > 0) {
                                    try {
                                        this.dimensionRatioValue = Float.parseFloat(r);
                                    } catch (NumberFormatException e5) {
                                    }
                                }
                            } else {
                                String nominator = this.dimensionRatio.substring(commaIndex, colonIndex);
                                String denominator = this.dimensionRatio.substring(colonIndex + 1);
                                if (nominator.length() > 0 && denominator.length() > 0) {
                                    try {
                                        float nominatorValue = Float.parseFloat(nominator);
                                        float denominatorValue = Float.parseFloat(denominator);
                                        if (nominatorValue > f && denominatorValue > f) {
                                            if (this.dimensionRatioSide == 1) {
                                                try {
                                                    this.dimensionRatioValue = Math.abs(denominatorValue / nominatorValue);
                                                } catch (NumberFormatException e6) {
                                                }
                                            } else {
                                                this.dimensionRatioValue = Math.abs(nominatorValue / denominatorValue);
                                            }
                                        }
                                    } catch (NumberFormatException e7) {
                                    }
                                }
                            }
                        }
                        break;
                    case 45:
                        this.horizontalWeight = a.getFloat(attr, this.horizontalWeight);
                        break;
                    case 46:
                        this.verticalWeight = a.getFloat(attr, this.verticalWeight);
                        break;
                    case 47:
                        this.horizontalChainStyle = a.getInt(attr, i2);
                        break;
                    case 48:
                        this.verticalChainStyle = a.getInt(attr, i2);
                        break;
                    case 49:
                        this.editorAbsoluteX = a.getDimensionPixelOffset(attr, this.editorAbsoluteX);
                        break;
                    case 50:
                        this.editorAbsoluteY = a.getDimensionPixelOffset(attr, this.editorAbsoluteY);
                        break;
                }
                i4++;
                i = -1;
                i2 = 0;
                f = 0.0f;
                i3 = 1;
            }
            a.recycle();
            validate();
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                if (!(this.widget instanceof Guideline)) {
                    this.widget = new Guideline();
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int width, int height) {
            super(width, height);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
            this.guideBegin = -1;
            this.guideEnd = -1;
            this.guidePercent = -1.0f;
            this.leftToLeft = -1;
            this.leftToRight = -1;
            this.rightToLeft = -1;
            this.rightToRight = -1;
            this.topToTop = -1;
            this.topToBottom = -1;
            this.bottomToTop = -1;
            this.bottomToBottom = -1;
            this.baselineToBaseline = -1;
            this.circleConstraint = -1;
            this.circleRadius = 0;
            this.circleAngle = 0.0f;
            this.startToEnd = -1;
            this.startToStart = -1;
            this.endToStart = -1;
            this.endToEnd = -1;
            this.goneLeftMargin = -1;
            this.goneTopMargin = -1;
            this.goneRightMargin = -1;
            this.goneBottomMargin = -1;
            this.goneStartMargin = -1;
            this.goneEndMargin = -1;
            this.horizontalBias = 0.5f;
            this.verticalBias = 0.5f;
            this.dimensionRatio = null;
            this.dimensionRatioValue = 0.0f;
            this.dimensionRatioSide = 1;
            this.horizontalWeight = -1.0f;
            this.verticalWeight = -1.0f;
            this.horizontalChainStyle = 0;
            this.verticalChainStyle = 0;
            this.matchConstraintDefaultWidth = 0;
            this.matchConstraintDefaultHeight = 0;
            this.matchConstraintMinWidth = 0;
            this.matchConstraintMinHeight = 0;
            this.matchConstraintMaxWidth = 0;
            this.matchConstraintMaxHeight = 0;
            this.matchConstraintPercentWidth = 1.0f;
            this.matchConstraintPercentHeight = 1.0f;
            this.editorAbsoluteX = -1;
            this.editorAbsoluteY = -1;
            this.orientation = -1;
            this.constrainedWidth = false;
            this.constrainedHeight = false;
            this.horizontalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.verticalDimensionFixed = ConstraintLayout.USE_CONSTRAINTS_HELPER;
            this.needsBaseline = false;
            this.isGuideline = false;
            this.isHelper = false;
            this.isInPlaceholder = false;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolvedHorizontalBias = 0.5f;
            this.widget = new ConstraintWidget();
            this.helped = false;
        }

        @Override // android.view.ViewGroup.MarginLayoutParams, android.view.ViewGroup.LayoutParams
        @TargetApi(17)
        public void resolveLayoutDirection(int layoutDirection) {
            int preLeftMargin = this.leftMargin;
            int preRightMargin = this.rightMargin;
            super.resolveLayoutDirection(layoutDirection);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            this.resolvedGuideBegin = this.guideBegin;
            this.resolvedGuideEnd = this.guideEnd;
            this.resolvedGuidePercent = this.guidePercent;
            if (1 == getLayoutDirection() ? ConstraintLayout.USE_CONSTRAINTS_HELPER : false) {
                boolean startEndDefined = false;
                if (this.startToEnd != -1) {
                    this.resolvedRightToLeft = this.startToEnd;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                } else if (this.startToStart != -1) {
                    this.resolvedRightToRight = this.startToStart;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.endToStart != -1) {
                    this.resolvedLeftToRight = this.endToStart;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.endToEnd != -1) {
                    this.resolvedLeftToLeft = this.endToEnd;
                    startEndDefined = ConstraintLayout.USE_CONSTRAINTS_HELPER;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneRightMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneEndMargin;
                }
                if (startEndDefined) {
                    this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                }
                if (this.isGuideline && this.orientation == 1) {
                    if (this.guidePercent != -1.0f) {
                        this.resolvedGuidePercent = 1.0f - this.guidePercent;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuideEnd = -1;
                    } else if (this.guideBegin != -1) {
                        this.resolvedGuideEnd = this.guideBegin;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuidePercent = -1.0f;
                    } else if (this.guideEnd != -1) {
                        this.resolvedGuideBegin = this.guideEnd;
                        this.resolvedGuideEnd = -1;
                        this.resolvedGuidePercent = -1.0f;
                    }
                }
            } else {
                if (this.startToEnd != -1) {
                    this.resolvedLeftToRight = this.startToEnd;
                }
                if (this.startToStart != -1) {
                    this.resolvedLeftToLeft = this.startToStart;
                }
                if (this.endToStart != -1) {
                    this.resolvedRightToLeft = this.endToStart;
                }
                if (this.endToEnd != -1) {
                    this.resolvedRightToRight = this.endToEnd;
                }
                if (this.goneStartMargin != -1) {
                    this.resolveGoneLeftMargin = this.goneStartMargin;
                }
                if (this.goneEndMargin != -1) {
                    this.resolveGoneRightMargin = this.goneEndMargin;
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                if (this.rightToLeft != -1) {
                    this.resolvedRightToLeft = this.rightToLeft;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                } else if (this.rightToRight != -1) {
                    this.resolvedRightToRight = this.rightToRight;
                    if (this.rightMargin <= 0 && preRightMargin > 0) {
                        this.rightMargin = preRightMargin;
                    }
                }
                if (this.leftToLeft != -1) {
                    this.resolvedLeftToLeft = this.leftToLeft;
                    if (this.leftMargin <= 0 && preLeftMargin > 0) {
                        this.leftMargin = preLeftMargin;
                    }
                } else if (this.leftToRight != -1) {
                    this.resolvedLeftToRight = this.leftToRight;
                    if (this.leftMargin <= 0 && preLeftMargin > 0) {
                        this.leftMargin = preLeftMargin;
                    }
                }
            }
        }
    }

    @Override // android.view.ViewParent, android.view.View
    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = USE_CONSTRAINTS_HELPER;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }

    @Override // android.view.ViewGroup
    public boolean shouldDelayChildPressedState() {
        return false;
    }
}
