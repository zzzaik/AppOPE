package br.com.zeno.appope

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_selecionado.*
import kotlinx.android.synthetic.main.toolbar.*

class SelecionadoActivity : AppCompatActivity() {
    private val context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecionado)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Inicio"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        var opcao = intent.getStringExtra("escolha")
        textViewOpcao.text = "$opcao"
    }
}
