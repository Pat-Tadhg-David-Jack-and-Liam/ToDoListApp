package com.dpjlt.todolist;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
    // todo make private and access with getter
    public static ToDoList toDoList = AppLaunch.toDoList;

    // add task popup, Pat
    // it keeps crashing, cant figure out why
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText newTaskPopup;
    private Button newTaskSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toDoList.addItem("WOW");
//
//        // add task popup
//        Button addButton = (Button) findViewById(R.id.addButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(MainActivity.this, Pop.class));
//                }
//        });

        // RecyclerView rvTodoList = (RecyclerView) findViewById(R.id.)
        // bind the recyclerview
    }

    public void onBtnClick (View view){
        createNewTaskDialog();
//         TextView txtHello = findViewById(R.id.textJack);
//         EditText Object type to take user input
//         Plain Text in xml
//         EditText editTxt = findViewById(R.id.edittxt);
//         txtHello.setText(editTxt.getText());
//        this.addTask();
    }


    public void createNewTaskDialog() {
        dialogBuilder = new AlertDialog.Builder(this);
        //final View taskPopupView = getLayoutInflater().inflate(R.layout.popup_for_add_task, null );


       // newTaskPopup = taskPopupView.findViewById(R.id.addTaskButton);

       // dialogBuilder.setView(taskPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        newTaskPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this is where we add the task to list, idk how yet
            }
        });
    }
}