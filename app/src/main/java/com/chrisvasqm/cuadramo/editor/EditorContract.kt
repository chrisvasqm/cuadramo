package com.chrisvasqm.cuadramo.editor

import com.chrisvasqm.cuadramo.BasePresenter
import com.chrisvasqm.cuadramo.data.models.Cuadre

interface EditorContract {

    interface View {
        fun getCash(): Int
        fun getTicketsTotal(): Int
        fun getTicketsLeft(): Int
        fun getFood(): Int
        fun getFreebies(): Int
        fun getDelivery(): Int
        fun getOthers(): Int
    }

    interface Presenter : BasePresenter<View> {
        fun save(cuadre: Cuadre)
    }

    interface Router {

        fun goToCatalog()

    }

}