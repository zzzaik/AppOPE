package br.com.zeno.appope

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.time.Instant

class LoginActivity : AppCompatActivity() {
    private val context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin = buttonEntrar

        btnLogin.setOnClickListener {onClickEntrar()}
    }

    fun onClickEntrar(){
        val campoLogin = editTextLogin
        val campoSenha = editTextSenha
        val strLogin = campoLogin.text.toString()
        val strSenha = campoSenha.text.toString()
        if (strLogin == "aluno" && strSenha == "impacta") {
            val intent = Intent(context, InicioActivity::class.java)
            intent.putExtra("nome", strLogin)
            startActivityForResult(intent, 0)
        } else {
            Toast.makeText(context, "Login ou senha icorretos!", Toast.LENGTH_LONG).show()
        }
    }
}
