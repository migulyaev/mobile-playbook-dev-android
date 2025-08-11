package com.netlight.sec.finstergram.ui.list;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import com.netlight.sec.finstergram.data.DatabaseHelper;
import com.netlight.sec.finstergram.data.ImageStore;
import com.netlight.sec.finstergram.data.UserSettings;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ImageListViewModel.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0005\u001a\u00020\u00038BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/netlight/sec/finstergram/ui/list/ImageListViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "appContext", "getAppContext", "()Landroid/app/Application;", "dbHelper", "Lcom/netlight/sec/finstergram/data/DatabaseHelper;", "deleteAccount", "", "deleteImage", "imagePath", "", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes6.dex */
public final class ImageListViewModel extends AndroidViewModel {
    private final DatabaseHelper dbHelper;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ImageListViewModel(Application application) {
        super(application);
        Intrinsics.checkNotNullParameter(application, "application");
        this.dbHelper = new DatabaseHelper(getAppContext());
    }

    private final Application getAppContext() {
        Application application = getApplication();
        Intrinsics.checkNotNullExpressionValue(application, "getApplication<Application>()");
        return application;
    }

    public final void deleteImage(String imagePath) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        ImageStore.INSTANCE.deleteImage(getAppContext(), imagePath);
    }

    public final void deleteAccount() {
        this.dbHelper.deleteUsers();
        UserSettings.INSTANCE.reset(getAppContext());
        ImageStore.INSTANCE.deleteAllImages(getAppContext());
    }
}
