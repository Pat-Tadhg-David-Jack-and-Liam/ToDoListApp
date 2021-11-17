package com.dpjlt.todolist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public final class ToDoList {
    public class Item {
        private String taskHeading;
        private boolean checked;

        private Item(String taskHeading, boolean checked){
            this.taskHeading = taskHeading;
            this.checked = checked;
        }
        private Item(String taskHeading){
            this.taskHeading = taskHeading;
            this.checked = false;
        }
        final public boolean getChecked(){
            return checked;
        }
        final public void setChecked(boolean value){
            checked = value;
        }
        final public String getTaskHeading() {
            return taskHeading;
        }
        final public void setTaskHeading(String taskHeading) {
            this.taskHeading = taskHeading;
        }
    }

    private List<Item> toDoListTasks;


    public ToDoList(){
        toDoListTasks = new ArrayList<Item>();
    }

    /**
     *
     * @param taskHeading title of the task
     * @param checked if the task is completed
     * @throws IllegalArgumentException when task heading > 100 chars
     */
    final public void addItem(String taskHeading, boolean checked) throws IllegalArgumentException{
        if(taskHeading.length() < 100) {
            Item newItem = new Item(taskHeading, checked);
            toDoListTasks.add(newItem);
        } else {
            throw new IllegalArgumentException("task heading must be less than 100 chars");
        }

    }

    final public void addItem(String taskHeading,boolean checked, TodoItemsAdapter mTodoItemsAdapter) throws IllegalArgumentException{
        this.addItem(taskHeading, checked);
        mTodoItemsAdapter.notifyItemInserted(this.getLength() - 1);

    }

    final public void removeItem(Item item, TodoItemsAdapter mTodoItemsAdapter){
        int index = toDoListTasks.indexOf(item);
        toDoListTasks.remove(item);
        mTodoItemsAdapter.notifyItemRemoved(index);
    }

    final public int getLength(){
        return toDoListTasks.size();
    }

    /**
     * Get the item at a particular index in the list
     * @param index index of the item to get
     * @return The item at <i>index</i> in toDoListTasks
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= getLength())
     */
    final public Item getIndex(int index) throws IndexOutOfBoundsException{
        return toDoListTasks.get(index);
    }

    public static void addTaskDB(SQLiteDatabase db, Item task) {
        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", task.getTaskHeading());
        db.insert("TASKS", null, taskValues);
    }

}
