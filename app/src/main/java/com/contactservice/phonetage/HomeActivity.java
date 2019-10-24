package com.contactservice.phonetage;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.contactservice.phonetage.ui.Blanklist;
import com.contactservice.phonetage.ui.Helpusing;
import com.contactservice.phonetage.ui.Purchase;
import com.contactservice.phonetage.ui.Reporterror;
import com.contactservice.phonetage.ui.Settings;
import com.contactservice.phonetage.ui.Share;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;


import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;

import android.view.Menu;

public class HomeActivity extends ParentActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    DrawerLayout drawer;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        hidestatusbar();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.bringToFront();
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.fr_mainview, R.id.fr_blank, R.id.fr_help, R.id.fr_share,
                R.id.fr_settings, R.id.fr_report, R.id.fr_purchase)
                .setDrawerLayout(drawer)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);

        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setCheckable(true);
        drawer.closeDrawers();
        int id = menuItem.getItemId();
        
        switch (id){

            case R.id.nav_mainview:
                navController.navigate(R.id.fr_mainview);
                break;


            case R.id.nav_blank:
                navController.navigate(R.id.fr_blank);
                break;

            case R.id.nav_help:
                navController.navigate(R.id.fr_help);
                break;

            case R.id.nav_share:
                navController.navigate(R.id.fr_share);
                break;

            case R.id.nav_settings:
                navController.navigate(R.id.fr_settings);
                break;

            case R.id.nav_report:
                navController.navigate(R.id.fr_report);
                break;

            case R.id.nav_purchase:
                navController.navigate(R.id.fr_purchase);
                break;
        }
        return true;
    }
}
