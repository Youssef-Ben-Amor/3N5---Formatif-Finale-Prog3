package com.example.tapelelapin

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tapelelapin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding;
    private lateinit var Listedesboutoons: List<Button>
    var scoretops = 0
    var scoreflops = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvTops.setOnClickListener(View.OnClickListener {
            println("cucurella")
            binding.tvTops.setText("Lapin")
        })
        Listedesboutoons = listOf(
            binding.btn1,
            binding.btn2,
            binding.btn3,
            binding.btn4,
            binding.btn5,
            binding.btn6,
            binding.btn7,
            binding.btn8,
            binding.btn9
        )
        for (btn in Listedesboutoons) {
            btn.setOnClickListener({
                reagirAuClic(it)
            })
        }
        intitialiser()

    }

    fun intitialiser() {
        //mettre tous les autres à taupe
        for (btn in Listedesboutoons) {
            btn.setText("Taupe")
        }
        // choisir un bouton au hazard dans la liste
        val BoutonLapin: Button = Listedesboutoons.random()
        BoutonLapin.setText("Lapin")
    }

    fun reagirAuClic(it: View) {
        val boutonClique: Button = it as Button
        if (boutonClique.text == "Lapin") {
            boutonClique.setText("Taupe")
            intitialiser()
            scoretops++
        } else {
            scoreflops++
        }
        binding.tvTops.setText("Tops:  $scoretops")
        binding.tvFlops.setText("Flops:  $scoreflops")

    }
}