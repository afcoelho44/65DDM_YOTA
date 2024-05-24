package udesc.br.yota.Providers

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.yota.R
import com.google.common.primitives.Bytes
import udesc.br.yota.ui.repository.MusicDao
import udesc.br.yota.ui.repository.MusicRepository
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class MusicPlayerProvider: Service() {


    private var mediaPlayer: MediaPlayer? = null
    private var repository: MusicRepository? = null
    private var currentMusic: Int = 0
    private var isForeground: Boolean = false
    private var intent:Intent? =null
    private lateinit var context: Context

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile private var instance: MusicPlayerProvider? = null

        fun getInstance(context: Context?): MusicPlayerProvider {
            return instance ?: synchronized(this) {
                instance ?: MusicPlayerProvider().apply {
                    initMediaPlayer(context!!)
                    instance=this
                }
            }
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        initMediaPlayer(this)
    }

    @SuppressLint("ForegroundServiceType")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (!isForeground) {
            val notification = createNotification()
            this.startForeground(1, notification)
            isForeground = true
        }
        return START_STICKY
    }

    private fun initMediaPlayer(context: Context) {
        this@MusicPlayerProvider.context = context
        if (repository == null) {
            repository = MusicDao()
        }
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(context, repository!!.getMusic(currentMusic).id)
        }
    }

    fun playAndPause() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.pause()
            stopSelf()
            isForeground = false
        } else {
            mediaPlayer?.start()
            intent = Intent(context, MusicPlayerProvider::class.java)
            ContextCompat.startForegroundService(context, intent!!)
        }
    }

    fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }

    fun nextMusic() {
        val nextMusic = if (currentMusic == repository!!.getAllMusics().lastIndex)
            repository!!.getMusic(0).id
        else
            repository!!.getMusic(++currentMusic).id

        val newMediaPlayer: MediaPlayer = MediaPlayer.create(context, nextMusic)
        playMediaPlayer(newMediaPlayer)
    }

    fun previousMusic() {
        val previousMusic = if (currentMusic == 0)
            repository!!.getMusic(repository!!.getAllMusics().lastIndex).id
        else
            repository!!.getMusic(--currentMusic).id

        val newMediaPlayer: MediaPlayer = MediaPlayer.create(context, previousMusic)
        playMediaPlayer(newMediaPlayer)
    }

    fun activeLoop() {
        mediaPlayer?.isLooping = true
    }

    fun setMusicRepository(repository: MusicRepository) {
        this.repository = repository
    }

    fun setMusic(musicId: Int) {
        val newMediaPlayer: MediaPlayer = MediaPlayer.create(context, musicId)
        playMediaPlayer(newMediaPlayer)
    }

    private fun playMediaPlayer(newPlayer: MediaPlayer) {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = newPlayer
        mediaPlayer?.start()
    }

    private fun createNotification(): Notification {
        val channelId = "MusicPlayerServiceChannel"
        val channelName = "Music Player Service"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }

        return NotificationCompat.Builder(this, channelId)
            .setContentTitle("Music Player")
            .setContentText("Playing music...")
            .setSmallIcon(R.drawable.logoyota_1)
            .build()
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }

    fun setMusicWithByteArray(bytes: ByteArray){
        try {
            val tempFile = File.createTempFile("temp_audio", ".mp3", context.cacheDir)
            tempFile.deleteOnExit()

            val fos = FileOutputStream(tempFile)
            fos.write(bytes)
            fos.close()

            val newMediaPlayer = MediaPlayer()
            newMediaPlayer.setDataSource(tempFile.absolutePath)

            newMediaPlayer.setOnCompletionListener {
                newMediaPlayer.release()
                tempFile.delete()
            }

            playMediaPlayer(newMediaPlayer)

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}