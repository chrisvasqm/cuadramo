package com.chrisvasqm.cuadramo.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.chrisvasqm.cuadramo.data.model.Cuadre

@Dao
interface CuadreDao {

    @Query("SELECT * FROM cuadres ORDER BY createdAt DESC")
    suspend fun getAll(): List<CuadreEntity>

    @Query("SELECT * FROM cuadres WHERE id = :id")
    suspend fun find(id: Int): CuadreEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cuadre: CuadreEntity)

    @Update
    suspend fun update(cuadre: CuadreEntity)

    @Delete
    suspend fun delete(cuadre: CuadreEntity)

    @Query("SELECT EXISTS(SELECT * FROM cuadres WHERE id = :id)")
    fun exists(id: Int): Boolean

}