package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Chain {
    private static final boolean DEBUG = false;

    Chain() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void applyChainConstraints(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem system, int orientation) {
        ChainHead[] chainsArray;
        int chainsSize;
        int offset;
        if (orientation == 0) {
            offset = 0;
            chainsSize = constraintWidgetContainer.mHorizontalChainsSize;
            chainsArray = constraintWidgetContainer.mHorizontalChainsArray;
        } else {
            offset = 2;
            chainsSize = constraintWidgetContainer.mVerticalChainsSize;
            chainsArray = constraintWidgetContainer.mVerticalChainsArray;
        }
        for (int i = 0; i < chainsSize; i++) {
            ChainHead first = chainsArray[i];
            first.define();
            if (!constraintWidgetContainer.optimizeFor(4)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            } else if (!Optimizer.applyChainOptimized(constraintWidgetContainer, system, orientation, offset, first)) {
                applyChainConstraints(constraintWidgetContainer, system, orientation, offset, first);
            }
        }
    }

    /* JADX INFO: Multiple debug info for r2v32 'beginNextAnchor'  android.support.constraint.solver.widgets.ConstraintAnchor: [D('beginNext' android.support.constraint.solver.SolverVariable), D('beginNextAnchor' android.support.constraint.solver.widgets.ConstraintAnchor)] */
    /* JADX INFO: Multiple debug info for r3v40 android.support.constraint.solver.SolverVariable: [D('beginNext' android.support.constraint.solver.SolverVariable), D('beginNextTarget' android.support.constraint.solver.SolverVariable)] */
    /* JADX INFO: Multiple debug info for r4v28 android.support.constraint.solver.ArrayRow: [D('lastMatch' android.support.constraint.solver.widgets.ConstraintWidget), D('row' android.support.constraint.solver.ArrayRow)] */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0633  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x0638  */
    /* JADX WARN: Removed duplicated region for block: B:295:0x063f  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0644  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0649  */
    /* JADX WARN: Removed duplicated region for block: B:306:0x065e  */
    /* Code decompiled incorrectly, please refer to instructions dump */
    static void applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer r62, android.support.constraint.solver.LinearSystem r63, int r64, int r65, android.support.constraint.solver.widgets.ChainHead r66) {
        /*
        // Method dump skipped, instructions count: 1704
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.Chain.applyChainConstraints(android.support.constraint.solver.widgets.ConstraintWidgetContainer, android.support.constraint.solver.LinearSystem, int, int, android.support.constraint.solver.widgets.ChainHead):void");
    }
}
