package com.chrisvasqm.cuadramo

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.chrisvasqm.cuadramo.view.catalog.CatalogActivity
import org.junit.Before
import org.junit.Test

class CatalogActivityTests {

    @Before
    fun setUp() {
        InstrumentationRegistry.getInstrumentation().targetContext.deleteDatabase("database-cuadramo")
    }

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

    @Test
    fun catalog_CreateNewCuadre_IsDisplayed() {
        launchActivity<CatalogActivity>().use {
            createCuadre()

            onView(withId(R.id.catalogRecyclerView))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun catalog_ExistingCuadre_CanBeEdited() {
        launchActivity<CatalogActivity>().use {
            createCuadre()

            onView(withId(R.id.catalogRecyclerView))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                    )
                )

            onView(withText("Edit"))
                .perform(click())

            onView(withId(R.id.inputTicketsTotal))
                .perform(replaceText("3"))

            onView(withId(R.id.btnCuadrar))
                .perform(click())

            onView(withId(R.id.btnSave))
                .perform(click())

            onView(withText("100"))
                .check(matches(isDisplayed()))
        }
    }

    @Test
    fun catalog_ExistingCuadre_CanBeDeleted() {
        launchActivity<CatalogActivity>().use {
            createCuadre()

            onView(withId(R.id.catalogRecyclerView))
                .perform(
                    RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        click()
                    )
                )

            onView(withId(R.id.textDelete))
                .perform(click())

            onView(withText("DELETE"))
                .perform(click())

            onView(withId(R.id.imageViewEmpty))
                .check(matches(isDisplayed()))
        }
    }

    private fun createCuadre() {
        onView(withId(R.id.fabAdd))
            .perform(click())

        onView(withId(R.id.inputTicketsTotal))
            .perform(typeText("2"))

        onView(withId(R.id.inputTicketsLeft))
            .perform(typeText("1"), closeSoftKeyboard())

        onView(withId(R.id.btnCuadrar))
            .perform(click())

        onView(withId(R.id.btnSave))
            .perform(click())
    }

}