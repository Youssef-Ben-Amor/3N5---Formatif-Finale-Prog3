package youssefbenamor.tp2_youssefbenamor

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import youssefbenamor.tp2_youssefbenamor.adapters.SubjectAdapter
import youssefbenamor.tp2_youssefbenamor.databinding.ActivitySubjectsBinding

class SubjectsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySubjectsBinding
    private lateinit var adapter: SubjectAdapter // L'adapteur est accessible partout dans notre classe

    override fun onCreate(savedInstanceState: Bundle?) {
        title = "Sujets"
        super.onCreate(savedInstanceState)
        binding = ActivitySubjectsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecycler()
        fillRecycler()
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
        // Faire une liste nommé items de 100 sujet court de moyenne
        val items: MutableList<String> = mutableListOf()
//        for (i in 1..100) {
//            items.add("Quelle est la moyenne entre $i et ${i + 10} ?")
//        }
        items.add("Habib Bourguiba - Premier président de la Tunisie après l'indépendance.")
        items.add("Zine El Abidine Ben Ali - Ancien président, connu pour son long mandat.")
        items.add("Tahar Haddad - Réformateur et homme de lettres.")
        items.add("Abdelwaheb Meddeb - Écrivain et poète.")
        items.add("Hédi Baccouche - Politicien et ancien ministre.")
        items.add("Salah Mejri - Joueur de basketball, a joué en NBA.")
        items.add("Youssef Chahed - Ancien Premier ministre.")
        items.add("Rim Sassi - Artiste et chanteuse.")
        items.add("Miriam Ben Salem - Actrice et animatrice de télévision.")
        items.add("Alaaeldin Mkhazni - Musicien et compositeur.")
        items.add("Amine Gouiri - Footballeur professionnel.")
        items.add("Khaled - Chanteur, connu comme le \"roi du raï\".")
        items.add("Dali Bouzid - Réalisateur et scénariste.")
        items.add("Latifa - Chanteuse populaire en arabe.")
        items.add("Rached Ghannouchi - Politicien, leader du mouvement Ennahda.")
        items.add("Sofiane Chahed - Artiste de rap.")
        items.add("Noura Ben Salah - Réformatrice et activiste.")
        items.add("Abdelaziz Ben Dhia - Philosophe et intellectuel.")
        items.add("Khalil Gibran - Poète et écrivain d'origine libanaise, mais ayant des liens en Tunisie.")
        items.add("Mahmoud Darwich - Poète palestinien, souvent associé à la culture tunisienne.")
        items.add("Habib Selmi - Écrivain et poète.")
        items.add("Hédi Kaddour - Écrivain et poète.")
        items.add("Hédi Thabet - Acteur et humoriste.")
        items.add("Hédi Donia - Chanteur et musicien.")
        items.add("Hédi Jouini - Chanteur et musicien.")
        items.add("Hédi Guella - Chanteur et musicien.")
        items.add("Hédi Habbouba - Chanteur et musicien.")
        items.add("Hédi Tounsi - Chanteur et musicien.")
        items.add("Hédi Lassoued - Chanteur et musicien.")
        items.add("Hédi Kallel - Chanteur et musicien.")
        items.add("Hédi Ben Sedrine - Chanteur et musicien.")
        items.add("Hédi Ben Nasr - Chanteur et musicien.")
        items.add("Hédi Ben Gharbia - Chanteur et musicien.")
        items.add("Hédi Ben Mbarek - Chanteur et musicien.")
        items.add("Hédi Ben Amor - Chanteur et musicien.")
        items.add("Hédi Ben Ali - Chanteur et musicien.")
        items.add("Hédi Ben Aissa - Chanteur et musicien.")
        items.add("Hédi Ben Abdallah - Chanteur et musicien.")
        items.add("Hédi Ben Ammar - Chanteur et musicien.")
        items.add("Hédi Ben Ayed - Chanteur et musicien.")
        items.add("Hédi Ben Azzouz - Chanteur et musicien.")
        items.add("Hédi Ben Boubaker - Chanteur et musicien.")
        items.add("Hédi Ben Brahim - Chanteur et musicien.")
        items.add("Hédi Ben Chedly - Chanteur et musicien.")
        items.add("Hédi Ben Dhia - Chanteur et musicien.")
        items.add("Hédi Ben Fadhel - Chanteur et musicien.")
        items.add("Hédi Ben Farhat - Chanteur et musicien.")
        items.add("Hédi Ben Gara - Chanteur et musicien.")
        items.add("Hédi Ben Ghali - Chanteur et musicien.")
        items.add("Hédi Ben Hassen - Chanteur et musicien.")
        items.add("Hédi Ben Jemaa - Chanteur et musicien.")
        items.add("Hédi Ben Kacem - Chanteur et musicien.")
        items.add("Tahar Haddad - Réformateur et homme de lettres.")
        items.add("Abdelwaheb Meddeb - Écrivain et poète.")
        items.add("Hédi Baccouche - Politicien et ancien ministre.")
        items.add("Salah Mejri - Joueur de basketball, a joué en NBA.")
        items.add("Youssef Chahed - Ancien Premier ministre.")
        items.add("Rim Sassi - Artiste et chanteuse.")
        items.add("Miriam Ben Salem - Actrice et animatrice de télévision.")
        items.add("Alaaeldin Mkhazni - Musicien et compositeur.")
        items.add("Amine Gouiri - Footballeur professionnel.")
        items.add("Khaled - Chanteur, connu comme le \"roi du raï\".")
        items.add("Dali Bouzid - Réalisateur et scénariste.")
        items.add("Latifa - Chanteuse populaire en arabe.")
        items.add("Rached Ghannouchi - Politicien, leader du mouvement Ennahda.")
        items.add("Sofiane Chahed - Artiste de rap.")
        items.add("Noura Ben Salah - Réformatrice et activiste.")
        items.add("Abdelaziz Ben Dhia - Philosophe et intellectuel.")
        items.add("Khalil Gibran - Poète et écrivain d'origine libanaise, mais ayant des liens en Tunisie.")
        items.add("Mahmoud Darwich - Poète palestinien, souvent associé à la culture tunisienne.")
        items.add("Habib Selmi - Écrivain et poète.")
        items.add("Hédi Kaddour - Écrivain et poète.")
        items.add("Hédi Thabet - Acteur et humoriste.")
        items.add("Hédi Donia - Chanteur et musicien.")
        items.add("Hédi Jouini - Chanteur et musicien.")
        items.add("Hédi Guella - Chanteur et musicien.")
        items.add("Hédi Habbouba - Chanteur et musicien.")
        items.add("Hédi Tounsi - Chanteur et musicien.")
        items.add("Hédi Lassoued - Chanteur et musicien.")
        items.add("Hédi Kallel - Chanteur et musicien.")
        items.add("Hédi Ben Sedrine - Chanteur et musicien.")
        items.add("Hédi Ben Nasr - Chanteur et musicien.")
        items.add("Hédi Ben Gharbia - Chanteur et musicien.")
        items.add("Hédi Ben Mbarek - Chanteur et musicien.")
        items.add("Hédi Ben Amor - Chanteur et musicien.")
        items.add("Hédi Ben Ali - Chanteur et musicien.")
        items.add("Hédi Ben Aissa - Chanteur et musicien.")
        items.add("Hédi Ben Abdallah - Chanteur et musicien.")
        items.add("Hédi Ben Ammar - Chanteur et musicien.")
        items.add("Hédi Ben Ayed - Chanteur et musicien.")
        items.add("Hédi Ben Azzouz - Chanteur et musicien.")
        items.add("Hédi Ben Boubaker - Chanteur et musicien.")
        items.add("Hédi Ben Brahim - Chanteur et musicien.")
        items.add("Hédi Ben Chedly - Chanteur et musicien.")
        items.add("Hédi Ben Dhia - Chanteur et musicien.")
        items.add("Hédi Ben Fadhel - Chanteur et musicien.")
        items.add("Hédi Ben Farhat - Chanteur et musicien.")
        items.add("Hédi Ben Gara - Chanteur et musicien.")
        items.add("Hédi Ben Ghali - Chanteur et musicien.")
        items.add("Hédi Ben Hassen - Chanteur et musicien.")
        items.add("Hédi Ben Jemaa - Chanteur et musicien.")
        items.add("Khalil Gibran - Poète et écrivain d'origine libanaise, mais ayant des liens en Tunisie.")
        items.add("Mahmoud Darwich - Poète palestinien, souvent associé à la culture tunisienne.")
        adapter.submitList(items) // Pour changer le contenu de la liste, utiliser submitList de l'adapteur
    }
}