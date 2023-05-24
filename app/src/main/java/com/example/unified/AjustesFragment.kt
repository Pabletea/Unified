package com.example.unified

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unified.databinding.FragmentAjustesBinding
import com.example.unified.databinding.FragmentHomeBinding

class AjustesFragment : Fragment() {
    private var _binding : FragmentAjustesBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAjustesBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cerrarSesionBtn.setOnClickListener{
            showLogoutDialog()
        }
    }
    private fun showLogoutDialog() {

        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("Cerrar sesión")
        builder.setMessage("¿Estás seguro de que deseas cerrar la sesión?")
        builder.setPositiveButton("Sí") { dialog, which ->
            //Cambiar a la activity de login
            val intent = android.content.Intent(this.context, LogIn::class.java)
            startActivity(intent)
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, which ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

}