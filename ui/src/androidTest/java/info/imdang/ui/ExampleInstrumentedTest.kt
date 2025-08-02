package info.imdang.ui

import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import info.imdang.core.presentation.onboarding.enums.UserPurpose
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.ui.onboarding.Step0Screen

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class Step0ScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun step0Screen_initialState_noBtnSelected() {
        var selectedPurpose: UserPurpose? = null

        composeTestRule.setContent {
            ImdangAppNewTheme {
                Step0Screen { purpose -> selectedPurpose = purpose }
            }
        }

        composeTestRule.onNodeWithText("실거주").assertIsNotSelected()
        composeTestRule.onNodeWithText("갭투자").assertIsNotSelected()
        assertEquals(null,selectedPurpose)
    }

    @Test
    fun step0Screen_clickRealResidence_selected(){
        var selectedPurpose: UserPurpose? = null

        composeTestRule.setContent {
            ImdangAppNewTheme {
                Step0Screen { purpose -> selectedPurpose = purpose }
            }
        }

        composeTestRule.onNodeWithText("실거주").performClick()
        composeTestRule.onNodeWithText("실거주").assertIsSelected()
        composeTestRule.onNodeWithText("갭투자").assertIsNotSelected()
        composeTestRule.onNodeWithText("다음").performClick()
        assertEquals(UserPurpose.REAL_RESIDENCE,selectedPurpose)
    }
}