package udesc.br.yota.ui.musicLibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LibraryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Não existem músicas aqui"
    }
    val text: LiveData<String> = _text

    fun listMusics(){

    }
}