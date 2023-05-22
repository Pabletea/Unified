package com.example.unified

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.unified.databinding.ActivityPerfilDataBinding

class PerfilDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPerfilDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilDataBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.closeBtnPerfData.setOnClickListener{
            finish()
        }



    }
}