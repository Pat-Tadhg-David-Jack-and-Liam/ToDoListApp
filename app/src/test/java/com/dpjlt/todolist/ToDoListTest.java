package com.dpjlt.todolist;

import static org.mockito.Mockito.*;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;

public class ToDoListTest {
    private ToDoList toDoList;
    @Before
    public void setup(){
        final ToDoListSQLiteHelper toDoListDatabaseHelper = mock(ToDoListSQLiteHelper.class);
        final SQLiteDatabase mockDb = mock(SQLiteDatabase.class);
        final Cursor mockCursor = mock(Cursor.class);
        doReturn(mockDb).when(toDoListDatabaseHelper).getReadableDatabase();
        doReturn(mockDb).when(toDoListDatabaseHelper).getWritableDatabase();
        doReturn(mockCursor).when(mockDb).query("TASKS", new String[] {"_id", "TASK_NAME", "TASK_CHECKED, TASK_DATE", "TASK_TAG", "TASK_PRIORITY"},
                null,null,null,null,null);

        toDoList = new ToDoList(toDoListDatabaseHelper, "TASKS");
    }
    @Test
    public void addItem() {
        assert toDoList.addItem("eggs", false, "01/01/2000", "fdsaf", "LOW" ) != null;
        String longString = new String(new char[101]).replace('\0', ' ');
        // test it throws an error when expected
        try{
            toDoList.addItem(longString, false, "01/01/2000", "fdsaf", "LOW");
            throw new AssertionError();
        } catch (IllegalArgumentException ignored) {

        }
    }

    @Test
    public void removeItem(){
        ToDoList.Item mockItem = mock(ToDoList.Item.class);
        TodoItemsAdapterArchive mockAdapter = mock(TodoItemsAdapterArchive.class);
        doNothing().when(mockAdapter).notifyDataSetChanged();
        // would rather we didn't have to rely on this but no way to directly add it
        assert toDoList.removeItem(mockItem, mockAdapter) == mockItem;

    }

}