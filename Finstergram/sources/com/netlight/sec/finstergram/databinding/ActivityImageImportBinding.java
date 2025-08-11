package com.netlight.sec.finstergram.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.netlight.sec.finstergram.R;

/* loaded from: classes7.dex */
public final class ActivityImageImportBinding implements ViewBinding {
    public final Button importButton;
    public final LinearLayout rootView;
    private final LinearLayout rootView_;

    private ActivityImageImportBinding(LinearLayout rootView_, Button importButton, LinearLayout rootView) {
        this.rootView_ = rootView_;
        this.importButton = importButton;
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView_;
    }

    public static ActivityImageImportBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityImageImportBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_image_import, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityImageImportBinding bind(View rootView) {
        Button importButton = (Button) ViewBindings.findChildViewById(rootView, R.id.importButton);
        if (importButton == null) {
            String missingId = rootView.getResources().getResourceName(R.id.importButton);
            throw new NullPointerException("Missing required view with ID: ".concat(missingId));
        }
        LinearLayout rootView_ = (LinearLayout) rootView;
        return new ActivityImageImportBinding((LinearLayout) rootView, importButton, rootView_);
    }
}
