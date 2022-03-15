package com.chrisvasqm.cuadramo.data

interface Repository<T> {

    suspend fun all(): List<T>?

    suspend fun find(id: Int): T

    suspend fun add(entity: T)

    suspend fun remove(entity: T)

    suspend fun save(entity: T)

    suspend fun exists(entity: T): Boolean

}