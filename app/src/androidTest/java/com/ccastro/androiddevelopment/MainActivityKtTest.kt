package com.ccastro.androiddevelopment

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import com.ccastro.androiddevelopment.ui.theme.AndroidDevelopmentTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat


class MainActivityKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {

        lateinit var amountText: String
        lateinit var tipPercentText: String
        lateinit var resultText: String
        val expectedTip = NumberFormat.getCurrencyInstance().format(2)

        composeTestRule.setContent {
            AndroidDevelopmentTheme {
                TipTimeLayout()
                amountText = stringResource(R.string.bill_amount)
                tipPercentText = stringResource(id = R.string.how_was_the_service)
                resultText = String.format(stringResource(id = R.string.tip_amount), expectedTip)
            }
        }
        composeTestRule.onNodeWithText(amountText)
            .performTextInput("10")

        composeTestRule.onNodeWithText(tipPercentText)
            .performTextInput("20")

        composeTestRule.onNodeWithText(resultText).assertExists("No node with this text was found.")

    }
}