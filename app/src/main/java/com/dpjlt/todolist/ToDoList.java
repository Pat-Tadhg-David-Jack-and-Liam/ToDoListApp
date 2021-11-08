package com.dpjlt.todolist;

import java.util.ArrayList;
import java.util.List;

public final class ToDoList {
    public class Item {
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
