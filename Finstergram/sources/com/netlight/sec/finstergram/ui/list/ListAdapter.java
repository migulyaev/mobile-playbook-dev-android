package com.netlight.sec.finstergram.ui.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.netlight.sec.finstergram.R;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ListAdapter.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J$\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u000e2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/netlight/sec/finstergram/ui/list/ListAdapter;", "Landroid/widget/BaseAdapter;", "context", "Landroid/content/Context;", "itemList", "", "Lcom/netlight/sec/finstergram/ui/list/ListItem;", "onDeleteItem", "Lkotlin/Function1;", "", "onShareItem", "onClickItem", "(Landroid/content/Context;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getCount", "", "getItem", "", "position", "getItemId", "", "getView", "Landroid/view/View;", "convertView", "parent", "Landroid/view/ViewGroup;", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes6.dex */
public final class ListAdapter extends BaseAdapter {
    private final Context context;
    private final List<ListItem> itemList;
    private final Function1<ListItem, Unit> onClickItem;
    private final Function1<ListItem, Unit> onDeleteItem;
    private final Function1<ListItem, Unit> onShareItem;

    /* JADX WARN: Multi-variable type inference failed */
    public ListAdapter(Context context, List<ListItem> itemList, Function1<? super ListItem, Unit> onDeleteItem, Function1<? super ListItem, Unit> onShareItem, Function1<? super ListItem, Unit> onClickItem) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(itemList, "itemList");
        Intrinsics.checkNotNullParameter(onDeleteItem, "onDeleteItem");
        Intrinsics.checkNotNullParameter(onShareItem, "onShareItem");
        Intrinsics.checkNotNullParameter(onClickItem, "onClickItem");
        this.context = context;
        this.itemList = itemList;
        this.onDeleteItem = onDeleteItem;
        this.onShareItem = onShareItem;
        this.onClickItem = onClickItem;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.itemList.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int position) {
        return this.itemList.get(position);
    }

    @Override // android.widget.Adapter
    public long getItemId(int position) {
        return position;
    }

    @Override // android.widget.Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View resultingView;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            resultingView = inflater.inflate(R.layout.list_item, parent, false);
        } else {
            resultingView = convertView;
        }
        final ListItem item = this.itemList.get(position);
        View findViewById = resultingView.findViewById(R.id.list_item_image);
        Intrinsics.checkNotNullExpressionValue(findViewById, "resultingView.findViewById(R.id.list_item_image)");
        ImageView itemImage = (ImageView) findViewById;
        itemImage.setImageBitmap(item.getImage());
        View findViewById2 = resultingView.findViewById(R.id.list_item_delete_btn);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "resultingView.findViewBy….id.list_item_delete_btn)");
        ImageButton deleteButton = (ImageButton) findViewById2;
        deleteButton.setOnClickListener(new View.OnClickListener() { // from class: com.netlight.sec.finstergram.ui.list.ListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ListAdapter.getView$lambda$0(ListAdapter.this, item, view);
            }
        });
        View findViewById3 = resultingView.findViewById(R.id.list_item_share_btn);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "resultingView.findViewBy…R.id.list_item_share_btn)");
        ImageButton shareButton = (ImageButton) findViewById3;
        shareButton.setOnClickListener(new View.OnClickListener() { // from class: com.netlight.sec.finstergram.ui.list.ListAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ListAdapter.getView$lambda$1(ListAdapter.this, item, view);
            }
        });
        View findViewById4 = resultingView.findViewById(R.id.list_item_root);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "resultingView.findViewById(R.id.list_item_root)");
        LinearLayout itemRoot = (LinearLayout) findViewById4;
        itemRoot.setOnClickListener(new View.OnClickListener() { // from class: com.netlight.sec.finstergram.ui.list.ListAdapter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ListAdapter.getView$lambda$2(ListAdapter.this, item, view);
            }
        });
        Intrinsics.checkNotNullExpressionValue(resultingView, "resultingView");
        return resultingView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getView$lambda$0(ListAdapter this$0, ListItem item, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.onDeleteItem.invoke(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getView$lambda$1(ListAdapter this$0, ListItem item, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.onShareItem.invoke(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void getView$lambda$2(ListAdapter this$0, ListItem item, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0.onClickItem.invoke(item);
    }
}
