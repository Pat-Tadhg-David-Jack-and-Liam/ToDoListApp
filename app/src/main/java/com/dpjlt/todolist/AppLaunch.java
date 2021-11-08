package com.dpjlt.todolist;

import android.app.Application;
import android.util.Log;

public class AppLaunch extends Application {
    public static ToDoList toDoList;

    public AppLaunch(){
        toDoList = new ToDoList();
        Log.i("main", "Constructor fired");
    }
}
