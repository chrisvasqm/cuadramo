package com.chrisvasqm.cuadramo.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*

class AboutActivity : AppCompatActivity(), AboutContract.View {

    private lateinit var router: AboutContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)
        displayVersionNumber()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        router = AboutRouter(this)

        linearGitHubLink.setOnClickListener { goToGitHub() }

        linearChristianLink.setOnClickListener { goToChristianLink() }

        linearCarlaLink.setOnClickListener { goToCristalLink() }
    }

    override fun goToGitHub() {
        router.goToGitHub()
    }

    override fun goToChristianLink() {
        router.goToChristianLink()
    }

    override fun goToCristalLink() {
        router.goToCristalLink()
    }

    private fun displayVersionNumber() {
        val packageInfo = this.packageManager.getPackageInfo(packageName, 0)
        textVersionNumber.text = "v${packageInfo.versionName}"
    }
}
