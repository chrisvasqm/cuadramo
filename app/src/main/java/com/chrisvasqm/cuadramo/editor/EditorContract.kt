package com.chrisvasqm.cuadramo.editor

import com.chrisvasqm.cuadramo.BasePresenter
import com.chrisvasqm.cuadramo.data.models.Cuadre

interface EditorContract {

    interface View

    interface Presenter : BasePresenter<View> {
        fun save(cuadre: Cuadre)
    }

    interface Router {

        fun goToCatalog()

    }

}