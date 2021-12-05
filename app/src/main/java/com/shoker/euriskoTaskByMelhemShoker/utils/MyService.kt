package com.shoker.euriskoTaskByMelhemShoker.utils

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.IBinder
import android.os.SystemClock
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Toast


class MyService : Service() {
    private var intent: Intent? = null
    val BROADCAST_ACTION = "com.shoker.euriskoTaskByMelhemShoker.ui.timer"
    private val handler = Handler()
    private var initial_time: Long = 0
    private lateinit var   preferences : SharedPreferences
    var timeInMilliseconds = 0L
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
    // onTaskRemoved(intent)
        Toast.makeText(
            applicationContext, "This is a Service running in Background",
            Toast.LENGTH_SHORT
        ).show()

        return START_STICKY
    }
    override fun onCreate() {



        super.onCreate()
        preferences =  applicationContext.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);


      //  initial_time = SystemClock.uptimeMillis()
        val lastTime=preferences.getLong("timer",0)
        initial_time= SystemClock.uptimeMillis()-lastTime
        Toast.makeText(this, initial_time.toString(), Toast.LENGTH_LONG).show();

        intent = Intent(BROADCAST_ACTION)
        handler.removeCallbacks(sendUpdatesToUI)
        handler.postDelayed(sendUpdatesToUI, 1000) // 1 second
    }

    private val sendUpdatesToUI: Runnable = object : Runnable {
        override fun run() {
            DisplayLoggingInfo()
            handler.postDelayed(this, 1000) // 1 seconds
        }
    }

    private fun DisplayLoggingInfo() {
        timeInMilliseconds = SystemClock.uptimeMillis() - initial_time
        val timer = timeInMilliseconds.toInt() / 1000
        intent!!.putExtra("time", timer)
        sendBroadcast(intent)
    }

    override fun onBind(intent: Intent): IBinder? {
        // TODO: Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }

    override fun onDestroy() {
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show();
        Toast.makeText(this, (timeInMilliseconds).toString(), Toast.LENGTH_LONG).show();

        Log.d(TAG, "onDestroy");
        val t :Long=preferences.getLong("timer",0)

            preferences.edit().putLong( "timer", timeInMilliseconds ).apply()


        Log.d(TAG, "onDestroy");

        handler.removeCallbacks(sendUpdatesToUI);
    }
    override fun onTaskRemoved(rootIntent: Intent) {
        val restartServiceIntent = Intent(applicationContext, this.javaClass)
        restartServiceIntent.setPackage(packageName)
        startService(restartServiceIntent)
        super.onTaskRemoved(rootIntent)
    }
}