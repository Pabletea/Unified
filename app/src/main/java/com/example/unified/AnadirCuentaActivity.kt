package com.example.unified

import android.R
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.unified.databinding.ActivityAnadirCuentaBinding

class AnadirCuentaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnadirCuentaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnadirCuentaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var visible:Boolean =false
        //lista de string
        var servData:List<String> = listOf()

        //Poblar el spinner con redes sociales,compras,correo y otro
        val opciones = arrayOf("Otros", "Compras", "Correo", "Social")
        binding.spinnerTiposServ.adapter = ArrayAdapter(this, R.layout.simple_spinner_item, opciones)

        binding.contrasenaServiceEdit.setText(generarCadenaAleatoria(10, 3, 3))

        binding.closeBtn.setOnClickListener{
            finish()
        }

        binding.makePswVisible.setOnClickListener{
            if(!visible){
                binding.contrasenaServiceEdit.inputType = 129
                visible = true
            }else {
                binding.contrasenaServiceEdit.inputType = 1
                visible = false
            }
        }
        binding.generatePass.setOnClickListener{
            binding.contrasenaServiceEdit.setText(generarCadenaAleatoria(10, 3, 3))
        }


        //si los edit text de la pantalla estan vacios, no se puede crear la cuenta
        binding.anadirServiceBtn.setOnClickListener{
            if(binding.nombreServiceEdit.text.toString().isEmpty() || binding.userServiceEdit.text.toString().isEmpty() || binding.contrasenaServiceEdit.text.toString().isEmpty()){
                Toast.makeText(this, "Por favor rellena todos los campos", Toast.LENGTH_SHORT).show()
            }else{
                var pass:String=""
                try {
                    val key = "clave-secreta123" // La clave debe tener 16, 24 o 32 caracteres para AES-128, AES-192 o AES-256 respectivamente
                    val plainText = "binding.contrasenaServiceEdit.text.toString()"
                    val encryptedText = AESEncryptionUtil.encrypt(key, plainText)
                    println("Texto cifrado: $encryptedText")
                    pass=encryptedText

                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Error encrupt")
                }


                //Añadir los valores de los edit y el spinner a servData
                servData+=binding.nombreServiceEdit.text.toString()
                servData+=binding.userServiceEdit.text.toString()
                servData+=pass
                servData+=binding.urlServiceEdit.text.toString()
                servData+=binding.spinnerTiposServ.selectedItem.toString()
                var asT = AddServiceThread()
                asT.userMail=GlobalValues.instance.userMail
                asT.servData=servData
                asT.tryLogThread()
                if (asT.uploadchecked) {
                    Toast.makeText(this, "Cuenta añadida correctamente", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error al añadir la cuenta", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }



    }
    fun generarCadenaAleatoria(longitud: Int, cantidadDigitos: Int, cantidadSimbolos: Int): String {
        val letrasMay = ('A'..'Z')
        val letrasMin = ('a'..'z')
        val digitos = ('0'..'9')
        val simbolos = listOf('!', '@', '#', '$', '%', '&', '*','^')

        //


        val totalCaracteres = cantidadDigitos + cantidadSimbolos
        val cantidadLetras = longitud - totalCaracteres

        if (cantidadLetras < 0 || cantidadDigitos < 0 || cantidadSimbolos < 0) {
            throw IllegalArgumentException("Los parámetros deben ser valores positivos.")
        }

        var cadenaAleatoria = ""
        repeat(cantidadLetras) {
                cadenaAleatoria += letrasMay.random()
            }

            repeat(cantidadLetras) {
                cadenaAleatoria += letrasMin.random()
            }

            repeat(cantidadDigitos) {
                cadenaAleatoria += digitos.random()
            }
        repeat(cantidadSimbolos) {
            cadenaAleatoria += simbolos.random()
        }

        return cadenaAleatoria.toList().shuffled().joinToString("")
    }
}