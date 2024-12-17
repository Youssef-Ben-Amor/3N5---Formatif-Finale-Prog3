package com.example.classefavoris

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.classefavoris.adapters.AlbumAdaptre
import com.example.classefavoris.databinding.ActivityMain2Binding
import com.example.classefavoris.databinding.ActivityMainBinding
import com.example.classefavoris.models.Album


class MainActivity2 : AppCompatActivity() {
    //créer une variable de type binfing pour mon acitivité 2
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val AlbumName = intent.getStringExtra("AlbumName")
        val artistName = intent.getStringExtra("artistName")
        if(AlbumName != null && artistName != null){
            val monExtra = AlbumName + "\n " + artistName
            binding.tvMonExtra.text = monExtra
        }
    }
}