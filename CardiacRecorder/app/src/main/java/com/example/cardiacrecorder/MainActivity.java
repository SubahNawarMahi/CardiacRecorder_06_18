package com.example.cardiacrecorder;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

//    private Button measureButton, historyButton, addButton;
//    private ImageButton editButton, deleteButton;
//    private View measureUnderline, historyUnderline;
    TabLayout tabLayout;
    ViewPager viewPager;

   // @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

//        addButton = findViewById(R.id.addButton);
//        measureButton = findViewById(R.id.measureButton);
//        historyButton = findViewById(R.id.historyButton);
//
//        measureUnderline = findViewById(R.id.measureUnderline);
//        historyUnderline = findViewById(R.id.historyUnderline);
//
//        replaceFragment(new MeasureActivity());
//        measureUnderline.setVisibility(View.VISIBLE);
//        historyUnderline.setVisibility(View.INVISIBLE);
//
//        measureButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                measureUnderline.setVisibility(View.VISIBLE);
//                historyUnderline.setVisibility(View.INVISIBLE);
//                addButton.setVisibility(View.VISIBLE);
//
//                replaceFragment(new MeasureActivity());
//
//            }
//        });
//
//        historyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                measureUnderline.setVisibility(View.INVISIBLE);
//                historyUnderline.setVisibility(View.VISIBLE);
//                addButton.setVisibility(View.INVISIBLE);
//
//                replaceFragment(new HistoryActivity());
//
//            }
//        });
//
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, CreateRecord.class);
//                intent.putExtra("fromWhichActivity", "mainactivity");
//                startActivity(intent);
//            }
//        });
//
//    }
//
//    private void replaceFragment(Fragment historyActivity) {
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.frameLayout, historyActivity);
//        fragmentTransaction.commit();
//    }
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPagerId);

        tabLayout.setupWithViewPager(viewPager);

        VpAdapter vpAdapter = new VpAdapter(getSupportFragmentManager());
        vpAdapter.addFragment(new MeasureActivity(), "Measure");
        vpAdapter.addFragment(new HistoryActivity(), "History");

        viewPager.setAdapter(vpAdapter);
    }
}