package youssefbenamor.navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import youssefbenamor.navigation.databinding.ActivityAutreBinding

class AutreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAutreBinding;

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityAutreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // définir une variable nommé numberPicker qui récupére la valeur de numberPicker selectionné
        val numberPicker = intent.getIntExtra("numberPicker", 0)
        title= "Article #$numberPicker"

        binding.btnContact.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
    }
}