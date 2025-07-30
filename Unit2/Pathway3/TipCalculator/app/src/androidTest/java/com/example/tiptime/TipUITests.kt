package com.example.tiptime

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import com.example.tiptime.ui.theme.TipTimeTheme
import org.junit.Rule
import org.junit.Test
import java.text.NumberFormat

class TipUITests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    TipTimeLayout()
                }
            }
        }
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val billAmountText = context.getString(R.string.bill_amount)
        val tipPercentageText = context.getString(R.string.how_was_the_service)

        composeTestRule.onNodeWithText(billAmountText)
            .performTextInput("10.0")
        composeTestRule.onNodeWithText(tipPercentageText)
            .performTextInput("20.0")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2.0)
        composeTestRule.onNodeWithText("Tip Amount: $expectedTip")
            .assertExists(
                "No node with this text was found."
            )
    }
}