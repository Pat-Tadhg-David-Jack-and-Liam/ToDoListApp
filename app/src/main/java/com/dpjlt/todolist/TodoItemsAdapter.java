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
 * @author Group 11
 */
public class TodoItemsAdapter extends RecyclerView.Adapter<TodoItemsAdapter.ViewHolder>{
    /**
     * Provides direct reference to each of the views within item_todo.xml
     * @author Group 11
     */
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textTask;
        public CheckBox check;
        public Button removeTaskButton;
        public TextView textDueDate;
        public TextView textTag;
        public TextView textPriority;
        public ToDoList.Item toDoListItem;
        public final SQLiteOpenHelper toDoListDatabaseHelper = new ToDoListSQLiteHelper(this.itemView.getContext());


        /**
         * @param itemView the entire item row (item_todo.xml)
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTask = itemView.findViewById(R.id.textTask);
            check = itemView.findViewById(R.id.check);
            textDueDate = itemView.findViewById(R.id.textDueDate);
            textTag = itemView.findViewById(R.id.textTag);
            textPriority = itemView.findViewById(R.id.textPriority);
            removeTaskButton = itemView.findViewById(R.id.removeTaskButton);
            removeTaskButton.setOnClickListener(v -> todoList.removeItem(toDoListItem, MainActivity.mTodoListAdapter));
            check.setOnClickListener(v -> {
                toDoListItem.setChecked(((CheckBox) v).isChecked());
                ContentValues contentValues = new ContentValues();
                contentValues.put("TASK_CHECKED", ((CheckBox) v).isChecked());
                toDoListDatabaseHelper.getWritableDatabase().update("TASKS", contentValues,"TASK_NAME = ?", new String[] {toDoListItem.getTaskHeading()});
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
        holder.textDueDate.setText(item.getDueDate());
        holder.textTag.setText(item.getTag());
        holder.textPriority.setText(item.getPriority());
    }

    @Override
    public int getItemCount() {
        return todoList.getLength();
    }
}
