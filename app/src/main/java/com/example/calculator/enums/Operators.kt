package com.example.calculator.enums

/**
 * Enum representing the arithmetic operators used in the calculator.
 * Each operator is associated with its corresponding character representation.
 */
enum class Operators(val operator: Char) {
    PLUS('+'),     // Represents the addition operator (+).
    MINUS('-'),    // Represents the subtraction operator (-).
    MULTIPLY('*'), // Represents the multiplication operator (*).
    DIVIDE('/')    // Represents the division operator (/).
}
