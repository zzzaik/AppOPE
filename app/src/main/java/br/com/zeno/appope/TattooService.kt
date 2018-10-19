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

            return tattoos
        }
        return tattoos
    }


    fun save(tattoo: Tattoo): Response {
        val json = HttpHelper.post("$host/tattoo", tattoo.toJson())
        return parserJson(json)
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