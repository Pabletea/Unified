package com.example.unified

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.unified.databinding.ActivityPerfilDataBinding

class PerfilDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bool: Boolean= false
        //REcibir el intent en una lista


        var account:String =""
        account= intent.getStringExtra("account").toString()

        var pass:String = account.split(",")[1]
        var newPass:String=""

        try {
            val key = "clave-secreta123" // La clave debe tener 16, 24 o 32 caracteres para AES-128, AES-192 o AES-256 respectivament
            newPass = AESEncryptionUtil.decrypt(key, pass)
        } catch (e: Exception) {
            e.printStackTrace()
            println("Error al descifrar: " + e.message)
        }


        //Cambiar el texto del edit text

        binding.accNickEdit.setText(account.split(",")[0])
        binding.passNickEdit.setText(newPass)


        binding.makeVisible.setOnClickListener{
            if(!bool){
                //Cambiar el input type a text
                binding.passNickEdit.inputType = 1
                bool=true
            }else{
                //Cambiar el input type a password
                binding.passNickEdit.inputType = 129
                bool=false
            }
        }


        binding.closeBtnPerfData.setOnClickListener{
            finish()
        }



    }
}