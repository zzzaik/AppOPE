package br.com.zeno.appope

import com.google.gson.GsonBuilder
import java.io.Serializable

class Users: Serializable {

    var id:Long = 0
    var login = ""
    var senha = ""

    override fun toString(): String {
        return "$login,$senha"
    }

    fun retLogin(): String{
        return "$login"
    }

    fun retSenha(): String{
        return "$senha"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}