package com.netlight.sec.finstergram.ui.list;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.FileProvider;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.netlight.sec.finstergram.R;
import com.netlight.sec.finstergram.data.ImageStore;
import com.netlight.sec.finstergram.data.UserSettings;
import com.netlight.sec.finstergram.ui.FinstergramBaseActivity;
import com.netlight.sec.finstergram.ui.detail.ImageDetailActivity;
import com.netlight.sec.finstergram.ui.p000import.ImageImportActivity;
import com.netlight.sec.finstergram.ui.settings.SettingsActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageListActivity.kt */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014H\u0002J\u0012\u0010\u0016\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u0012H\u0014J\u0010\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020#H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lcom/netlight/sec/finstergram/ui/list/ImageListActivity;", "Lcom/netlight/sec/finstergram/ui/FinstergramBaseActivity;", "()V", "addButton", "Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "getAddButton", "()Lcom/google/android/material/floatingactionbutton/FloatingActionButton;", "listView", "Landroid/widget/ListView;", "getListView", "()Landroid/widget/ListView;", "noImagesMessage", "Landroid/widget/TextView;", "getNoImagesMessage", "()Landroid/widget/TextView;", "viewModel", "Lcom/netlight/sec/finstergram/ui/list/ImageListViewModel;", "createImageList", "", "itemList", "", "Lcom/netlight/sec/finstergram/ui/list/ListItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onResume", "setBackgroundColor", TypedValues.Custom.S_COLOR, "", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes6.dex */
public final class ImageListActivity extends FinstergramBaseActivity {
    private ImageListViewModel viewModel;

    private final ListView getListView() {
        View findViewById = findViewById(R.id.listView);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.listView)");
        return (ListView) findViewById;
    }

    private final TextView getNoImagesMessage() {
        View findViewById = findViewById(R.id.noImages);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.noImages)");
        return (TextView) findViewById;
    }

    private final FloatingActionButton getAddButton() {
        View findViewById = findViewById(R.id.fab);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.fab)");
        return (FloatingActionButton) findViewById;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        this.viewModel = (ImageListViewModel) new ViewModelProvider(this).get(ImageListViewModel.class);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.netlight.sec.finstergram.ui.FinstergramBaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        getAddButton().setOnClickListener(new View.OnClickListener() { // from class: com.netlight.sec.finstergram.ui.list.ImageListActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImageListActivity.onResume$lambda$0(ImageListActivity.this, view);
            }
        });
        Iterable $this$map$iv = ImageStore.INSTANCE.loadImages(this);
        Collection destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10));
        for (Object item$iv$iv : $this$map$iv) {
            Pair it = (Pair) item$iv$iv;
            destination$iv$iv.add(new ListItem((Bitmap) it.getFirst(), (String) it.getSecond()));
        }
        List itemList = (List) destination$iv$iv;
        createImageList(itemList);
        invalidateOptionsMenu();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onResume$lambda$0(ImageListActivity this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent(this$0, (Class<?>) ImageImportActivity.class);
        this$0.startActivity(intent);
    }

    @Override // com.netlight.sec.finstergram.ui.FinstergramBaseActivity
    public void setBackgroundColor(int color) {
        ((CoordinatorLayout) findViewById(R.id.coordinatorLayout)).setBackgroundColor(color);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createImageList(final List<ListItem> itemList) {
        getListView().setAdapter((android.widget.ListAdapter) new ListAdapter(this, itemList, new Function1<ListItem, Unit>() { // from class: com.netlight.sec.finstergram.ui.list.ImageListActivity$createImageList$1
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ListItem listItem) {
                invoke2(listItem);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ListItem itemToRemove) {
                ImageListViewModel imageListViewModel;
                Intrinsics.checkNotNullParameter(itemToRemove, "itemToRemove");
                imageListViewModel = ImageListActivity.this.viewModel;
                if (imageListViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    imageListViewModel = null;
                }
                imageListViewModel.deleteImage(itemToRemove.getFileName());
                ImageListActivity imageListActivity = ImageListActivity.this;
                Iterable $this$filter$iv = itemList;
                Collection destination$iv$iv = new ArrayList();
                for (Object element$iv$iv : $this$filter$iv) {
                    ListItem it = (ListItem) element$iv$iv;
                    if (it != itemToRemove) {
                        destination$iv$iv.add(element$iv$iv);
                    }
                }
                imageListActivity.createImageList((List) destination$iv$iv);
            }
        }, new Function1<ListItem, Unit>() { // from class: com.netlight.sec.finstergram.ui.list.ImageListActivity$createImageList$2
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ListItem listItem) {
                invoke2(listItem);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ListItem itemToShare) {
                Intrinsics.checkNotNullParameter(itemToShare, "itemToShare");
                Uri imageUri = FileProvider.getUriForFile(ImageListActivity.this, "com.netlight.sec.finstergram.imageprovider", new File(itemToShare.getFileName()));
                Intent shareIntent = new Intent("android.intent.action.SEND");
                shareIntent.setType("image/*");
                shareIntent.putExtra("android.intent.extra.STREAM", imageUri);
                ImageListActivity.this.startActivity(Intent.createChooser(shareIntent, "Share Image"));
            }
        }, new Function1<ListItem, Unit>() { // from class: com.netlight.sec.finstergram.ui.list.ImageListActivity$createImageList$3
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ListItem listItem) {
                invoke2(listItem);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ListItem imageClicked) {
                Intrinsics.checkNotNullParameter(imageClicked, "imageClicked");
                Intent intent = new Intent(ImageListActivity.this, (Class<?>) ImageDetailActivity.class);
                intent.putExtra("imagePath", imageClicked.getFileName());
                ImageListActivity.this.startActivity(intent);
            }
        }));
        getNoImagesMessage().setVisibility(itemList.isEmpty() ^ true ? 8 : 0);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        getMenuInflater().inflate(R.menu.menu_list, menu);
        if (!UserSettings.INSTANCE.getInstance().getRequirePassword()) {
            menu.findItem(R.id.action_logout).setVisible(false);
            return true;
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemId()) {
            case R.id.action_delete_account /* 2131230780 */:
                ImageListViewModel imageListViewModel = this.viewModel;
                if (imageListViewModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    imageListViewModel = null;
                }
                imageListViewModel.deleteAccount();
                finish();
                return true;
            case R.id.action_logout /* 2131230783 */:
                finish();
                return true;
            case R.id.action_settings /* 2131230789 */:
                Intent intent = new Intent(this, (Class<?>) SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
