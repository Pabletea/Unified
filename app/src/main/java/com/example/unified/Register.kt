package com.example.unified

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.unified.databinding.ActivityRegisterBinding

class Register : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var nomUser = ""
        var mailUSer = ""
        var passUser = ""
        var passUser2 = ""
        var insertado = 0


        binding.signupbtn.setOnClickListener {
            nomUser = binding.username.text.toString()
            mailUSer = binding.email.text.toString()
            passUser = binding.password.text.toString()
            passUser2 = binding.repassword.text.toString()

            if (nomUser.isEmpty() || mailUSer.isEmpty() || passUser.isEmpty() || passUser2.isEmpty()) {
                Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (passUser != passUser2) {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                } else {
                    val lgR = RegisterThread()
                    lgR.nomUser=nomUser
                    lgR.emailuser=mailUSer
                    lgR.passuser=passUser
                    lgR.tryLogThread()
                    insertado = lgR.insertado
                    if (insertado == 1) {
                        Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    } else {
                        Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT)
                            .show()
                    }
                }



                binding.loginText.setOnClickListener {
                    finish()
                }


            }
        }
    }
}