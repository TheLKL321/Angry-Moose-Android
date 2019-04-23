package com.example.thelkl321.angrymooseandroid;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.thelkl321.angrymooseandroid.fight.StandardFightActivity;
import com.example.thelkl321.angrymooseandroid.fight.TimeFightActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withTagValue;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTests {

    @Rule
    public IntentsTestRule<MainActivity> mainActivityTestRule =
            new IntentsTestRule<>(MainActivity.class, true, true);

    @Test
    public void all_views_display_properly() {
        onView(withId(R.id.titleText)).check(matches(isDisplayed()));
        onView(withId(R.id.playButton)).check(matches(isDisplayed()));
        onView(withId(R.id.optionsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.creditsButton)).check(matches(isDisplayed()));
        onView(withId(R.id.exitButton)).check(matches(isDisplayed()));
        onView(withId(R.id.fbButton)).check(matches(isDisplayed()));
        onView(withId(R.id.twitterButton)).check(matches(isDisplayed()));
        onView(withId(R.id.rateButton)).check(matches(isDisplayed()));
    }

    @Test
    public void playButton_click_opens_PlayFragment_back_closes() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.playFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.buttonPager)).check(matches(isDisplayed()));
        onView(withTagValue(is((Object) "easy"))).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.playFragment)).check(matches(not(isDisplayed())));
        onView(withId(R.id.buttonPager)).check(matches(not(isDisplayed())));
        onView(withTagValue(is((Object) "easy"))).check(matches(not(isDisplayed())));
    }

    @Test
    public void swipes_change_difficulty() throws InterruptedException {
        onView(withId(R.id.playButton)).perform(click());
        onView(withTagValue(is((Object) "easy"))).check(matches(isDisplayed()));

        onView(withId(R.id.buttonPager)).perform(swipeLeft());
        Thread.sleep(300);
        onView(withTagValue(is((Object) "normal"))).check(matches(isDisplayed()));
        onView(withTagValue(is((Object) "easy"))).check(matches(not(isDisplayed())));

        onView(withId(R.id.buttonPager)).perform(swipeLeft());
        Thread.sleep(200);
        onView(withTagValue(is((Object) "hard"))).check(matches(isDisplayed()));
        onView(withTagValue(is((Object) "normal"))).check(matches(not(isDisplayed())));

        onView(withId(R.id.buttonPager)).perform(swipeLeft());
        Thread.sleep(200);
        onView(withTagValue(is((Object) "impossible"))).check(matches(isDisplayed()));
        onView(withTagValue(is((Object) "hard"))).check(matches(not(isDisplayed())));

        onView(withId(R.id.buttonPager)).perform(swipeRight());
        Thread.sleep(200);
        onView(withTagValue(is((Object) "hard"))).check(matches(isDisplayed()));
        onView(withTagValue(is((Object) "impossible"))).check(matches(not(isDisplayed())));

        onView(withId(R.id.buttonPager)).perform(swipeRight());
        Thread.sleep(200);
        onView(withTagValue(is((Object) "normal"))).check(matches(isDisplayed()));
        onView(withTagValue(is((Object) "hard"))).check(matches(not(isDisplayed())));

        onView(withId(R.id.buttonPager)).perform(swipeRight());
        Thread.sleep(200);
        onView(withTagValue(is((Object) "easy"))).check(matches(isDisplayed()));
        onView(withTagValue(is((Object) "normal"))).check(matches(not(isDisplayed())));
    }

    @Test
    public void difficultyButton_click_launches_StandardFightActivity() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.buttonPager)).perform(click());

        intended(hasComponent(StandardFightActivity.class.getName()));
    }

    @Test
    public void timeButton_toggles_gamemode() {
        onView(withId(R.id.playButton)).perform(click());
        onView(withId(R.id.timeButton)).perform(click());
        onView(withId(R.id.buttonPager)).perform(click());

        intended(hasComponent(TimeFightActivity.class.getName()));
    }

    @Test
    public void optionsButton_click_opens_optionsFragment_back_closes() {
        onView(withId(R.id.optionsButton)).perform(click());
        onView(withId(R.id.optionsFragment)).check(matches(isDisplayed()));
        pressBack();
        onView(withId(R.id.optionsFragment)).check(matches(not(isDisplayed())));
    }

}