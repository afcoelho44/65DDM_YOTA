package com.example.yota.ui.player

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.yota.R
import com.example.yota.ui.player.ui.player.PlayerFragment

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PlayerFragment.newInstance())
                .commitNow()
        }
    }
}