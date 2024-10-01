package com.gkuziel.app_views.di


import android.app.Application
import com.gkuziel.core.di.AppCore
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class DualUIEventViewer : Application() {

    @Inject
    lateinit var appCore: AppCore

    override fun onCreate() {
        super.onCreate()
        appCore.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
        appCore.onTerminate()
    }
}