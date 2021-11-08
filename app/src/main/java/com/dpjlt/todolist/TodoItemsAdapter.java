package com.dpjlt.todolist;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TodoItemsAdapter extends RecyclerView.Adapter<TodoItemsAdapter.ViewHolder>{

    public class ViewHolder extends RecyclerView.ViewHolder{
        //todo
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //todo
        }
    }

    private ToDoList todoList;

    public  TodoItemsAdapter(ToDoList todoList){
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //todo
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemsAdapter.ViewHolder holder, int position) {
        //todo

    }

    @Override
    public int getItemCount() {
        //todo
        return 0;
    }
}
