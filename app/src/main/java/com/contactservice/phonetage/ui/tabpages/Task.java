package com.contactservice.phonetage.ui.tabpages;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.contactservice.phonetage.Adapters.ContactAdapter;
import com.contactservice.phonetage.FirebaseDB.ContactDataCallback;
import com.contactservice.phonetage.FirebaseDB.FirebaseDB;
import com.contactservice.phonetage.Models.Model_Log;
import com.contactservice.phonetage.R;
import com.contactservice.phonetage.Utils.CustomUtils;
import com.contactservice.phonetage.Utils.LocalStorage;
import com.google.protobuf.Any;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Task extends Fragment {

    private TaskViewModel mViewModel;

    RecyclerView recyler_log;
    ContactAdapter contactAdapter;
    FirebaseDB firebaseDB;

    ProgressBar progressBar;
    public static Task newInstance() {
        return new Task();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.task_fragment, container, false);
        recyler_log = view.findViewById(R.id.recyle_logview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyler_log.setLayoutManager(layoutManager);

        progressBar = view.findViewById(R.id.progressbar);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        firebaseDB = new FirebaseDB(getContext());

        mViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        String mynumber = LocalStorage.getString( getContext() , LocalStorage.SharePreKEY.phonenumber,"");
        getDataFromDB();
        // TODO: Use the ViewModel
    }


    private void showlistview(){

    }

    public void getDataFromDB(){

        progressBar.setVisibility(View.VISIBLE);

        firebaseDB.getdataFromDB(new ContactDataCallback() {
            @Override
            public void oncallback(ArrayList list) {
//                mViewModel.array_contact.addAll(list);

                ArrayList<Model_Log> array_logs = new ArrayList<>();

                for (int i = 0 ; i < list.size(); i++)
                {
                    Object object = list.get(i);
                    JSONObject jsonObject = CustomUtils.objectToJSONObject(object);
                    Log.e("object", jsonObject.toString());

                    Model_Log model_log = new Model_Log();
                    try {
                        model_log.setPhonenumber(jsonObject.getString("phonenumber"));
                        model_log.setCallID(jsonObject.getString("callID"));
                        model_log.setCallNumber(jsonObject.getString("callNumber"));
                        model_log.setCallduration(jsonObject.getString("callduration"));
                        model_log.setCallType(jsonObject.getString("callType"));
                        model_log.setCalltime(jsonObject.getString("calltime"));

                        array_logs.add(model_log);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (i == list.size() - 1)
                    {
                        contactAdapter = new ContactAdapter(getContext(), array_logs);
                        recyler_log.setAdapter(contactAdapter);
                    }
                }

                progressBar.setVisibility(View.GONE);
            }
        });
    }

}
