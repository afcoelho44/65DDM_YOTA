package udesc.br.yota.ui.player

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.yota.R

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    private var _mediaPlayer: MediaPlayer? = null

    val mediaPlayer: MediaPlayer
        get() {
            if (_mediaPlayer == null) _mediaPlayer = MediaPlayer.create(getApplication(), R.raw.music)
            return _mediaPlayer!!
        }

    override fun onCleared() {
        super.onCleared()
        _mediaPlayer?.release()
    }
}