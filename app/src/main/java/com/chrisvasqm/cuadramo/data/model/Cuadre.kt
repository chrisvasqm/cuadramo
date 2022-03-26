package com.chrisvasqm.cuadramo.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chrisvasqm.cuadramo.extensions.getCurrentDateTime
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
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
    var others: Int = 0,

    @ColumnInfo(name = "createdAt")
    var createdAt: Date = getCurrentDateTime()
) : Parcelable {

    val revenue: Int
        get() = (cash + ticketsSold * TICKET_COST) - expenses

    val expenses: Int
        get() = food + delivery + others + (freebies * TICKET_COST)

    val ticketsSold: Int
        get() = ticketsTotal - ticketsLeft

}

private const val TICKET_COST = 50