package com.fha.deezer

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Fresco.initialize(this)
        startKoin(this,
            listOf(mainModule),
            loadPropertiesFromFile = true)
    }
}
