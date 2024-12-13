package com.example.monrecyclerview.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.monrecyclerview.MainActivity2
import com.example.monrecyclerview.databinding.AlbumItemBinding
import com.example.monrecyclerview.models.Android

class AlbumAdaptre : ListAdapter<Android, AlbumAdaptre.AlbumItemViewHolder>(AlbumItemDiffCallback) {

    // binding nous permet d'accéder à tout le champs de notre layout personne_item.xml
    inner class AlbumItemViewHolder(private val binding: AlbumItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(andoid: Android) {
            binding.TvversionoName.text = andoid.VersionName
            binding.Tvname.text = andoid.Version

            binding.btnDetails.setOnClickListener {
                val intent: Intent = Intent(binding.root.context, MainActivity2::class.java)
                intent.putExtra("id", andoid.id)
                intent.putExtra("Version", andoid.Version)
                intent.putExtra("VersionName", andoid.VersionName)

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

object AlbumItemDiffCallback : DiffUtil.ItemCallback<Android>() {
    override fun areItemsTheSame(oldItem: Android, newItem: Android): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Android, newItem: Android): Boolean {
        return oldItem.Version == newItem.Version &&
                oldItem.VersionName == newItem.VersionName
    }
}