package com.dpjlt.todolist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/*
    Storage classes and data types

    INTEGER       Any integer type
    TEXT          Any character type
    REAL          Any floating point number
    NUMERIC       Booleans, dates, and date-times
    BLOB          Binary Large Object

    Column sizes do not need to be specified in SQLite

 */

public class ToDoListSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Tasks";
    // For version control. Makes it easier to make changes and make rollbacks
    private static final int DATABASE_VERSION = 3;

    // Context is the activity it's in
    // null is something to do with cursors not sure what
    //todo update comment explaining null value in SQLiteHelper constructor
    ToDoListSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creates database when first needed
    // This method should include all code needed to create the tables needed for the app
    // for the FIRST TIME any subsequent changes are to be made in onUpgrade
    @Override
    public void onCreate(SQLiteDatabase db){
        // All SQL to be executed must be done in the method execSQL(String sqlStatement) on DB
        db.execSQL("CREATE TABLE TASKS (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TASK_NAME TEXT," +
                "TASK_CHECKED NUMERIC,"+
                "TASK_DATE TEXT)");

    }

    // If any modifications are to be made after the initial database is made
    // the modifications are to be made in this method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if ( oldVersion < 3 ) {
            db.execSQL("ALTER TABLE TASKS ADD COLUMN TASK_DATE TEXT DEFAULT ''");
        }
        if ( oldVersion < 2 ) {
            db.execSQL("ALTER TABLE TASKS ADD COLUMN TASK_CHECKED NUMERIC DEFAULT 0");
        }
    }

//    public static void addTaskDB(SQLiteDatabase db, Item task) {
//        ContentValues taskValues = new ContentValues();
//        taskValues.put("TASK_NAME", task.getTaskHeading());
//        db.insert("TASKS", null, taskValues);
//    }
}
