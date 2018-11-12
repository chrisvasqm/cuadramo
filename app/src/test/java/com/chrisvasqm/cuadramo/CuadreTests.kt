package com.chrisvasqm.cuadramo

import assertk.assert
import assertk.assertions.isEqualTo
import com.chrisvasqm.cuadramo.data.models.Cuadre
import org.junit.Test

class CuadreTests {

    @Test
    fun revenue_WithAllValues_ReturnsThreeHundred() {
        val cuadre = Cuadre(
                300,
                10,
                2,
                100,
                2,
                150,
                50
        )

        assert(cuadre.revenue).isEqualTo(300)
    }

    @Test
    fun expenses_WithAllValues_ReturnsFourHundred() {
        val cuadre = Cuadre(
                300,
                10,
                2,
                100,
                2,
                150,
                50
        )

        assert(cuadre.expenses).isEqualTo(400)
    }
}