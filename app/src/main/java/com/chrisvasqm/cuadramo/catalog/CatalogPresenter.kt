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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun signOut() {
        view?.showSignOutDialog()
    }

}
