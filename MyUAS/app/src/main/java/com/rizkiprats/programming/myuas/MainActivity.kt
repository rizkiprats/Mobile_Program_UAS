package com.rizkiprats.programming.myuas

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eketan.model.LoginResponse
import com.rizkiprats.programming.myuas.adapter.NavigationRVAdapter
import com.rizkiprats.programming.myuas.api.ApiClient
import com.rizkiprats.programming.myuas.model.DataModel
import com.rizkiprats.programming.myuas.model.NavigationItemModel
import com.rizkiprats.programming.myuas.model.RekapModel
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: NavigationRVAdapter

    val PREFS_NAME = "BelajarSharedPreferences"
    val KEY_USERNAME = "key.username"
    val KEY_PASSWORD = "key.password"

    lateinit var sharedPref: SharedPreferences


    fun getUsername(): String? = sharedPref.getString(KEY_USERNAME, null)
    fun getPassword(): String? = sharedPref.getString(KEY_PASSWORD, null)

    var  data = ArrayList<DataModel>()

    private var items = arrayListOf(
            NavigationItemModel(R.drawable.ic_home, "Home"),
            NavigationItemModel(R.drawable.ic_profile, "Result"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        setContentView(R.layout.activity_main)

        val api= ApiClient().getInstance()
        api.userLogin(getUsername().toString(), getPassword().toString()).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.body()?.response == true) {

                    data = response.body()?.payload_2!!

                } else {
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Pesan error", "\$(t.message)e" + t.message)
            }
        })


        drawerLayout = findViewById(R.id.drawer_layout)

        username.setText(getUsername())
        password.setText(getPassword())

        // Set the toolbar
        setSupportActionBar(activity_main_toolbar)

        // Setup Recyclerview's Layout
        navigation_rv.layoutManager = LinearLayoutManager(this)
        navigation_rv.setHasFixedSize(true)

        // Add Item Touch Listener
        navigation_rv.addOnItemTouchListener(RecyclerTouchListener(this, object : ClickListener {
            override fun onClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        // # Home Fragment
                        val bundle = Bundle()
                        //bundle.putSerializable("bundle_key", data);
                        bundle.putSerializable("list", data)
                        //bundle.putString("fragmentName", "Home Fragment")
                        val homeFragment = DemoFragment()
                        homeFragment.arguments = bundle
                        supportFragmentManager.beginTransaction()
                                .replace(R.id.activity_main_content_id, homeFragment).commit()
                    }
                    1 -> {
                        // # Profile Activity
                        val intent = Intent(this@MainActivity, DemoActivity::class.java)
                        intent.putExtra("activityName", "Profile Activity")
                        startActivity(intent)
                    }
                }
                // Don't highlight the 'Profile' and 'Like us on Facebook' item row
                if (position != 6 && position != 4) {
                    updateAdapter(position)
                }
                Handler().postDelayed({
                    drawerLayout.closeDrawer(GravityCompat.START)
                }, 200)
            }
        }))

        // Update Adapter with item data and highlight the default menu item ('Home' Fragment)
        updateAdapter(0)

        // Set 'Home' as the default fragment when the app starts
        val bundle = Bundle()
        bundle.putString("fragmentName", "Home Fragment")
        val homeFragment = DemoFragment()
        homeFragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_content_id, homeFragment).commit()


        // Close the soft keyboard when you open or close the Drawer
        val toggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
                this,
                drawerLayout,
                activity_main_toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                // Triggered once the drawer closes
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                // Triggered once the drawer opens
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }
        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()


        // Set Header Image
        //navigation_header_img

        // Set background of Drawer
        navigation_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
    }

    private fun updateAdapter(highlightItemPos: Int) {
        adapter = NavigationRVAdapter(items, highlightItemPos)
        navigation_rv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            // Checking for fragment count on back stack
            if (supportFragmentManager.backStackEntryCount > 0) {
                // Go to the previous fragment
                supportFragmentManager.popBackStack()
            } else {
                // Exit the app
                super.onBackPressed()
            }
        }
    }

}