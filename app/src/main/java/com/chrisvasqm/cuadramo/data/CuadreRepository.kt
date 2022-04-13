package com.chrisvasqm.cuadramo.data

import com.chrisvasqm.cuadramo.data.local.CuadreDao
import com.chrisvasqm.cuadramo.data.model.Cuadre
import javax.inject.Inject

class CuadreRepository @Inject constructor(
    private val dao: CuadreDao
) {

    suspend fun all(): MutableList<Cuadre> {
        return dao.getAll()
    }

    suspend fun add(cuadre: Cuadre) {
        dao.insert(cuadre)
    }

    suspend fun remove(cuadre: Cuadre) {
        dao.delete(cuadre)
    }

}