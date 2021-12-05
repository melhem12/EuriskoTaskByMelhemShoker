package com.shoker.euriskoTaskByMelhemShoker

import android.app.AlertDialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.shoker.euriskoTaskByMelhemShoker.databinding.ActivityLoginBinding
import com.shoker.euriskoTaskByMelhemShoker.utils.MyService
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

class Login : AppCompatActivity() {

    private val userId1="melhem"
    private val pass1="melhem123"
    private val userId2="philipe"
    private val pass2="philipe123"
    private lateinit var   preferences : SharedPreferences

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding= ActivityLoginBinding.inflate(layoutInflater) ;
        setContentView(binding.root)
        preferences=getSharedPreferences("myPrefs", MODE_PRIVATE)
        val user=preferences.getString("user", "")
        val keep=preferences.getBoolean("stayLogedIn", false)
        Log.d("/////", keep.toString())
        if(!user.isNullOrEmpty() && keep){
            finish()
            startActivity(Intent(this, Home::class.java))

        }
        binding.loginBtn.setOnClickListener() {


            if (binding.txtUsername.text.toString().isEmpty()) {
                binding.txtUsername.requestFocus();
                binding.textFieldUserName.error = "please Enter  Username";
            }

            if (binding.txtPassword.text.toString().isEmpty()) {
                binding.txtPassword.requestFocus();
                binding.textFieldPassword.error = "please Enter  password";

            }
            if(binding.txtPassword.text.toString().isNotEmpty()&&binding.txtPassword.text.toString().isNotEmpty()){
       var sucess=  userLogin(binding.txtUsername.text.toString(), binding.txtPassword.text.toString())
                if(sucess){
                    Toast.makeText(this, " success", Toast.LENGTH_SHORT).show()
                    preferences.edit().putString("user", binding.txtUsername.text.toString()).apply()
                    preferences.edit().putBoolean("stayLogedIn", binding.check.isChecked).apply()
                    preferences.edit().putLong("timer", 0).apply()

                    if(preferences.getString("firstDate", null).isNullOrEmpty()){
                        val today = LocalDate.now()
                        val time= LocalTime.now()
                        val formattedDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                        val formattedTime= time.format(DateTimeFormatter.ofPattern("HH:mm"))

                        preferences.edit().putString("firstDate", "$formattedDate $formattedTime").apply()

                    }

                   // preferences.edit().putString("FirstDtae",binding);
                   finish()
                    startActivity(Intent(this, Home::class.java))

                }
                else
                {
                    Toast.makeText(this, binding.txtUsername.text.toString(), Toast.LENGTH_SHORT).show()

                }
            }





        }


        }

    private fun userLogin(user: String, pass: String) :Boolean {
        if(user==userId1&&pass==pass1){
            Toast.makeText(this, "hyyy", Toast.LENGTH_SHORT).show()
            return  true
        }else
            if(user==userId2&&pass==pass2){
                return  true
            }
                return  false
    }



}