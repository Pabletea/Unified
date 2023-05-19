package com.example.unified

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.unified.databinding.FragmentMigracionBinding
import com.example.unified.databinding.FragmentServicesBinding

class ServicesFragment : Fragment() {
    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!
    //variable que sea igual al linear layout container
    private val serviceContainer: LinearLayout get() = binding.contenedorServices

    val num=3

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentServicesBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for(i in 0 until num){
            val instacia  = layoutInflater.inflate(R.layout.service_item, null)
            //cambiar el texto de la instacia creada
            instacia.findViewById<com.google.android.material.textview.MaterialTextView>(R.id.serviceName).text = "Servicio $i"
            serviceContainer.addView(instacia)
        }


    }


}