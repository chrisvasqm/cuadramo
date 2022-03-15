package com.chrisvasqm.cuadramo.data.model

import com.chrisvasqm.cuadramo.data.local.CuadreEntity
import com.chrisvasqm.cuadramo.extensions.getCurrentDateTime
import java.util.*

private const val TICKET_COST = 50

data class Cuadre(
    var id: Long = 0,
    var cash: Int = 0,
    var ticketsTotal: Int = 0,
    var ticketsLeft: Int = 0,
    var food: Int = 0,
    var freebies: Int = 0,
    var delivery: Int = 0,
    var extras: Int = 0,
    var createdAt: Date = getCurrentDateTime()
) {

    val revenue: Int
        get() = (cash + ticketsSold * TICKET_COST) - expenses

    val expenses: Int
        get() = food + delivery + extras + (freebies * TICKET_COST)

    val ticketsSold: Int
        get() = ticketsTotal - ticketsLeft

}

fun Cuadre.toDomain() = CuadreEntity(
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