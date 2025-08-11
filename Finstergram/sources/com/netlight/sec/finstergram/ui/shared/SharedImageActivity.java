package com.netlight.sec.finstergram.ui.shared;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.material.snackbar.Snackbar;
import com.netlight.sec.finstergram.R;
import com.netlight.sec.finstergram.data.ImageStore;
import com.netlight.sec.finstergram.ui.FinstergramBaseActivity;
import com.netlight.sec.finstergram.ui.login.LoginActivity;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.Json;

/* compiled from: SharedImageActivity.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \u001c2\u00020\u0001:\u0002\u001c\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0002J\u0012\u0010\u0012\u001a\u00020\r2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\rH\u0014J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016R\u001c\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\n \u0005*\u0004\u0018\u00010\t0\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001e"}, d2 = {"Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity;", "Lcom/netlight/sec/finstergram/ui/FinstergramBaseActivity;", "()V", "continueButton", "Landroid/widget/Button;", "kotlin.jvm.PlatformType", "getContinueButton", "()Landroid/widget/Button;", "rootView", "Landroid/widget/LinearLayout;", "getRootView", "()Landroid/widget/LinearLayout;", "importImage", "", "imageUri", "Landroid/net/Uri;", "metadataOptions", "Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "parseMetadataOptions", "intent", "Landroid/content/Intent;", "setBackgroundColor", TypedValues.Custom.S_COLOR, "", "Companion", "MetadataOptions", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes6.dex */
public final class SharedImageActivity extends FinstergramBaseActivity {
    public static final String INTENT_EXTRA_IMG_METADATA_OPTION = "INTENT_EXTRA_IMG_METADATA_OPTION";
    public static final String INTENT_EXTRA_IMG_METADATA_OPTION_TYPE = "INTENT_EXTRA_IMG_METADATA_OPTION_TYPE";

    private final LinearLayout getRootView() {
        return (LinearLayout) findViewById(R.id.rootView);
    }

    private final Button getContinueButton() {
        return (Button) findViewById(R.id.continueButton);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_image);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.netlight.sec.finstergram.ui.FinstergramBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getContinueButton().setOnClickListener(new View.OnClickListener() { // from class: com.netlight.sec.finstergram.ui.shared.SharedImageActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SharedImageActivity.onResume$lambda$0(SharedImageActivity.this, view);
            }
        });
        Parcelable parcelableExtra = getIntent().getParcelableExtra("android.intent.extra.STREAM");
        Uri imageUri = parcelableExtra instanceof Uri ? (Uri) parcelableExtra : null;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        MetadataOptions imageMetadataOptions = parseMetadataOptions(intent);
        if (imageUri != null) {
            importImage(imageUri, imageMetadataOptions);
            Snackbar.make(getRootView(), "Image imported!", -1).show();
            setResult(-1, getIntent());
        } else {
            setResult(0, getIntent());
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$0(SharedImageActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, (Class<?>) LoginActivity.class);
        this$0.startActivity(intent);
        this$0.finish();
    }

    private final MetadataOptions parseMetadataOptions(Intent intent) {
        String optionType = intent.getStringExtra(INTENT_EXTRA_IMG_METADATA_OPTION_TYPE);
        String option = intent.getStringExtra(INTENT_EXTRA_IMG_METADATA_OPTION);
        if (optionType == null || option == null) {
            return null;
        }
        Class<?> cls = Class.forName(optionType);
        Intrinsics.checkNotNullExpressionValue(cls, "forName(optionType)");
        KClass optionClass = JvmClassMappingKt.getKotlinClass(cls);
        KSerializer serializer = SerializersKt.serializer(optionClass);
        Object decodeFromString = Json.INSTANCE.decodeFromString(serializer, option);
        if (decodeFromString instanceof MetadataOptions) {
            return (MetadataOptions) decodeFromString;
        }
        return null;
    }

    private final void importImage(Uri imageUri, MetadataOptions metadataOptions) {
        ImageStore.INSTANCE.storeImage(this, imageUri);
    }

    /* compiled from: SharedImageActivity.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions;", "", "()V", "AddMetadata", "KeepMetadata", "StripMetadata", "Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions$AddMetadata;", "Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions$KeepMetadata;", "Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions$StripMetadata;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes6.dex */
    public static abstract class MetadataOptions {
        public /* synthetic */ MetadataOptions(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private MetadataOptions() {
        }

        /* compiled from: SharedImageActivity.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions$AddMetadata;", "Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions;", "data", "", "(Ljava/lang/String;)V", "getData", "()Ljava/lang/String;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
        /* loaded from: classes6.dex */
        public static final class AddMetadata extends MetadataOptions {
            private final String data;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public AddMetadata(String data) {
                super(null);
                Intrinsics.checkNotNullParameter(data, "data");
                this.data = data;
            }

            public final String getData() {
                return this.data;
            }
        }

        /* compiled from: SharedImageActivity.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions$KeepMetadata;", "Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions;", "()V", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
        /* loaded from: classes6.dex */
        public static final class KeepMetadata extends MetadataOptions {
            public static final KeepMetadata INSTANCE = new KeepMetadata();

            private KeepMetadata() {
                super(null);
            }
        }

        /* compiled from: SharedImageActivity.kt */
        @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions$StripMetadata;", "Lcom/netlight/sec/finstergram/ui/shared/SharedImageActivity$MetadataOptions;", "()V", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
        /* loaded from: classes6.dex */
        public static final class StripMetadata extends MetadataOptions {
            public static final StripMetadata INSTANCE = new StripMetadata();

            private StripMetadata() {
                super(null);
            }
        }
    }

    @Override // com.netlight.sec.finstergram.ui.FinstergramBaseActivity
    public void setBackgroundColor(int color) {
        getRootView().setBackgroundColor(color);
    }
}
