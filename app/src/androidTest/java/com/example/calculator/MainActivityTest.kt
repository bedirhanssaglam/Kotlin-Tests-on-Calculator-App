package com.example.calculator

import com.example.calculator.enums.ActionType
import org.junit.Test

/**
 * Test class for [MainActivity].
 */
class MainActivityTest : BaseMainActivityTest() {

    /**
     * Test method for digit button click.
     */
    @Test
    fun testOnDigit() {
        testOnAction(expectedResult = "1", buttonId = R.id.btnOne, actionType = ActionType.DIGIT)
    }

    /**
     * Test method for decimal point button click.
     */
    @Test
    fun testOnDecimalPoint() {
        testOnAction(expectedResult = ".", buttonId = R.id.btnDot, actionType = ActionType.DECIMAL_POINT)
    }

    /**
     * Test method for operator button click.
     */
    @Test
    fun testOnOperator() {
        testOnAction(expectedResult = "+", buttonId = R.id.btnAdd, actionType = ActionType.OPERATOR)
    }

    /**
     * Test method for addition calculation.
     */
    @Test
    fun performCalculation_Addition() {
        performCalculation(
            expectedResult = "3",
            buttonIds = listOf(R.id.btnOne, R.id.btnAdd, R.id.btnTwo, R.id.btnEqual)
        )
    }

    /**
     * Test method for multiplication calculation.
     */
    @Test
    fun performCalculation_Multiplication() {
        performCalculation(
            expectedResult = "6",
            buttonIds = listOf(R.id.btnTwo, R.id.btnMultiply, R.id.btnThree, R.id.btnEqual)
        )
    }

    /**
     * Test method for division calculation.
     */
    @Test
    fun performCalculation_Division() {
        performCalculation(
            expectedResult = "4",
            buttonIds = listOf(R.id.btnEight, R.id.btnDivide, R.id.btnTwo, R.id.btnEqual)
        )
    }

    /**
     * Test method for complex expression calculation.
     */
    @Test
    fun performCalculation_ComplexExpression() {
        performCalculation(
            expectedResult = "12.5",
            buttonIds = listOf(
                R.id.btnFive,
                R.id.btnDot,
                R.id.btnFive,
                R.id.btnMultiply,
                R.id.btnTwo,
                R.id.btnEqual,
                R.id.btnAdd,
                R.id.btnOne,
                R.id.btnDot,
                R.id.btnFive,
                R.id.btnEqual
            )
        )
    }
}
