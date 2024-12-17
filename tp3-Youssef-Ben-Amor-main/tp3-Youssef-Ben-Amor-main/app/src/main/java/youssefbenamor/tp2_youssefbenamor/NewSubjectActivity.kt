package youssefbenamor.tp2_youssefbenamor

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.depinfo.sujetbd.Sujet
import org.depinfo.sujetbd.UtilitaireBD
import youssefbenamor.tp2_youssefbenamor.databinding.ActivitySubjectsBinding
import youssefbenamor.tp2_youssefbenamor.databinding.NewSubjectBinding
import youssefbenamor.tp2_youssefbenamor.services.YoussefService

class NewSubjectActivity : AppCompatActivity() {
    private lateinit var binding: NewSubjectBinding;
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        title="Nouveau sujet"
        super.onCreate(savedInstanceState)
        binding = NewSubjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupDrawer()
        binding.addButton.setOnClickListener {
            try {
                val service = YoussefService(UtilitaireBD.get(this))
                service.ajouterSujet(binding.editText.text.toString())
                val intent = Intent(this, SubjectsActivity::class.java).apply {
                    putExtra("newSubject", binding.editText.text.toString())
                }
                startActivity(intent)
            }
            catch (e: IllegalArgumentException){
                val errorMessage = when (e.message) {
                    "Sujet vide" -> "Sujet vide"
                    "Sujet trop court, 5 min" -> "Sujet trop court, 5 min"
                    "Sujet déjà existant" -> "Sujet déjà existant"
                    else -> "Erreur inconnue"
                }
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                binding.editText.error = errorMessage
            }
        }
    }

    private fun setupDrawer() {
        setupDrawerApplicationBar()
        setupDrawerItemSelected()
    }

    private fun setupDrawerApplicationBar() {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.dlTiroir,  R.string.ouvert, R.string.ferme)
        binding.dlTiroir.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }
    private fun setupDrawerItemSelected() {
        binding.nvTiroir.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.accueil_item -> {
                    //Navigation vers la page d'accueil "Accueil"
                    val intent = Intent(this, SubjectsActivity::class.java)
                    startActivity(intent)
                }
                R.id.ajouter_sujet_item -> {
                    //Navigation vers la page d'ajout de sujet "Ajouter un sujet"
                    val intent = Intent(this, NewSubjectActivity::class.java)
                    startActivity(intent)
                }
                R.id.supprimer_votes_item -> {
                    try {
                        val service = YoussefService(UtilitaireBD.get(this))
                        service.supprimerTousLesVotes()
                        val intent = Intent(this, SubjectsActivity::class.java)
                        startActivity(intent)
                    }
                    catch (e : Exception){
                        //Toat d'erreur avec le message d'erreur du service
                        val errorMessage = when (e.message) {
                            "Il n'y a pas de votes à supprimer" -> "Il n'y a pas de votes à supprimer"
                            else -> "Erreur inconnue"
                        }
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
                R.id.supprimer_sujets_item -> {
                    try {
                        val service = YoussefService(UtilitaireBD.get(this))
                        service.supprimerTousLesSujets()
                        val intent = Intent(this, SubjectsActivity::class.java)
                        startActivity(intent)
                    }
                    catch (e : Exception){
                        //Toat d'erreur avec le message d'erreur du service
                        val errorMessage = when (e.message) {
                            "Il n'y a pas de sujets à supprimer" -> "Il n'y a pas de sujets à supprimer"
                            else -> "Erreur inconnue"
                        }
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            false
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
        super.onConfigurationChanged(newConfig)
    }
}