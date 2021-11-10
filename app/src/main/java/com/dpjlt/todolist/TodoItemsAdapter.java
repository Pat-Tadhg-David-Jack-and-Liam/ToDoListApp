package com.dpjlt.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
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
        public ToDoList.Item toDoListItem;
        public final SQLiteOpenHelper toDoListDatabaseHelper = new ToDoListSQLiteHelper(this.itemView.getContext());


        /**
         * @param itemView the entire item row (item_todo.xml)
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTask = itemView.findViewById(R.id.textTask);
            check = itemView.findViewById(R.id.check);
            removeTaskButton = itemView.findViewById(R.id.removeTaskButton);
            removeTaskButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    todoList.removeItem(toDoListItem, MainActivity.mTodoListAdapter);
                    toDoListDatabaseHelper.getWritableDatabase().delete("TASKS", "TASK_NAME = ?", new String[] {toDoListItem.getTaskHeading()});
                }
            });
            check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    toDoListItem.setChecked(((CheckBox) v).isChecked());
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("TASK_CHECKED", ((CheckBox) v).isChecked());
                    toDoListDatabaseHelper.getWritableDatabase().update("TASKS", contentValues,"TASK_NAME = ?", new String[] {toDoListItem.getTaskHeading()});
                }
            });

        }
    }

    private static final ToDoList todoList = AppLaunch.getToDoList();



    @NonNull
    @Override
    public TodoItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // inflate the layout
        View todoItemView = inflater.inflate(R.layout.item_todo, parent, false);

        return new ViewHolder(todoItemView);
    }

    /**
     * God knows what this does but we shouldn't need to call it anyways
     * @param holder viewholder we created earlier
     * @param position position in list of model we are currently using
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= todoList.getLength())
     */
    @Override
    public void onBindViewHolder(@NonNull TodoItemsAdapter.ViewHolder holder, int position) throws IndexOutOfBoundsException {
        ToDoList.Item item = todoList.getIndex(position);
        holder.toDoListItem = item;
        holder.check.setChecked(item.getChecked());
        holder.textTask.setText(item.getTaskHeading());
    }

    @Override
    public int getItemCount() {
        return todoList.getLength();
    }
}
