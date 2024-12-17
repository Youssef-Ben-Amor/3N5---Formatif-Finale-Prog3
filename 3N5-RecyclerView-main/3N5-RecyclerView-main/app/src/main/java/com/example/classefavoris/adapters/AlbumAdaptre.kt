package com.example.classefavoris.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.classefavoris.MainActivity2
import com.example.classefavoris.databinding.AlbumItemBinding
import com.example.classefavoris.models.Album

class AlbumAdaptre : ListAdapter<Album, AlbumAdaptre.AlbumItemViewHolder>(AlbumItemDiffCallback) {

    // binding nous permet d'accéder à tout le champs de notre layout personne_item.xml
    inner class AlbumItemViewHolder(private val binding: AlbumItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(album: Album) {
            binding.TvartistName.text = album.artistName
            binding.Tvname.text = album.name

            binding.btnDetails.setOnClickListener {
                val intent: Intent = Intent(binding.root.context, MainActivity2::class.java)
                intent.putExtra("id", album.id)
                intent.putExtra("AlbumName", album.name)
                intent.putExtra("artistName", album.artistName)

                // Démarrer l'activité SecondActivity
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumItemViewHolder {
        val binding: AlbumItemBinding = AlbumItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object AlbumItemDiffCallback : DiffUtil.ItemCallback<Album>() {
    override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
        return oldItem.name == newItem.name &&
                oldItem.artistName == newItem.artistName
    }
}