package bt.edu.todo_17;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import bt.edu.todo_17.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction appCompatImageButton = onView(
allOf(withId(R.id.increaseTeam1), withContentDescription("Plus Button"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
0),
1),
isDisplayed()));
        appCompatImageButton.perform(click());
        
        ViewInteraction textView = onView(
allOf(withId(R.id.score_1), withText("1"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
isDisplayed()));
        textView.check(matches(withText("1")));
        
        ViewInteraction appCompatImageButton2 = onView(
allOf(withId(R.id.decreaseTeam1), withContentDescription("Minus Button"),
childAtPosition(
childAtPosition(
withClassName(is("android.widget.LinearLayout")),
0),
0),
isDisplayed()));
        appCompatImageButton2.perform(click());
        
        ViewInteraction textView2 = onView(
allOf(withId(R.id.score_1), withText("0"),
withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class))),
isDisplayed()));
        textView2.check(matches(withText("0")));
        }
    
    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup)parent).getChildAt(position));
            }
        };
    }
    }
