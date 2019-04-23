package com.example.thelkl321.angrymooseandroid;

import android.content.Intent;
import android.widget.TextView;

import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.thelkl321.angrymooseandroid.fight.StandardFightActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class StandardFightActivityTests {

    @Rule
    public IntentsTestRule<StandardFightActivity> standardFightActivityTestRule =
            new IntentsTestRule<>(StandardFightActivity.class, false, false);

    @Before
    public void setDifficultyData() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.MOOSE_KEY, 10).putExtra(MainActivity.PLAYER_KEY, 10);
        standardFightActivityTestRule.launchActivity(intent);
    }

    @Test
    public void all_views_display_properly() {
        onView(withId(R.id.middleText)).check(matches(isDisplayed()));
        onView(withId(R.id.mooseHealthBar)).check(matches(isDisplayed()));
        onView(withId(R.id.playerHealthBar)).check(matches(isDisplayed()));
        onView(withId(R.id.logText)).check(matches(isDisplayed()));
        onView(withId(R.id.attackButton)).check(matches(isDisplayed()));
        onView(withId(R.id.kickButton)).check(matches(isDisplayed()));
        onView(withId(R.id.leapButton)).check(matches(isDisplayed()));
        onView(withId(R.id.dodgeButton)).check(matches(isDisplayed()));
        onView(withId(R.id.throwButton)).check(matches(isDisplayed()));

        onView(withId(R.id.endgameFragment)).check(matches(not(isDisplayed())));
        onView(withId(R.id.outcomeText)).check(matches(not(isDisplayed())));
        onView(withId(R.id.lastEventText)).check(matches(not(isDisplayed())));
        onView(withId(R.id.turnText)).check(matches(not(isDisplayed())));
        onView(withId(R.id.retryButton)).check(matches(not(isDisplayed())));
        onView(withId(R.id.shareButton)).check(matches(not(isDisplayed())));
        onView(withId(R.id.backButton)).check(matches(not(isDisplayed())));
    }

    @Test
    public void attackButton_works() {
        int initialLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        onView(withId(R.id.attackButton)).perform(click());
        int newLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        assertTrue(newLength > initialLength);
    }

    @Test
    public void kickButton_works() {
        int initialLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        onView(withId(R.id.kickButton)).perform(click());
        int newLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        assertTrue(newLength > initialLength);
    }

    @Test
    public void leapButton_works() {
        int initialLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        onView(withId(R.id.leapButton)).perform(click());
        int newLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        assertTrue(newLength > initialLength);
    }

    @Test
    public void dodgeButton_works() {
        int initialLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        onView(withId(R.id.dodgeButton)).perform(click());
        int newLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        assertTrue(newLength > initialLength);
    }

    @Test
    public void throwButton_works() {
        int initialLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        onView(withId(R.id.throwButton)).perform(click());
        int newLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        assertTrue(newLength > initialLength);
    }

    @Test
    public void on_back_SurrenderDialogFragment_appears() {
        pressBack();
        onView(withText("Do you want to give up?")).check(matches(isDisplayed()));
    }

    @Test
    public void SurrenderDialogFragment_closes_on_negative() {
        pressBack();
        onView(withText("Do you want to give up?")).check(matches(isDisplayed()));
        onView(withId(android.R.id.button2)).perform(click());
        onView(withText("Do you want to give up?")).check(doesNotExist());
    }

    @Test
    public void SurrenderDialogFragment_closes_on_back() {
        pressBack();
        onView(withText("Do you want to give up?")).check(matches(isDisplayed()));
        pressBack();
        onView(withText("Do you want to give up?")).check(doesNotExist());
    }

    @Test
    public void surrender_is_an_option() {
        MainActivity.gamemode = Gamemode.STANDARD_GAMEMODE;
        pressBack();
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.endgameFragment)).check(matches(isDisplayed()));
        onView(withText("You ran away like a baby")).check(matches(isDisplayed()));
    }

    @Test
    public void endgameFragment_displays_properly() {
        MainActivity.gamemode = Gamemode.STANDARD_GAMEMODE;
        pressBack();
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.endgameFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.outcomeText)).check(matches(isDisplayed()));
        onView(withId(R.id.lastEventText)).check(matches(isDisplayed()));
        onView(withId(R.id.turnText)).check(matches(isDisplayed()));
        onView(withId(R.id.retryButton)).check(matches(isDisplayed()));
        onView(withId(R.id.shareButton)).check(matches(isDisplayed()));
        onView(withId(R.id.backButton)).check(matches(isDisplayed()));
    }

    @Test
    public void retryButton_works() {
        // Fill the log a bit
        onView(withId(R.id.attackButton)).perform(click());
        onView(withId(R.id.kickButton)).perform(click());
        onView(withId(R.id.leapButton)).perform(click());

        MainActivity.gamemode = Gamemode.STANDARD_GAMEMODE;
        pressBack();
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.endgameFragment)).check(matches(isDisplayed()));
        onView(withId(R.id.retryButton)).perform(click());
        onView(withId(R.id.endgameFragment)).check(matches(not(isDisplayed())));

        onView(withId(R.id.logText)).check(matches(withText(startsWith("\nA huge moose is standing in front of you"))));
        int logLength = ((TextView) standardFightActivityTestRule.getActivity().findViewById(R.id.logText)).getText().length();
        assertTrue(logLength > 60);
        assertTrue(logLength < 120);
    }

    @Test
    public void backButton_works() {
        MainActivity.gamemode = Gamemode.STANDARD_GAMEMODE;
        pressBack();
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.backButton)).perform(click());

        intended(hasComponent(MainActivity.class.getName()));
    }
}
