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
    var cash: Int = 0,

    @ColumnInfo(name = "ticketsTotal")
    var ticketsTotal: Int = 0,

    @ColumnInfo(name = "ticketsLeft")
    var ticketsLeft: Int = 0,

    @ColumnInfo(name = "food")
    var food: Int = 0,

    @ColumnInfo(name = "freebies")
    var freebies: Int = 0,

    @ColumnInfo(name = "delivery")
    var delivery: Int = 0,

    @ColumnInfo(name = "extras")
    var extras: Int = 0,

    @ColumnInfo(name = "createdAt")
    var createdAt: Date = getCurrentDateTime()
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