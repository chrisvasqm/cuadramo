package com.chrisvasqm.cuadramo

import android.app.Application
import com.chrisvasqm.cuadramo.logs.ReleaseTree
import timber.log.Timber

class CuadramoApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
        else Timber.plant(ReleaseTree())
    }

}