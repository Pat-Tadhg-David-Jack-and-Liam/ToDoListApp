package com.dpjlt.todolist;

import android.content.Context;
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
    public static TodoItemsAdapter mTodoListAdapter = new TodoItemsAdapter(toDoList);
    private EditText editTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView rvTodoList = findViewById(R.id.rvTodoList);
        //bind the recyclerview
        rvTodoList.setAdapter(mTodoListAdapter);
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));

        this.setupEditTextListener();
        // start up task for testing : )
        toDoList.addItem("WOW");
    }

    public void addTask (View view){
        toDoList.addItem(editTodo.getText().toString());
        editTodo.setText("");
        // close the keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void removeTask (View view){
//        toDoList.removeItem();
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