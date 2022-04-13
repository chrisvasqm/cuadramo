package com.chrisvasqm.cuadramo.data

import com.chrisvasqm.cuadramo.data.local.CuadreDao
import com.chrisvasqm.cuadramo.data.model.Cuadre
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow
import javax.inject.Inject

class CuadreRepository @Inject constructor(
    private val dao: CuadreDao
) {

    fun getAll() = dao.getAll()

    suspend fun add(cuadre: Cuadre) { dao.insert(cuadre) }

    suspend fun remove(cuadre: Cuadre) { dao.delete(cuadre) }

}