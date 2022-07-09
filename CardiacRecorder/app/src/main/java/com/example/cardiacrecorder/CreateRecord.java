package com.example.cardiacrecorder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CreateRecord extends AppCompatActivity {

    //    private Button selectDate, selectTime, saveButton;
//    private String entryDate, entryTime, entryNotes, fromActivity;
//    private int entrySystolic, entryDiastolic, entryPulse, levelCalculate;
//    private EditText systolic, diastolic, pulse, notes;
//    private ImageView tickMark, requiredSystolic, requiredDiastolic, requiredPulse, backButton;
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
//        Intent intent = getIntent();
//        fromActivity = intent.getStringExtra("fromWhichActivity");
//
//        getSupportActionBar().hide();
//
//
//        selectDate = findViewById(R.id.date);
//        selectTime = findViewById(R.id.time);
//        saveButton = findViewById(R.id.saveButton);
//        tickMark = findViewById(R.id.tickButton);
//        systolic = findViewById(R.id.systolic);
//        diastolic = findViewById(R.id.diastolic);
//        pulse = findViewById(R.id.pulse);
//        notes = findViewById(R.id.notes);
//        requiredSystolic = findViewById(R.id.requiredSystolic);
//        requiredDiastolic = findViewById(R.id.requiredDiastolic);
//        requiredPulse = findViewById(R.id.requiredPulse);
//        backButton = findViewById(R.id.backButton);
//
//        final Calendar calendar = Calendar.getInstance();
//        final int year = calendar.get(Calendar.YEAR);
//        final int month = calendar.get(Calendar.MONTH);
//        final int day = calendar.get(Calendar.DAY_OF_MONTH);
//        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
//        final int min = calendar.get(Calendar.MINUTE);
//
//
//        Date date = new Date();
//        SimpleDateFormat formatTime = new SimpleDateFormat("hh.mm aa");
//        String time = formatTime.format(date);
//        selectTime.setText(time);
//        selectTime.setTextColor(Color.parseColor("#000000"));
//
//        Date c = Calendar.getInstance().getTime();
//        System.out.println("Current time => " + c);
//
//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//        String formattedDate = df.format(c);
//
//        selectDate.setText(formattedDate);
//        selectDate.setTextColor(Color.parseColor("#000000"));

//        selectDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DatePickerDialog dialog = new DatePickerDialog(CreateRecord.this, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//
//                        month = month+1;
//                        String monthName = "";
//                        if (month == 1) monthName = "JAN";
//                        else if (month == 2) monthName = "FEB";
//                        else if (month == 3) monthName = "MAR";
//                        else if (month == 4) monthName = "APR";
//                        else if (month == 5) monthName = "MAY";
//                        else if (month == 6) monthName = "JUN";
//                        else if (month == 7) monthName = "JUL";
//                        else if (month == 8) monthName = "AUG";
//                        else if (month == 9) monthName = "SEP";
//                        else if (month == 10) monthName = "OCT";
//                        else if (month == 11) monthName = "NOV";
//                        else if (month == 12) monthName = "DEC";
//
//                        String date = dayOfMonth+"-"+monthName+"-"+year;
//                        selectDate.setText(date);
//                        selectDate.setTextColor(Color.parseColor("#000000"));
//
//
//                    }
//                }, year, month, day);
//                dialog.show();
//            }
//        });

//        selectTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                TimePickerDialog timePickerDialog = new TimePickerDialog(CreateRecord.this, new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//
//                        String AM_PM;
//                        if (hourOfDay>=0&&hourOfDay<12){
//                            AM_PM=" AM";
//                        }else {
//                            AM_PM=" PM";
//                         }
//                        if (hourOfDay == 0 || hourOfDay == 12) hourOfDay = 12;
//                        else hourOfDay = hourOfDay%12;
//                    selectTime.setText ( hourOfDay + ":" + minute+""+AM_PM );
//                    }
//                }, hour, min, false);
//                timePickerDialog.show();
//            }
//        });
//
//        backButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (fromActivity.equals("mainactivity")) {
//                    Intent intent = new Intent(CreateRecord.this, MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//
//            }
//        });

//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                setValues();
//
//                if(checkValues()) {
//                    calculateLevel();
//
//                    Intent intent1 = new Intent(CreateRecord.this, MainActivity.class);
//                    startActivity(intent1);
//                    finish();
//                }
//
//            }
//        });
//
//        tickMark.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                setValues();
//
//                if (checkValues()) {
//                    calculateLevel();
//
//                    Intent intent1 = new Intent(CreateRecord.this, MainActivity.class);
//                    startActivity(intent1);
//                    finish();
//                }
//            }
//        });
//
//    }

//    private void setValues() {
//        entryDate = selectDate.getText().toString();
//        entryTime = selectTime.getText().toString();
//        if (systolic.getText().toString().length() != 0) entrySystolic = Integer.parseInt(systolic.getText().toString());
//        if (diastolic.getText().toString().length() != 0) entryDiastolic = Integer.parseInt(diastolic.getText().toString());
//        if (pulse.getText().toString().length() != 0) entryPulse = Integer.parseInt(pulse.getText().toString());
//        entryNotes = notes.getText().toString();
//    }

//    private boolean checkValues() {
//
//        if (TextUtils.isEmpty(systolic.getText().toString()) || TextUtils.isEmpty(diastolic.getText().toString()) || TextUtils.isEmpty(pulse.getText().toString()) || entrySystolic>370 || entryDiastolic < 10 || entrySystolic<entryDiastolic || entryPulse < 30 || entryPulse > 220) {
//
//            if (TextUtils.isEmpty(systolic.getText().toString())) {
//                systolic.setError("Please enter a value");
//                //requiredSystolic.setVisibility(View.VISIBLE);
//            }
//
//            if (TextUtils.isEmpty(diastolic.getText().toString())) {
//                diastolic.setError("Please enter a value");
//                //requiredDiastolic.setVisibility(View.VISIBLE);
//            }
//
//            if (TextUtils.isEmpty(pulse.getText().toString())) {
//                pulse.setError("Please enter a value");
//                //pulse.setVisibility(View.VISIBLE);
//            }
//
//            if (entrySystolic<entryDiastolic) {
//
//                systolic.setError("Systolic must be greater than Diastolic");
//                //requiredSystolic.setVisibility(View.VISIBLE);
//
//                diastolic.setError("Systolic must be greater than Diastolic");
//                //requiredDiastolic.setVisibility(View.VISIBLE);
//            }
//
//            if (entrySystolic > 370) {
//                systolic.setError("Invalid input");
//                //requiredSystolic.setVisibility(View.VISIBLE);
//            }
//
//            if (entryDiastolic < 10) {
//                diastolic.setError("Invalid input");
//                //requiredDiastolic.setVisibility(View.VISIBLE);
//            }
//
//            if (entryPulse < 30 || entryPulse > 220) {
//                pulse.setError("Invalid input");
//                //requiredPulse.setVisibility(View.VISIBLE);
//            }
//
//            return false;
//
//        }
//        return true;
//    }

//    void calculateLevel() {
//
//        if (entrySystolic < 110 || entryDiastolic < 60) levelCalculate = 1;
//        else if (entrySystolic < 120 || entryDiastolic < 80) levelCalculate = 2;
//        else if (entrySystolic < 130 || entryDiastolic < 85) levelCalculate = 3;
//        else if (entrySystolic >= 180 || entryDiastolic >= 110) levelCalculate = 7;
//        else if (entrySystolic >= 160 || entryDiastolic >= 100) levelCalculate = 6;
//        else if (entrySystolic >= 140 || entryDiastolic >= 90) levelCalculate = 5;
//        else if (entrySystolic >= 130 || entryDiastolic >= 85) levelCalculate = 4;
//
//    }

