package com.chrisvasqm.cuadramo

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.chrisvasqm.cuadramo.view.catalog.CatalogActivity
import org.junit.Test

class CatalogActivityTests {

    @Test
    fun catalogActivity_WithOutItems_EmptyViewIsDisplayed() {
        launchActivity<CatalogActivity>().use {
            onView(withId(R.id.fabAdd)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun catalog_canNavigateTo_AboutScreen() {
        launchActivity<CatalogActivity>().use {
            openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getInstrumentation().targetContext)

            onView(withText("About"))
                .perform(click())

            onView(withId(R.id.textVersionNumber))
                .check(matches(isDisplayed()))
        }
    }
}