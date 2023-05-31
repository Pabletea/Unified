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
import com.google.android.material.textview.MaterialTextView
import java.util.Locale

class PerfilesFragment : Fragment() {
    private var _binding: FragmentPerfilesBinding? = null
    private val binding get() = _binding!!


    private val serviceContainer: LinearLayout get() = binding.contenedorServices

    var numCuentas=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPerfilesBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.accHeader.text= GlobalValues.instance.servType.uppercase(Locale.ROOT)

        var gaT = GetAccountThread()
        gaT.user = GlobalValues.instance.userMail
        gaT.tryLogThread()

        for(i in gaT.accounts){
            var value:String=""
            value=i.split(",")[2]
            if(value.equals(GlobalValues.instance.servType)){
                numCuentas++
            }
        }
        //lista que solo contenga las cuentas del servicio seleccionado
        var listaCuentas= mutableListOf<String>()
        for(i in gaT.accounts){
            var value:String=""
            value=i.split(",")[2]
            if(value.equals(GlobalValues.instance.servType)){
                listaCuentas.add(i)
            }
        }

//        numCuentas=gaT.accounts.size
        if(numCuentas>0) {
            for (i in 0 until numCuentas) {
                val instacia = layoutInflater.inflate(R.layout.service_item, null)
                //cambiar el texto de la instacia creada por la primera cadena de la lista separada por coma
                var account =listaCuentas[i]
                instacia.findViewById<MaterialTextView>(R.id.serviceName).text = account.split(",")[0]
                serviceContainer.addView(instacia)
                instacia.setOnClickListener {
                    val intent = Intent(activity, PerfilDataActivity::class.java)
                    intent.putExtra("account",account)
                    startActivity(intent)
                }
            }
        }



        binding.backBtn.setOnClickListener{
            parentFragmentManager.popBackStack()
        }


    }
}