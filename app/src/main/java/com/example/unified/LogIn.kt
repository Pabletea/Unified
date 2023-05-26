package com.example.unified

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.unified.databinding.ActivityLoginBinding

class LogIn : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    var userMail=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var password=""



        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.crearCuentaTxt.setOnClickListener{
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        binding.loginBtn.setOnClickListener {
            userMail = binding.username.text.toString()
            password = binding.password.text.toString()
            var lT = LoginThread()
            lT.user = userMail
            lT.pass=password
            lT.tryLogThread()
            if (userMail.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                if(lT.userchecked && lT.passchecked){
                    //Pasar el valor de user a la activity DatosPerfilActivity
                    GlobalValues.instance.userMail=userMail
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Error en las credenciales", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}