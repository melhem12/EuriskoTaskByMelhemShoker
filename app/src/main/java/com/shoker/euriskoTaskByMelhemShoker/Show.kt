package com.shoker.euriskoTaskByMelhemShoker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.shoker.euriskoTaskByMelhemShoker.databinding.ActivityShowBinding

class Show : AppCompatActivity() {
    lateinit var binding: ActivityShowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityShowBinding.inflate(layoutInflater)
        setContentView(binding.root)
      val title= intent.getStringExtra("title")
        val id=   intent.getStringExtra("id")
        val userId=   intent.getStringExtra("userId")
        val completed=   intent.getStringExtra("completed")
        binding.titletxt.text=title
        binding.idtxt.text=id
        binding.userdTxt.text=userId
        binding.textView8.text=completed

    }
}