package com.dpjlt.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public final class ToDoList {
    public class Item {
        private String taskHeading;
        private boolean checked;
        private String dueDate;
        private String taskTag;
        private String taskPriority;

        private Item(String taskHeading, boolean checked,String dueDate, String taskTag, String taskPriority) {
            this.taskHeading = taskHeading;
            this.checked = checked;
            this.dueDate = dueDate;
            this.taskTag = taskTag;
            this.taskPriority = taskPriority;
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

        final public String getDueDate() {
            return dueDate;
        }
        final public String getTag() {
            return taskTag;
        }
        final public String getPriority() {
            return taskPriority;
        }
    }

    private List<Item> toDoListTasks;
    private ToDoListSQLiteHelper toDoListDatabaseHelper;


    public ToDoList(ToDoListSQLiteHelper toDoListDatabaseHelper){
        this.toDoListDatabaseHelper = toDoListDatabaseHelper;
        toDoListTasks = new ArrayList<>();
        SQLiteDatabase dbRead = toDoListDatabaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.query("TASKS", new String[] {"_id", "TASK_NAME", "TASK_CHECKED, TASK_DATE", "TASK_TAG", "TASK_PRIORITY"},
                null,null,null,null,null);

        addTasksFromDB(cursor);

        cursor.close();
    }

    /**
     * Add tasks from the database to the list
     * @param cursor a cursor on the database we're using
     */
    public void addTasksFromDB(Cursor cursor){
        if(cursor.moveToFirst()){
            String taskName = cursor.getString(1);
            boolean taskChecked = cursor.getInt(2) > 0;
            String dueDate = cursor.getString(3);
            String taskTag = cursor.getString(4);
            String taskPriority = cursor.getString(5);
            this.addItem(taskName, taskChecked, dueDate, taskTag, taskPriority);
            while(cursor.moveToNext()){
                taskName = cursor.getString(1);
                taskChecked = cursor.getInt(2) > 0;
                dueDate = cursor.getString(3);
                taskTag = cursor.getString(4);
                taskPriority = cursor.getString(5);
                this.addItem(taskName,taskChecked, dueDate, taskTag, taskPriority);
            }
        }
    }

    /**
     * Method for adding to the list when the App starts
     * @param taskHeading title of the task
     * @param checked if the task is completed
     * @throws IllegalArgumentException when task heading > 100 chars
     */
    final public void addItem(String taskHeading, boolean checked, String dueDate, String taskTag, String taskPriority) throws IllegalArgumentException{
        if(taskHeading.length() < 100) {
            Item newItem = new Item(taskHeading, checked, dueDate, taskTag, taskPriority);
            toDoListTasks.add(newItem);
        } else {
            throw new IllegalArgumentException("task heading must be less than 100 chars");
        }
    }
    /**
     * Method for adding new  items to the list
     * @param taskHeading title of the task
     * @param checked if the task is completed
     * @throws IllegalArgumentException when task heading > 100 chars
     */
    final public void addItem(String taskHeading,boolean checked, String dueDate, String taskTag, String taskPriority, TodoItemsAdapter mTodoItemsAdapter) throws IllegalArgumentException, ParseException {
        this.addItem(taskHeading, checked, dueDate, taskTag, taskPriority);
        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", taskHeading);
        taskValues.put("TASK_CHECKED", checked);
        taskValues.put("TASK_DATE", dueDate);
        taskValues.put("TASK_TAG", taskTag);
        taskValues.put("TASK_PRIORITY", taskPriority);
        toDoListDatabaseHelper.getWritableDatabase().insert("TASKS",null,  taskValues);
        mTodoItemsAdapter.notifyItemInserted(this.getLength() - 1);

    }

    final public void removeItem(Item item, TodoItemsAdapter mTodoItemsAdapter){
        int index = toDoListTasks.indexOf(item);
        toDoListTasks.remove(item);
        toDoListDatabaseHelper.getWritableDatabase().delete("TASKS", "TASK_NAME = ?", new String[] {item.getTaskHeading()});
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

}
