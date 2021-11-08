package com.dpjlt.todolist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public final class ToDoList {
    private class Item {
        private String taskHeading;

        private Item(String taskHeading){
            this.taskHeading = taskHeading;
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

    final public void addItem(String taskHeading){
        Item newItem = new Item(taskHeading);
        toDoListTasks.add(newItem);
    }

    final public void removeItem(Item item){
        toDoListTasks.remove(item);
    }

    final public int getLength(){
        return toDoListTasks.size();
    }

    final public Item getIndex(int index){
        return toDoListTasks.get(index);
    }

    public static void addTaskDB(SQLiteDatabase db, Item task) {
        ContentValues taskValues = new ContentValues();
        taskValues.put("TASK_NAME", task.getTaskHeading());
        db.insert("TASKS", null, taskValues);

    }

}
