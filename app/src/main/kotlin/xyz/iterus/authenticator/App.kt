package xyz.iterus.authenticator

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import xyz.iterus.authenticator.di.koinModules

/*
False positive "Unused symbol" for a custom Android application class referenced in AndroidManifest.xml file:
https://youtrack.jetbrains.net/issue/KT-27971
*/
@Suppress("unused")
class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Workaround for: https://github.com/InsertKoinIO/koin/issues/847
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()
            modules(koinModules)
        }
    }
}
