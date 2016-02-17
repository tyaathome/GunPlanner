package com.example.trungie.gunplanner;

import android.app.Application;
import android.content.Context;

import com.example.trungie.gunplanner.object.BackLog;
import com.example.trungie.gunplanner.object.DataBase;
import com.example.trungie.gunplanner.object.DiskLruUtils;
import com.jakewharton.disklrucache.DiskLruCache;

import java.io.File;

public class MyApplication extends Application{

    private static DiskLruCache mDiskLruCache;
    private static Context mApplicationContext;
    private static DataBase mDB;
    private static final String DB_FLAG = "database";

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
        initDiskLruCache();
    }

    private void initDiskLruCache(){
        try {
            File cacheDir= DiskLruUtils.getDiskLruCacheDir(this, "data");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
            int versionCode=DiskLruUtils.getAppVersionCode(this);
            mDiskLruCache= DiskLruCache.open(cacheDir, versionCode, 1, DiskLruUtils.IO_BUFFER_SIZE);

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static DiskLruCache getDiskLruCache() {
        return mDiskLruCache;
    }

    public static Context getContext() {
        return mApplicationContext;
    }

    public static DataBase getDB() {
        mDB = (DataBase) DiskLruUtils.getObject(DB_FLAG);
        if(mDB == null) {
            mDB = new DataBase();
        }
        return mDB;
    }

    public static void saveDB(DataBase db) {
        mDB = db;
        DiskLruUtils.saveObject(DB_FLAG, mDB);
    }
}
