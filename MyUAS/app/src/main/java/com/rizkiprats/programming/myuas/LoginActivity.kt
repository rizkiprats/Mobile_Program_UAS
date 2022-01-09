package com.rizkiprats.programming.myuas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.eketan.model.LoginResponse
import com.rizkiprats.programming.myuas.api.ApiClient
import com.rizkiprats.programming.myuas.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.security.NoSuchAlgorithmException
import javax.net.ssl.SSLContext

class LoginActivity : AppCompatActivity() {
    private var binding : ActivityLoginBinding? = null
    private var username :String = ""
    private var password :String = ""

    val PREFS_NAME = "BelajarSharedPreferences"
    val KEY_USERNAME = "key.username"
    val KEY_PASSWORD = "key.password"

    lateinit var sharedPref: SharedPreferences

    fun saveUsername(username: String){
        val editor = sharedPref.edit()
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }
    fun savePassword(password: String){
        val editor:SharedPreferences.Editor = sharedPref.edit()
        editor.putString(KEY_PASSWORD, password)
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        btnLogin.setOnClickListener{
            val username = binding!!.etUsername.text.toString()
            val password = binding!!.etPassword.text.toString()

            if (username.isEmpty() and password.isEmpty()) {
                etUsername.error = "User required"
                etUsername.requestFocus()
                etPassword.error = "Password required"
                etPassword.requestFocus()
            }else{
                val api= ApiClient().getInstance()
                api.userLogin(username, password).enqueue(object:Callback<LoginResponse>{
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if (response.body()?.response==true){
                            saveUsername(response.body()?.payload!!.username)
                            savePassword(response.body()?.payload!!.password)

                            startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@LoginActivity,"Login Gagal",Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        Log.e("Pesan error",  "\$(t.message)e"+t.message)
                    }
                })
            }
        }

    }

}




