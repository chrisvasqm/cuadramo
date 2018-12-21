package com.chrisvasqm.cuadramo.catalog

import com.chrisvasqm.cuadramo.BasePresenter
import com.chrisvasqm.cuadramo.data.models.Cuadre

interface CatalogContract {

    interface View {

        fun showCatalog(cuadres: MutableList<Cuadre>)

        fun showSignOutDialog()

        fun rate()

    }

    interface Presenter : BasePresenter<View> {

        fun loadCatalog()

        fun signOut()

        fun rate()

    }

    interface Router {

        fun goToEditorScreen()

        fun goToPlayStore()

    }

}