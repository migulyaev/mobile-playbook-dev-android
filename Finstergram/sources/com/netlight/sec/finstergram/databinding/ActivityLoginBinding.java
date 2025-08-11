package com.netlight.sec.finstergram.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.netlight.sec.finstergram.R;

/* loaded from: classes7.dex */
public final class ActivityLoginBinding implements ViewBinding {
    public final Button buttonRegisterLogin;
    public final EditText password;
    public final ConstraintLayout rootView;
    private final ConstraintLayout rootView_;
    public final EditText username;
    public final TextView welcomeSubtitle;
    public final TextView welcomeTitle;

    private ActivityLoginBinding(ConstraintLayout rootView_, Button buttonRegisterLogin, EditText password, ConstraintLayout rootView, EditText username, TextView welcomeSubtitle, TextView welcomeTitle) {
        this.rootView_ = rootView_;
        this.buttonRegisterLogin = buttonRegisterLogin;
        this.password = password;
        this.rootView = rootView;
        this.username = username;
        this.welcomeSubtitle = welcomeSubtitle;
        this.welcomeTitle = welcomeTitle;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView_;
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityLoginBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_login, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityLoginBinding bind(View rootView) {
        int id = R.id.buttonRegisterLogin;
        Button buttonRegisterLogin = (Button) ViewBindings.findChildViewById(rootView, R.id.buttonRegisterLogin);
        if (buttonRegisterLogin != null) {
            id = R.id.password;
            EditText password = (EditText) ViewBindings.findChildViewById(rootView, R.id.password);
            if (password != null) {
                ConstraintLayout rootView_ = (ConstraintLayout) rootView;
                id = R.id.username;
                EditText username = (EditText) ViewBindings.findChildViewById(rootView, R.id.username);
                if (username != null) {
                    id = R.id.welcomeSubtitle;
                    TextView welcomeSubtitle = (TextView) ViewBindings.findChildViewById(rootView, R.id.welcomeSubtitle);
                    if (welcomeSubtitle != null) {
                        id = R.id.welcomeTitle;
                        TextView welcomeTitle = (TextView) ViewBindings.findChildViewById(rootView, R.id.welcomeTitle);
                        if (welcomeTitle != null) {
                            return new ActivityLoginBinding((ConstraintLayout) rootView, buttonRegisterLogin, password, rootView_, username, welcomeSubtitle, welcomeTitle);
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}
