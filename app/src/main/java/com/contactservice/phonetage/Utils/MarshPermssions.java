package com.contactservice.phonetage.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MarshPermssions {

    public static final int CAMERA_PERMISSION_REQUEST_CODE = 3;
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE_BY_GALLERY = 0;
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE_BY_CAMERA = 1;
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST_CODE_BY_LOAD_PROFILE = 2;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 4;
    public static final int SMS_RECEIVING_CODE = 5;
    public static final int SMS_READ_CODE = 6;

    public static final int READCONTACT = 700;
    public static final int READ_CALL_LOGS = 8;
    public static final int READ_PHONE_STATUS = 9;

    Activity activity;
    Context mContext;

    public MarshPermssions(Activity activity) {
        this.activity = activity;
        this.mContext = activity;
    }

    //call logs permission
    public boolean checkphonecalllogs(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CALL_LOG);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public void requestcalllogs(int requestCode){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CALL_LOG)){
            Toast.makeText(mContext.getApplicationContext(), "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_CALL_LOG},requestCode);
        }
    }

    //call phone status

    public boolean checkreadphonestatus(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public void requestreadphonestatus(int requestCode){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE)){
            Toast.makeText(mContext.getApplicationContext(), "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_PHONE_STATE},requestCode);
        }
    }


    public boolean checkPermissionForExternalStorage(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkPermissionForCamera(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }


    public boolean checkPermissionContact(){
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_CONTACTS);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkSMSReceving()
    {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.RECEIVE_SMS);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkReadSMS()
    {
        int result = ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_SMS);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public boolean checkLocationPermission(){

        int result = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            return false;
        }
    }

    public void requestPermissionForExternalStorage(int requestCode){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(mContext.getApplicationContext(), "External Storage permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},requestCode);
        }
    }

    public void requestPermissionForCamera(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)){
            Toast.makeText(mContext.getApplicationContext(), "Camera permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.CAMERA},CAMERA_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForContact(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_CONTACTS)){
            Toast.makeText(mContext.getApplicationContext(), "Contact permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_CONTACTS},READCONTACT);
        }
    }

    public void requestPermissionForLocation(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION) && ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_COARSE_LOCATION)){
            Toast.makeText(mContext.getApplicationContext(), "Location permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    public void requestPermissionForSMSReceiver()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.RECEIVE_SMS)){
            Toast.makeText(mContext.getApplicationContext(), "RECEIVE SMS permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.RECEIVE_SMS},SMS_RECEIVING_CODE);
        }
    }

    public void requestPermissionForSMSRead()
    {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_SMS)){
            Toast.makeText(mContext.getApplicationContext(), "RECEIVE SMS permission needed. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity,new String[]{Manifest.permission.READ_SMS},SMS_RECEIVING_CODE);
        }
    }
}
