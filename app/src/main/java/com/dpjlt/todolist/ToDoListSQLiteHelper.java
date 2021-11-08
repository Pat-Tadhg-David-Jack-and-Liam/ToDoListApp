package com.dpjlt.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ToDoListSQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Tasks";
    // For version control. Makes it easier to make changes and make rollbacks
    private static final int DATABASE_VERSION = 1;


    ToDoListSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creates database when first needed
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE TASKS (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "TASK_NAME TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)

    }

    public static void addTaskDB(SQLiteDatabase db, Item task,) {
        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", task.getTaskHeading());
        db.insert("TASKS", null, taskValues);

    }
}
