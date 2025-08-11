package com.netlight.sec.finstergram.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.netlight.sec.finstergram.R;

/* loaded from: classes7.dex */
public final class ActivityImageDetailBinding implements ViewBinding {
    public final ImageView image;
    public final LinearLayout rootView;
    private final LinearLayout rootView_;

    private ActivityImageDetailBinding(LinearLayout rootView_, ImageView image, LinearLayout rootView) {
        this.rootView_ = rootView_;
        this.image = image;
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView_;
    }

    public static ActivityImageDetailBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityImageDetailBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_image_detail, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityImageDetailBinding bind(View rootView) {
        ImageView image = (ImageView) ViewBindings.findChildViewById(rootView, R.id.image);
        if (image == null) {
            String missingId = rootView.getResources().getResourceName(R.id.image);
            throw new NullPointerException("Missing required view with ID: ".concat(missingId));
        }
        LinearLayout rootView_ = (LinearLayout) rootView;
        return new ActivityImageDetailBinding((LinearLayout) rootView, image, rootView_);
    }
}
