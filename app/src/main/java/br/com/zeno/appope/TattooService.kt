package br.com.zeno.appope

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object TattooService {

    val host = "http://draco.pythonanywhere.com"
    val TAG = "WS_AppOpe"

    fun getTattoo (context: Context): List<Tattoo> {
        var tattoos = ArrayList<Tattoo>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/tattoos"
            val json = HttpHelper.get(url)
            tattoos = parserJson(json)

            for (d in tattoos) {
                saveOffline(d)
            }
            return tattoos
        } else {
            val dao = DatabaseManager.getTattooDAO()
            val tattoos = dao.findAll()
            return tattoos
        }
    }

    fun getTattoo (context: Context, id: Long): Tattoo? {

        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/tattoo/${id}"
            val json = HttpHelper.get(url)
            val tattoo = parserJson<Tattoo>(json)

            return tattoo
        } else {
            val dao = DatabaseManager.getTattooDAO()
            val tattoo = dao.getById(id)
            return tattoo
        }
    }

    fun save(tattoo: Tattoo): Response {
        val json = HttpHelper.post("$host/tattoo", tattoo.toJson())
        return parserJson(json)
    }

    fun saveOffline(tattoo: Tattoo) : Boolean {
        val dao = DatabaseManager.getTattooDAO()

        if (! existeTattoo(tattoo)) {
            dao.insert(tattoo)
        }
        return true
    }

    fun existeTattoo(tattoo: Tattoo): Boolean {
        val dao = DatabaseManager.getTattooDAO()
        return dao.getById(tattoo.id) != null
    }

    fun delete(tattoo: Tattoo): Response {
        if (AndroidUtils.isInternetDisponivel(AppOpeApplication.getInstance().applicationContext)) {
            val url = "$host/tattoo/${tattoo.id}"
            val json = HttpHelper.delete(url)

            return parserJson(json)
        } else {
            val dao = DatabaseManager.getTattooDAO()
            dao.delete(tattoo)
            return Response(status = "OK", msg = "Dados salvos localmente")
        }

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}