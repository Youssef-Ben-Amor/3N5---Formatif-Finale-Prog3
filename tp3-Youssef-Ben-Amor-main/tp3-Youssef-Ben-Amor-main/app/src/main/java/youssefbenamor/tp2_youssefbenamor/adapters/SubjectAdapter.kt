package youssefbenamor.tp2_youssefbenamor.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import youssefbenamor.tp2_youssefbenamor.ResultActivity
import youssefbenamor.tp2_youssefbenamor.VotesActivity
import youssefbenamor.tp2_youssefbenamor.databinding.SubjectItemBinding
import org.depinfo.sujetbd.Sujet

class SubjectAdapter :
    ListAdapter<Sujet, SubjectAdapter.SubjectItemViewHolder>(SubjectItemDiffCallback) {

    inner class SubjectItemViewHolder(private val binding: SubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Sujet) {
            binding.TvSubject.text = item.contenu // On affiche l'élément dans le TextView
            binding.TvSubject.setOnClickListener {
                // Démarrer l'activité VotesActivity
                val intent = Intent(binding.root.context, VotesActivity::class.java)
                intent.putExtra("id", item.id)
                binding.root.context.startActivity(intent)
            }
            binding.btnResult.setOnClickListener {
                // Démarrer l'activité ResultActivity
                val intent = Intent(binding.root.context, ResultActivity::class.java)
                intent.putExtra("id", item.id)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItemViewHolder {
        val binding: SubjectItemBinding =
            SubjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectItemViewHolder, position: Int) {
        val item: Sujet = getItem(position)
        holder.bind(item)
    }
}

object SubjectItemDiffCallback : DiffUtil.ItemCallback<Sujet>() {
    override fun areItemsTheSame(oldItem: Sujet, newItem: Sujet): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Sujet, newItem: Sujet): Boolean {
        return oldItem == newItem
    }
}