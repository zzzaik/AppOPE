package br.com.zeno.appope

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.VisibleForTesting
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import br.com.zeno.appope.R.id.*
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.concurrent.Delayed
import java.util.concurrent.TimeUnit

class InicioActivity : AppCompatActivity() {
    private val context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
        setSupportActionBar(toolbar)
        var user = intent.getStringExtra("nome")
        supportActionBar?.title = "Inicio"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val login = textViewLogin
        login.text = "$user"
        login.visibility = View.VISIBLE
        btnOpcao1.setOnClickListener{onClickOpcao1()}
        btnOpcao2.setOnClickListener{onClickOpcao2()}
        btnOpcao3.setOnClickListener{onClickOpcao3()}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    fun onClickOpcao1(){
        val strBtn = btnOpcao1.text.toString()
        var opcao = Intent(context, SelecionadoActivity::class.java)
        opcao.putExtra("escolha", strBtn)
        startActivityForResult(opcao, 0)
    }

    fun onClickOpcao2(){
        val strBtn = btnOpcao2.text.toString()
        var opcao = Intent(context, SelecionadoActivity::class.java)
        opcao.putExtra("escolha", strBtn)
        startActivityForResult(opcao, 1)
    }

    fun onClickOpcao3(){
        val strBtn = btnOpcao3.text.toString()
        var opcao = Intent(context, SelecionadoActivity::class.java)
        opcao.putExtra("escolha", strBtn)
        startActivityForResult(opcao, 2)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        if (id == actionBuscar) {
//            Toast.makeText(context, "Buscar", Toast.LENGTH_SHORT).show()
        }
        else if (id == actionAdicionar) {
            Toast.makeText(context, "Adicionar", Toast.LENGTH_SHORT).show()
        }
        else if (id == actionAtualizar) {
            val progressBar = progressBarAtualizar
            progressBar.visibility = View.VISIBLE
            Handler().postDelayed({
                Toast.makeText(context, "Atualizado", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }, 2000)
        }
        else if (id == actionConfiguracoes) {
            val intent = Intent(context, ConfiguracoesActivity::class.java)
            startActivityForResult(intent, 0)
        }
        else if (id == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
