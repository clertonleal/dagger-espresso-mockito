package clertonleal.com.nubank.tests.android_tests;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import clertonleal.com.nubank.R;
import clertonleal.com.nubank.activity.MainActivity;
import clertonleal.com.nubank.application.TestApplication;
import clertonleal.com.nubank.dagger.TestComponent;
import clertonleal.com.nubank.service.ConnectionService;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class OfflineActivityTest {

    @Inject
    ConnectionService connectionService;

    public OfflineActivityTest() {
        ((TestComponent) TestApplication.getComponent()).inject(this);
    }

    @Rule
    public static final ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() {
        when(connectionService.hasConnection()).thenReturn(false);
    }

    @Test
    public void test_offline_screen() {
        activityRule.launchActivity(new Intent());
        onView(withId(R.id.layout_empty_view)).check(matches(isDisplayed()));
        onView(withText(R.string.no_internet)).check(matches(isDisplayed()));
        onView(withId(R.id.image_refresh)).check(matches(isDisplayed()));
    }

    @Test
    public void test_should_reload_bills() {
        test_offline_screen();
        when(connectionService.hasConnection()).thenReturn(true);
        onView(withId(R.id.image_refresh)).perform(click());
        onView(withId(R.id.layout_empty_view)).check(matches(not(isDisplayed())));
        onView(allOf(withId(R.id.header), withContentDescription("0"))).check(matches(isDisplayed()));
    }

    @After
    public void teardown() {
        when(connectionService.hasConnection()).thenReturn(true);
    }
}
