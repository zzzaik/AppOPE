package br.com.zeno.appope

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.MenuItem
import android.widget.Toast
import br.com.zeno.appope.R.layout.activity_horarios
import kotlinx.android.synthetic.main.activity_horarios.*

class HorariosActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_horarios)
        configurarSideMenu()
    }

    private fun configurarSideMenu() {
        var menuLateral = layoutSideMenuHora

        var toggle = ActionBarDrawerToggle(this,menuLateral,R.string.nav_drawer_open,R.string.nav_drawer_close)

        menuLateral.addDrawerListener(toggle)
        toggle.syncState()

        val navigationView = sideMenuHora
        navigationView.setNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_catalogo -> {
                val intent = Intent(this, CatalogoActivity::class.java)
                startActivityForResult(intent, 0)
            }
            R.id.nav_portfolio -> {
                val intent = Intent(this, PortfolioActivity::class.java)
                startActivityForResult(intent, 0)
            }
            R.id.nav_horarios -> {
                val intent = Intent(this, HorariosActivity::class.java)
                startActivityForResult(intent, 0)
            }
            R.id.nav_sair -> {
                finish()
            }

        }
        val drawer = layoutSideMenuHora
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
