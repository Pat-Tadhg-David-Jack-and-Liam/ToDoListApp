package com.dpjlt.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class AddEditItemActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    private Spinner dropdown;
    private EditText dueDateBox;
    private static ToDoList toDoList = AppLaunch.getToDoList();
    public static TodoItemsAdapter mTodoListAdapter = MainActivity.mTodoListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_edit_task);
        dueDateBox = findViewById(R.id.due_date);

        // set up the priorities dropdown menu
        dropdown =  findViewById(R.id.priority);
        String[] priorities = new String[]{"No Priority", "Low", "Medium", "High"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        dropdown.setAdapter(adapter);
    }

    public void addTask (View view) {
        EditText nameBox = findViewById(R.id.name);
        EditText tagBox = findViewById(R.id.tag);
        Spinner priorityDropdown = findViewById(R.id.priority);

        String taskName = nameBox.getText().toString();
        String dueDate = dueDateBox.getText().toString();
        String tagName = tagBox.getText().toString();
        String priorityLevel = priorityDropdown.getSelectedItem().toString();

        toDoList.addItem(taskName, false, mTodoListAdapter);
        saveTask(view);
    }


    public void showDatePickerDailog(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }
    @Override
    public void onDateSet (android.widget.DatePicker view, int year, int month, int dayOfMonth) {
        String date = dayOfMonth + "/" + month + "/" + year;
        dueDateBox.setText(date);
    }


    public void saveTask(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}