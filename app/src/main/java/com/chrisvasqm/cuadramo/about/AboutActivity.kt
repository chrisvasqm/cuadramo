package com.chrisvasqm.cuadramo.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chrisvasqm.cuadramo.R
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.toolbar.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setSupportActionBar(toolbar)
        displayVersionNumber()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun displayVersionNumber() {
        val packageInfo = this.packageManager.getPackageInfo(packageName, 0)
        textVersionNumber.text = "v${packageInfo.versionName}"
    }
}
