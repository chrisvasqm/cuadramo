package com.chrisvasqm.cuadramo.data.local

import androidx.room.*
import com.chrisvasqm.cuadramo.data.model.Cuadre

@Dao
interface CuadreDao {

    @Query("SELECT * FROM cuadres ORDER BY createdAt DESC")
    suspend fun getAll(): MutableList<Cuadre>

    @Query("SELECT * FROM cuadres WHERE id = :id")
    suspend fun find(id: Long): Cuadre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cuadre: Cuadre)

    @Update
    suspend fun update(cuadre: Cuadre)

    @Delete
    suspend fun delete(cuadre: Cuadre)

    @Query("SELECT EXISTS(SELECT * FROM cuadres WHERE id = :id)")
    fun exists(id: Long): Boolean

}