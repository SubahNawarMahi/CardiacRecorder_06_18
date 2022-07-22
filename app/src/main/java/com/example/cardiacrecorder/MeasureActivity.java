package com.example.cardiacrecorder;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * this is a fragment used on MainActivity
 * this fragment will drive user to create new
 * record
 */
public class MeasureActivity extends Fragment {

    View view;

    /**
     * this method will execute when MeasureActivity
     * fragment will be created and this shows a button
     * to the user to add new record in database
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
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_measure_activity, container, false);

        FloatingActionButton addButton = view.findViewById(R.id.floatingActionButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            /**
             * this method change the intent and add new record to database
             * @param v
             * takes a View type parameter to show CreateRecord activity
             */
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CreateRecord.class);
                intent.putExtra("key","-1");
                startActivity(intent);
            }
        });


        return view;
    }
}