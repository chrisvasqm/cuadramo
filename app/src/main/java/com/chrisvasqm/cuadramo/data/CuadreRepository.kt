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

    suspend fun find(id: Int): Cuadre {
        return dao.find(id.toLong())
    }

    suspend fun add(cuadre: Cuadre) {
        dao.insert(cuadre)
    }

    suspend fun remove(cuadre: Cuadre) {
        dao.delete(cuadre)
    }

    suspend fun save(cuadre: Cuadre) {
        dao.update(cuadre)
    }

    suspend fun exists(id: Int): Boolean {
        return dao.exists(id.toLong())
    }

}