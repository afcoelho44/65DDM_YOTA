package com.example.yota

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yota.databinding.ActivityMainBinding
import com.example.yota.ui.player.PlayerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var destroyButton : Button
    private lateinit var openPlayerButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        destroyButton = findViewById(R.id.btDestroy)
        openPlayerButton = findViewById(R.id.btOpenPlayer)

        destroyButton.setOnClickListener   { _-> finish() }
        openPlayerButton.setOnClickListener{ _-> openPlayerActivity()}
    }

    override fun onStart() {
        super.onStart()
        Log.i("visualização do lifecycle", "on start")
    }

    override fun onResume() {
        super.onResume()
        Log.i("visualização do lifecycle", "on resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("visualização do lifecycle", "on pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("visualização do lifecycle", "on stop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("visualização do lifecycle", "on restart")
    }

    override fun onDestroy() {
        Log.i("visualização do lifecycle", "on destroy")
        super.onDestroy()
    }

    private fun openPlayerActivity(){
        startActivity(Intent(this, PlayerActivity::class.java))
    }
}