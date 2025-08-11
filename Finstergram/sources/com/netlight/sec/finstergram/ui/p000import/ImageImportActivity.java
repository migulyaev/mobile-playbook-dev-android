package com.netlight.sec.finstergram.ui.p000import;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.material.snackbar.Snackbar;
import com.netlight.sec.finstergram.R;
import com.netlight.sec.finstergram.data.ImageStore;
import com.netlight.sec.finstergram.ui.FinstergramBaseActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageImportActivity.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\f2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/netlight/sec/finstergram/ui/import/ImageImportActivity;", "Lcom/netlight/sec/finstergram/ui/FinstergramBaseActivity;", "()V", "importButton", "Landroid/widget/Button;", "getImportButton", "()Landroid/widget/Button;", "rootView", "Landroid/widget/LinearLayout;", "getRootView", "()Landroid/widget/LinearLayout;", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "setBackgroundColor", TypedValues.Custom.S_COLOR, "Companion", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes9.dex */
public final class ImageImportActivity extends FinstergramBaseActivity {
    public static final int PICK_IMAGE_REQUEST_CODE = 1337;

    private final LinearLayout getRootView() {
        View findViewById = findViewById(R.id.rootView);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.rootView)");
        return (LinearLayout) findViewById;
    }

    private final Button getImportButton() {
        View findViewById = findViewById(R.id.importButton);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.importButton)");
        return (Button) findViewById;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_import);
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        getImportButton().setOnClickListener(new View.OnClickListener() { // from class: com.netlight.sec.finstergram.ui.import.ImageImportActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImageImportActivity.onCreate$lambda$0(ImageImportActivity.this, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onCreate$lambda$0(ImageImportActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        this$0.startActivityForResult(intent, PICK_IMAGE_REQUEST_CODE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri imageUri;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1337 && resultCode == -1 && data != null && (imageUri = data.getData()) != null) {
            ImageStore.INSTANCE.storeImage(this, imageUri);
            Snackbar.make(getRootView(), "Image imported!", -1).show();
        }
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
        getRootView().setBackgroundColor(color);
    }
}
