package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.calculator.enums.Operators
import com.example.calculator.enums.LastCharacterType
import com.example.calculator.databinding.ActivityMainBinding
import com.example.calculator.utils.calculateByOperator
import com.example.calculator.utils.containsOperator
import com.example.calculator.utils.removeTrailingZeros

/**
 * [MainActivity] class for the calculator app.
 */
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    private lateinit var numberInput: EditText

    /** Variable to hold the type of the last entered character */
    private var lastCharacterType: LastCharacterType = LastCharacterType.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Instantiate ActivityMainBinding and specify the root layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        numberInput = binding.numberInput

        // Set a click listener for the Clear (CLR) button.
        binding.btnClear.setOnClickListener {
            // Clear the EditText.
            numberInput.setText("")
            // Reset the last character type.
            lastCharacterType = LastCharacterType.NONE
        }
    }

    /**
     * Function to handle digit button clicks.
     */
    fun onDigit(view: View) {
        view as Button
        // Append the button's text to the EditText.
        binding.numberInput.append(view.text)
        // Set the last character type as numeric.
        lastCharacterType = LastCharacterType.NUMERIC
    }

    /**
     * Function to handle decimal point button clicks.
     */
    fun onDecimalPoint(view: View) {
        // If the last entered character is not a dot and is not numeric:
        if (lastCharacterType != LastCharacterType.DOT) {
            // Append a dot to the EditText.
            numberInput.append(".")
            // Set the last character type as a dot.
            lastCharacterType = LastCharacterType.DOT
        }
    }

    /**
     * Function to handle operator (+, -, /, *) button clicks.
     */
    fun onOperator(view: View) {
        numberInput.text?.let {
            // If the last entered character is not an operator and there are no operators in the text:
            if (lastCharacterType != LastCharacterType.OPERATOR && !it.toString()
                    .containsOperator()
            ) {
                // Append the button's text to the EditText.
                numberInput.append((view as Button).text)
                // Set the last character type as an operator.
                lastCharacterType = LastCharacterType.OPERATOR
            }
        }
    }

    /**
     * Function to handle the Equal (=) button click.
     */
    fun onEqual(view: View) {
        // If the last entered character is numeric:
        if (lastCharacterType == LastCharacterType.NUMERIC) {
            // Get the current value from the EditText.
            val currentValue: String = numberInput.text.toString()

            // Get the list of operators.
            val operators: List<Char> = Operators.entries.map { it.operator }

            // Find the index of the last operator. This helps us determine which operator to perform the operation with.
            val operatorIndex: Int = currentValue.indexOfLast { it in operators }

            if (operatorIndex >= 0) {
                // Get the last operator and the numbers before and after it.
                val operator: Char = currentValue[operatorIndex]

                // If an operator is found, split the numbers before and after the operator.
                // This splits the expression into two parts: the first number and the second number.
                var first: String = currentValue.substring(0, operatorIndex)
                var second: String = currentValue.substring(operatorIndex + 1)

                // If the first number is empty and the expression starts with a minus sign:
                if (first.isEmpty() && currentValue.startsWith("-")) {
                    first = "-"
                    second = currentValue.substring(2, operatorIndex + 1)
                }

                // Perform the operation with the two numbers and the operator, and write the result to the EditText.
                val result: Double = Pair(first, second) calculateByOperator operator

                numberInput.setText(result.toString().removeTrailingZeros())
            }
        } else {
            // If the last character is not numeric, show a warning message.
            Toast.makeText(this, R.string.enter_number_warning, Toast.LENGTH_SHORT).show()
        }
        // Reset the character type to NONE after the equal operation.
        lastCharacterType = LastCharacterType.NONE
    }
}