package br.com.zeno.appope

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

// anotação define a lista de entidades e a versão do banco
@Database(entities = arrayOf(Tattoo::class), version = 1)
abstract class AppOpeDatabase: RoomDatabase() {
    abstract fun tattooDAO(): TattooDAO
}