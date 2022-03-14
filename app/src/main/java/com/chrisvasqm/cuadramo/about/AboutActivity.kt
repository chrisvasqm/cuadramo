package com.chrisvasqm.cuadramo.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.databinding.ActivityAboutBinding
import kotlinx.android.synthetic.main.toolbar.*

class AboutActivity : AppCompatActivity(), AboutContract.View {

    private lateinit var binding: ActivityAboutBinding

    private lateinit var router: AboutContract.Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        displayVersionNumber()

        binding.linearGitHubLink.setOnClickListener { goToGitHub() }
        binding.linearChristianLink.setOnClickListener { goToChristianLink() }
        binding.linearCarlaLink.setOnClickListener { goToCristalLink() }

        router = AboutRouter(this)
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
        binding.textVersionNumber.text = "v${packageInfo.versionName}"
    }
}
