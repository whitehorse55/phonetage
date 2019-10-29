package com.contactservice.phonetage.ui.tabpages;

import androidx.lifecycle.ViewModel;

import com.contactservice.phonetage.FirebaseDB.FirebaseDB;
import com.contactservice.phonetage.Models.Model_Log;
import com.contactservice.phonetage.Utils.LocalStorage;

import java.util.ArrayList;

public class TaskViewModel extends ViewModel {

    // TODO: Implement the ViewModel
    public ArrayList<Model_Log> array_contact = new ArrayList<>();

}
