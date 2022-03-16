package com.chrisvasqm.cuadramo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chrisvasqm.cuadramo.data.model.Cuadre

@Database(entities = [Cuadre::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCuadreDao(): CuadreDao

}