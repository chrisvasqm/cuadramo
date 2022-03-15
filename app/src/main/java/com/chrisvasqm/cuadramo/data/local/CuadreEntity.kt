package com.chrisvasqm.cuadramo.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.extensions.getCurrentDateTime
import java.util.*

@Entity(tableName = "cuadres")
data class CuadreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo(name = "cash")
    val cash: Int = 0,

    @ColumnInfo(name = "ticketsTotal")
    val ticketsTotal: Int = 0,

    @ColumnInfo(name = "ticketsLeft")
    val ticketsLeft: Int = 0,

    @ColumnInfo(name = "food")
    val food: Int = 0,

    @ColumnInfo(name = "freebies")
    val freebies: Int = 0,

    @ColumnInfo(name = "delivery")
    val delivery: Int = 0,

    @ColumnInfo(name = "extras")
    val extras: Int = 0,

    @ColumnInfo(name = "createdAt")
    val createdAt: Date = getCurrentDateTime()
)

fun CuadreEntity.toModel() = Cuadre(
    id = id,
    cash = cash,
    ticketsTotal = ticketsTotal,
    ticketsLeft = ticketsLeft,
    food = food,
    freebies = freebies,
    delivery = delivery,
    extras = extras,
    createdAt = createdAt
)