package com.example.unified

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.unified.databinding.ActivityAnadirCuentaBinding

class AnadirCuentaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnadirCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnadirCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Poblar el spinner con redes sociales,compras,correo y otro
        val opciones = arrayOf("Social", "Compras", "Correo", "Otros")
        binding.spinnerTiposServ.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, opciones)



        binding.closeBtn.setOnClickListener{
            finish()
        }


        //si los edit text de la pantalla estan vacios, no se puede crear la cuenta
        binding.anadirServiceBtn.setOnClickListener{
            if(binding.nombreServiceEdit.text.toString().isEmpty() || binding.userServiceEdit.text.toString().isEmpty() || binding.contrasenaServiceEdit.text.toString().isEmpty()){
                Toast.makeText(this, "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Cuenta añadida correctamente", Toast.LENGTH_SHORT).show()
                finish()
            }
        }



    }
}