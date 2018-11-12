package com.chrisvasqm.cuadramo

interface BasePresenter<V> {
    fun attach(view: V)
    fun detach()
}