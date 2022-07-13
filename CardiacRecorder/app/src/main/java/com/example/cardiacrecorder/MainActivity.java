package com.example.cardiacrecorder;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;

   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();



        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPagerId);

        tabLayout.setupWithViewPager(viewPager);

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new MeasureActivity(), "Measure");
        vpAdapter.addFragment(new HistoryActivity(), "History");

        viewPager.setAdapter(vpAdapter);
    }
}