package com.netlight.sec.finstergram.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.netlight.sec.finstergram.R;

/* loaded from: classes7.dex */
public final class ActivityImageListBinding implements ViewBinding {
    public final CoordinatorLayout coordinatorLayout;
    public final FloatingActionButton fab;
    public final ListView listView;
    public final TextView noImages;
    private final CoordinatorLayout rootView;

    private ActivityImageListBinding(CoordinatorLayout rootView, CoordinatorLayout coordinatorLayout, FloatingActionButton fab, ListView listView, TextView noImages) {
        this.rootView = rootView;
        this.coordinatorLayout = coordinatorLayout;
        this.fab = fab;
        this.listView = listView;
        this.noImages = noImages;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static ActivityImageListBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityImageListBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_image_list, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityImageListBinding bind(View rootView) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) rootView;
        int id = R.id.fab;
        FloatingActionButton fab = (FloatingActionButton) ViewBindings.findChildViewById(rootView, R.id.fab);
        if (fab != null) {
            id = R.id.listView;
            ListView listView = (ListView) ViewBindings.findChildViewById(rootView, R.id.listView);
            if (listView != null) {
                TextView noImages = (TextView) ViewBindings.findChildViewById(rootView, R.id.noImages);
                if (noImages == null) {
                    id = R.id.noImages;
                } else {
                    return new ActivityImageListBinding((CoordinatorLayout) rootView, coordinatorLayout, fab, listView, noImages);
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
