package com.example.unified

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.unified.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.socialCard.setOnClickListener{
            //cambiar de fragment
            val fragment1 = this
            val fragment2 = ServicesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(fragment1.id, fragment2)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.comprasCard.setOnClickListener{
            //cambiar de fragment
            val fragment1 = this
            val fragment2 = ServicesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(fragment1.id, fragment2)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.correoCard.setOnClickListener{
            //cambiar de fragment
            val fragment1 = this
            val fragment2 = ServicesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(fragment1.id, fragment2)
            transaction.addToBackStack(null)
            transaction.commit()
        }
        binding.otrosCard.setOnClickListener{
            //cambiar de fragment
            val fragment1 = this
            val fragment2 = ServicesFragment()
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(fragment1.id, fragment2)
            transaction.addToBackStack(null)
            transaction.commit()
        }

    }

}