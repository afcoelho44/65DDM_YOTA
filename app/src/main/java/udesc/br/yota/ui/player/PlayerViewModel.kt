package udesc.br.yota.ui.player

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import com.example.yota.R
import udesc.br.yota.Providers.MusicPlayerProvider
import udesc.br.yota.ui.repository.MusicDao

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    private var _mediaPlayer: MusicPlayerProvider = MusicPlayerProvider.getInstance(getApplication())

    val mediaPlayer: MusicPlayerProvider
        get() {
            return _mediaPlayer
        }

    override fun onCleared() {
        super.onCleared()
    }
}