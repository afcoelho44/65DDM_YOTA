package udesc.br.yota.ui.home

import android.os.Build
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalTime
import java.util.Calendar

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = getGreetingBasedOnTime()
    }

    val text: LiveData<String> = _text
    private fun getGreetingBasedOnTime(): String {
        val hour: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            LocalTime.now().hour
            else Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        return when (hour) {
            in 0..5 -> "Boa madrugada"
            in 6..11 -> "Bom dia"
            in 12..17 -> "Boa tarde"
            in 18..23 -> "Boa noite"
            else -> "Olá"
        }
    }
}