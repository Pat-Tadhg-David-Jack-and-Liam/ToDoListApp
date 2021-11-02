package com.dpjlt.todolist;

import java.util.ArrayList;
import java.util.List;

public class ListADT {
    private class Item {
        private String taskHeading;

        private Item(String taskHeading){
            this.taskHeading = taskHeading;
        }
    }

    private List<Item> toDoListTasks;


    public ListADT(){
        toDoListTasks = new ArrayList<Item>();
    }

    public void addItem(String taskHeading){
        Item newItem = new Item(taskHeading);
        toDoListTasks.add(newItem);
    }

    public void removeItem(Item item){
        toDoListTasks.remove(item);
    }

    public int getLength(){
        return toDoListTasks.size();
    }

}
