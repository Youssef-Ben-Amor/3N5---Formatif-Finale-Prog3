package youssefbenamor.tp2_youssefbenamor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import youssefbenamor.tp2_youssefbenamor.databinding.ActivitySubjectsBinding
import youssefbenamor.tp2_youssefbenamor.databinding.NewSubjectBinding

class NewSubjectActivity : AppCompatActivity() {
    private lateinit var binding: NewSubjectBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        title="Nouveau sujet"
        super.onCreate(savedInstanceState)
        binding = NewSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addButton.setOnClickListener {
            val intent = Intent(this, SubjectsActivity::class.java).apply {
                putExtra("newSubject", binding.editText.text.toString())
            }
            startActivity(intent)
        }
    }
}