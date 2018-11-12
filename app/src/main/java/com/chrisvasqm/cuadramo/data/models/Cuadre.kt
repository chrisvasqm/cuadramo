package com.chrisvasqm.cuadramo.data.models

private const val TICKET_COST = 50

data class Cuadre(
        var cash: Int,
        var ticketsTotal: Int,
        var ticketsLeft: Int,
        var food: Int,
        var freebies: Int,
        var delivery: Int,
        var extras: Int
) {

    val revenue: Int
        get() = (cash + (ticketsTotal - ticketsLeft) * TICKET_COST) - expenses

    val expenses: Int
        get() = food + delivery + extras + (freebies * TICKET_COST)

}