package com.example.vjfernandez.memento.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";
    private static final String TABLE_NAME = "Student";
    private static final String COL_1 = "Stu_NO";
    private static final String COL_2 = "Name";
    private static final String COL_3 = "Password";

    private static final String TABLE_NAME1 = "Tutorial";
    private static final String COL_4 = "Cource_Code";
    private static final String COL_5 = "Tutorial_Name";
    private static final String COL_6 = "Mark";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Student(Stu_NO char(11) PRIMARY KEY ,Name varchar(30),Password varchar(20))");
        db.execSQL("CREATE TABLE Time_Table(Cource_Code char(10) PRIMARY KEY ,Course_Name Varchar(20),Day time,Start_Time time,End_Time time,Venue Varchar(20))");
        db.execSQL("CREATE TABLE Attendance(Cource_Code char(10) PRIMARY KEY ,Total_Hours int,Attend_Hours int,Held_Hours int)");
        db.execSQL("CREATE TABLE Results(Cource_Code char(10) PRIMARY KEY ,Year varchar(5),Semester Char(1),Result varchar(2))");
        db.execSQL("CREATE TABLE Tutorial(Cource_Code char(10) PRIMARY KEY ,Tutorial_Name varchar(20),Mark Varchar(5))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        db.execSQL("DROP TABLE IF EXISTS Time_Table");
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        db.execSQL("DROP TABLE IF EXISTS Results");
        db.execSQL("DROP TABLE IF EXISTS Tutorial");
        onCreate(db);
    }

    public boolean insertData(String studentNumber, String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, studentNumber);
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataTutorial(String courseCode, String tutorial, String mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COL_4, courseCode);
        contentValues1.put(COL_5, tutorial);
        contentValues1.put(COL_6, mark);
        long result1 = db.insert(TABLE_NAME1, null, contentValues1);
        if (result1 == -1)
            return false;
        else
            return true;
    }



    public boolean getData(String stuNo,String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Student", null);

        while (res.moveToNext()){
            String StuNoDB=res.getString(0);
            String passwordDB=res.getString(2);

            if(StuNoDB.equals(stuNo) && passwordDB.equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean checkUser(String stuNo){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from Student", null);

        while (res.moveToNext()){
            String StuNoDB=res.getString(0);
            if(StuNoDB.equals(stuNo)){
                return true;
            }
        }
        return false;
    }

    public boolean checkTutorial(String courseCode,String tutorial){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res1 = db.rawQuery("select * from Tutorial", null);

        while (res1.moveToNext()){
            String courseCodeDB=res1.getString(0);
            String tutorialDB=res1.getString(1);

            if(courseCodeDB.equals(courseCode) && tutorialDB.equals(tutorial)){
                return true;
            }
        }
        return false;
    }


    public void resetTable(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+tableName);
    }

    public String getStudentName(String stuNo){
        SQLiteDatabase db = this.getReadableDatabase();
        String stuName="";
        Cursor res = db.rawQuery("select * from Student", null);
        while (res.moveToNext()){
            String StuNoDB=res.getString(0);
            if(StuNoDB.equals(stuNo)){
                stuName = res.getString(1);
                return stuName;
            }
        }
        return stuName;
    }

    public Cursor getAllDataTutorial(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("Insert into Tutorial values('SE/2015/047','Tutorial 01','80')");
        Cursor res1 = db.rawQuery("select * from Tutorial", null);

        return res1;
    }
}
