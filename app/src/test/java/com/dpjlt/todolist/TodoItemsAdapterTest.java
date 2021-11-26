package com.dpjlt.todolist;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;


public class TodoItemsAdapterTest {
    TodoItemsAdapter todoItemsAdapter;
    MockedStatic mockedInflator;
    MockedStatic mockedAppLaunch;


    @Before
    public void setUp(){
        todoItemsAdapter = new TodoItemsAdapter();
        mockedInflator = mockStatic(LayoutInflater.class);
        mockedAppLaunch = mockStatic(AppLaunch.class);
    }
    @After
    public void tearDown(){
        mockedInflator.close();
        mockedAppLaunch.close();
    }


    @Test
    public void stuff(){
        todoItemsAdapter.stuff();
    }

    @Test
    public void onCreateViewHolder() {
        ViewGroup mockParent = mock(ViewGroup.class);
        Context mockContext = mock(Context.class);
        LayoutInflater mockInflater = mock(LayoutInflater.class);
        View mockItemView = mock(View.class);
        CheckBox mockCheckBox = mock(CheckBox.class);
        Button mockButton = mock(Button.class);
        ToDoList mockToDoList = mock(ToDoList.class);


        int checkId = 2131230834;
        int removeButtonId = 2131231057;


        doReturn(mockContext).when(mockParent).getContext();
        when(LayoutInflater.from(mockContext)).thenReturn(mockInflater);
        doReturn(mockItemView).when(mockInflater).inflate(anyInt(), eq(mockParent), eq(false));

        doReturn(mockButton).when(mockItemView).findViewById(removeButtonId);
        doReturn(mockCheckBox).when(mockItemView).findViewById(checkId);
        doNothing().when(mockItemView).setOnClickListener(any());
        doNothing().when(mockToDoList).removeItem(any(), any(), any());
        when(AppLaunch.getToDoListActive()).thenReturn(mockToDoList);

        TodoItemsAdapter.ViewHolder viewHolder = todoItemsAdapter.onCreateViewHolder(mockParent, 0);

        assert viewHolder != null;
        assert viewHolder instanceof TodoItemsAdapter.ViewHolder;
    }

}