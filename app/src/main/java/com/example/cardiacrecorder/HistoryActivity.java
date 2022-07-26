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

      View  view = inflater.inflate(R.layout.fragment_history_activity, container, false);


        recordList = view.findViewById(R.id.recordList);

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