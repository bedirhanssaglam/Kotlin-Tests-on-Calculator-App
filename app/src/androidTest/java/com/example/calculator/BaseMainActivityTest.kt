package com.example.calculator

import android.widget.Button
import android.widget.EditText
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.calculator.enums.ActionType
import com.example.calculator.utils.TestHelper
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith

/**
 * Base test class containing common methods and setup/teardown for tests related to [MainActivity] for Calculator App.
 * This class is marked with the [AndroidJUnit4] test runner, indicating that the test class will run in an Android environment.
 * The [LargeTest] annotation indicates that the tests in this class are large, meaning they may involve complex interactions or dependencies.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
abstract class BaseMainActivityTest {
    private lateinit var activityScenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        // Launch MainActivity for each test
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        // Close the activity after each test
        activityScenario.close()
    }


    /**
     * Method to perform an action (such as clicking a button) and check the resulting text input.
     * @param action The action to perform, represented as a lambda function on MainActivity.
     * @param expectedResult The expected result after performing the action.
     */
    private fun performActionAndCheckTextInput(action: MainActivity.() -> Unit, expectedResult: String) {
        activityScenario.onActivity {
            // Perform the action on MainActivity
            it.action()

            // Check the text input for the expected result using TestHelper class
            TestHelper.checkTextInput<EditText>(expectedResult, it.binding.numberInput)
        }
    }

    /**
     * Method to test an action (such as clicking a button) on [MainActivity].
     * @param expectedResult The expected result after performing the action.
     * @param buttonId The resource ID of the button to click.
     * @param actionType The type of action to perform (e.g., DIGIT, DECIMAL_POINT, OPERATOR).
     */
    fun testOnAction(expectedResult: String, buttonId: Int, actionType: ActionType) {
        val action: MainActivity.() -> Unit = when (actionType) {
            ActionType.DIGIT -> {
                { onDigit(findViewById<Button>(buttonId)) }
            }

            ActionType.DECIMAL_POINT -> {
                { onDecimalPoint(findViewById<Button>(buttonId)) }
            }

            ActionType.OPERATOR -> {
                { onOperator(findViewById<Button>(buttonId)) }
            }
        }
        // Perform the action and check the resulting text input
        performActionAndCheckTextInput(action, expectedResult)
    }

    /**
     * Method to perform a calculation by clicking a sequence of buttons and verify the result.
     * @param expectedResult The expected result of the calculation.
     * @param buttonIds The resource IDs of the buttons to click in sequence to perform the calculation.
     */
    fun performCalculation(expectedResult: String, buttonIds: List<Int>) {
        buttonIds.forEach { buttonId: Int ->
            // Click each button in the sequence
            onView(withId(buttonId)).perform(click())
        }
        // Verify the result by checking the text input
        onView(withId(R.id.numberInput)).check(matches(withText(expectedResult)))
    }


}
