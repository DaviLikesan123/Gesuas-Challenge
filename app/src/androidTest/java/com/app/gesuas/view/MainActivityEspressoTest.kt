package com.app.gesuas.view


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.app.gesuas.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityEspressoTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun mainActivityEspressoTest() {
        val appCompatEditText = onView(
            allOf(withId(R.id.etName)))
        appCompatEditText.perform(scrollTo(), replaceText("David Lim"), closeSoftKeyboard())

        val appCompatEditText2 = onView(
            allOf(withId(R.id.etCpf)))
        appCompatEditText2.perform(scrollTo(), replaceText("12345678922"), closeSoftKeyboard())

        val appCompatEditText3 = onView(
            allOf(withId(R.id.etBirthDate)))
        appCompatEditText3.perform(scrollTo(), replaceText("16022001"), closeSoftKeyboard())

        val appCompatEditText4 = onView(
            allOf(withId(R.id.etPhone)))
        appCompatEditText4.perform(scrollTo(), replaceText("11983286221"), closeSoftKeyboard())

        val appCompatButton = onView(
            allOf(withId(R.id.btnRegister), withText("Cadastrar")))
        appCompatButton.perform(scrollTo(), click())

        val appCompatButton2 = onView(
            allOf(withId(R.id.btnRegister), withText("Continuar")))
        appCompatButton2.perform(scrollTo(), click())

        val appCompatButton3 = onView(
            allOf(withId(R.id.btnFollow), withText("Encaminhar para")))
        appCompatButton3.perform(click())

        val appCompatButton4 = onView(
            allOf(withId(R.id.btnSave), withText("Salvar")))
        appCompatButton4.perform(click())

        val appCompatButton5 = onView(
            allOf(withId(R.id.btnReason), withText("Motivo"))
        )
        appCompatButton5.perform(scrollTo(), click())

        val appCompatEditText5 = onView(
            allOf(withId(R.id.typeReason))
        )
        appCompatEditText5.perform(replaceText("O motivo do encaminhamento é que preciso de ajuda e vocês podem me ajudar!"), closeSoftKeyboard())

        val appCompatTextView = onView(
            allOf(withId(R.id.save), withText("Salvar"))
        )
        appCompatTextView.perform(click())

        val appCompatButton6 = onView(
            allOf(
                withId(R.id.btnContinue), withText("Acompanhamento"),
                childAtPosition(
                    allOf(
                        withId(R.id.principalConstraint),
                        childAtPosition(
                            withId(R.id.scrollView),
                            0
                        )
                    ),
                    10
                )
            )
        )
        appCompatButton6.perform(scrollTo(), click())

        val appCompatButton7 = onView(
            allOf(
                withId(R.id.btnConfirm), withText("Confirmar"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton7.perform(click())

        val appCompatButton8 = onView(
            allOf(
                withId(R.id.btnConfirm), withText("Voltar para cadastro"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatButton8.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
