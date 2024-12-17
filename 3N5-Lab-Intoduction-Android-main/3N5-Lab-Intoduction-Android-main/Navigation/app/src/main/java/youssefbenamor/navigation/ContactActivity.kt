package youssefbenamor.navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import youssefbenamor.navigation.databinding.ActivityContactBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Contact"
        super.onCreate(savedInstanceState)
        binding = ActivityContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAccueil.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("Name", binding.edtNom.text.toString())
            }
            startActivity(intent)
        }
    }
}