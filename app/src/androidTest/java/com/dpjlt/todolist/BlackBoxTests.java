package com.dpjlt.todolist;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(AndroidJUnit4.class)
@SmallTest
public class BlackBoxTests {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void initIntents() {
        Intents.init();
    }

    @Test
    public void changeActivity() {
        onView(withId(R.id.button)).perform(click());
        intended(hasComponent(AddEditItemActivity.class.getName()));
        Intents.release();
    }

    @Test
    public void editTaskHeaderText() {
        String taskHeader = "Test Task";
        onView(withId(R.id.button)).perform(click());
        intended(hasComponent(AddEditItemActivity.class.getName()));
        onView(withId(R.id.name))
                .perform(typeText(taskHeader), closeSoftKeyboard());
        onView(withId(R.id.name))
                .check(matches(withText(taskHeader)));
        Intents.release();
    }

}
