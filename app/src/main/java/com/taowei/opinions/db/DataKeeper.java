package com.taowei.opinions.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.taowei.opinions.data.dbdata.SQLInfo;

import java.util.ArrayList;

public class DataKeeper {

    private SQLiteHelper sqLiteHelper;

    private SQLiteDatabase database;

    private int doSaveTimes = 0;

    public DataKeeper(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public void saveDownLoadInfo(SQLInfo sqlInfo) {

        ContentValues cv = new ContentValues();
        cv.put("userID", sqlInfo.getUserID());
        cv.put("taskID", sqlInfo.getTaskID());
        cv.put("downLoadSize", sqlInfo.getDownloadSize());
        cv.put("fileName", sqlInfo.getFileName());
        cv.put("filePath", sqlInfo.getFilePath());
        cv.put("fileSize", sqlInfo.getFileSize());
        cv.put("url", sqlInfo.getUrl());
        database = sqLiteHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            cursor = database.rawQuery(
                    "SELECT * from " + SQLiteHelper.TABLE_NAME
                            + " WHERE userID = ? AND taskID = ? ", new String[]{sqlInfo.getUserID(), sqlInfo.getTaskID()});
            if (cursor.moveToNext()) {
                database.update(SQLiteHelper.TABLE_NAME, cv, "userID = ? AND taskID = ? ", new String[]{sqlInfo.getUserID(), sqlInfo.getTaskID()});
            } else {
                database.insert(SQLiteHelper.TABLE_NAME, null, cv);
            }
            cursor.close();
            database.close();
        } catch (Exception e) {
            doSaveTimes++;
            if (doSaveTimes < 5) { //最多只做5次数据保存，降低数据保存失败率
                saveDownLoadInfo(sqlInfo);
            } else {
                doSaveTimes = 0;
            }
            if (cursor != null) {
                cursor.close();
            }
            if (database != null) {
                database.close();
            }
        }
        doSaveTimes = 0;
    }

    public ArrayList<SQLInfo> getAllDownLoadInfo(){
        ArrayList<SQLInfo> downloadinfoList = new ArrayList<>();
        database = sqLiteHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery(
                "SELECT * from " + SQLiteHelper.TABLE_NAME, null);
        while(cursor.moveToNext()){
            SQLInfo downloadinfo = new SQLInfo();
            downloadinfo.setDownloadSize(cursor.getLong(cursor.getColumnIndex("downLoadSize")));
            downloadinfo.setFileName(cursor.getString(cursor.getColumnIndex("fileName")));
            downloadinfo.setFilePath(cursor.getString(cursor.getColumnIndex("filePath")));
            downloadinfo.setFileSize(cursor.getLong(cursor.getColumnIndex("fileSize")));
            downloadinfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
            downloadinfo.setTaskID(cursor.getString(cursor.getColumnIndex("taskID")));
            downloadinfo.setUserID(cursor.getString(cursor.getColumnIndex("userID")));
            downloadinfoList.add(downloadinfo);
        }
        cursor.close();
        database.close();
        return downloadinfoList;
    }

    public ArrayList<SQLInfo> getUserDownLoadInfo(String userID){
        ArrayList<SQLInfo> downloadinfoList = new ArrayList<>();
        database = sqLiteHelper.getWritableDatabase();
        try {
            Cursor cursor = null;
            cursor = database.rawQuery(
                    "SELECT * from " + SQLiteHelper.TABLE_NAME + " WHERE userID = '" + userID +"'", null);
            while(cursor.moveToNext()){
                SQLInfo downloadinfo = new SQLInfo();
                downloadinfo.setDownloadSize(cursor.getLong(cursor.getColumnIndex("downLoadSize")));
                downloadinfo.setFileName(cursor.getString(cursor.getColumnIndex("fileName")));
                downloadinfo.setFilePath(cursor.getString(cursor.getColumnIndex("filePath")));
                downloadinfo.setFileSize(cursor.getLong(cursor.getColumnIndex("fileSize")));
                downloadinfo.setUrl(cursor.getString(cursor.getColumnIndex("url")));
                downloadinfo.setTaskID(cursor.getString(cursor.getColumnIndex("taskID")));
                downloadinfo.setUserID(cursor.getString(cursor.getColumnIndex("userID")));
                downloadinfoList.add(downloadinfo);
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        database.close();
        return downloadinfoList;
    }
    public void deleteDownLoadInfo(String userID, String taskID){
        database = sqLiteHelper.getWritableDatabase();
        database.delete(SQLiteHelper.TABLE_NAME, "userID = ? AND taskID = ? ", new String[]{userID,taskID});
        database.close();
    }
}
