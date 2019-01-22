package com.chrisvasqm.cuadramo.catalog

import com.chrisvasqm.cuadramo.BasePresenter
import com.chrisvasqm.cuadramo.data.models.Cuadre

interface CatalogContract {

    interface View {

        fun showCatalog(cuadres: MutableList<Cuadre>)

        fun showSignOutDialog()

        fun rate()

        fun goToAboutScreen()

        fun goToSignInScreen()

    }

    interface Presenter : BasePresenter<View> {

        fun loadCatalog()

        fun signOut()

        fun goToPlayStore()

        fun goToAboutScreen()

    }

    interface Router {

        fun goToEditorScreen()

        fun goToPlayStore()

        fun goToAboutScreen()

        fun goToSignInScreen()

    }

}