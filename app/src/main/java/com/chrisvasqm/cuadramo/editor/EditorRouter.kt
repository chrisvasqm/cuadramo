package com.chrisvasqm.cuadramo.editor

import android.content.Intent
import com.chrisvasqm.cuadramo.catalog.CatalogActivity

class EditorRouter(private val activity: EditorActivity) : EditorContract.Router {

    override fun goToCatalog() {
        Intent(activity, CatalogActivity::class.java).also { activity.startActivity(it) }
    }

}
