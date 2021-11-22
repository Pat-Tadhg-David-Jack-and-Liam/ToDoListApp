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

        // set up the priorities dropdown menu
        dropdown =  findViewById(R.id.priority);
        String[] priorities = new String[]{"No Priority", "Low", "Medium", "High"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        dropdown.setAdapter(adapter);
    }

    public void addTaskToDB (String taskName){
        addTaskToDB(taskName, null, null, null);
    }
    public void addTaskToDB (String taskName, String dueDate, String tag, String priority) {
        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", taskName);
        taskValues.put("TASK_CHECKED", false);
        toDoListDatabaseHelper.getWritableDatabase().insert("TASKS",null,  taskValues);
    }

    public void addTask (View view) {
        EditText nameBox = findViewById(R.id.name);
        EditText dueDateBox = findViewById(R.id.due_date);
        EditText tagBox = findViewById(R.id.tag);
        Spinner priorityDropdown = findViewById(R.id.priority);

        String taskName = nameBox.getText().toString();
        String dueDate = dueDateBox.getText().toString();
        String tagName = tagBox.getText().toString();
        String priorityLevel = priorityDropdown.getSelectedItem().toString();

        toDoList.addItem(taskName, false, mTodoListAdapter);
        addTaskToDB(taskName);
//        editName.setText("");
////      close the keyboard
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        saveTask(view);
    }

    public void saveTask(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}