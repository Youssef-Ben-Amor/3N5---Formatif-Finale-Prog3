package com.example.classefavoris

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.classefavoris.adapters.AlbumAdaptre
import com.example.classefavoris.databinding.ActivityMainBinding
import com.example.classefavoris.models.Album

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AlbumAdaptre


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        fillRecycler()
    }

    private fun fillRecycler() {
        // Créer une liste d'éléments avec les ids et les noms des albums et l'artiste c'est XXXTENTACION avec ces chansons "Introduction (Instructions)", "ALONE, PART 3", "Moonlight!", "SAD!", "the remedy for a broken heart - why am I so in love", "Floor 555", "NUMB", "infinity - 888", "going down!", "Pain = BESTFRIEND", "$$$", "love yourself - interlude", "SMASH!", "I don't even speak spanish lol", "changes", "Hope", "schizophrenia", "before I close my eyes"
        val  artistName= "XXXTENTACION"
        val albums: MutableList<Album> = mutableListOf(
            Album(1, "Introduction (Instructions)",artistName),
            Album(2, "ALONE, PART 3", artistName),
            Album(3, "Moonlight!", artistName),
            Album(4, "SAD!", artistName),
            Album(5, "the remedy for a broken heart - why am I so in love", artistName),
            Album(6, "Floor 555", artistName),
            Album(7, "NUMB", artistName),
            Album(8, "infinity - 888 ", artistName),
            Album(9, "going down!", artistName),
            Album(10, "Pain = BESTFRIEND", artistName),
            Album(11, "$$$", artistName),
            Album(12, "love yourself - interlude", artistName),
            Album(13, "SMASH!", artistName),
            Album(14, "I don't even speak spanish lol", artistName),
            Album(15, "changes", artistName),
            Album(16, "Hope", artistName),
            Album(17, "schizophrenia", artistName),
            Album(18, "before I close my eyes", artistName)
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