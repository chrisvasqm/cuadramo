package com.chrisvasqm.cuadramo.catalog

import android.content.Intent
import android.net.Uri
import com.chrisvasqm.cuadramo.about.AboutActivity
import com.chrisvasqm.cuadramo.editor.EditorActivity
import com.chrisvasqm.cuadramo.signin.SignInActivity

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

    override fun goToAboutScreen() {
        Intent(activity, AboutActivity::class.java).also { activity.startActivity(it) }
    }

    override fun goToSignInScreen() {
        Intent(activity, SignInActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .also { activity.startActivity(it) }
    }

}
