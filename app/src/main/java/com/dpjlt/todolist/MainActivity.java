package com.dpjlt.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onBtnClick (View view){
        TextView txtHello = findViewById(R.id.textJack);
        // EditText Object type to take user input
        // Plain Text in xml
        EditText editTxt = findViewById(R.id.edittxt);
        txtHello.setText(editTxt.getText());

        // stop breaking the repo, Pat

        // no I don't think I will

        // liams test

        // one more test

        //test4

        //test5

        //test6

        //test8

        //test10
    }

}