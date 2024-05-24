package udesc.br.yota.ui.player

import android.annotation.SuppressLint
import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import com.example.yota.R
import udesc.br.yota.Providers.MusicPlayerProvider
import udesc.br.yota.ui.repository.MusicDao

class PlayerViewModel(application: Application) : AndroidViewModel(application) {
    @SuppressLint("StaticFieldLeak")
    private var _musicPlayerProvider: MusicPlayerProvider = MusicPlayerProvider.getInstance(getApplication())

    val musicPlayerProvider: MusicPlayerProvider
        get() {
            return _musicPlayerProvider
        }

    override fun onCleared() {
        super.onCleared()
    }
}