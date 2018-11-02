package br.com.zeno.appope

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.zeno.appope.R.layout.activity_tattoo
import br.com.zeno.appope.R.menu.menu_main
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.activity_tattoo.*
import kotlinx.android.synthetic.main.toolbar.*

class TattooActivity : AppCompatActivity() {

    var tattoo: Tattoo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_tattoo)

        if (intent.getSerializableExtra("tattoo") is Tattoo)
            tattoo = intent.getSerializableExtra("tattoo") as Tattoo


        val toolbar = toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = tattoo?.titulo

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val texto = tituloTattoo
        texto.text = tattoo?.titulo
        val imagem = imagemTattoo
        Picasso.with(this).load(tattoo?.img).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}
                    override fun onError() {}
                })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
