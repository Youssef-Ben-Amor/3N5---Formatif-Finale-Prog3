package youssefbenamor.tp2_youssefbenamor

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import youssefbenamor.tp2_youssefbenamor.databinding.ActivityVotesBinding
import youssefbenamor.tp2_youssefbenamor.databinding.NewSubjectBinding

class VotesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVotesBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        title="Voter"
        super.onCreate(savedInstanceState)
        binding = ActivityVotesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.VoteButton.setOnClickListener {
            val intent = Intent(this, SubjectsActivity::class.java).apply {
                putExtra("newSubject", binding.edtText.text.toString())
            }
            startActivity(intent)
        }
    }
}