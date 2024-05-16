package udesc.br.yota.ui.player

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.yota.R
import udesc.br.yota.ui.player.ui.player.PlayerFragment

class PlayerActivity : AppCompatActivity() {

    private lateinit var playerButton: Button
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PlayerFragment.newInstance())
                .commitNow()
        }
        initButtons();
    }

    private fun initButtons(){
        playerButton = findViewById(R.id.btPlay)
        playerButton.setOnClickListener{ _-> playAndPause() }
    }

    private fun playAndPause(){
        if (mediaPlayer == null) mediaPlayer = MediaPlayer.create(this, R.raw.music)
        mediaPlayer?.let { player ->
            if (player.isPlaying) {
                player.pause()
            } else {
                player.start()
            }
        }
    }
}