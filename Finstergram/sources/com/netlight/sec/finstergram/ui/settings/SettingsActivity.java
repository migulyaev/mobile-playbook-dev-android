package com.netlight.sec.finstergram.ui.settings;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.netlight.sec.finstergram.R;
import com.netlight.sec.finstergram.data.UserSettings;
import com.netlight.sec.finstergram.ui.FinstergramBaseActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsActivity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0014J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/netlight/sec/finstergram/ui/settings/SettingsActivity;", "Lcom/netlight/sec/finstergram/ui/FinstergramBaseActivity;", "()V", "darkModeSwitch", "Landroidx/appcompat/widget/SwitchCompat;", "getDarkModeSwitch", "()Landroidx/appcompat/widget/SwitchCompat;", "passwordRequiredSwitch", "getPasswordRequiredSwitch", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "setBackgroundColor", TypedValues.Custom.S_COLOR, "", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes8.dex */
public final class SettingsActivity extends FinstergramBaseActivity {
    private final SwitchCompat getDarkModeSwitch() {
        View findViewById = findViewById(R.id.darkModeSwitch);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.darkModeSwitch)");
        return (SwitchCompat) findViewById;
    }

    private final SwitchCompat getPasswordRequiredSwitch() {
        View findViewById = findViewById(R.id.passwordRequiredSwitch);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.passwordRequiredSwitch)");
        return (SwitchCompat) findViewById;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        UserSettings settings = UserSettings.INSTANCE.getInstance();
        getDarkModeSwitch().setChecked(settings.getDarkMode());
        getPasswordRequiredSwitch().setChecked(settings.getRequirePassword());
        getDarkModeSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.netlight.sec.finstergram.ui.settings.SettingsActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SettingsActivity.onCreate$lambda$0(SettingsActivity.this, compoundButton, z);
            }
        });
        getPasswordRequiredSwitch().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.netlight.sec.finstergram.ui.settings.SettingsActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SettingsActivity.onCreate$lambda$1(SettingsActivity.this, compoundButton, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(SettingsActivity this$0, CompoundButton compoundButton, boolean checked) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserSettings.Companion.store$default(UserSettings.INSTANCE, this$0, checked, false, 4, null);
        this$0.setBackgroundColor(checked ? this$0.getDark() : this$0.getLight());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$1(SettingsActivity this$0, CompoundButton compoundButton, boolean checked) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UserSettings.Companion.store$default(UserSettings.INSTANCE, this$0, false, checked, 2, null);
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        if (item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onContextItemSelected(item);
    }

    @Override // com.netlight.sec.finstergram.ui.FinstergramBaseActivity
    public void setBackgroundColor(int color) {
        ((LinearLayout) findViewById(R.id.rootView)).setBackgroundColor(color);
    }
}
