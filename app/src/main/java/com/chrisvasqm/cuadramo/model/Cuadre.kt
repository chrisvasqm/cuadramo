package com.chrisvasqm.cuadramo.model

import com.chrisvasqm.cuadramo.extensions.getCurrentDateTime
import java.util.*

private const val TICKET_COST = 50

data class Cuadre(
        var id: String = "",
        var cash: Int = 0,
        var ticketsTotal: Int = 0,
        var ticketsLeft: Int = 0,
        var food: Int = 0,
        var freebies: Int = 0,
        var delivery: Int = 0,
        var extras: Int = 0
) {

    val createdAt: Date = getCurrentDateTime()

    val revenue: Int
        get() = (cash + ticketsSold * TICKET_COST) - expenses

    val expenses: Int
        get() = food + delivery + extras + (freebies * TICKET_COST)

    val ticketsSold: Int
        get() = ticketsTotal - ticketsLeft

}