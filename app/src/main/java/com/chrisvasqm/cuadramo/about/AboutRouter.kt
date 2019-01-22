package com.chrisvasqm.cuadramo.about

import android.app.Activity
import android.content.Intent
import android.net.Uri

class AboutRouter(private val activity: AboutActivity) : AboutContract.Router {

    override fun goToGitHub() {
        val url = "https://github.com/chrisvasqm/cuadramo"
        goToLink(activity, url)
    }

    override fun goToChristianLink() {
        val url = "https://github.com/chrisvasqm"
        goToLink(activity, url)
    }

    override fun goToCristalLink() {
        val url = "https://www.behance.net/hipandac"
        goToLink(activity, url)
    }

    private fun goToLink(activity: Activity, url: String) {
        Intent(Intent.ACTION_VIEW)
                .apply { data = Uri.parse(url) }
                .also { activity.startActivity(it) }
    }

}
