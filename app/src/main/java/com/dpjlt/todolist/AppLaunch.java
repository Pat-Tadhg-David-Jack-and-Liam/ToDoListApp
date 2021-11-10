package com.dpjlt.todolist;

import android.app.Application;
import android.util.Log;

/**
 * Launches the application
 */
public class AppLaunch extends Application {
    private static ToDoList toDoList;

    /**
     * create the list of todos
     * todo add the database stuff here too
     */
    public AppLaunch(){
        toDoList = new ToDoList();
        Log.i("main", "Constructor fired");
    }

    public static ToDoList getToDoList() {
        return toDoList;
    }
}
