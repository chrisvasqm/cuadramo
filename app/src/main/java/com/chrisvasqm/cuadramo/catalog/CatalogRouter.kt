package com.chrisvasqm.cuadramo.catalog

import android.content.Intent
import com.chrisvasqm.cuadramo.editor.EditorActivity

class CatalogRouter(private val activity: CatalogActivity) : CatalogContract.Router {

    override fun goToEditorScreen() {
        Intent(activity, EditorActivity::class.java).also { activity.startActivity(it) }
    }

}
