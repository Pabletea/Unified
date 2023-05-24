package com.example.unified

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.unified.databinding.FragmentPerfilesBinding

class PerfilesFragment : Fragment() {
    private var _binding: FragmentPerfilesBinding? = null
    private val binding get() = _binding!!


    private val serviceContainer: LinearLayout get() = binding.contenedorServices

    val numCuentas=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPerfilesBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(numCuentas>0) {
            for (i in 0 until numCuentas) {
                val instacia = layoutInflater.inflate(R.layout.service_item, null)
                //cambiar el texto de la instacia creada
                instacia.findViewById<com.google.android.material.textview.MaterialTextView>(R.id.serviceName).text =
                    "Perfil $i"
                serviceContainer.addView(instacia)
                instacia.setOnClickListener {
                    val intent = Intent(this.context, PerfilDataActivity::class.java)
                    startActivity(intent)
                }
            }
        }



        binding.backBtn.setOnClickListener{
            parentFragmentManager.popBackStack()
        }


    }
}