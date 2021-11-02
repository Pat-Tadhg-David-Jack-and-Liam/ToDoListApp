package com.dpjlt.todolist;

import android.app.Application;
import android.util.Log;

public class AppLaunch extends Application {
    public static ListADT toDoList;

    public AppLaunch(){
        toDoList = new ListADT();
        Log.i("main", "Constructor fired");
    }
}
