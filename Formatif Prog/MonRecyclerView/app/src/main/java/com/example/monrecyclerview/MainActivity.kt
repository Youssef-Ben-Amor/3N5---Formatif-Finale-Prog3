package com.example.monrecyclerview

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.monrecyclerview.adapters.AlbumAdaptre
import com.example.monrecyclerview.databinding.ActivityMain2Binding
import com.example.monrecyclerview.databinding.ActivityMainBinding
import com.example.monrecyclerview.models.Android

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AlbumAdaptre


    override fun onCreate(savedInstanceState: Bundle?) {
        title="Versions"
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        fillRecycler()
    }

    private fun fillRecycler() {
        // Créer une liste d'éléments avec les ids et les noms des albums et l'artiste c'est XXXTENTACION avec ces chansons "Introduction (Instructions)", "ALONE, PART 3", "Moonlight!", "SAD!", "the remedy for a broken heart - why am I so in love", "Floor 555", "NUMB", "infinity - 888", "going down!", "Pain = BESTFRIEND", "$$$", "love yourself - interlude", "SMASH!", "I don't even speak spanish lol", "changes", "Hope", "schizophrenia", "before I close my eyes"
        val albums: MutableList<Android> = mutableListOf(
            Android(1, "Android 1.1","Petit Four"),
            Android(2, "Android 1.5", "Cupcake"),
            Android(3, "Android 2.0", "Eclair"),
            Android(4, "Android 3.0", "Hneycomb"),
            Android(5, "Android 4.0", "Ice Cream Sandwich"),
            Android(6, "Android 5.0", "Loillipop")
        )
        adapter.submitList(albums) // Pour changer le contenu de la liste, utiliser submitList de l'adapteur
    }

    private fun setupRecycler() {
        adapter = AlbumAdaptre() // Créer l'adapteur
        binding.rvAlbumAdaptre.adapter = adapter // Assigner l'adapteur au RecyclerView
        binding.rvAlbumAdaptre.setHasFixedSize(true) // Option pour améliorer les performances
        binding.rvAlbumAdaptre.addItemDecoration( // Ajouter un séparateur entre chaque élément
            DividerItemDecoration(
                binding.rvAlbumAdaptre.context, DividerItemDecoration.VERTICAL
            )
        )
    }
}