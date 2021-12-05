package com.shoker.euriskoTaskByMelhemShoker

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.content.IntentFilter
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.shoker.euriskoTaskByMelhemShoker.utils.IOnBackPressed
import com.shoker.euriskoTaskByMelhemShoker.utils.MyService
import java.util.*


class Home : AppCompatActivity() {
    private lateinit var   preferences : SharedPreferences
    private lateinit var   usrTxt : TextView

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        preferences=getSharedPreferences("myPrefs",MODE_PRIVATE)
      val user=preferences.getString("user","")
//        usrTxt=findViewById(R.id.myUsertxt)
//usrTxt.text=user

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        val hView = navView.getHeaderView(0)
        val textViewName = hView.findViewById(R.id.myUsertxt) as TextView
        textViewName.text=user
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_log_out
            ), drawerLayout
        )
        navView.menu.findItem(R.id.nav_log_out).setOnMenuItemClickListener {
            logoutDialog("")
            true
        }


        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        intent = Intent(this, MyService::class.java)
//        startService(intent)
//        registerReceiver(broadcastReceiver, IntentFilter(""))
      //  startService(Intent(applicationContext, MyService::class.java))
//val  fragment:TimerFragment= TimerFragment()
//
//        fragment.changeText("New text")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onDestroy() {
        stopService(Intent(this, MyService::class.java))
        super.onDestroy()
    }
    private fun logoutDialog(title: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("LOG OUT Alert")
        builder.setMessage("Are you sure  to Log out")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->

            preferences.edit().remove("user").apply()
            preferences.edit().remove("stayLogedIn").apply()
            //preferences.edit().putLong("timer",0).apply()
            stopService(Intent(this, MyService::class.java))
            finish()
            startActivity(Intent(this,Login::class.java))

        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }

        builder.show()

    }
//    override fun onBackPressed() {
//        val fragment =
//                this.supportFragmentManager.findFragmentById(R.id.timerFragement)
//        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
//            super.onBackPressed()
//        }
//    }
}