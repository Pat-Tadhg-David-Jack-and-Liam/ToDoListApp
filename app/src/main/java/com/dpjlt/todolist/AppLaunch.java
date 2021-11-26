package com.dpjlt.todolist;

import android.app.Application;
import android.util.Log;

/**
 * Launches the application
 */
public class AppLaunch extends Application {
    private static ToDoList toDoListActive;
    private static ToDoList toDoListArchive;

    @Override
    public void onCreate() {
        super.onCreate();
        final ToDoListSQLiteHelper toDoListDatabaseHelper = new ToDoListSQLiteHelper(getApplicationContext());
        toDoListActive = new ToDoList(toDoListDatabaseHelper, "TASKS");
        toDoListArchive = new ToDoList(toDoListDatabaseHelper, "ARCHIVE");

        Log.i("main", "Constructor fired");
    }
    public static ToDoList getToDoListActive() {
        return toDoListActive;
    }
    public static ToDoList getToDoListArchive(){return toDoListArchive;}
}
