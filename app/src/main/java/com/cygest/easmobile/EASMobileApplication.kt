package com.cygest.easmobile

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EASMobileApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}