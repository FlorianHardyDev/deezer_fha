package com.fha.deezer.view.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.fha.deezer.R
import com.fha.deezer.view.ui.fragment.PlaylistFragment

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PlaylistFragment.newInstance())
                .commitNow()
        }
    }

}
