package com.ccastro.androiddevelopment

import org.junit.Assert.assertEquals
import java.text.NumberFormat

class MainActivityKtTest {

    @org.junit.Test
    fun calculateTip_20PercentNoRoundUp() {
        val amount = 10.00
        val tipPercent = 20.00
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        val actualTip = calculateTip(amount, tipPercent, false)

        assertEquals(expectedTip, actualTip)

    }
}