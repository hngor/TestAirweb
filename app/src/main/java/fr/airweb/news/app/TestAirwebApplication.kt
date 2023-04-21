package fr.airweb.news.app

import android.app.Application
import fr.airweb.news.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TestAirwebApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TestAirwebApplication)
            modules(appModule)
        }
    }
}