package com.willvargas.deudoresapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.willvargas.deudoresapp.data.dao.UserDAO
import com.willvargas.deudoresapp.data.entities.User
import com.willvargas.deudoresapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.textViewRegistrarse.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        obtenerDatosGuardados()

        loginBinding.buttonLogin.setOnClickListener {
            val email = loginBinding.textImputUser.text.toString()
            val password = loginBinding.textImputPassword.text.toString()
            verificarUsuarioRegistrado(email,password)
        }

    }//onCreate

    private fun verificarUsuarioRegistrado(email: String, password: String) {
        val userDao: UserDAO = DeudoresApp.databaseUser.UserDAO()
        val user: User = userDao.searchUser(email)
        if(user != null){
            if(user.clave == password ){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else {
                Toast.makeText(this, getString(R.string.password_different), Toast.LENGTH_LONG).show()
            }
        }else {
            Toast.makeText(this, getString(R.string.dont_exist), Toast.LENGTH_LONG).show()
            with(loginBinding) {
                textImputUser.setText("")
                textImputPassword.setText("")
            }
        }
    }


    private fun obtenerDatosGuardados() {
        val data = intent.extras
        val email = data?.getString("email")
        val password = data?.getString("password")
        loginBinding.textImputUser.setText(email).toString()
        loginBinding.textImputPassword.setText(password).toString()
    }

}