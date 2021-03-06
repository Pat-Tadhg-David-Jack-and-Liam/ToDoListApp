package com.dpjlt.todolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
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


    public ToDoList(ToDoListSQLiteHelper toDoListDatabaseHelper, String table){
        this.toDoListDatabaseHelper = toDoListDatabaseHelper;
        toDoListTasks = new ArrayList<>();
        SQLiteDatabase dbRead = toDoListDatabaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.query(table, new String[] {"_id", "TASK_NAME", "TASK_CHECKED, TASK_DATE", "TASK_TAG", "TASK_PRIORITY"},
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
     * @
     * @throws IllegalArgumentException when task heading > 100 chars
     */
    final public Item addItem(String taskHeading, boolean checked, String dueDate, String taskTag, String taskPriority) throws IllegalArgumentException{
        if(taskHeading.length() < 100) {
            Item newItem = new Item(taskHeading, checked, dueDate, taskTag, taskPriority);
            toDoListTasks.add(newItem);
            return newItem;
        } else {
            throw new IllegalArgumentException("task heading must be less than 100 chars");
        }


    }

    final public void addItem(Item item){
        toDoListTasks.add(item);
    }


    /**
     * Method for adding new  items to the list
     * @param taskHeading title of the task
     * @param checked if the task is completed
     * @throws IllegalArgumentException when task heading > 100 chars
     */
    final public Item addItem(String taskHeading,boolean checked, String dueDate, String taskTag, String taskPriority, TodoItemsAdapter mTodoItemsAdapter) throws IllegalArgumentException, ParseException {
        Item newItem = this.addItem(taskHeading, checked, dueDate, taskTag, taskPriority);
        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", taskHeading);
        taskValues.put("TASK_CHECKED", checked);
        taskValues.put("TASK_DATE", dueDate);
        taskValues.put("TASK_TAG", taskTag);
        taskValues.put("TASK_PRIORITY", taskPriority);
        toDoListDatabaseHelper.getWritableDatabase().insert("TASKS",null,  taskValues);
        mTodoItemsAdapter.notifyItemInserted(this.getLength() - 1);
        return newItem;

    }

    final public void removeItem(Item item, TodoItemsAdapter mTodoItemsAdapter, TodoItemsAdapterArchive aTodoItemsAdapter){
        int index = toDoListTasks.indexOf(item);
        toDoListTasks.remove(item);
       // String SQL = String.format("INSERT INTO ARCHIVE SELECT * FROM TASKS WHERE TASK_NAME = '%s';", item.getTaskHeading() );
       // toDoListDatabaseHelper.getWritableDatabase().rawQuery("INSERT INTO ARCHIVE SELECT * FROM TASKS WHERE TASK_NAME = ? AND TASK_TAG = ?;", new String[] {item.getTaskHeading(), item.getTag()});

        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", item.getTaskHeading());
        taskValues.put("TASK_CHECKED", item.getChecked());
        taskValues.put("TASK_DATE", item.getDueDate());
        taskValues.put("TASK_TAG", item.getTag());
        taskValues.put("TASK_PRIORITY", item.getPriority());
        toDoListDatabaseHelper.getWritableDatabase().insert("ARCHIVE",null,taskValues);




        toDoListDatabaseHelper.getWritableDatabase().delete("TASKS", "TASK_NAME = ?", new String[] {item.getTaskHeading()});
        mTodoItemsAdapter.notifyItemRemoved(index);
        AppLaunch.getToDoListArchive().addItem(item);
        aTodoItemsAdapter.notifyItemInserted(AppLaunch.getToDoListArchive().getLength() - 1);
    }





    final public Item removeItem(Item item,     TodoItemsAdapterArchive aTodoItemsAdapter){
        int index = toDoListTasks.indexOf(item);
        toDoListTasks.remove(item);
        toDoListDatabaseHelper.getWritableDatabase().delete("ARCHIVE", "TASK_NAME = ?", new String[] {item.getTaskHeading()});
        aTodoItemsAdapter.notifyItemRemoved(index);
        return item;
    }

    final public void removeItemSortActive(){
        for(Iterator<Item> iterator = toDoListTasks.iterator(); iterator.hasNext();){
            Item item = iterator.next();
            int index = toDoListTasks.indexOf(item);
            iterator.remove();
            MainActivity.mTodoListAdapter.notifyItemRemoved(index);
        }
    }

    final public void removeItemSortArchive(){
        for(Iterator<Item> iterator = toDoListTasks.iterator(); iterator.hasNext();){
            Item item = iterator.next();
            int index = toDoListTasks.indexOf(item);
            iterator.remove();
            ArchivedTasks.aTodoListAdapter.notifyItemRemoved(index);
        }
    }

    final public void sortByTagActive(){
        SQLiteDatabase dbRead = toDoListDatabaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.rawQuery("SELECT * FROM TASKS ORDER BY TASK_TAG", new String[] {} );
        addTasksFromDB(cursor);
        cursor.close();
        MainActivity.mTodoListAdapter.notifyDataSetChanged();
    }

    final public void sortByPriorityActive(){
        SQLiteDatabase dbRead = toDoListDatabaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.rawQuery("SELECT * FROM TASKS ORDER BY (CASE TASK_PRIORITY WHEN 'High' THEN 1 WHEN 'Medium' THEN 2 WHEN 'Low' THEN 3 WHEN 'No Priority' THEN 4 END)", new String[] {} );
                //dbRead.rawQuery("SELECT * FROM TASKS ORDER BY FIELD(TASK_TAG, 'High', 'Medium', 'Low')", new String[] {} );
        addTasksFromDB(cursor);
        cursor.close();
        MainActivity.mTodoListAdapter.notifyDataSetChanged();
    }

    // ORDER BY FIELD(TASK_TAG, 'High', 'Medium', 'Low')

    final public void sortByTagArchive(){
        SQLiteDatabase dbRead = toDoListDatabaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.rawQuery("SELECT * FROM ARCHIVE ORDER BY TASK_TAG", new String[] {} );
        addTasksFromDB(cursor);
        cursor.close();
        ArchivedTasks.aTodoListAdapter.notifyDataSetChanged();
    }

    final public void sortByPriorityArchive(){
        SQLiteDatabase dbRead = toDoListDatabaseHelper.getReadableDatabase();
        Cursor cursor = dbRead.rawQuery("SELECT * FROM ARCHIVE ORDER BY (CASE TASK_PRIORITY WHEN 'High' THEN 1 WHEN 'Medium' THEN 2 WHEN 'Low' THEN 3 WHEN 'No Priority' THEN 4 END)", new String[] {} );
        addTasksFromDB(cursor);
        cursor.close();
        ArchivedTasks.aTodoListAdapter.notifyDataSetChanged();
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
