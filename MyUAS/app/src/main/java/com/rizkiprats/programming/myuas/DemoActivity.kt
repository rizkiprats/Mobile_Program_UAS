package com.rizkiprats.programming.myuas

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.eketan.model.LoginResponse
import com.rizkiprats.programming.myuas.adapter.DataAdapter
import com.rizkiprats.programming.myuas.api.ApiClient
import com.rizkiprats.programming.myuas.model.DataModel
import kotlinx.android.synthetic.main.activity_demo.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DemoActivity : AppCompatActivity() {

    val PREFS_NAME = "BelajarSharedPreferences"
    val KEY_USERNAME = "key.username"
    val KEY_PASSWORD = "key.password"

    lateinit var sharedPref: SharedPreferences


    fun getUsername(): String? = sharedPref.getString(KEY_USERNAME, null)
    fun getPassword(): String? = sharedPref.getString(KEY_PASSWORD, null)

    var  data = ArrayList<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

        val api= ApiClient().getInstance()
        api.userLogin(getUsername().toString(), getPassword().toString()).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.body()?.response == true) {

                    data = response.body()?.payload_2!!

                    recyclerview.adapter = DataAdapter(data)


                } else {
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Pesan error", "\$(t.message)e" + t.message)
            }
        })

    }
}
