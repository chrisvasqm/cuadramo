package com.chrisvasqm.cuadramo.about

import android.content.Intent
import android.net.Uri

class AboutRouter(private val activity: AboutActivity) : AboutContract.Router {

    override fun goToGitHub() {
        val url = "https://github.com/chrisvasqm/cuadramo"
        Intent(Intent.ACTION_VIEW)
                .apply { data = Uri.parse(url) }
                .also { activity.startActivity(it) }
    }

}
