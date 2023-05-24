package com.example.unified

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.unified.databinding.FragmentServicesBinding
import java.io.InputStream
import java.net.URL
import com.squareup.picasso.Picasso


class ServicesFragment : Fragment() {
    private var _binding: FragmentServicesBinding? = null
    private val binding get() = _binding!!
    //variable que sea igual al linear layout container
    private val serviceContainer: LinearLayout get() = binding.contenedorServices

    val numServices=0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentServicesBinding.inflate(inflater, container, false)
        return binding.root


    }

    @SuppressLint("MissingInflatedId")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(numServices>0) {
            for (i in 0 until numServices) {
                val instacia = layoutInflater.inflate(R.layout.service_item, null)

                instacia.findViewById<com.google.android.material.textview.MaterialTextView>(R.id.serviceName).text =
                    "Servicio $i"
                //Cambiar el src de la imagen de la instancia
                serviceContainer.addView(instacia)
                //al pulsar en cada una de las instancias creadas poner hola
                //Variable que sea igual a la imagen de la instancia
                val imageServ = instacia.findViewById<ImageView>(R.id.imagenServ)
                val imageUrl = "https://static.cdninstagram.com/rsrc.php/v3/yb/r/lswP1OF1o6P.png"

                Picasso.get().load(imageUrl).into(imageServ)
                instacia.setOnClickListener {
                    //Cambiar de fragment
                    val fragment1 = this
                    val fragment2 = PerfilesFragment()
                    val transaction = parentFragmentManager.beginTransaction()
                    transaction.replace(fragment1.id, fragment2)
                    transaction.addToBackStack(null)
                    transaction.commit()
                }

            }
        }


        binding.backBtn.setOnClickListener{
            parentFragmentManager.popBackStack()
        }


    }
    fun loadImageFromWebOperations(url: String): Drawable? {
        return try {
            val inputStream: InputStream = URL(url).content as InputStream
            Drawable.createFromStream(inputStream, "src name")
        } catch (e: Exception) {
            null
        }
    }


}