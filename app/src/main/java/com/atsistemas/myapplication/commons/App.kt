package com.atsistemas.myapplication.commons

import android.app.Application
import com.atsistemas.data.di.dataModule
import com.atsistemas.myapplication.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by Juan Manuel Rincón on 3/8/21.
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App) //Context
            modules(listOf(dataModule, uiModule)) //Modules
        }
    }
}