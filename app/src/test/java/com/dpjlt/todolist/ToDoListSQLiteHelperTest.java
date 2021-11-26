package com.dpjlt.todolist;

import static org.junit.Assert.*;

import android.content.Context;

import org.junit.Test;

public class ToDoListSQLiteHelperTest {

    @Test
    public void onUpgrade() {
        Context context = new MockContext();
        ToDoListSQLiteHelper toDoListSQLiteHelper = new ToDoListSQLiteHelper(context);
    }


}