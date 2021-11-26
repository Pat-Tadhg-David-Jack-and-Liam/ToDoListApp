package com.dpjlt.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static ToDoList toDoList = AppLaunch.getToDoListActive();
    public static TodoItemsAdapter mTodoListAdapter = new TodoItemsAdapter();
    private EditText editTodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvTodoList = findViewById(R.id.rvTodoListActive);
        //bind the recyclerview
        rvTodoList.setAdapter(mTodoListAdapter);
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));

        rvTodoList.addItemDecoration(new DividerItemDecoration(rvTodoList.getContext(), DividerItemDecoration.VERTICAL));

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

//        this.setupEditTextListener();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        switch(item.getItemId()) {

            case R.id.Archived_Tasks:
                //Toast.makeText(this, "Work PLz", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, ArchivedTasks.class);
                startActivity(intent);
                // Returning true lets android know we've dealt with the item being clicked
                return true;

            case R.id.Active_Tasks:
                Toast.makeText(this, "Already in Active Tasks", Toast.LENGTH_LONG).show();
                return true;

            default:
                Toast.makeText(this, "umm wut", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

    }}




//
//    public void addTaskToDB (String taskName){
//        ContentValues taskValues = new ContentValues();
//        taskValues.put("TASK_NAME", taskName);
//        taskValues.put("TASK_CHECKED", false);
//        toDoListDatabaseHelper.getWritableDatabase().insert("TASKS",null,  taskValues);
//    }

//    public void addTask (View view){
//        openEditTaskScreen();
//        String taskName = editTodo.getText().toString();
//        toDoList.addItem(taskName, mTodoListAdapter);
//        addTaskToDB(taskName);
//        editTodo.setText("");
        // close the keyboard
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//    }

//


    public void openEditTaskScreen(View view) {
        Intent intent = new Intent(this, AddEditItemActivity.class);
        startActivity(intent);
    }


}