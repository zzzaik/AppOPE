package br.com.zeno.appope

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.Menu
import kotlinx.android.synthetic.main.activity_selecionado.*
import kotlinx.android.synthetic.main.toolbar.*

class SelecionadoActivity : AppCompatActivity() {
    private val context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selecionado)
        setSupportActionBar(toolbar)
        var opcao = intent.getStringExtra("escolha")
        supportActionBar?.title = "$opcao"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        textViewOpcao.text = "$opcao"
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.actionBuscar)?.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
        })
        return true
    }

}
