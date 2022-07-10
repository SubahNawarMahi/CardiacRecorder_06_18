package com.example.cardiacrecorder;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class HistoryActivity extends Fragment {

//    ImageButton editButton;
    ListView recordList;
    SimpleCursorAdapter simpleCursorAdapter;
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View  view = inflater.inflate(R.layout.fragment_history_activity, container, false);

//        editButton = view.findViewById(R.id.editButton);
    //    ListView recordList = view.findViewById(R.id.recordList);

//        Record r1 = new Record("100", "70", "65", "28-JUN-2022", "10:30 AM", "Sick..", 1);
//        Record r2 = r1;
//        Record r3 = r1;
//        Record r4 = r1;
//        Record r5 = r1;
//        Record r6 = r1;
//        Record r7 = r1;
//        Record r8 = r1;
//        Record r9 = r1;
//        Record r10 = r1;
//
//        ArrayList<Record> recordArrayList = new ArrayList<>();
//
//        recordArrayList.add(r1);
//        recordArrayList.add(r2);
//        recordArrayList.add(r3);
//        recordArrayList.add(r4);
//        recordArrayList.add(r5);
//        recordArrayList.add(r6);
//        recordArrayList.add(r7);
//        recordArrayList.add(r8);
//        recordArrayList.add(r9);
//        recordArrayList.add(r10);
//
        recordList = view.findViewById(R.id.recordList);
//        // sample data
//        List<String> list = new ArrayList<>();
//        for(int i=0;i<100;i++)
//            list.add("Item "+i);
//
//        CustomAdapter listAdapter = new CustomAdapter(list,getContext());
        //       recordList.setAdapter(listAdapter);
        myDatabaseHelper = new MyDatabaseHelper(getActivity());
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        loadData();

        recordList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String i = cursor.getString(0);


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                View view1 = getLayoutInflater().inflate(R.layout.pop_up_layout,null);

                TextView textView = view1.findViewById(R.id.textView10);
                Button updateBtn = view1.findViewById(R.id.update);
                Button deleteBtn = view1.findViewById(R.id.delete);

                builder.setView(view1);

                AlertDialog alertDialog = builder.create();

                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(),CreateRecord.class);
                        intent.putExtra("key",i);
                        startActivity(intent);
                        alertDialog.dismiss();
                        loadData();
                    }
                });
                deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        long x = myDatabaseHelper.deleteList(i);

                        if(x>0)
                        {
                            Toast.makeText(getContext(),"Data is deleted",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(),"Data is deleted",Toast.LENGTH_SHORT).show();
                        }
                        alertDialog.dismiss();
                        loadData();
                    }
                });

                alertDialog.show();

                return true;
            }
        });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    public void loadData()
    {
        simpleCursorAdapter = myDatabaseHelper.loadListViewFromDB();
        recordList.setAdapter(simpleCursorAdapter);

    }


}