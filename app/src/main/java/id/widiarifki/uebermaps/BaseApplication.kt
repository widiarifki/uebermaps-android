package id.widiarifki.uebermaps

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import id.widiarifki.uebermaps.helper.PreferenceHelper

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        PreferenceHelper.init(applicationContext)
    }

}