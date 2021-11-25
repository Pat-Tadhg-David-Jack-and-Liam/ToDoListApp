package com.dpjlt.todolist;

import android.app.Application;
import android.util.Log;

/**
 * Launches the application
 */
public class AppLaunch extends Application {
    private static ToDoList toDoList;

    public void onCreate() {
        super.onCreate();
        final ToDoListSQLiteHelper toDoListDatabaseHelper = new ToDoListSQLiteHelper(getApplicationContext());
        toDoList = new ToDoList(toDoListDatabaseHelper);
        Log.i("main", "Constructor fired");
    }
    public static ToDoList getToDoList() {
        return toDoList;
    }
}
