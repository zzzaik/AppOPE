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
        if (Prefs.getBoolean("Lembrar")){
            checkBoxLembrar.isChecked = true
        }
        var campoLogin = editTextLogin
        var campoSenha = editTextSenha
        if (Prefs.getString("Login") != null &&
                Prefs.getString("Senha") != null){
            campoLogin.setText(Prefs.getString("Login"))
            campoSenha.setText(Prefs.getString("Senha"))
        }

        val btnLogin = buttonEntrar
        btnLogin.setOnClickListener {onClickEntrar()}
    }

    fun onClickEntrar(){
        var campoLogin = editTextLogin
        var campoSenha = editTextSenha
        val strLogin = campoLogin.text.toString()
        val strSenha = campoSenha.text.toString()
        var users = taskUsers()
        if (strLogin == "aluno" && strSenha == "impacta") {
            val intent = Intent(context, InicioActivity::class.java)
            intent.putExtra("login", strLogin)
            startActivityForResult(intent, 0)
            onClickLembrar(strLogin, strSenha)
        } else {
            campoLogin.setText("")
            campoSenha.setText("")
            Toast.makeText(context, "Login ou senha icorretos!", Toast.LENGTH_LONG).show()
        }
    }

    fun onClickLembrar(strLogin: String, strSenha: String){
        if (checkBoxLembrar.isChecked()){
            Prefs.setString("Login", "$strLogin")
            Prefs.setString("Senha", "$strSenha")
            Prefs.setBoolean("Lembrar", true)
        }
    }

    fun taskUsers(){
        Thread {
            this.users = UserService.getUsers(context)
        }.start()
    }
}
