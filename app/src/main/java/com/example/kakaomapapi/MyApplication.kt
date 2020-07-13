package com.example.kakaomapapi

import android.app.Application
import android.content.Context
import android.util.Log
import com.flurry.android.FlurryAgent
import com.flurry.android.FlurryPerformance
import timber.log.Timber

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
//        Timber.plant(FileLogTree(this))
//        FileLogger.writeFile(this)
//        initFlurry()
    }

    private fun initFlurry() {
        FlurryAgent.Builder()
            .withLogLevel(Log.VERBOSE)
            .withLogEnabled(true)
            .build(this, getString(R.string.FLURRY_API_KEY))
    }

}
