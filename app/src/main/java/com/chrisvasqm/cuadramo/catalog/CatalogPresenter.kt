package com.chrisvasqm.cuadramo.catalog

class CatalogPresenter : CatalogContract.Presenter {
    private var view: CatalogContract.View? = null

    override fun attach(view: CatalogContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun loadCatalog() {
        view?.showCatalog(mutableListOf())
    }

    override fun signOut() {
        view?.showSignOutDialog()
    }

    override fun goToPlayStore() {
        view?.rate()
    }

    override fun goToAboutScreen() {
        view?.goToAboutScreen()
    }

}
