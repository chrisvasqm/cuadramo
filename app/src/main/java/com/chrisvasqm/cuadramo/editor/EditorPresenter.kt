package com.chrisvasqm.cuadramo.editor

class EditorPresenter : EditorContract.Presenter {

    private var view: EditorContract.View? = null

    override fun attach(view: EditorContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun clearForm() {
        view?.clearForm()
    }

    override fun showPreview() {
        view?.showPreview()
    }

}