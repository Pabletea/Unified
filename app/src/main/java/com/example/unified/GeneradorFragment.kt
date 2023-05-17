package com.example.unified

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
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
        binding.contrasenaGenerada.text = generarCadenaAleatoria(20,10,4)

        binding.copiarContrasena.setOnClickListener{

            val clipData = ClipData.newPlainText("text", binding.contrasenaGenerada.text)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(requireContext(), "Texto copiado al portapapeles", Toast.LENGTH_SHORT).show()

        }


    }

    fun generarCadenaAleatoria(longitud: Int, cantidadDigitos: Int, cantidadSimbolos: Int): String {
        val letras = ('a'..'z') + ('A'..'Z')
        val digitos = ('0'..'9')
        val simbolos = listOf('!', '@', '#', '$', '%', '&', '*','^')

        val totalCaracteres = cantidadDigitos + cantidadSimbolos
        val cantidadLetras = longitud - totalCaracteres

        if (cantidadLetras < 0 || cantidadDigitos < 0 || cantidadSimbolos < 0) {
            throw IllegalArgumentException("Los parámetros deben ser valores positivos.")
        }

        var cadenaAleatoria = ""

        repeat(cantidadLetras) {
            cadenaAleatoria += letras.random()
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