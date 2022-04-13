package com.chrisvasqm.cuadramo.data.local

import androidx.room.*
import com.chrisvasqm.cuadramo.data.model.Cuadre
import kotlinx.coroutines.flow.Flow

@Dao
interface CuadreDao {

    @Query("SELECT * FROM cuadres ORDER BY createdAt DESC")
    fun getAll(): Flow<List<Cuadre>>

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