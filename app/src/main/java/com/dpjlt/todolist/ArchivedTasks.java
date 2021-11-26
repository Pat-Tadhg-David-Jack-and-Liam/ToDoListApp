package com.dpjlt.todolist;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ArchivedTasks extends AppCompatActivity {
    private static ToDoList toDoList = AppLaunch.getToDoListArchive();
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
        getMenuInflater().inflate(R.menu.menu_main_archive, menu);
        int SortByPriority = 2;
        MenuItem item = menu.getItem(SortByPriority);
        SpannableString NewSortByPriorityString = new SpannableString("Sort By Priority");
        NewSortByPriorityString.setSpan(new ForegroundColorSpan(Color.BLACK), 0, NewSortByPriorityString.length(), 0);
        item.setTitle(NewSortByPriorityString);
        getSupportActionBar().setTitle("Archive");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){


        switch(item.getItemId()) {

//            case R.id.Archived_Tasks:
//                Toast.makeText(this, "Already in Archived Tasks", Toast.LENGTH_LONG).show();
//                return true;

            case R.id.Active_Tasks:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);

            case R.id.SortTag:
                toDoList.removeItemSortArchive();
                toDoList.sortByTagArchive();
                return true;

            case R.id.SortPriority:
                toDoList.removeItemSortArchive();
                toDoList.sortByPriorityArchive();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }}
}