package br.com.zeno.appope

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TattooService {

    val host = "http://draco.pythonanywhere.com"
    val TAG = "WS_AppOpe"
    val dao = DatabaseManager.getTattooDAO()

    fun getTattoo (context: Context): List<Tattoo> {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/tattoos"
            val json = HttpHelper.get(url)
            val tattoos = parserJson<List<Tattoo>>(json)
            for (t in tattoos) {
                saveOffline(t)
            }
            return tattoos
        } else {
            val tattoos = dao.findAll()
            return tattoos
        }
    }

    fun getTattoo (context: Context, id: Long): Tattoo? {
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/tattoos/${id}"
            val json = HttpHelper.get(url)
            val tattoo = parserJson<Tattoo>(json)
            return tattoo
        } else {
            val dao = DatabaseManager.getTattooDAO()
            val tattoo = dao.getById(id)
            return tattoo
        }
    }

    fun saveOffline(tattoo: Tattoo): Boolean{
        if (! existeTattoo(tattoo.id)){
            dao.insert(tattoo)
            return true
        }
        return false
    }

    fun save(tattoo: Tattoo): Response {
        val json = HttpHelper.post("$host/tattoo", tattoo.toJson())
        return parserJson(json)
    }

    fun existeTattoo(id: Long): Boolean {
        val tattoo = dao.getById(id)
        return tattoo!= null
    }

    fun delete(tattoo: Tattoo): Response {
        val url = "$host/tattoo/${tattoo.id}"
        val json = HttpHelper.delete(url)
        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}