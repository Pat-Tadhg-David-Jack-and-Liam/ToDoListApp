package com.dpjlt.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static ToDoList toDoList = AppLaunch.getToDoList();
    public static TodoItemsAdapter mTodoListAdapter = new TodoItemsAdapter();
    private EditText editTodo;
    public final SQLiteOpenHelper toDoListDatabaseHelper = new ToDoListSQLiteHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvTodoList = findViewById(R.id.rvTodoList);
        //bind the recyclerview
        rvTodoList.setAdapter(mTodoListAdapter);
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));
        this.setupEditTextListener();


        SQLiteDatabase dbRead = toDoListDatabaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.query("TASKS", new String[] {"_id", "TASK_NAME", "TASK_CHECKED"},
           null,null,null,null,null);

        if(cursor.moveToFirst()){
            String taskName = cursor.getString(1);
            boolean taskChecked = cursor.getInt(2) > 0;
            toDoList.addItem(taskName, taskChecked);
            while(cursor.moveToNext()){
                taskName = cursor.getString(1);
                taskChecked = cursor.getInt(2) > 0;
                toDoList.addItem(taskName,taskChecked);
            }

        }

    }
    
    public void addTaskToDB (String taskName){
        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", taskName);
        taskValues.put("TASK_CHECKED", false);
        toDoListDatabaseHelper.getWritableDatabase().insert("TASKS",null,  taskValues);
    }

    public void addTask (View view){
        String taskName = editTodo.getText().toString();
        toDoList.addItem(taskName, false, mTodoListAdapter);
        addTaskToDB(taskName);
        editTodo.setText("");
        // close the keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    private void setupEditTextListener(){
        editTodo = findViewById(R.id.editTodo);
        editTodo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addTask(textView);
                    return true;
                }
                return false;
            }
        });
    }


}