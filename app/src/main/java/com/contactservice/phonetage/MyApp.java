package com.contactservice.phonetage;

import android.Manifest;
import android.app.ActionBar;
import android.app.Application;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.CallLog;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.contactservice.phonetage.Utils.MarshPermssions;
import com.google.firebase.firestore.FirebaseFirestore;

public class MyApp extends Application {

    private static final String TAG = MyApp.class.getSimpleName();
    public static MyApp instance = null;
    private FirebaseFirestore db;

    public static MyApp getInstance() {
        if (null == instance) {
            instance = new MyApp();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        db = FirebaseFirestore.getInstance();
    }

    public FirebaseFirestore getDbInstance(){
        return db;
    }

}
