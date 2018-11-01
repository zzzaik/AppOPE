package br.com.zeno.appope

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Tattoo::class), version = 1)
abstract class APPOPEDatabase: RoomDatabase() {
    abstract fun tattooDAO(): TattooDAO
}