package com.netlight.sec.finstergram.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.netlight.sec.finstergram.R;

/* loaded from: classes7.dex */
public final class ListItemBinding implements ViewBinding {
    public final ImageButton listItemDeleteBtn;
    public final ImageView listItemImage;
    public final LinearLayout listItemRoot;
    public final ImageButton listItemShareBtn;
    private final LinearLayout rootView;

    private ListItemBinding(LinearLayout rootView, ImageButton listItemDeleteBtn, ImageView listItemImage, LinearLayout listItemRoot, ImageButton listItemShareBtn) {
        this.rootView = rootView;
        this.listItemDeleteBtn = listItemDeleteBtn;
        this.listItemImage = listItemImage;
        this.listItemRoot = listItemRoot;
        this.listItemShareBtn = listItemShareBtn;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ListItemBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ListItemBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.list_item, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ListItemBinding bind(View rootView) {
        int id = R.id.list_item_delete_btn;
        ImageButton listItemDeleteBtn = (ImageButton) ViewBindings.findChildViewById(rootView, R.id.list_item_delete_btn);
        if (listItemDeleteBtn != null) {
            id = R.id.list_item_image;
            ImageView listItemImage = (ImageView) ViewBindings.findChildViewById(rootView, R.id.list_item_image);
            if (listItemImage != null) {
                LinearLayout listItemRoot = (LinearLayout) rootView;
                id = R.id.list_item_share_btn;
                ImageButton listItemShareBtn = (ImageButton) ViewBindings.findChildViewById(rootView, R.id.list_item_share_btn);
                if (listItemShareBtn != null) {
                    return new ListItemBinding((LinearLayout) rootView, listItemDeleteBtn, listItemImage, listItemRoot, listItemShareBtn);
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
