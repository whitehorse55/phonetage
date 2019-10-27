package com.contactservice.phonetage.FirebaseDB;


import android.content.Context;
import android.util.Log;

import com.contactservice.phonetage.Models.Model_Log;
import com.contactservice.phonetage.MyApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

interface ILogRepo{
    void saveDataDB(Model_Log model_log);
    void getdataFromDB();
    void phoneverification();
    void saveDataArray(ArrayList<Model_Log> array_log);
//    void doesUserEmailExist(String email, FirestoreModel firestoreUserModel);
//
//    void addNewRegisteredUser(FirestoreUserModel firestoreUserModel);
//
//    void getLoginUserByEmail(String email);

}

public class FirebaseDB implements ILogRepo {

    private static final String TAG = FirebaseDB.class.getSimpleName();
    private Context mContext;
    private FirebaseFirestore db;
    private MyApp myApp;

    public FirebaseDB(Context context) {
        this.mContext = context;
        myApp = MyApp.getInstance();
        db = myApp.getDbInstance();
    }


    @Override
    public void saveDataDB(Model_Log model_log) {

    }

    @Override
    public void getdataFromDB() {

    }

    @Override
    public void phoneverification() {

    }

    @Override
    public void saveDataArray(ArrayList<Model_Log> array_log) {
        Log.e("logarray==>", array_log.toString());
    }


}
