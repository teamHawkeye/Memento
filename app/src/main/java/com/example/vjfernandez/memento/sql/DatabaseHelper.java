package com.example.vjfernandez.memento.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "Student";
    public static final String COL_1 = "Stu_NO";
    public static final String COL_2 = "Name";
    public static final String COL_3 = "Password";
    public static final String COL_4 = "MARKS ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Student(Stu_NO char(11) PRIMARY KEY ,Name varchar(30),Password varchar(20))");
        db.execSQL("CREATE TABLE Time_Table(Cource_Code char(10) PRIMARY KEY ,Course_Name Varchar(20),Day time,Start_Time time,End_Time time,Venue Varchar(20))");
        db.execSQL("CREATE TABLE Attendance(Cource_Code char(10) PRIMARY KEY ,Total_Hours int,Attend_Hours int,Held_Hours int)");
        db.execSQL("CREATE TABLE Results(Cource_Code char(10) PRIMARY KEY ,Year varchar(05),Semester Char(1),Result varchar(2))");
        db.execSQL("CREATE TABLE Assignment(Cource_Code char(10) PRIMARY KEY ,Tutorial_Name varchar(20),Mark int)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Student");
        db.execSQL("DROP TABLE IF EXISTS Time_Table");
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        db.execSQL("DROP TABLE IF EXISTS Results");
        db.execSQL("DROP TABLE IF EXISTS Assignment");
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

    public void resetTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Student");
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
}
