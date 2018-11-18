package com.chrisvasqm.cuadramo.editor

import com.chrisvasqm.cuadramo.data.models.Cuadre

class EditorPresenter : EditorContract.Presenter {

    private var view: EditorContract.View? = null

    override fun attach(view: EditorContract.View) {
        this.view = view
    }

    override fun detach() {
        view = null
    }

    override fun save(cuadre: Cuadre) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}