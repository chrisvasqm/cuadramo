package com.chrisvasqm.cuadramo

import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.google.common.truth.Truth.assertThat
import org.junit.Test


class CuadreTests {

    @Test
    fun ticketsSold_TwoTicketsTotalAndOneLeft_ReturnsOne() {
        val cuadre = Cuadre(ticketsTotal = 2, ticketsLeft = 1)

        val actual = cuadre.ticketsSold

        assertThat(actual).isEqualTo(1)
    }

    @Test
    fun revenue_OneTicketSoldAndNoExpenses_ReturnsFifty() {
        val cuadre = Cuadre(ticketsTotal = 2, ticketsLeft = 1)

        val actual = cuadre.revenue

        assertThat(actual).isEqualTo(50)
    }

    @Test
    fun revenue_TwoTicketsSoldAndFiftyOnExpenses_ReturnsFifty() {
        val cuadre = Cuadre(ticketsTotal = 2, ticketsLeft = 0, others = 50)

        val actual = cuadre.revenue

        assertThat(actual).isEqualTo(50)
    }

    @Test
    fun revenue_TwoTicketsSoldAndOneFreebie_ReturnsFifty() {
        val cuadre = Cuadre(ticketsTotal = 2, ticketsLeft = 0, freebies = 1)

        val actual = cuadre.revenue

        assertThat(actual).isEqualTo(50)
    }

    @Test
    fun revenue_TwoTicketsSoldAndFoodCost_ReturnsFifty() {
        val cuadre = Cuadre(ticketsTotal = 2, ticketsLeft = 0, food = 50)

        val actual = cuadre.revenue

        assertThat(actual).isEqualTo(50)
    }

    @Test
    fun revenue_TwoTicketsSoldAndDelivery_ReturnsFifty() {
        val cuadre = Cuadre(ticketsTotal = 2, ticketsLeft = 0, delivery = 50)

        val actual = cuadre.revenue

        assertThat(actual).isEqualTo(50)
    }

    @Test
    fun revenue_TenTicketsSoldWithOneFreebieAndFoodAndDeliveryAndOthers_ReturnsFifty() {
        val cuadre = Cuadre(
            ticketsTotal = 10,
            ticketsLeft = 0,
            freebies = 1,
            food = 100,
            delivery = 100,
            others = 100
        )

        val actual = cuadre.revenue

        assertThat(actual).isEqualTo(150)
    }


}
