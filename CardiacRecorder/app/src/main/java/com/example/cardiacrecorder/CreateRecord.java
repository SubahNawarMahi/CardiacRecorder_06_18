package com.example.cardiacrecorder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateRecord extends AppCompatActivity {


    MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_record);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_create_record);

        EditText date, time, sys, dias, pul, comm;
        Button save;

        date = findViewById(R.id.date);
        time = findViewById(R.id.time);
        sys = findViewById(R.id.systolic);
        dias = findViewById(R.id.diastolic);
        pul = findViewById(R.id.pulse);
        comm = findViewById(R.id.notes);
        save = findViewById(R.id.saveButton);

        myDatabaseHelper = new MyDatabaseHelper(CreateRecord.this);
        SQLiteDatabase sqLiteDatabase = myDatabaseHelper.getWritableDatabase();


        Calendar calendar = Calendar.getInstance();

        Date currentDate = calendar.getTime();
        String date_value = DateFormat.getDateInstance(DateFormat.FULL).format(currentDate);
        date.setText(date_value);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String time_value = simpleDateFormat.format(calendar.getTime());
        time.setText(time_value);

        String s = getIntent().getStringExtra("key");

        if (s.equals("-1")) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    String systol = sys.getText().toString();
                    String diastol = dias.getText().toString();
                    String pulse = pul.getText().toString();
                    String comments = comm.getText().toString();

                    String blood_pressure_status = "";
                    String pulse_status = "";

                    sys.setText("");
                    dias.setText("");
                    pul.setText("");
                    comm.setText("");


                    if (TextUtils.isEmpty(systol)) {
                        sys.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(diastol)) {
                        dias.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(comments)) {
                        comm.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(pulse)) {
                        pul.setError("Required");
                        return;
                    }

                    int x = Integer.parseInt(systol);
                    int y = Integer.parseInt(diastol);

                    if (x > 180 || y > 120) {
                        blood_pressure_status += "Hypertensive Crisis";
                    } else if (x > 140 || y > 90) {
                        blood_pressure_status += "Hypertension_1";
                    } else if (x >= 130 && x <= 139 || y >= 80 && y <= 89) {
                        blood_pressure_status += "Hypertension_2";
                    } else if ((x >= 120 && x <= 129) && (y >= 60 && y <= 80)) {
                        blood_pressure_status += "Elevated";
                    } else if ((x >= 90 && x <= 120 || y >= 60 && y <= 80)) {
                        blood_pressure_status += "Normal";

                    } else if (x < 90 && y < 60) {
                        blood_pressure_status += "Hypotension";
                    }


                    if (Integer.parseInt(pulse) >= 60 && Integer.parseInt(pulse) <= 80) {
                        pulse_status += "normal";
                    } else {
                        pulse_status += "exceptional";
                    }

                    long id = myDatabaseHelper.insertData(systol, diastol, blood_pressure_status, pulse, pulse_status, date_value, time_value, comments);

                    if (id == -1) {
                        Toast.makeText(CreateRecord.this, "Data is not saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CreateRecord.this, "Data is  saved", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateRecord.this, MainActivity.class));
                        finish();
                    }
                }


            });


        } else if (Integer.parseInt(s) != -1) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String id = s;
                    String systol = sys.getText().toString();
                    String diastol = dias.getText().toString();
                    String pulse = pul.getText().toString();
                    String comments = comm.getText().toString();

                    String blood_pressure_status = "";
                    String pulse_status = "";

                    sys.setText("");
                    dias.setText("");
                    pul.setText("");
                    comm.setText("");


                    if (TextUtils.isEmpty(systol)) {
                        sys.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(diastol)) {
                        dias.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(comments)) {
                        comm.setError("Required");
                        return;
                    } else if (TextUtils.isEmpty(pulse)) {
                        pul.setError("Required");
                        return;
                    }

                    int x = Integer.parseInt(systol);
                    int y = Integer.parseInt(diastol);

                    if (x > 180 || y > 120) {
                        blood_pressure_status += "Hypertensive Crisis";
                    } else if (x > 140 || y > 90) {
                        blood_pressure_status += "Hypertension_1";
                    } else if (x >= 130 && x <= 139 || y >= 80 && y <= 89) {
                        blood_pressure_status += "Hypertension_2";
                    } else if ((x >= 120 && x <= 129) && (y >= 60 && y <= 80)) {
                        blood_pressure_status += "Elevated";
                    } else if ((x >= 90 && x <= 120 || y >= 60 && y <= 80)) {
                        blood_pressure_status += "Normal";

                    } else if (x < 90 && y < 60) {
                        blood_pressure_status += "Hypotension";
                    }


                    if (Integer.parseInt(pulse) >= 60 && Integer.parseInt(pulse) <= 80) {
                        pulse_status += "normal";
                    } else {
                        pulse_status += "exceptional";
                    }

                    boolean i = myDatabaseHelper.updateData(id, systol, diastol, blood_pressure_status, pulse, pulse_status, date_value, time_value, comments);

                    if (i) {
                        Toast.makeText(CreateRecord.this, "data is updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CreateRecord.this, MainActivity.class));
                        finish();

                    } else {
                        Toast.makeText(CreateRecord.this, "data is not updated", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });


        }

    }
}


