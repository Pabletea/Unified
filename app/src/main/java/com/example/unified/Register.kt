package com.example.unified

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unified.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loginText.setOnClickListener{
            finish()
        }




    }
}