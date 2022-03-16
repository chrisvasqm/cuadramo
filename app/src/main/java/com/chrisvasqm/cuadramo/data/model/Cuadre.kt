package com.chrisvasqm.cuadramo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chrisvasqm.cuadramo.extensions.getCurrentDateTime
import java.util.*

private const val TICKET_COST = 50

@Entity(tableName = "cuadres")
data class Cuadre(
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
) {

    val revenue: Int
        get() = (cash + ticketsSold * TICKET_COST) - expenses

    val expenses: Int
        get() = food + delivery + extras + (freebies * TICKET_COST)

    val ticketsSold: Int
        get() = ticketsTotal - ticketsLeft

}