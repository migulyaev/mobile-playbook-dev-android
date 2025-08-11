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
public final class ActivitySharedImageBinding implements ViewBinding {
    public final Button continueButton;
    public final LinearLayout rootView;
    private final LinearLayout rootView_;

    private ActivitySharedImageBinding(LinearLayout rootView_, Button continueButton, LinearLayout rootView) {
        this.rootView_ = rootView_;
        this.continueButton = continueButton;
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView_;
    }

    public static ActivitySharedImageBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySharedImageBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_shared_image, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySharedImageBinding bind(View rootView) {
        Button continueButton = (Button) ViewBindings.findChildViewById(rootView, R.id.continueButton);
        if (continueButton == null) {
            String missingId = rootView.getResources().getResourceName(R.id.continueButton);
            throw new NullPointerException("Missing required view with ID: ".concat(missingId));
        }
        LinearLayout rootView_ = (LinearLayout) rootView;
        return new ActivitySharedImageBinding((LinearLayout) rootView, continueButton, rootView_);
    }
}
