package kz.mobile.mvvm

import android.app.Application
import kz.mobile.mvvm.quotes.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MainApplication)
            modules(appModules)
        }
    }

}