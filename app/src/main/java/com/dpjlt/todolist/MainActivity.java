package com.dpjlt.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    private static ToDoList toDoList = AppLaunch.getToDoList();
    public static TodoItemsAdapter mTodoListAdapter = new TodoItemsAdapter();
    private EditText editTodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvTodoList = findViewById(R.id.rvTodoList);
        //bind the recyclerview
        rvTodoList.setAdapter(mTodoListAdapter);
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));

        rvTodoList.addItemDecoration(new DividerItemDecoration(rvTodoList.getContext(), DividerItemDecoration.VERTICAL));

//        this.setupEditTextListener();



    }
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
//    private void setupEditTextListener(){
//        editTodo = findViewById(R.id.editTodo);
//        editTodo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    addTask(textView);
//                    return true;
//                }
//                return false;
//            }
//        });
//    }

    public void openEditTaskScreen(View view) {
        Intent intent = new Intent(this, AddEditItemActivity.class);
        startActivity(intent);
    }


}