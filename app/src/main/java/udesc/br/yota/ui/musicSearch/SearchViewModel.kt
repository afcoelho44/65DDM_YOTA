package udesc.br.yota.ui.musicSearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Pesquise sua música!"
    }
    val text: LiveData<String> = _text
}