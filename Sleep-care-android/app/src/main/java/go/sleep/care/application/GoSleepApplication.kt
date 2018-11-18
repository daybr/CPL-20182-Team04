package go.sleep.care.application

import android.app.Application
import android.support.v7.app.AppCompatDelegate

class GoSleepApplication : Application() {
    companion object {
        lateinit var instance: GoSleepApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

}