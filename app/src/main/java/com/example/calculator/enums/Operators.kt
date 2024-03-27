package com.example.calculator.enums

/**
 * Enum representing the arithmetic operators used in the calculator.
 * Each operator is associated with its corresponding character representation.
 */
enum class Operators(val operator: Char) {
    PLUS(operator = '+'),      // Represents the addition operator (+).
    MINUS(operator = '-'),     // Represents the subtraction operator (-).
    MULTIPLY(operator = '*'),  // Represents the multiplication operator (*).
    DIVIDE(operator = '/')    // Represents the division operator (/).
}
