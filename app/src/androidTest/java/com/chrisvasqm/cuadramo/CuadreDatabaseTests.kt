package com.chrisvasqm.cuadramo

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.chrisvasqm.cuadramo.data.local.AppDatabase
import com.chrisvasqm.cuadramo.data.local.CuadreDao
import com.chrisvasqm.cuadramo.data.local.CuadreEntity
import com.google.common.truth.Truth.*
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
    fun getAll_WhenDatabaseIsEmpty_IsNotEmpty() = runBlocking {
        val cuadre = CuadreEntity()

        dao.insert(cuadre)
        val isNotEmpty = dao.getAll().isNotEmpty()

        assert(isNotEmpty)
    }

    @Test
    fun find_NonExistingData_ReturnsNoError(): Unit = runBlocking {
        dao.find(1)
    }

    @Test
    fun find_ExistingData_ReturnsData() = runBlocking {
        val id = 1L
        val cuadre = CuadreEntity(id = id)
        dao.insert(cuadre)

        val actual = dao.find(id)

        assertThat(actual).isNotNull()
        assertThat(actual).isInstanceOf(CuadreEntity::class.java)
    }

    @Test
    fun insert_NewData_IsInserted() = runBlocking {
        val cuadre = CuadreEntity(id = 1)
        dao.insert(cuadre)

        assertThat(dao.getAll()).isNotEmpty()
    }

    @Test
    fun insert_ExistingData_GetsReplaced() = runBlocking {
        val cuadre = CuadreEntity(id = 1)
        dao.insert(cuadre)

        val expected = 100
        cuadre.cash = expected
        dao.insert(cuadre)
        val result = dao.find(1)

        assertThat(result.cash).isEqualTo(expected)
    }

    @Test
    fun update_ExistingData_ReturnsUpdatedData() = runBlocking {
        val id = 1L
        val cuadre = CuadreEntity(id = id)
        dao.insert(cuadre)

        cuadre.cash = 100
        dao.update(cuadre)

        val updated = dao.find(id)
        assertThat(updated.cash).isEqualTo(100)
    }

    @Test
    fun update_NonExistingData_ReturnsNoError() = runBlocking {
        val id = 1L
        val cuadre = CuadreEntity(id = id)
        dao.insert(cuadre)

        cuadre.cash = 100
        dao.update(cuadre)
    }

    @Test
    fun delete_WithExistingData_ReturnsEmptyTable() = runBlocking {
        val cuadre = CuadreEntity(id = 1)
        dao.insert(cuadre)

        dao.delete(cuadre)

        assertThat(dao.getAll()).isEmpty()
    }

    @Test
    fun delete_WithoutData_ReturnsNoError() = runBlocking {
        val cuadre = CuadreEntity(id = 1)

        dao.delete(cuadre)
    }

    @Test
    fun exists_WithoutData_ReturnsFalse(): Unit = runBlocking {
        assertThat(dao.exists(1)).isFalse()
    }

    @Test
    fun exists_WithData_ReturnsTrue() = runBlocking {
        val id = 1L
        val cuadre = CuadreEntity(id = id)
        dao.insert(cuadre)

        assertThat(dao.exists(id)).isTrue()
    }

    @After
    fun tearDown() {
        db.close()
    }

}