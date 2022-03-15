package com.chrisvasqm.cuadramo.data

import com.chrisvasqm.cuadramo.data.local.CuadreDao
import com.chrisvasqm.cuadramo.data.local.toModel
import com.chrisvasqm.cuadramo.data.model.Cuadre
import com.chrisvasqm.cuadramo.data.model.toDomain
import javax.inject.Inject

class CuadreRepository @Inject constructor(
    private val dao: CuadreDao
) {

    suspend fun all(): List<Cuadre> {
        return dao.getAll().map { it.toModel() }
    }

    suspend fun find(id: Int): Cuadre {
        return dao.find(id.toLong()).toModel()
    }

    suspend fun add(entity: Cuadre) {
        dao.insert(entity.toDomain())
    }

    suspend fun remove(entity: Cuadre) {
        dao.delete(entity.toDomain())
    }

    suspend fun save(entity: Cuadre) {
        dao.update(entity.toDomain())
    }

    suspend fun exists(id: Int): Boolean {
        return dao.exists(id)
    }

}