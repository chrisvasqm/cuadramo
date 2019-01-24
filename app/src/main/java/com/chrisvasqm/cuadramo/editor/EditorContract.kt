package com.chrisvasqm.cuadramo.editor

import com.chrisvasqm.cuadramo.BasePresenter
import com.chrisvasqm.cuadramo.data.models.Cuadre

interface EditorContract {

    interface View {

        fun getCuadre(): Cuadre

        fun clearForm()

        fun displayUndoMessage()

        fun saveTemporaryCuadre()

        fun restoreForm()

        fun showPreview()

    }

    interface Presenter : BasePresenter<View> {

        fun clearForm()

        fun showPreview()

    }

    interface Router {

        fun goToCatalog()

    }

}