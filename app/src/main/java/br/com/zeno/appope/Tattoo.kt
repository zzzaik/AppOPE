package br.com.zeno.appope

//import android.arch.persistence.room.Entity
//import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "tattoos")
class Tattoo : Serializable {

    @PrimaryKey
    var id:Long = 0
    var titulo = ""
    var img = ""

    override fun toString(): String {
        return "Tattoo(Titulo=$titulo)"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}