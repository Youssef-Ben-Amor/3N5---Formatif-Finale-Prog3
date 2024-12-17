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
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.snackbar.Snackbar
import org.depinfo.sujetbd.UtilitaireBD
import youssefbenamor.tp2_youssefbenamor.adapters.SubjectAdapter
import youssefbenamor.tp2_youssefbenamor.databinding.ActivitySubjectsBinding
import youssefbenamor.tp2_youssefbenamor.databinding.NavHeaderBinding
import youssefbenamor.tp2_youssefbenamor.services.YoussefService

class SubjectsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubjectsBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var adapter: SubjectAdapter // L'adapteur est accessible partout dans notre classe

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Sujets"
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        fillRecycler()
        setupDrawer()
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

    private fun setupRecycler() {
        adapter = SubjectAdapter() // Créer l'adapteur
        binding.rvSubjectAdapter.adapter = adapter // Assigner l'adapteur au RecyclerView
        binding.rvSubjectAdapter.setHasFixedSize(true) // Option pour améliorer les performances
        binding.rvSubjectAdapter.addItemDecoration( // Ajouter un séparateur entre chaque élément
            DividerItemDecoration(
                binding.rvSubjectAdapter.context, DividerItemDecoration.VERTICAL
            )
        )
        binding.btnAddSubject.setOnClickListener {
            // Démarrer l'activité NewSubjectActivity
            val intent: Intent = Intent(binding.root.context, NewSubjectActivity::class.java)
            binding.root.context.startActivity(intent)
        }
    }

    private fun fillRecycler() {
        try {
            // Retrieve the list of subjects sorted by the number of votes from the service
            val service = YoussefService(UtilitaireBD.get(this))
            val sujets = service.listeSujets()
            // Submit the list of subjects to the adapter
            adapter.submitList(sujets)
        } catch (e: Exception) {
            // Display an error message
            Toast.makeText(this, "Erreur inconnue", Toast.LENGTH_SHORT).show()
        }
    }
}