package com.dpjlt.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AddEditItemActivity extends AppCompatActivity {
    private Spinner dropdown;
    private static ToDoList toDoList = AppLaunch.getToDoList();
    public static TodoItemsAdapter mTodoListAdapter = new TodoItemsAdapter();
//    private EditText editName;
    public final SQLiteOpenHelper toDoListDatabaseHelper = new ToDoListSQLiteHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_task);

        dropdown =  findViewById(R.id.editPriority);
        String[] priorities = new String[]{"No Priority", "Low", "Medium", "High"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        dropdown.setAdapter(adapter);
    }

    public void addTaskToDB (String taskName){
        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", taskName);
        taskValues.put("TASK_CHECKED", false);
        toDoListDatabaseHelper.getWritableDatabase().insert("TASKS",null,  taskValues);
    }

    public void addTask (View view) {
        EditText editName = findViewById(R.id.editName);
        EditText editDueDate = findViewById(R.id.editDueDate);
        EditText editTag = findViewById(R.id.editTag);
        String taskName = editName.getText().toString();
        String dueDate = editDueDate.getText().toString();
        String tagName = editTag.getText().toString();
        toDoList.addItem(taskName, mTodoListAdapter);
        addTaskToDB(taskName);
//        editName.setText("");
////      close the keyboard
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        saveTask(view);
    }

    public void saveTask(View view) {
//        EditText editName = findViewById(R.id.editName);
//
//        String taskName = editName.getText().toString();

        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}