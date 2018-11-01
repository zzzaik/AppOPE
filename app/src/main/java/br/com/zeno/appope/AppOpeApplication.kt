package br.com.zeno.appope

import android.app.Application
import java.lang.IllegalStateException

class AppOpeApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        private var appInstance: AppOpeApplication?  = null
        fun getInstance(): AppOpeApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configure application no Android Manifest")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}