package com.example.unified

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.unified.databinding.ActivityCambioContrasenaBinding

class CambioContrasenaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCambioContrasenaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCambioContrasenaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var bool: Boolean= false
        var bool2:Boolean=true
        var bool3:Boolean=false
        var pass: String=""
        var newPass:String=""
        var userMail=""
        userMail=GlobalValues.instance.userMail


        binding.cambioContrasenaEdit.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.cambioContrasenaEdit.setText("")
                bool2=false
            }
        }

        binding.passCheckBtn.setOnClickListener{
            if(!bool){
                //Cambiar el input type a text
                binding.cambioContrasenaEdit.inputType = 1
                bool=true
            }else{
                //Cambiar el input type a password
                binding.cambioContrasenaEdit.inputType = 129
                bool=false
            }
        }

        binding.compContraTxtBtn.setOnClickListener {
            pass = binding.cambioContrasenaEdit.text.toString()
            if(!bool3) {
                binding.compContraTxtBtn.text="Comprobar contraseña"
                if (pass.isEmpty()) {
                    Toast.makeText(this, "Introduce tu contraseña actual", Toast.LENGTH_SHORT).show()

                } else {

                    var lgT = LoginThread()
                    lgT.user=userMail
                    lgT.pass=pass
                    lgT.tryLogThread()

                    if(lgT.passchecked) {
                        binding.cambioContrasenaEdit.inputType = 129
                        binding.linearNewPass.isVisible = true
                        binding.linearOldPass.alpha = 0.5f
                        binding.cambioContrasenaEdit.isEnabled = false
                        binding.cambioContrasenaEdit.isFocusable = false
                        binding.cambioContrasenaEdit.isFocusableInTouchMode = false
                        binding.passCheckBtn.isEnabled = false
                        binding.passCheckBtn.isFocusable = false
                        binding.passCheckBtn.isFocusableInTouchMode = false
                        binding.compContraTxtBtn.text = "Cambiar contraseña"
                        bool3 = true
                    }else{
                        Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                newPass=binding.newPassEdit.text.toString()
                if(newPass.isEmpty()){
                    Toast.makeText(this, "Introduce tu nueva contraseña", Toast.LENGTH_SHORT).show()
                }else{
                    binding.linearNewPass.alpha = 0.5f
                    binding.newPassEdit.isEnabled = false
                    binding.newPassEdit.isFocusable = false
                    binding.newPassEdit.isFocusableInTouchMode = false
                    binding.newPassCheckBtn.isEnabled = false
                    binding.newPassCheckBtn.isFocusable = false
                    binding.newPassCheckBtn.isFocusableInTouchMode = false

                    var udT = UpdatePassThread()
                    udT.userMail=userMail
                    udT.newPass=newPass
                    udT.tryLogThread()
                    if(udT.updatechecked) {
                        Toast.makeText(this, "Contraseña cambiada correctamente", Toast.LENGTH_SHORT).show()
                        bool2 = true
                    }else{
                        Toast.makeText(this, "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
        binding.cerrarDatos.setOnClickListener {
            if (!bool2) {
                Toast.makeText(this, "Debes guardar los cambios antes de salir", Toast.LENGTH_SHORT).show()
            } else {
                finish()
            }
        }
    }
}