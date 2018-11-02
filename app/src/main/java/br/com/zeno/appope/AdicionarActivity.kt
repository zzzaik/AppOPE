package br.com.zeno.appope

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_adicionar.*

class AdicionarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)
        setTitle("Nova Tattoo")

        buttonAdicionar.setOnClickListener {
            val tattoo = Tattoo()
            tattoo.titulo = editTextTitulo.text.toString()
            tattoo.img = editTextImg.text.toString()

            taskAtualizar(tattoo)
        }
    }

    private fun taskAtualizar(tattoo: Tattoo) {
        Thread {
            TattooService.save(tattoo)
            runOnUiThread {
                finish()
            }
        }.start()
    }
}
