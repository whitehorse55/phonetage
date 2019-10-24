package com.contactservice.phonetage.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contactservice.phonetage.R;
import com.contactservice.phonetage.ui.tabpages.TabspageAdapter;
import com.google.android.material.tabs.TabLayout;

public class Mainview extends Fragment {

    private MainviewViewModel mViewModel;
    TabspageAdapter tabspageAdapter;

    public static Mainview newInstance() {
        return new Mainview();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.mainview_fragment, container, false);

        ViewPager viewPager =  view.findViewById(R.id.pager_mainview);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(tabspageAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabslayout);
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }

    @Override
    public void onAttach(Context context) {
        tabspageAdapter = new TabspageAdapter(getContext(),getChildFragmentManager());
        super.onAttach(context);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainviewViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
