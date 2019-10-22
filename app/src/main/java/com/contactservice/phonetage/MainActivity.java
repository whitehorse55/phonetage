package com.contactservice.phonetage;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.hidestatusbar();
    }
}
