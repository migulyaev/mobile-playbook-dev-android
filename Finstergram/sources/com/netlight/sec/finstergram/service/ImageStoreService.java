package com.netlight.sec.finstergram.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import com.netlight.sec.finstergram.data.ImageStore;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageStoreService.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00162\u00020\u0001:\u0002\u0015\u0016B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0002J\u0012\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\r\u001a\u00020\u0006H\u0016J\"\u0010\u000e\u001a\u00020\u000f2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016J\u0016\u0010\u0012\u001a\u00020\u00062\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/netlight/sec/finstergram/service/ImageStoreService;", "Landroid/app/Service;", "()V", "commandReceiver", "Landroid/content/BroadcastReceiver;", "handleDeleteAllImages", "", "handleDeleteImage", "intent", "Landroid/content/Intent;", "handleStoreImage", "onBind", "Landroid/os/IBinder;", "onDestroy", "onStartCommand", "", "flags", "startId", "performAsync", "block", "Lkotlin/Function0;", "Command", "Companion", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes7.dex */
public final class ImageStoreService extends Service {
    public static final String INTENT_ACTION_FINSTERGRAM_IMG_SERVICE = "INTENT_ACTION_FINSTERGRAM_IMG_SERVICE";
    public static final String INTENT_EXTRA_COMMAND = "INTENT_EXTRA_COMMAND";
    public static final String INTENT_EXTRA_DESTINATION_PATH = "INTENT_EXTRA_DESTINATION_PATH";
    private final BroadcastReceiver commandReceiver = new BroadcastReceiver() { // from class: com.netlight.sec.finstergram.service.ImageStoreService$commandReceiver$1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            String command = intent != null ? intent.getStringExtra(ImageStoreService.INTENT_EXTRA_COMMAND) : null;
            Log.i("SERVICE", "Received Broadcast with command " + command);
            if (command != null) {
                switch (command.hashCode()) {
                    case -2044012307:
                        if (command.equals("DELETE_ALL")) {
                            ImageStoreService imageStoreService = ImageStoreService.this;
                            final ImageStoreService imageStoreService2 = ImageStoreService.this;
                            imageStoreService.performAsync(new Function0<Unit>() { // from class: com.netlight.sec.finstergram.service.ImageStoreService$commandReceiver$1$onReceive$2
                                /* JADX INFO: Access modifiers changed from: package-private */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    ImageStoreService.this.handleDeleteAllImages();
                                }
                            });
                            return;
                        }
                        return;
                    case -1488363097:
                        if (command.equals("DELETE_IMAGE")) {
                            ImageStoreService imageStoreService3 = ImageStoreService.this;
                            final ImageStoreService imageStoreService4 = ImageStoreService.this;
                            imageStoreService3.performAsync(new Function0<Unit>() { // from class: com.netlight.sec.finstergram.service.ImageStoreService$commandReceiver$1$onReceive$1
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    ImageStoreService.this.handleDeleteImage(intent);
                                }
                            });
                            return;
                        }
                        return;
                    case 1573961789:
                        if (command.equals("STORE_IMAGE")) {
                            ImageStoreService imageStoreService5 = ImageStoreService.this;
                            final ImageStoreService imageStoreService6 = ImageStoreService.this;
                            imageStoreService5.performAsync(new Function0<Unit>() { // from class: com.netlight.sec.finstergram.service.ImageStoreService$commandReceiver$1$onReceive$3
                                /* JADX INFO: Access modifiers changed from: package-private */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    ImageStoreService.this.handleStoreImage(intent);
                                }
                            });
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    };

    /* compiled from: ImageStoreService.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/netlight/sec/finstergram/service/ImageStoreService$Command;", "", "(Ljava/lang/String;I)V", "DELETE_IMAGE", "DELETE_ALL", "STORE_IMAGE", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes7.dex */
    public enum Command {
        DELETE_IMAGE,
        DELETE_ALL,
        STORE_IMAGE
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter(INTENT_ACTION_FINSTERGRAM_IMG_SERVICE);
        IntentFilter filterWithData = new IntentFilter(INTENT_ACTION_FINSTERGRAM_IMG_SERVICE, "*/*");
        registerReceiver(this.commandReceiver, filter);
        registerReceiver(this.commandReceiver, filterWithData);
        Log.i("SERVICE", "Service started");
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.commandReceiver);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDeleteImage(Intent intent) {
        String destinationPath = intent != null ? intent.getStringExtra(INTENT_EXTRA_DESTINATION_PATH) : null;
        if (destinationPath != null) {
            ImageStore.INSTANCE.deleteImageSync(destinationPath);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDeleteAllImages() {
        ImageStore.INSTANCE.deleteAllImagesSync(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleStoreImage(Intent intent) {
        Uri it;
        if (intent != null && (it = intent.getData()) != null) {
            ImageStore.INSTANCE.storeImageSync(this, it);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void performAsync(final Function0<Unit> block) {
        new Thread(new Runnable() { // from class: com.netlight.sec.finstergram.service.ImageStoreService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ImageStoreService.performAsync$lambda$2(Function0.this);
            }
        }, "ImageStoreService background thread").start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void performAsync$lambda$2(Function0 block) {
        Intrinsics.checkNotNullParameter(block, "$block");
        block.invoke();
    }
}
