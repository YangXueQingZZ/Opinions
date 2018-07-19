package com.taowei.opinions.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String mDatabasename = "filedownloader";

    private static SQLiteDatabase.CursorFactory mFactory = null;
    private static final int mVersion = 1;
    public static final String TABLE_NAME = "downloadinfo"; //文件下载信息数据表名称

    public SQLiteHelper(Context context) {
        super(context, mDatabasename, mFactory, mVersion);
    }

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //创建文件下载信息数据表
        String downloadsql = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME +" ("
                + "id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , "
                + "userID VARCHAR, "
                + "taskID VARCHAR, "
                + "url VARCHAR, "
                + "filePath VARCHAR, "
                + "fileName VARCHAR, "
                + "fileSize VARCHAR, "
                + "downLoadSize VARCHAR "
                + ")";
        sqLiteDatabase.execSQL(downloadsql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
