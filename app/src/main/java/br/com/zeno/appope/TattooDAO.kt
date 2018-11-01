package br.com.zeno.appope

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface TattooDAO {
    @Query("SELECT * FROM tattoos WHERE id = :id")
    fun getById(id: Long): Tattoo?

    @Query("SELECT * FROM tattoos")
    fun findAll(): List<Tattoo>

    @Insert
    fun insert(tattoo: Tattoo)

    @Delete
    fun delete(tattoo: Tattoo)
}