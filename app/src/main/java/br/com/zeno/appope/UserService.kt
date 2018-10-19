package br.com.zeno.appope

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object UserService {


    val host = "http://draco.pythonanywhere.com"
    val TAG = "WS_AppOpe"

    fun getUsers (context: Context): List<Users> {
        var usuarios = ArrayList<Users>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/users"
            val json = HttpHelper.get(url)
            usuarios = parserJson(json)

            return usuarios
        }
        return ArrayList<Users>()
    }


    fun save(users: Users): Response {
        val json = HttpHelper.post("$host/users", users.toJson())
        return parserJson(json)
    }

    fun delete(users: Users): Response {
        val url = "$host/users/${users.id}"
        val json = HttpHelper.delete(url)
        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}