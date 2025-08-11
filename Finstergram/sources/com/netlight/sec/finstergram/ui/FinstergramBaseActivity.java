package com.netlight.sec.finstergram.ui;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.netlight.sec.finstergram.data.UserSettings;
import kotlin.Metadata;

/* compiled from: FinstergramBaseActivity.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0014J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0004H&R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006¨\u0006\r"}, d2 = {"Lcom/netlight/sec/finstergram/ui/FinstergramBaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "dark", "", "getDark", "()I", "light", "getLight", "onResume", "", "setBackgroundColor", TypedValues.Custom.S_COLOR, "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public abstract class FinstergramBaseActivity extends AppCompatActivity {
    private final int dark = Color.parseColor("#68696b");
    private final int light = Color.parseColor("#ffffff");

    public abstract void setBackgroundColor(int color);

    public final int getDark() {
        return this.dark;
    }

    public final int getLight() {
        return this.light;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        int backGroundColor;
        super.onResume();
        if (UserSettings.INSTANCE.getInstance().getDarkMode()) {
            backGroundColor = this.dark;
        } else {
            backGroundColor = this.light;
        }
        setBackgroundColor(backGroundColor);
    }
}
