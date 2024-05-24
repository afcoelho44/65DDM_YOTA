package udesc.br.yota

import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.IconCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yota.R
import com.example.yota.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import udesc.br.yota.Providers.MusicPlayerProvider
import udesc.br.yota.ui.player.PlayerActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var playAndPauseOnMiniPlayerButton : FloatingActionButton
    private lateinit var miniPlayer : FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        miniPlayer = findViewById(R.id.mini_player)
        miniPlayer.setOnClickListener{ _-> openPlayerActivity()}
        playAndPauseOnMiniPlayerButton = findViewById(R.id.mini_player_button)
        playAndPauseOnMiniPlayerButton.setOnClickListener{ _-> playAndPause()}
    }

    private fun playAndPause(){
       val player = MusicPlayerProvider.getInstance(null)
        val icon: Icon
        if (player.isPlaying()){
            icon = Icon.createWithResource(this, R.drawable.ic_play_arrow_24)
            playAndPauseOnMiniPlayerButton.setImageIcon(icon)
        } else {
            icon = Icon.createWithResource(this, R.drawable.ic_pause_24)
            playAndPauseOnMiniPlayerButton.setImageIcon(icon)
        }
        player.playAndPause()
    }

    private fun openPlayerActivity(){
        startActivity(Intent(this, PlayerActivity::class.java))
    }
}