package com.chrisvasqm.cuadramo.catalog

import com.chrisvasqm.cuadramo.BasePresenter
import com.chrisvasqm.cuadramo.data.models.Cuadre

interface CatalogContract {

    interface View {

        fun showCatalog(cuadres: MutableList<Cuadre>)

        fun showSignOutDialog()

    }

    interface Presenter : BasePresenter<View> {

        fun loadCatalog()

        fun signOut()

    }

    interface Router {

        fun goToEditorScreen()

    }

}