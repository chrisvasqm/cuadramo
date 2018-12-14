package com.chrisvasqm.cuadramo.data.models

private const val TICKET_COST = 50

data class Cuadre(
        var cash: Int = 0,
        var ticketsTotal: Int = 0,
        var ticketsLeft: Int = 0,
        var food: Int = 0,
        var freebies: Int = 0,
        var delivery: Int = 0,
        var extras: Int = 0
) {

    val revenue: Int
        get() = (cash + (ticketsTotal - ticketsLeft) * TICKET_COST) - expenses

    val expenses: Int
        get() = food + delivery + extras + (freebies * TICKET_COST)

}