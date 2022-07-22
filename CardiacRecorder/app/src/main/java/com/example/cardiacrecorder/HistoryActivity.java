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

/**
 * this HistoryActivity is a java class which extends
 * fragment and this class will fetch data from sqlite
 * database and using these data a single record is
 * created then these records will be stored as
 * ListArray as RecordList. Created RecordList will be
 * showed to user.
 */
public class HistoryActivity extends Fragment {

    //    ImageButton editButton;
    ListView recordList;
    SimpleCursorAdapter simpleCursorAdapter;
    MyDatabaseHelper myDatabaseHelper;
    SQLiteDatabase sqLiteDatabase;

    /**
     * this method will execute when HistoryActivity
     * fragment will be created and this shows RecordList
     * to user.
     * @param inflater
     * this is a LayoutInflater type parameter
     * @param container
     * this is a ViewGroup type parameter
     * @param savedInstanceState
     * this is a Bundle type parameter
     * @return
     * returns a View which will be presented to user
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.fragment_history_activity, container, false);


        recordList = view.findViewById(R.id.recordList);

        myDatabaseHelper = new MyDatabaseHelper(getActivity());
        sqLiteDatabase = myDatabaseHelper.getWritableDatabase();

        loadData();

        recordList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            /**
             * on long click on a record this method will
             * be executed and show two option delete,
             * update on that data
             * @param parent
             * this is a AdapterView parameter
             * @param view
             * this is a view type parameter to show to user
             * @param position
             * this is a int type parameter and it is the position
             * of list which is long pressed
             * @param id
             * this is a long type parameter which means the id of
             * that record on database
             * @return
             * returns a boolean value (true for successful execution)
             */
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
                    /**
                     * this is a lamda method which will execute if
                     * user press the update button and that particular
                     * data will be updated
                     * @param v
                     * takes a View type parameter to show another activity
                     * using intent
                     */
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
                    /**
                     * this is a lamda method which will execute if
                     * user press the delete button and that particular
                     * data will be deleted
                     * @param v
                     * takes a View type parameter to show another activity
                     * and delete that particular record based on id and show
                     * the RecordList and also delete from sqlite database
                     */
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

    /**
     * this method will execute loadData method
     */
    @Override
    public void onStart() {
        super.onStart();
        loadData();
    }

    /**
     * this method will loadData to a simpleCursorAdapter
     * then set that adapter to RecordList
     */
    public void loadData()
    {
        simpleCursorAdapter = myDatabaseHelper.loadListViewFromDB();
        recordList.setAdapter(simpleCursorAdapter);

    }


}