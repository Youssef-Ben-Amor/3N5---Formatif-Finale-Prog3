package youssefbenamor.navigation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import youssefbenamor.navigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Accueil"
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = 10

        val Name = intent.getStringExtra("Name")
        if (Name != null){
            binding.txt1.setText("Bonjour $Name")
        }
        else{
            binding.txt1.setText("Bonjour X")
        }

        binding.btn1.setOnClickListener {
            val intent = Intent(this, AutreActivity::class.java)
            intent.putExtra("numberPicker", binding.numberPicker.value)
            startActivity(intent)
        }
        binding.btn2.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            startActivity(intent)
        }
    }
}