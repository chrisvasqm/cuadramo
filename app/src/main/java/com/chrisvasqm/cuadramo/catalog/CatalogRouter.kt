package com.chrisvasqm.cuadramo.catalog

import android.content.Intent
import android.net.Uri
import com.chrisvasqm.cuadramo.editor.EditorActivity

class CatalogRouter(private val activity: CatalogActivity) : CatalogContract.Router {

    override fun goToEditorScreen() {
        Intent(activity, EditorActivity::class.java).also { activity.startActivity(it) }
    }

    override fun goToPlayStore() {
        val url = "https://play.google.com/store/apps/details?id=com.chrisvasqm.cuadramo"
        Intent(Intent.ACTION_VIEW)
                .apply { data = Uri.parse(url) }
                .also { activity.startActivity(it) }
    }

}
