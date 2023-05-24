package com.example.unified

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.unified.databinding.ActivityLoginBinding

class LogIn : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var user=""
        var password=""



        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.crearCuentaTxt.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            user = binding.username.text.toString()
            password = binding.password.text.toString()
            if (user.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

    }
}