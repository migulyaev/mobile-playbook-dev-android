package com.netlight.sec.finstergram.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import androidx.appcompat.widget.SwitchCompat;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.netlight.sec.finstergram.R;

/* loaded from: classes7.dex */
public final class ActivitySettingsBinding implements ViewBinding {
    public final SwitchCompat darkModeSwitch;
    public final SwitchCompat passwordRequiredSwitch;
    public final LinearLayout rootView;
    private final LinearLayout rootView_;

    private ActivitySettingsBinding(LinearLayout rootView_, SwitchCompat darkModeSwitch, SwitchCompat passwordRequiredSwitch, LinearLayout rootView) {
        this.rootView_ = rootView_;
        this.darkModeSwitch = darkModeSwitch;
        this.passwordRequiredSwitch = passwordRequiredSwitch;
        this.rootView = rootView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView_;
    }

    public static ActivitySettingsBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivitySettingsBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_settings, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivitySettingsBinding bind(View rootView) {
        int id = R.id.darkModeSwitch;
        SwitchCompat darkModeSwitch = (SwitchCompat) ViewBindings.findChildViewById(rootView, R.id.darkModeSwitch);
        if (darkModeSwitch != null) {
            id = R.id.passwordRequiredSwitch;
            SwitchCompat passwordRequiredSwitch = (SwitchCompat) ViewBindings.findChildViewById(rootView, R.id.passwordRequiredSwitch);
            if (passwordRequiredSwitch != null) {
                LinearLayout rootView_ = (LinearLayout) rootView;
                return new ActivitySettingsBinding((LinearLayout) rootView, darkModeSwitch, passwordRequiredSwitch, rootView_);
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
