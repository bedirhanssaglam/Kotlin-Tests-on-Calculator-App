package com.example.calculator.utils

import com.example.calculator.constants.CalculatorConstants
import com.example.calculator.enums.Operators

typealias NumericPair = Pair<String, String>

/** Removes trailing zeros after the decimal point for the given [String]. Example: 3.00 -> 3 */
fun String.removeTrailingZeros(): String = if (contains(".0")) substring(0, length - 2) else this

/** Checks if the [String] contains any arithmetic operators.
 * We check if the string starts with "-" to determine if it is a negative number.
 * If the string starts with a negative sign, there is no need to look for operators.
 * Otherwise, we check if any of the characters in the string are arithmetic operators ('/', '*', '+', '-').
 */
fun String.containsOperator(): Boolean =
    !startsWith("-") && any { it: Char -> it in Operators.entries.map { it.operator } }

/**
 * Performs an arithmetic operation on the given two string values and returns the result.
 * @param operator The arithmetic operator (+, -, *, /)
 * @return The result of the operation
 */
infix fun NumericPair.calculateByOperator(operator: Char): Double {
    val (first: String, second: String) = this // Extracts variables from Pair.

    // Converts the strings 'first' and 'second' into Double values.
    val firstValue: Double =
        if (first.isEmpty() && first.startsWith("-")) CalculatorConstants.DEFAULT_NEGATIVE_VALUE else first.toDouble()
    val secondValue: Double = second.toDouble()

    // Performs the operation based on the operator type and returns the result.
    return when (operator) {
        Operators.PLUS.operator -> firstValue + secondValue
        Operators.MINUS.operator -> firstValue - secondValue
        Operators.MULTIPLY.operator -> firstValue * secondValue
        Operators.DIVIDE.operator -> firstValue / secondValue
        else -> CalculatorConstants.DEFAULT_RESULT_VALUE
    }
}
