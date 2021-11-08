package com.dpjlt.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Convert a position in the item_todo into a list row item to be inserted
 * @author liam
 */
public class TodoItemsAdapter extends RecyclerView.Adapter<TodoItemsAdapter.ViewHolder>{
    /**
     * Provides direct reference to each of the views within item_todo.xml
     * @author liam
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textTask;
        public CheckBox check;
        public Button removeTaskButton;

        /**
         * @param itemView the entire item row (item_todo.xml)
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTask = itemView.findViewById(R.id.textTask);
            check = itemView.findViewById(R.id.check);
            removeTaskButton = itemView.findViewById(R.id.removeTaskButton);
        }
    }

    private final ToDoList todoList;

    /**
     * @param todoList the list of to-do items to add
     */
    public TodoItemsAdapter(ToDoList todoList){
        this.todoList=todoList;
    }


    @NonNull
    @Override
    public TodoItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the layout
        View todoItemView = inflater.inflate(R.layout.item_todo, parent, false);

        return new ViewHolder(todoItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoItemsAdapter.ViewHolder holder, int position) {
        ToDoList.Item item = todoList.getIndex(position);

        holder.textTask.setText(item.getTaskHeading());
    }

    @Override
    public int getItemCount() {
        return todoList.getLength();
    }
}
