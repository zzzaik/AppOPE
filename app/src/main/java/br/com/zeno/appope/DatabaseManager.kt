package br.com.zeno.appope

import android.arch.persistence.room.Room

object DatabaseManager {
    private var dbInstance: APPOPEDatabase

    init {
        val appContexto = AppOpeApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(appContexto, APPOPEDatabase::class.java, "ope.sqlite").build()
    }

    fun getTattooDAO(): TattooDAO{
        return dbInstance.tattooDAO()
    }
}