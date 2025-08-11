package com.netlight.sec.finstergram.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.netlight.sec.finstergram.R;

/* loaded from: classes7.dex */
public final class ActivityImageLoaderBinding implements ViewBinding {
    public final ConstraintLayout imageLoader;
    public final ImageView imageView;
    public final TextView metadataText;
    private final ConstraintLayout rootView;
    public final TextView text;

    private ActivityImageLoaderBinding(ConstraintLayout rootView, ConstraintLayout imageLoader, ImageView imageView, TextView metadataText, TextView text) {
        this.rootView = rootView;
        this.imageLoader = imageLoader;
        this.imageView = imageView;
        this.metadataText = metadataText;
        this.text = text;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityImageLoaderBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityImageLoaderBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_image_loader, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityImageLoaderBinding bind(View rootView) {
        ConstraintLayout imageLoader = (ConstraintLayout) rootView;
        int id = R.id.imageView;
        ImageView imageView = (ImageView) ViewBindings.findChildViewById(rootView, R.id.imageView);
        if (imageView != null) {
            id = R.id.metadata_text;
            TextView metadataText = (TextView) ViewBindings.findChildViewById(rootView, R.id.metadata_text);
            if (metadataText != null) {
                TextView text = (TextView) ViewBindings.findChildViewById(rootView, R.id.text);
                if (text == null) {
                    id = R.id.text;
                } else {
                    return new ActivityImageLoaderBinding((ConstraintLayout) rootView, imageLoader, imageView, metadataText, text);
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
