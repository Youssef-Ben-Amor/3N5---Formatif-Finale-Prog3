package youssefbenamor.tp2_youssefbenamor

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import org.depinfo.sujetbd.UtilitaireBD
import youssefbenamor.tp2_youssefbenamor.databinding.ActivityResultBinding
import youssefbenamor.tp2_youssefbenamor.services.YoussefService


class IntegerValueFormatter : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        return value.toInt().toString()
    }
}

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        title="Stats"
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupDrawer()

        val id = intent.getLongExtra("id", 0)
        val sujet =YoussefService(UtilitaireBD.get(this)).sujetParId(id)
        binding.tvQuestion.text = sujet?.contenu
        val service = YoussefService(UtilitaireBD.get(this))
        val moyenne = service.moyenneVotes(id)
        val distribution = service.distributionVotes(id)
        binding.tvValue.text = "Moyenne des votes : $moyenne"
        val notes = distribution.mapKeys {
            entry ->
            when (entry.key) {
                1 -> "★"
                2 -> "★★"
                3 -> "★★★"
                4 -> "★★★★"
                5 -> "★★★★★"
                else -> "${entry.key}"
            }
        }
        setupPieChar(notes)
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

    private fun setupPieChar(notes: Map<String, Int>) {
        val chart = binding.chart
        // On traduit notre map en tableau de PieEntry, que notre PieChart s'attend à recevoir.
        val entries = notes.map { entry ->
            // Un PieEntry a besoin d'une valeur et d'une étiquette.
            // On va donc chercher les clés et les valeurs de notre map.
            PieEntry(entry.value.toFloat(), entry.key)
        }
        val dataSet = PieDataSet(entries, "Étoiles")
        // Recommendation : utilisez l'intellisens sur ColorTemplate pour trouver un ensemble de couleurs à votre goût.
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.asList()
        val data = PieData(dataSet)
        data.setValueTextColor(Color.WHITE)
        data.setValueTextSize(24f)
        data.setValueFormatter(IntegerValueFormatter())
        binding.chart.legend.isEnabled = false
        binding.chart.description.isEnabled = false
        chart.data = data
        // Toujours invalider le graphique après avoir modifié ses caractéristiques.
        chart.invalidate()
    }
}
