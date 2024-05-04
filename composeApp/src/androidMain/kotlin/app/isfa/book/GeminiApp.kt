package app.isfa.book

import android.app.Application
import di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class GeminiApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GeminiApp)
            androidLogger()
            modules(appModule())
        }
    }
}