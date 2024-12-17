package youssefbenamor.tp2_youssefbenamor.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import youssefbenamor.tp2_youssefbenamor.NewSubjectActivity
import youssefbenamor.tp2_youssefbenamor.ResultActivity
import youssefbenamor.tp2_youssefbenamor.SubjectsActivity
import youssefbenamor.tp2_youssefbenamor.VotesActivity
import youssefbenamor.tp2_youssefbenamor.databinding.SubjectItemBinding

class SubjectAdapter :
    ListAdapter<String, SubjectAdapter.SubjectItemViewHolder>(SubjectItemDiffCallback) {

    // binding nous permet d'accéder à tout le champs de notre layout mon_item.xml et à tout le champs de notre layout activity_subjects.xml pour mon boutton
    inner class SubjectItemViewHolder(private val binding: SubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.TvSubject.text = item // On affiche l'élément dans le TextView
            binding.TvSubject.setOnClickListener {
                // Démarrer l'activité VotesActivity
                val intent: Intent = Intent(binding.root.context, VotesActivity::class.java)
                binding.root.context.startActivity(intent)
            }
            binding.btnResult.setOnClickListener {
                // Démarrer l'activité ResultActivity
                val intent: Intent = Intent(binding.root.context, ResultActivity::class.java)
                binding.root.context.startActivity(intent)
            }
        }
    }


//    inner class SubjectItemViewHolder(private val binding: SubjectItemBinding, private val binding2: SubjectsActivityBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(item: String) {
//            binding.TvSubject.text = item // On affiche l'élément dans le TextView
//            binding2..setOnClickListener {
//                val intent: Intent = Intent(binding.root.context, NewSubjectActivity::class.java)
//                // Démarrer l'activité NewSubjectActivity
//                binding.root.context.startActivity(intent)
//            }
//        }
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectItemViewHolder {
        val binding: SubjectItemBinding =
            SubjectItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SubjectItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SubjectItemViewHolder, position: Int) {
        val item: String = getItem(position)
        holder.bind(item)
    }

}

object SubjectItemDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}