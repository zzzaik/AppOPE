package br.com.zeno.appope

import android.arch.persistence.room.Room

object DatabaseManager {

    // singleton
    private var dbInstance: AppOpeDatabase
    init {
        val appContext = AppOpeApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // contexto global
                AppOpeDatabase::class.java, // Referência da classe do banco
                "lms.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getTattooDAO(): TattooDAO {
        return dbInstance.tattooDAO()
    }
}