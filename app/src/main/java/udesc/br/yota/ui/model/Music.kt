package udesc.br.yota.ui.model

import android.media.MediaPlayer
import com.fasterxml.jackson.annotation.JsonProperty

class Music(@JsonProperty("id") val id: Int,
            @JsonProperty("name")val name: String,
            @JsonProperty("image")val image: Int?) {

}