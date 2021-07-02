package ru.ildus.translator

import android.content.Context
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import ru.ildus.translator.view.main.MainActivity
import ru.ildus.translator.view.main.SearchDialogFragment
import ru.ildus.translator.view.main.adapter.MainAdapter

@RunWith(AndroidJUnit4::class)
class MainActivityRecyclerTest {
    private lateinit var scenario: ActivityScenario<MainActivity>
    private val uiDevice: UiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val context = ApplicationProvider.getApplicationContext<Context>()
    private val packageName = context.packageName

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun activitySearch_ScrollTo() {

            loadList()
        uiDevice.wait(
            Until.findObject(By.res(packageName, "totalCountTextView")),
            TIMEOUT
        )
            Espresso.onView(withId(R.id.main_activity_recyclerview))
                .perform(
                    RecyclerViewActions.scrollTo<MainAdapter.RecyclerItemViewHolder>(
                        ViewMatchers.hasDescendant(ViewMatchers.withText("рентген"))
                    )
                )

    }
    //клик на элемент списка, всплывает Toast слова
    @Test
    fun activitySearch_PerformClickAtPosition() {

        loadList()
        uiDevice.wait(
            Until.findObject(By.res(packageName, "totalCountTextView")),
            TIMEOUT
        )
        Espresso.onView(withId(R.id.main_activity_recyclerview))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<MainAdapter.RecyclerItemViewHolder>(
                    5,
                    ViewActions.click()
                )
            )

    }

    private fun loadList() {
        Espresso.onView(withId(R.id.search_fab)).perform(ViewActions.click())
        Espresso.onView(withId(R.id.search_edit_text))
            .perform(ViewActions.replaceText("x"), ViewActions.closeSoftKeyboard())
        Espresso.onView(withId(R.id.search_edit_text)).perform(ViewActions.pressImeActionButton())
        Espresso.onView(withId(R.id.search_button_textview)).perform(ViewActions.click())
    }



    @After
    fun close() {
        scenario.close()
    }
    companion object {
        private const val TIMEOUT = 5000L
    }
}