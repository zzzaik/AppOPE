package br.com.zeno.appope

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_tattoo.*
import kotlinx.android.synthetic.main.toolbar.*

class TattooActivity : AppCompatActivity() {

    var tattoo: Tattoo? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tattoo)

        tattoo = intent.getSerializableExtra("tattoo") as Tattoo

        var toolbar = toolbar
        setSupportActionBar(toolbar)

        supportActionBar?.title = tattoo?.titulo

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        var texto = tituloTattoo
        texto.text = tattoo?.titulo
        var imagem = imagemTattoo
        Picasso.with(this).load(tattoo?.img).fit().into(imagem,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {}
                    override fun onError() {}
                })
    }
}
