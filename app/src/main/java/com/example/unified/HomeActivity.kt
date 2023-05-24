package com.example.unified

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var fab:FloatingActionButton
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var binding: HomeActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val userMail= intent.getStringExtra("user")


        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        fab = findViewById(R.id.fab)
        drawerLayout=findViewById(R.id.drawer_layout)
//        var navigationView:NavigationView = findViewById(R.id.nav_view)
//        var toolbar:androidx.appcompat.widget.Toolbar=findViewById(R.id.toolbar)
//
//
//        setSupportActionBar(toolbar)
//        var toogle = ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_nav,R.string.close_nav)
//        drawerLayout.addDrawerListener(toogle)
//        toogle.syncState()
//        navigationView.setNavigationItemSelectedListener { menuItem ->
//            // Manejar la selección del elemento del menú aquí
//            when (menuItem.itemId) {
//                R.id.nav_home -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, HomeFragment())
//                        .commit()
//                }
//                R.id.nav_settings -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.frame_layout, AjustesFragment())
//                        .commit()
//                }
//
//            }
//
//            // Cerrar el drawer después de la selección
//            drawerLayout.closeDrawers()
//
//            true // Indicar que el evento de selección ha sido manejado correctamente
//        }











        if(savedInstanceState == null){
            supportFragmentManager.beginTransaction().replace(R.id.frame_layout,HomeFragment()).commit()
        }



       bottomNavigationView.background = null



        bottomNavigationView.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.inicio -> replaceFragment(HomeFragment())
                R.id.generador -> replaceFragment(GeneradorFragment())
                R.id.migracion -> replaceFragment(MigracionFragment())
                R.id.perfil -> replaceFragment(AjustesFragment())
            }

            true
        }

        fab.setOnClickListener { showBottomDialog() }




    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }

    private fun showBottomDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.bottomsheetlayout)

        val anadirServicioBtn = dialog.findViewById<LinearLayout>(R.id.layoutVideo)
        val cancelButton = dialog.findViewById<ImageView>(R.id.cancelButton)

        anadirServicioBtn.setOnClickListener {
            //Cambiar a la activity de anadir cuenta
            dialog.dismiss()
            intent = intent.setClass(this, AnadirCuentaActivity::class.java)
            startActivity(intent)

        }


//        shortsLayout.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(this, "Create a short is Clicked", Toast.LENGTH_SHORT).show()
//        }
//
//        liveLayout.setOnClickListener {
//            dialog.dismiss()
//            Toast.makeText(this, "Go live is Clicked", Toast.LENGTH_SHORT).show()
//        }

        cancelButton.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations = R.style.DialogAnimation
        dialog.window?.setGravity(Gravity.BOTTOM)
    }



}