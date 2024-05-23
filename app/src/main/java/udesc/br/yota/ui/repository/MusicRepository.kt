package udesc.br.yota.ui.repository

import com.example.yota.R
import udesc.br.yota.ui.model.Music
import udesc.br.yota.ui.model.User


interface MusicRepository {
    fun saveMusic(music: Music)
    fun getMusic(index: Int): Music
    fun getFirst(): Music
    fun getAllMusics(): List<Music>

}
class MusicDao():MusicRepository{

    companion object{
        val musics: List<Music> = listOf(
            Music(R.raw.help, "Help", null),
            Music(R.raw.adele_hello, "Hello", null),
            Music(R.raw.the_one, "The One", null),
            Music(R.raw.whispers, "Whispers", null),
            Music(R.raw.christmas, "Christmas", null),
            Music(R.raw.ac_dc_who_made_who, "Who Made Who", null),
            Music(R.raw.adele_rolling_in_the_deep, "Rolling In The Deep", null),
            Music(R.raw.adele_set_fire_to_the_rain, "Set Fire To The Rain", null),
            Music(R.raw.adele_someone_like_you, "Someone Like You", null),
            Music(R.raw.don_t_go_breaking_my_heart, "Don't Go Breaking My Heart", null),
            Music(R.raw.saudade_bandida, "Saudade Bandida", null),
            Music(R.raw.let_it_be, "Let It Be", null),
            Music(R.raw.empty_garden, "Empty Garden", null),
            Music(R.raw.don_t_let_the_sun_go_down_on_me, "Don't Let The Sun Go Down On Me", null),
            Music(R.raw.goodbye_yellow_brick_road, "Goodbye Yellow Brick Road", null),
            Music(R.raw.roberto_carlos_nossa_senhora, "Nossa Senhora", null),
            Music(R.raw.skyline_pigeon, "Skyline Pigeon", null),
            Music(R.raw.i_guess_that_s_why_they_call_it_the_blues, "I Guess That's Why They Call It Blues", null)
        )
    }

    override fun saveMusic(music: Music) {
        TODO("Not yet implemented")
    }

    override fun getMusic(index: Int): Music {
        return musics[index]
    }

    override fun getFirst(): Music {
        return getAllMusics()[0]
    }

    override fun getAllMusics(): List<Music> {
        return musics
    }
}