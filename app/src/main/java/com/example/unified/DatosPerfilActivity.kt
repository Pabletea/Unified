package com.example.unified

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.example.unified.databinding.ActivityDatosPerfilBinding

class DatosPerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDatosPerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatosPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var bool:Boolean=false
        var datosUser:List<String> = listOf()

        //Crear objeto de la clase login
        //Variable que sea igual al valor de user en la clase LogIn



        var gdT = GetDataThread()
        gdT.userMail=GlobalValues.instance.userMail
        gdT.tryLogThread()

        datosUser=gdT.datosUser

        var bindinglist:ArrayList<EditText> = ArrayList<EditText>()
        var bindinglist2:ArrayList<EditText> = ArrayList<EditText>()
        poblateArrayEdit(bindinglist)
        poblateArray2(bindinglist2)

        //Empezamos a iterar por 1 para evitar mostrar el id del usuario
        var i:Int=1

        bindinglist.forEach {
            it.setText(datosUser.get(i))
            i++
        }


        binding.cerrarDatos.setOnClickListener{
            if(bool){
                Toast.makeText(this,"Debes guardar los cambios antes de salir", Toast.LENGTH_SHORT).show()
            }else {
                finish()
            }
        }






        binding.editarDatosUser.setOnClickListener{
            bool = changeValues(bindinglist2,bool)
        }


    }
    fun changeValues(bindinglist:ArrayList<EditText>, boolean: Boolean):Boolean {
        var bool2:Boolean=false
        if (!boolean) {
            binding.editarDatosUser.text = "Guardar"
            bindinglist.forEach {
                it.apply {
                    alpha = 1f
                    isEnabled = true
                    isFocusable = true
                    isFocusableInTouchMode = true
                    bool2=true
                }
            }
        } else {
            binding.editarDatosUser.text = "Editar"
            var arrayString:ArrayList<String> = ArrayList<String>()
            poblateArrayString(arrayString)
            var udT = UpdateDataThread()
            udT.newData=arrayString
            udT.userMail=GlobalValues.instance.userMail
            udT.tryLogThread()

            if(udT.updatechecked) {
                Toast.makeText(this,"Datos actualizados correctamente", Toast.LENGTH_SHORT).show()

                bindinglist.forEach {
                    it.apply {
                        alpha = 0.5f
                        isEnabled = false
                        isFocusable = false
                        isFocusableInTouchMode = false
                        bool2 = false
                    }
                }
            }else{
                Toast.makeText(this,"Error al actualizar los datos", Toast.LENGTH_SHORT).show()
            }
        }

        return bool2
    }
    fun poblateArrayEdit(bindinglist: ArrayList<EditText>){
        bindinglist.add(binding.nomUserEdit)
        bindinglist.add(binding.appeUserEdit)
        bindinglist.add(binding.mailUserEdit)
        bindinglist.add(binding.fechaUserEdit)
        bindinglist.add(binding.tlfUserEdit)
    }
    fun poblateArray2(bindinglist: ArrayList<EditText>){
        bindinglist.add(binding.nomUserEdit)
        bindinglist.add(binding.appeUserEdit)
        bindinglist.add(binding.tlfUserEdit)
    }
    fun poblateArrayString(arrayString : ArrayList<String>){
        arrayString.add(binding.nomUserEdit.text.toString())
        arrayString.add(binding.appeUserEdit.text.toString())
        arrayString.add(binding.tlfUserEdit.text.toString())
    }
}