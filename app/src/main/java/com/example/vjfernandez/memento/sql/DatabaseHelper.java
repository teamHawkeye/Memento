package com.example.vjfernandez.memento.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Student.db";

    private static final String STUDENT_TABLE = "Student";
    private static final String TIMETABLE_TABLE = "Time_Table";
    private static final String ATTENDANCE_TABLE = "Attendance";
    private static final String RESULT_TABLE = "Results";
    private static final String TUTORIAL_TABLE = "Tutorial";

    private static final String STUDENT_NO_COL = "Stu_NO";
    private static final String NAME_COL = "Name";
    private static final String PASSWORD_COL = "Password";
    private static final String COURSE_CODE_COL = "Course_Code";
    private static final String COURSE_NAME_COL = "Course_Name";
    private static final String DAY_COL = "Day";
    private static final String START_TIME_COL = "Start_Time";
    private static final String END_TIME_COL = "End_Time";
    private static final String VENUE_COL = "Venue";
    private static final String YEAR_COL = "Year";
    private static final String SEMESTER_COL = "Semester";
    private static final String RESULT_COL = "Result";
    private static final String TUTORIAL_NAME_COL = "Tutorial_Name";
    private static final String MARK_COL = "Mark";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Tutorial(Course_Code char(10) PRIMARY KEY ,Tutorial_Name varchar(20),Mark Varchar(5))");
        db.execSQL("CREATE TABLE Student(Stu_NO char(11) PRIMARY KEY ,Name varchar(30),Password varchar(20))");
        db.execSQL("CREATE TABLE Time_Table(Course_Code char(10) PRIMARY KEY ,Course_Name Varchar(20),Day char(3),Start_Time time,End_Time time,Venue Varchar(20))");
        db.execSQL("CREATE TABLE Attendance(Course_Code char(10) PRIMARY KEY ,Total_Hours int,Attend_Hours int,Held_Hours int)");
        db.execSQL("CREATE TABLE Results(Course_Code char(10) PRIMARY KEY ,Year varchar(5),Semester Char(1),Result varchar(2))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Tutorial");
        db.execSQL("DROP TABLE IF EXISTS Time_Table");
        db.execSQL("DROP TABLE IF EXISTS Attendance");
        db.execSQL("DROP TABLE IF EXISTS Results");
        db.execSQL("DROP TABLE IF EXISTS Student");
        onCreate(db);
    }

    public boolean insertData(String studentNumber, String name, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_NO_COL, studentNumber);
        contentValues.put(NAME_COL, name);
        contentValues.put(PASSWORD_COL, password);
        long result = db.insert(STUDENT_TABLE, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertDataTutorial(String courseCode, String tutorial, String mark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COURSE_CODE_COL, courseCode);
        contentValues1.put(TUTORIAL_NAME_COL, tutorial);
        contentValues1.put(MARK_COL, mark);
        long result1 = db.insert(TUTORIAL_TABLE, null, contentValues1);
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

    public String getAllDataTutorial(){
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("INSERT INTO Tutorial VALUES('SE/2015/047','Tutorial 01','80')");
        Cursor res = db.rawQuery("SELECT * FROM "+TUTORIAL_TABLE, null);

        String course="dfsddfg";
        while (res.moveToNext()){
            course=res.getString(0);
        }
        return course;
    }
}
