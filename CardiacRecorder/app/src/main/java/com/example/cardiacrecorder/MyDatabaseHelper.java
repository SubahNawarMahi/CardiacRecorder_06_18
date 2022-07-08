package com.example.cardiacrecorder;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME="cardiac_recorder_list";
    private static final String TABLE_NAME="cardiac_recorder_list_details";
    private static final String ID="_id";
    private static final String SYSTOLIC="systolic";
    private static final String DIASTOLIC="diastolic";
    private static final String BLOOD_PRESSURE_STATUS="pressure_sat";
    private static final String PULSE="pulse";
    private static final String PULSE_STATUS="pulse_stat";
    private static final String DATE="date";
    private static final String TIME="time";
    private static final String COMMENTS="comments";
    private static final int VERSION_NUMBER= 1;
    private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+SYSTOLIC+" varchar,"+DIASTOLIC+" varchar,"+BLOOD_PRESSURE_STATUS+" varchar,"+PULSE+" varchar,"+PULSE_STATUS+","+DATE+" varchar,"+TIME+" varchar,"+COMMENTS+" varchar(255));";

    private static final String SELECT_ALL ="SELECT * FROM cardiac_recorder_list WHERE id = ?";
    private static final String UPDATE_DATA = "SELECT * FROM "+TABLE_NAME;
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_NAME;



private Context context;

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME,null,VERSION_NUMBER);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//
//        Toast.makeText(context,"database created",Toast.LENGTH_SHORT).show();
        //db.execSQL(CREATE_TABLE);
        try{
            db.execSQL(CREATE_TABLE);

        }catch(Exception e)
        {
            Toast.makeText(context,"Error : "+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        try
        {
            Toast.makeText(context,"onUpgrade is called",Toast.LENGTH_SHORT).show();
            db.execSQL(DROP_TABLE);
            onCreate(db);



        }
        catch (Exception e)
        {
            Toast.makeText(context,"Exception : "+e,Toast.LENGTH_SHORT).show();
        }
    }

    public long insertData(String systol, String diastol, String blood_pressure_status, String pulse, String pulse_status, String date_value, String time_value, String comments) {

    SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(SYSTOLIC,systol);
        contentValues.put(DIASTOLIC,diastol);
        contentValues.put(BLOOD_PRESSURE_STATUS,blood_pressure_status);
        contentValues.put(PULSE,pulse);
        contentValues.put(PULSE_STATUS,pulse_status);
        contentValues.put(DATE,"Date: "+date_value);
        contentValues.put(TIME,"Time: "+time_value);
        contentValues.put(COMMENTS,"Comments: "+comments);
        long id= sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        return id;
    }
    public Boolean updateData(String id,String sys,String dias,String pressure_status,String pulse,String pulse_status,String date,String time,String comments)
    {

        // Toast.makeText(context,"id: "+id,Toast.LENGTH_SHORT).show();
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,id);
        contentValues.put(SYSTOLIC,sys);
        contentValues.put(DIASTOLIC,dias);
        contentValues.put(BLOOD_PRESSURE_STATUS,pressure_status);
        contentValues.put(PULSE,pulse);
        contentValues.put(PULSE_STATUS,pulse_status);
        contentValues.put(DATE,"Date:"+date);
        contentValues.put(TIME,"Time:"+time);
        contentValues.put(COMMENTS,"Com: "+comments);

        sqLiteDatabase.update(TABLE_NAME, contentValues, "_id = ?", new String[]{id});

        return true;
    }
    public long deleteList(String id)
    {
        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        return  sqLiteDatabase.delete(TABLE_NAME,ID+" = ?",new String[]{id});
    }


    public SimpleCursorAdapter loadListViewFromDB() {

        SQLiteDatabase sqLiteDatabase =  this.getWritableDatabase();
        String columns[] = {MyDatabaseHelper.ID,MyDatabaseHelper.SYSTOLIC,MyDatabaseHelper.DIASTOLIC,MyDatabaseHelper.BLOOD_PRESSURE_STATUS,MyDatabaseHelper.PULSE,MyDatabaseHelper.PULSE_STATUS,MyDatabaseHelper.DATE,MyDatabaseHelper.TIME,MyDatabaseHelper.COMMENTS};

        Cursor cursor = sqLiteDatabase.query(MyDatabaseHelper.TABLE_NAME,columns,null,null,null,null,null);

        String[] fromFieldNames = new String[]{
                MyDatabaseHelper.ID,MyDatabaseHelper.SYSTOLIC,MyDatabaseHelper.DIASTOLIC,MyDatabaseHelper.BLOOD_PRESSURE_STATUS,MyDatabaseHelper.PULSE,MyDatabaseHelper.PULSE_STATUS,MyDatabaseHelper.DATE,MyDatabaseHelper.TIME,MyDatabaseHelper.COMMENTS
        };

        int[] toViewId = new int[]{
                R.id.list_id,R.id.systol,R.id.diastol,R.id.pressure_status,R.id.pulse_Id,R.id.pulse_status,R.id.date_Id,R.id.time_Id,R.id.comments
        };
        SimpleCursorAdapter listAdapter = new SimpleCursorAdapter(context,R.layout.customlistlayout,cursor,fromFieldNames,toViewId);
        return listAdapter;

    }
}
