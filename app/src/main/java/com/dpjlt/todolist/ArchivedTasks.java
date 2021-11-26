package com.dpjlt.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class ArchivedTasks extends AppCompatActivity {
    public static TodoItemsAdapterArchive aTodoListAdapter = new TodoItemsAdapterArchive();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archived_tasks);


        RecyclerView rvTodoList = findViewById(R.id.rvTodoListArchive);
        rvTodoList.setAdapter(aTodoListAdapter);
        rvTodoList.setLayoutManager(new LinearLayoutManager(this));
        rvTodoList.addItemDecoration(new DividerItemDecoration(rvTodoList.getContext(), DividerItemDecoration.VERTICAL));

        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);


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
                Toast.makeText(this, "Already in Archived Tasks", Toast.LENGTH_LONG).show();
                return true;

            case R.id.Active_Tasks:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            default:
                return super.onOptionsItemSelected(item);

        }}
}