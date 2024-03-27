package com.example.calculator.utils

import android.widget.EditText
import org.junit.Assert.assertEquals

/**
 * Utility class to assist in testing [EditText] input.
 */
object TestHelper {
     /**
     * Checks the text input of an EditText against the expected result.
     * @param expectedResult The expected result of the text input.
     * @param editText The EditText object whose text input is being checked.
     */
    fun <T : EditText> checkTextInput(expectedResult: String, editText: T) {
         // Get the actual result from the EditText
        val actualResult: String = editText.text.toString()

         // Assert that the actual result matches the expected result
        assertEquals(expectedResult, actualResult)
    }
}
