package udesc.br.yota.ui.player

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.example.yota.R

class PlayerActivity : AppCompatActivity() {

    private lateinit var playerButton: Button
    private val playerViewModel: PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .commitNow()
        }
        initButtons();
    }

    private fun initButtons(){
        playerButton = findViewById(R.id.btPlay)
        playerButton.setOnClickListener{ _-> playAndPause() }
    }

    private fun playAndPause(){
            val player : MediaPlayer = playerViewModel.mediaPlayer
            if (player.isPlaying){
                player.pause()
                playerButton.text = getString(R.string.play)
            } else {
                playerButton.text = getString(R.string.pause)
                player.start()
            }
    }
}