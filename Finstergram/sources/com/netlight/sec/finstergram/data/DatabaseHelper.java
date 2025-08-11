package com.netlight.sec.finstergram.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DatabaseHelper.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u0004\u0018\u00010\bJ\u0006\u0010\r\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J \u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J\u0016\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b¨\u0006\u0017"}, d2 = {"Lcom/netlight/sec/finstergram/data/DatabaseHelper;", "Landroid/database/sqlite/SQLiteOpenHelper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "authenticateUser", "", "name", "", "password", "deleteUsers", "", "getUsernameIfRegistered", "isUserRegistered", "onCreate", "db", "Landroid/database/sqlite/SQLiteDatabase;", "onUpgrade", "oldVersion", "", "newVersion", "registerUser", "Companion", "app_debug"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes7.dex */
public final class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "user_credentials.db";
    private static final int DATABASE_VERSION = 1;
    private static final long INSERT_FAILED_ERROR_CODE = -1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        db.execSQL("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, password TEXT)");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
    }

    public final void deleteUsers() {
        getWritableDatabase().execSQL("DELETE FROM users");
    }

    public final boolean registerUser(String name, String password) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(password, "password");
        ContentValues values = new ContentValues();
        values.put("username", name);
        values.put("password", password);
        long newRowId = getWritableDatabase().insert("users", null, values);
        return newRowId != -1;
    }

    public final boolean authenticateUser(String name, String password) {
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(password, "password");
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM users WHERE username='" + name + "' AND password='" + password + '\'', new String[0]);
        Intrinsics.checkNotNullExpressionValue(cursor, "readableDatabase.rawQuer…      arrayOf()\n        )");
        boolean userExists = cursor.getCount() > 0;
        cursor.close();
        return userExists;
    }

    public final boolean isUserRegistered() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM users", null);
        boolean isEmpty = cursor.getCount() == 0;
        cursor.close();
        return !isEmpty;
    }

    public final String getUsernameIfRegistered() {
        Cursor cursor = getReadableDatabase().rawQuery("SELECT username FROM users", null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor.getString(0);
        }
        return null;
    }
}
