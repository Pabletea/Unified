package com.example.unified

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.unified.databinding.FragmentGeneradorBinding


class GeneradorFragment : Fragment() {

    private var _binding : FragmentGeneradorBinding? = null
    private val binding get() = _binding!!
    private lateinit var clipboardManager: ClipboardManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGeneradorBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clipboardManager = requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        binding.contrasenaGenerada.text=generarCadenaAleatoria(20,10,4,true,true,true)

        binding.copiarContrasena.setOnClickListener{

            val clipData = ClipData.newPlainText("text", binding.contrasenaGenerada.text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(requireContext(), "Texto copiado al portapapeles", Toast.LENGTH_SHORT).show()

        }

        binding.generarContrasenaBtn.setOnClickListener{

                val longitud = 20
                val cantidadDigitos = 10
                val cantidadSimbolos = 4
                val tieneMay = binding.AZCheckBox.isChecked
                val tieneMin = binding.azCheckBox.isChecked
                val tieneDig = binding.numberCheckBox.isChecked

                binding.contrasenaGenerada.text = generarCadenaAleatoria(longitud,cantidadDigitos,cantidadSimbolos,tieneMay,tieneMin,tieneDig)
        }


    }

    fun generarCadenaAleatoria(longitud: Int, cantidadDigitos: Int, cantidadSimbolos: Int,tieneMay: Boolean,tieneMin:Boolean,tieneDig:Boolean): String {
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
        if(tieneMay) {
            repeat(cantidadLetras) {
                cadenaAleatoria += letrasMay.random()
            }
        }
        if(tieneMin) {
            repeat(cantidadLetras) {
                cadenaAleatoria += letrasMin.random()
            }
        }
        if(tieneDig){
            repeat(cantidadDigitos) {
                cadenaAleatoria += digitos.random()
            }
        }
        repeat(cantidadSimbolos) {
            cadenaAleatoria += simbolos.random()
        }

        return cadenaAleatoria.toList().shuffled().joinToString("")
    }

}