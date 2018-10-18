package br.com.zeno.appope

import android.app.Application
import java.lang.IllegalStateException

class AppOpeApplication: Application() {
    // chamado quando android iniciar o processo da aplicação
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        // singleton
        private var appInstance: AppOpeApplication?  = null
        fun getInstance(): AppOpeApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}