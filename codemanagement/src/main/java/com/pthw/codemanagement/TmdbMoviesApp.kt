package com.pthw.codemanagement

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Created by P.T.H.W on 02/10/2023.
 */
@HiltAndroidApp
class TmdbMoviesApp : Application() {
    override fun onCreate() {
        super.onCreate()

        // log
        setupTimber()
    }
}

private fun setupTimber() {
    if (BuildConfig.DEBUG) {
        Timber.plant(object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return String.format(
                    "C:%s, L:%s",
                    super.createStackElementTag(element)?.substringBefore("$") ?: "Timber",
                    element.lineNumber,
//                        element.methodName
                )
            }
        })
    }
}