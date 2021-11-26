package com.dpjlt.todolist;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class BlackBoxTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void changeActivity() {
        Intents.init();
        onView(withId(R.id.button)).perform(click());
        intended(hasComponent(AddEditItemActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void editTaskHeaderText() {
        String taskHeader = "Test Task";
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.name))
                .perform(typeText(taskHeader), closeSoftKeyboard());
        onView(withId(R.id.name))
                .check(matches(withText(taskHeader)));

    }
    @Test
    public void editTaskDueDate() {
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.due_date)).perform(click());
        onView(withId(android.R.id.button3)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(1900, 10, 28));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.due_date)).check(matches(withText(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)+"/"+ (Calendar.getInstance().get(Calendar.MONTH)+1)+"/"+Calendar.getInstance().get(Calendar.YEAR))));
    }

    }


