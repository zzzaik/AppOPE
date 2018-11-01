package br.com.zeno.appope

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*
import java.time.Instant

class LoginActivity : AppCompatActivity() {
    private val context get() = this
    private var users = listOf<Users>()
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
        var users = taskUsers()
        Toast.makeText(context, "$users",Toast.LENGTH_LONG).show()
        if (strLogin == "aluno" && strSenha == "impacta") {
            val intent = Intent(context, InicioActivity::class.java)
            intent.putExtra("nome", strLogin)
            startActivityForResult(intent, 0)
        } else {
            Toast.makeText(context, "Login ou senha icorretos!", Toast.LENGTH_LONG).show()
        }
        onClickLembrar(strLogin, strSenha)
    }

    fun onClickLembrar(strLogin: String, strSenha: String){
        if (checkBoxLembrar.isChecked()){
            Prefs.setString("Login", "$strLogin")
            Prefs.setString("Senha", "$strSenha")
        }
    }

    fun taskUsers(){
        Thread {
            this.users = UserService.getUsers(context)
        }.start()
    }
}
