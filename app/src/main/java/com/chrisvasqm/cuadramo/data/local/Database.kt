package com.chrisvasqm.cuadramo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CuadreEntity::class], version = 1)
abstract class Database : RoomDatabase() {

    abstract fun getCuadreDao(): CuadreDao

}