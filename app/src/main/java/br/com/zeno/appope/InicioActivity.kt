package br.com.zeno.appope

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.annotation.VisibleForTesting
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
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

class InicioActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context get() = this

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    private fun configurarSideMenu() {
        var toolbar = toolbar
        var menuLateral = layoutSideMenu

        var toggle = ActionBarDrawerToggle(context,menuLateral,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close)

        menuLateral.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = sideMenu
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            nav_catalogo -> {
                val intent = Intent(context, CatalogoActivity::class.java)
                startActivityForResult(intent, 0)
            }
            nav_portfolio -> {
                val intent = Intent(context, PortfolioActivity::class.java)
                startActivityForResult(intent, 0)
            }
            nav_horarios -> {
                val intent = Intent(context, HorariosActivity::class.java)
                startActivityForResult(intent, 0)
            }
            nav_sair -> {
                Toast.makeText(context,"Saindo",Toast.LENGTH_SHORT).show()
                finish()
            }

        }
        val drawer = layoutSideMenu
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

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

        configurarSideMenu()
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

