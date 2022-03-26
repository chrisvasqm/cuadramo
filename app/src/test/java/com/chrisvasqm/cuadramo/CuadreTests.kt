package com.chrisvasqm.cuadramo

import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.google.common.truth.Truth
import com.google.common.truth.Truth.*
import org.junit.Test

import org.junit.Assert.*


class CuadreTests {

    @Test
    fun ticketsSold_TwoTicketsTotalAndOneLeft_ReturnsOne() {
        val cuadre = Cuadre(ticketsTotal = 2, ticketsLeft = 1)

        val actual = cuadre.ticketsSold

        assertThat(actual).isEqualTo(1)
    }

}
