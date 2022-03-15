package com.chrisvasqm.cuadramo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chrisvasqm.cuadramo.data.local.AppDatabase
import com.chrisvasqm.cuadramo.data.local.CuadreDao
import com.chrisvasqm.cuadramo.data.local.CuadreEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CuadreDatabaseTests {

    private lateinit var db: AppDatabase
    private lateinit var dao: CuadreDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).build()
        dao = db.getCuadreDao()
    }

    @Test
    fun getAll_NoDataInsertedYet_ReturnsEmpty() = runBlocking {
        val isEmpty = dao.getAll().isEmpty()
        assert(isEmpty)
    }

    @Test
    fun getAll_WhenNotEmpty_ReturnsTrue() = runBlocking {
        val cuadre = CuadreEntity()

        dao.insert(cuadre)
        val isNotEmpty = dao.getAll().isNotEmpty()

        assert(isNotEmpty)
    }

    @After
    fun tearDown() {
        db.close()
    }

}