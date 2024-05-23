package udesc.br.yota

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yota.R
import com.example.yota.databinding.ActivityMainBinding
import udesc.br.yota.ui.player.PlayerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var planAndPauseOnMiniPlayerButton : Button
    private lateinit var miniPlayer : FrameLayout


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

        miniPlayer = findViewById(R.id.mini_player)
        miniPlayer.setOnClickListener{ _-> openPlayerActivity()}
        //planAndPauseOnMiniPlayerButton.setOnClickListener{ _-> playAndPause()}
    }

    private fun playAndPause(){

    }

    private fun openPlayerActivity(){
        startActivity(Intent(this, PlayerActivity::class.java))
    }
}