package com.example.calculator

import com.example.calculator.utils.calculateByOperator
import com.example.calculator.utils.containsOperator
import com.example.calculator.utils.removeTrailingZeros
import org.junit.Test
import org.junit.Assert.assertEquals

class CalculatorUtilsTest {

    @Test
    fun testRemoveZeroAfterDot() {
        assertEquals("10", "10.0".removeTrailingZeros())
        assertEquals("3.14159", "3.14159".removeTrailingZeros())
        assertEquals("-5", "-5.0".removeTrailingZeros())
        assertEquals("1000", "1000".removeTrailingZeros())
    }

    @Test
    fun testIsOperatorAdded() {
        assertEquals(true, "5+3".containsOperator())
        assertEquals(true, "10-2".containsOperator())
        assertEquals(true, "4*6".containsOperator())
        assertEquals(true, "8/2".containsOperator())
        assertEquals(false, "-5".containsOperator())
        assertEquals(false, "100".containsOperator())
        assertEquals(false, "3.14".containsOperator())
    }

    @Test
    fun testCalculateByOperator() {
        assertEquals(8.0, Pair("5", "3") calculateByOperator '+', 0.001)
        assertEquals(5.0, Pair("10", "5") calculateByOperator '-', 0.001)
        assertEquals(24.0, Pair("4", "6") calculateByOperator '*', 0.001)
        assertEquals(4.0, Pair("8", "2") calculateByOperator '/', 0.001)
        assertEquals(-8.0, Pair("-5", "-3") calculateByOperator '+', 0.001)
        assertEquals(105.0, Pair("100", "5") calculateByOperator '+', 0.001)
    }
}
