package clertonleal.com.nubank.tests.android_tests;

import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.widget.TextView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import clertonleal.com.nubank.R;
import clertonleal.com.nubank.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BillActivityTest {

    public static final String PAGE_1 = "0";
    public static final String PAGE_2 = "1";
    public static final String PAGE_3 = "2";
    public static final String PAGE_4 = "3";

    @Rule
    public static final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void test_overdue_screen() {
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_1))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_1))).check(matches(hasBackgroundColor(R.color.green)));
        onView(allOf(withId(R.id.tab_name), withContentDescription(PAGE_1))).check(matches(hasTextColor(R.color.green)));
        onView(allOf(withId(R.id.paid_block), withContentDescription(PAGE_1))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.closed_block), withContentDescription(PAGE_1))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.btn_generate_billet), withContentDescription(PAGE_1))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.list), withContentDescription(PAGE_1))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.bill_value), withContentDescription(PAGE_1))).check(matches(withText("R$ 389,33")));
        onView(allOf(withId(R.id.bill_due_date), withContentDescription(PAGE_1))).check(matches(withText("Vencimento 20 ABR")));
        onView(allOf(withId(R.id.bill_message), withContentDescription(PAGE_1))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.paid_value), withContentDescription(PAGE_1))).check(matches(withText("R$ 389,33")));
        onView(allOf(withId(R.id.list), withContentDescription(PAGE_1))).check(matches(hasRows(4)));
    }

    @Test
    public void test_closed_screen() {
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_1))).perform(swipeLeft());
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_2))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_2))).check(matches(hasBackgroundColor(R.color.red)));
        onView(allOf(withId(R.id.tab_name), withContentDescription(PAGE_2))).check(matches(hasTextColor(R.color.red)));
        onView(allOf(withId(R.id.paid_block), withContentDescription(PAGE_2))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.closed_block), withContentDescription(PAGE_2))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.btn_generate_billet), withContentDescription(PAGE_2))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.list), withContentDescription(PAGE_2))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.bill_value), withContentDescription(PAGE_2))).check(matches(withText("R$ 1.743,26")));
        onView(allOf(withId(R.id.bill_due_date), withContentDescription(PAGE_2))).check(matches(withText("Vencimento 20 MAI")));
        onView(allOf(withId(R.id.bill_message), withContentDescription(PAGE_2))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.past_balance), withContentDescription(PAGE_2))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.interest), withContentDescription(PAGE_2))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.value_spent), withContentDescription(PAGE_2))).check(matches(withText("R$ 1.743,26")));
        onView(allOf(withId(R.id.list), withContentDescription(PAGE_2))).check(matches(hasRows(23)));
    }

    @Test
    public void test_open_screen() {
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_1))).perform(swipeLeft());
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_2))).perform(swipeLeft());
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_3))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_3))).check(matches(hasBackgroundColor(R.color.blue)));
        onView(allOf(withId(R.id.tab_name), withContentDescription(PAGE_3))).check(matches(hasTextColor(R.color.blue)));
        onView(allOf(withId(R.id.paid_block), withContentDescription(PAGE_3))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.closed_block), withContentDescription(PAGE_3))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.btn_generate_billet), withContentDescription(PAGE_3))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.list), withContentDescription(PAGE_3))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.bill_value), withContentDescription(PAGE_3))).check(matches(withText("R$ 396,27")));
        onView(allOf(withId(R.id.bill_due_date), withContentDescription(PAGE_3))).check(matches(withText("Vencimento 20 JUN")));
        onView(allOf(withId(R.id.bill_message), withContentDescription(PAGE_3))).check(matches(withText("Fechamento em 05 DE JUNHO")));
        onView(allOf(withId(R.id.list), withContentDescription(PAGE_3))).check(matches(hasRows(7)));
    }

    @Test
    public void test_future_screen() {
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_1))).perform(swipeLeft());
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_2))).perform(swipeLeft());
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_3))).perform(swipeLeft());
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_4))).check(matches(isDisplayed()));
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_4))).check(matches(hasBackgroundColor(R.color.orange)));
        onView(allOf(withId(R.id.tab_name), withContentDescription(PAGE_4))).check(matches(hasTextColor(R.color.orange)));
        onView(allOf(withId(R.id.paid_block), withContentDescription(PAGE_4))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.closed_block), withContentDescription(PAGE_4))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.btn_generate_billet), withContentDescription(PAGE_4))).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.list), withContentDescription(PAGE_4))).check(matches(isDisplayed()));

        onView(allOf(withId(R.id.bill_value), withContentDescription(PAGE_4))).check(matches(withText("R$ 19,54")));
        onView(allOf(withId(R.id.bill_due_date), withContentDescription(PAGE_4))).check(matches(withText("Vencimento 20 JUL")));
        onView(allOf(withId(R.id.bill_message), withContentDescription(PAGE_4))).check(matches(withText("FATURA PARCIAL")));
        onView(allOf(withId(R.id.list), withContentDescription(PAGE_4))).check(matches(hasRows(1)));

        onView(allOf(withId(R.id.header), withContentDescription(PAGE_4))).perform(swipeLeft());
        onView(allOf(withId(R.id.header), withContentDescription(PAGE_4))).check(matches(isDisplayed()));
    }

    public static Matcher<View> hasTextColor(@ColorRes int color) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("is displayed on the screen to the user");
            }

            @Override
            public boolean matchesSafely(View view) {
                return ((TextView) view).getCurrentTextColor() == view.getResources().getColor(color);
            }
        };
    }

    public static Matcher<View> hasBackgroundColor(@ColorRes int color) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("is displayed on the screen to the user");
            }

            @Override
            public boolean matchesSafely(View view) {
                ColorDrawable localColor = (ColorDrawable) view.getBackground();
                return localColor.getColor() == view.getResources().getColor(color);
            }
        };
    }

    public static Matcher<View> hasRows(int numberOfRows) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("is displayed on the screen to the user");
            }

            @Override
            public boolean matchesSafely(View view) {
                return ((RecyclerView) view).getAdapter().getItemCount() == numberOfRows;
            }
        };
    }

}
