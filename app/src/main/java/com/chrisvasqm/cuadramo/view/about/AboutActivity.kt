package com.chrisvasqm.cuadramo.view.about

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.chrisvasqm.cuadramo.R
import com.chrisvasqm.cuadramo.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        displayVersionNumber()

        binding.linearGitHubLink.setOnClickListener { goToGitHub() }
        binding.linearChristianLink.setOnClickListener { goToChristianLink() }
        binding.linearCarlaLink.setOnClickListener { goToCristalLink() }
    }

    private fun goToGitHub() {
        val url = "https://github.com/chrisvasqm/cuadramo"
        goToLink(this, url)
    }

    private fun goToChristianLink() {
        val url = "https://github.com/chrisvasqm"
        goToLink(this, url)
    }

    private fun goToCristalLink() {
        val url = "https://www.behance.net/hipandac"
        goToLink(this, url)
    }

    private fun goToLink(activity: Activity, url: String) {
        Intent(Intent.ACTION_VIEW)
            .apply { data = Uri.parse(url) }
            .also { activity.startActivity(it) }
    }

    private fun displayVersionNumber() {
        val packageInfo = this.packageManager.getPackageInfo(packageName, 0)
        binding.textVersionNumber.text = "v${packageInfo.versionName}"
    }
}
