package udesc.br.yota.ui.player

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import com.example.yota.R
import udesc.br.yota.Providers.MusicPlayerProvider

class PlayerActivity : AppCompatActivity() {

    private lateinit var playerButton: Button
    private lateinit var nextMusicButton: Button
    private lateinit var previousMusicButton: Button
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
        nextMusicButton = findViewById(R.id.btNext)
        previousMusicButton = findViewById(R.id.btRewind)

        playerButton.setOnClickListener{ _-> playAndPause() }
        nextMusicButton.setOnClickListener{_-> nextMusic()}
        previousMusicButton.setOnClickListener{_-> previousMusic()}
    }

    private fun playAndPause(){
        val player : MusicPlayerProvider = playerViewModel.mediaPlayer
        if (player.isPlaying()){
            playerButton.text = getString(R.string.play)
        } else {
            playerButton.text = getString(R.string.pause)
        }
        player.playAndPause()
    }

    private fun nextMusic(){
        val player : MusicPlayerProvider = playerViewModel.mediaPlayer
        player.nextMusic()
    }

    private fun previousMusic(){
        val player : MusicPlayerProvider = playerViewModel.mediaPlayer
        player.previousMusic()
    }
}