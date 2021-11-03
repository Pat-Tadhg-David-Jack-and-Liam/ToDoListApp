package com.dpjlt.todolist;

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

}
