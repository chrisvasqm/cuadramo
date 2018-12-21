package com.chrisvasqm.cuadramo.logs

import android.util.Log
import timber.log.Timber

class ReleaseTree : Timber.Tree() {

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN) {
            TODO("Add Firebase's Crashlytics reports for release")
        }
    }

}