package com.example.calculator.enums

/**
 * Enum representing the type of the last character entered the calculator.
 * It can be numeric, a dot (decimal point), an operator, or none if no character has been entered yet.
 */
enum class LastCharacterType {
    NUMERIC, // Represents a numeric character.
    DOT,     // Represents a dot (decimal point).
    OPERATOR, // Represents an operator (+, -, *, /).
    NONE     // Represents no character entered yet.
}
