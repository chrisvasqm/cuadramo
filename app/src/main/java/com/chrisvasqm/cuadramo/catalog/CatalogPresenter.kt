package com.chrisvasqm.cuadramo.catalog

import com.chrisvasqm.cuadramo.data.models.Cuadre

class CatalogPresenter : CatalogContract.Presenter {

    private var view: CatalogContract.View? = null

    override fun attach(view: CatalogContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadCatalog() {
        view?.showCatalog(mutableListOf(
                Cuadre(
                        300,
                        10,
                        2,
                        100,
                        2,
                        150,
                        50
                ),
                Cuadre(
                        300,
                        10,
                        2,
                        100,
                        2,
                        150,
                        50
                )
        ))
    }

    override fun signOut() {
        view?.showSignOutDialog()
    }

}
