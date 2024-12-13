package com.example.monrecyclerview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.monrecyclerview.adapters.AlbumAdaptre

import com.example.monrecyclerview.models.Android
import com.example.monrecyclerview.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {
    //créer une variable de type binfing pour mon acitivité 2
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val Version = intent.getStringExtra("Version")
        val VersionName = intent.getStringExtra("VersionName")
        if(Version != null && VersionName != null){
            val monExtra = Version + "\n " + VersionName
            binding.tvMonExtra.text = monExtra
        }
    }
}