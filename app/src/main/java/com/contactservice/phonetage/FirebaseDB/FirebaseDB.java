package com.contactservice.phonetage.FirebaseDB;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.contactservice.phonetage.Models.Model_Log;
import com.contactservice.phonetage.MyApp;
import com.contactservice.phonetage.Utils.LocalStorage;
import com.contactservice.phonetage.Utils.MarshPermssions;
//import com.google.api.core.ApiFuture;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firestore.v1.WriteResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.contactservice.phonetage.Utils.MarshPermssions.READ_PHONE_STATUS;

interface ILogRepo{
    void getdataFromDB(ContactDataCallback callback);
    void phoneverification();
    void saveDataArray(ArrayList<Model_Log> array_log);
}

public class FirebaseDB implements ILogRepo{

    private static final String TAG = FirebaseDB.class.getSimpleName();
    private Context mContext;
    public FirebaseFirestore db;
    private MarshPermssions marshPermssions;

    public FirebaseDB(Context context) {
        this.mContext = context;
        marshPermssions = new MarshPermssions((Activity)context);
        db = FirebaseFirestore.getInstance();
    }


    @Override
    public void getdataFromDB(final ContactDataCallback callback) {

        String mynumber = LocalStorage.getString(mContext, LocalStorage.SharePreKEY.phonenumber,"");

//        final ArrayList array_logs = new ArrayList();
        db.collection("loglist").document(mynumber).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful())
                {
                    DocumentSnapshot  documentSnapshot = task.getResult();
                    ArrayList list = (ArrayList) documentSnapshot.get("info");
                    callback.oncallback(list);
                }
            }
        });

    }



    @Override
    public void phoneverification() {

    }

    @Override
    public void saveDataArray(ArrayList<Model_Log> array_log) {
        String mynumber = LocalStorage.getString(mContext, LocalStorage.SharePreKEY.phonenumber,"");
        Map<String, Object> docData = new HashMap<>();
        Collections.addAll(array_log);
        docData.put("info", array_log);
        db.collection("loglist").document(mynumber).set(docData);
    }

}
