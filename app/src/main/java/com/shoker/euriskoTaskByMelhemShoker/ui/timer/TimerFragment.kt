package com.shoker.euriskoTaskByMelhemShoker.ui.timer

import android.app.AlertDialog
import android.content.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.shoker.euriskoTaskByMelhemShoker.Login
import com.shoker.euriskoTaskByMelhemShoker.R
import com.shoker.euriskoTaskByMelhemShoker.utils.IOnBackPressed
import com.shoker.euriskoTaskByMelhemShoker.utils.MyService
import java.time.LocalDate
import java.util.*


class TimerFragment : Fragment() {
//    private var seconds = 0
//
//    // Is the stopwatch running?
//    private var running = false
//
//    private var wasRunning = false


    private lateinit var   preferences : SharedPreferences

    lateinit var timeView :TextView
    lateinit var firstLoginDateTxt :TextView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_timer, container, false)
        preferences= (context?.getSharedPreferences("myPrefs", AppCompatActivity.MODE_PRIVATE) ) as SharedPreferences

        val myFirstLogin = preferences.getString("firstDate",null)
        firstLoginDateTxt=root.findViewById(R.id.FirstLoginDate)

        firstLoginDateTxt.text=myFirstLogin.toString()
        requireActivity().startService(Intent(activity, MyService::class.java))
        requireActivity().registerReceiver(broadcastReceiver, IntentFilter("com.shoker.euriskoTaskByMelhemShoker.ui.timer"))
        //running=true
//        if (savedInstanceState != null) {
//            // Get the previous state of the stopwatch
//            // if the activity has been
//            // destroyed and recreated.
//            seconds= savedInstanceState.getInt("seconds")
//            running= savedInstanceState.getBoolean("running");
//            wasRunning= savedInstanceState.getBoolean("wasRunning");
//        }
      //  runTimer();


         timeView = root.findViewById(
             R.id.time_view
         )
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
           exitDialog()
            }
        })
        return root


    }


    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            updateUI(intent)
        }
    }

    private fun updateUI(intent: Intent) {
        val time = intent.getIntExtra("time", 0)
        Log.d("Hello", "Time $time")
        val mins = time / 60
        val secs = time % 60
        timeView.setText(
            "" + mins + "m"
                    + String.format("%02d", secs)+"s"
        )
    }

//    override fun onDestroy() {
//        requireActivity().stopService(Intent(activity, MyService::class.java))
//        super.onDestroy()
//    }

//    override fun onStop() {
//        requireActivity().stopService(Intent(activity, MyService::class.java))
//
//        super.onStop()
//    }

    private fun exitDialog() {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("exit application")
        builder.setMessage("Are you sure  to exit application")

        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            requireActivity().stopService(Intent(activity, MyService::class.java))

            activity?.finish()
        }

        builder.setNegativeButton(android.R.string.no) { dialog, which ->

        }

        builder.show()

    }




}