package br.com.zeno.appope

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import br.com.zeno.appope.R.id.*
import kotlinx.android.synthetic.main.activity_inicio.*
import kotlinx.android.synthetic.main.side_drawer_header.*
import kotlinx.android.synthetic.main.toolbar.*

class InicioActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val context get() = this
    private var tattoos = listOf<Tattoo>()
    var recyclerTattoo: RecyclerView? = null

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

    override fun onResume() {
        super.onResume()
        taskTattoo()
    }

    fun taskTattoo(){
        Thread {
            this.tattoos = TattooService.getTattoo(context)
            runOnUiThread {
                recyclerTattoo?.adapter = TattooAdapter(this.tattoos) { onClickTattoo(it) }
                enviaNotificacao(this.tattoos.get(0))
            }
        }.start()
    }

    fun onClickTattoo(tattoo: Tattoo){
        val intent = Intent(context, TattooActivity::class.java)
        intent.putExtra("tattoo", tattoo)
        startActivity(intent)
    }

    private fun configurarSideMenu() {
        var toolbar = toolbar
        var menuLateral = layoutSideMenu
        val args:Bundle? = intent.extras
        val user = args?.getString("login")
//        var txtNomeUser = textNomeUsuario
//        System.out.println(user)
//        Toast.makeText(context, "$user", Toast.LENGTH_LONG).show()
//        if (user == null) {
//            textNomeUsuario.setText("NOME NÃO ENCONTRADO")
////            txtNomeUser.text = "NOME NÃO ENCONTRADO"
//        } else {
//            textNomeUsuario.setText(user)
////            txtNomeUser.text = "$user"
//        }


        var toggle = ActionBarDrawerToggle(context,menuLateral,toolbar,R.string.nav_drawer_open,R.string.nav_drawer_close)

        menuLateral.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = sideMenu
        navigationView.setNavigationItemSelectedListener(this)
    }

    fun enviaNotificacao(tattoo: Tattoo) {
        val intent = Intent(this, TattooActivity::class.java)
        intent.putExtra("tattoo", tattoo)
        NotificationUtil.create(this, 1, intent, "Tattoo", "Você tem nova tattoo na ${tattoo.titulo}")
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
        val args:Bundle? = intent.extras
        val user = args?.getString("login")
        supportActionBar?.title = "Inicio"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val login = textViewLogin
        login.text = "$user"
        login.visibility = View.VISIBLE

        configurarSideMenu()

        recyclerTattoo = findViewById<RecyclerView>(R.id.recyclerTattoos)
        recyclerTattoo?.layoutManager = LinearLayoutManager(context)
        recyclerTattoo?.itemAnimator = DefaultItemAnimator()
        recyclerTattoo?.setHasFixedSize(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var id = item?.itemId
        if (id == actionBuscar) {
//            Toast.makeText(context, "Buscar", Toast.LENGTH_SHORT).show()
        }
        else if (id == actionAdicionar) {
            val intent = Intent(context, AdicionarActivity::class.java)
            startActivityForResult(intent, 0)
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

