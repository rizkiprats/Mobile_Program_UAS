package com.rizkiprats.programming.myuas

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class session : AppCompatActivity() {
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
    fun clearData(){
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }
    fun getUsername(): String? = sharedPref.getString(KEY_USERNAME,null)
    fun getPassword(): String? = sharedPref.getString(KEY_PASSWORD,null)


    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    }


}

