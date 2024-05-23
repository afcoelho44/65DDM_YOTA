package udesc.br.yota.Providers

import android.content.Context
import android.media.MediaPlayer
import udesc.br.yota.ui.repository.MusicDao
import udesc.br.yota.ui.repository.MusicRepository

class MusicPlayerProvider private constructor() {

    private var mediaPlayer: MediaPlayer? = null
    private var repository: MusicRepository? = null
    private var currentMusic: Int = 0
    private lateinit var context: Context

    companion object {
        @Volatile private var instance: MusicPlayerProvider? = null

        fun getInstance(context: Context): MusicPlayerProvider {
            return instance ?: synchronized(this) {
                instance ?: MusicPlayerProvider().apply {
                    initMediaPlayer(context)
                    instance = this
                }
            }
        }
    }

    private fun initMediaPlayer(context: Context) : MusicPlayerProvider{
        if (repository == null) setMusicRepository(MusicDao())
        if(mediaPlayer == null) mediaPlayer= MediaPlayer.create(context, repository!!.getMusic(currentMusic).id)
        this.context = context
        return this
    }

    fun playAndPause(){
        if (mediaPlayer!!.isPlaying) mediaPlayer!!.pause()
        else mediaPlayer!!.start()
    }

    fun isPlaying() : Boolean {
        return mediaPlayer!!.isPlaying
    }

    fun nextMusic(){
        val nextMusic = if (currentMusic == repository!!.getAllMusics().lastIndex)
            repository!!.getMusic(0).id
        else repository!!.getMusic(currentMusic++).id

        val newMediaPlayer : MediaPlayer = MediaPlayer.create(context, nextMusic)
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        mediaPlayer = null
        mediaPlayer = newMediaPlayer
        mediaPlayer!!.start()
    }

    fun previousMusic(){
        val previousMusic = if (currentMusic == 0)
            repository!!.getMusic(repository!!.getAllMusics().lastIndex).id
        else repository!!.getMusic(--currentMusic).id

        val newMediaPlayer : MediaPlayer = MediaPlayer.create(context, previousMusic)
        mediaPlayer!!.stop()
        mediaPlayer!!.release()
        mediaPlayer = null
        mediaPlayer = newMediaPlayer
        mediaPlayer!!.start()
    }

    fun activeLoop(){
    }
    fun setMusicRepository(repository: MusicRepository){
        this.repository = repository
    }

}