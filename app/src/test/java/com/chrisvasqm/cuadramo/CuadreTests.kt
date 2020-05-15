package com.chrisvasqm.cuadramo

import assertk.assert
import assertk.assertions.isEqualTo
import com.chrisvasqm.cuadramo.data.models.Cuadre
import org.junit.Test

class CuadreTests {

    @Test
    fun revenue_WithAllValues_ReturnsThreeThousandSevenHundred() {
        val cuadre = Cuadre(
                "",
                100,
                100,
                20,
                150,
                2,
                50,
                100
        )

        assert(cuadre.revenue).isEqualTo(3700)
    }

    @Test
    fun expenses_WithAllValues_ReturnsFourHundred() {
        val cuadre = Cuadre(
                "",
                100,
                100,
                20,
                150,
                2,
                50,
                100
        )

        assert(cuadre.expenses).isEqualTo(400)
    }
}