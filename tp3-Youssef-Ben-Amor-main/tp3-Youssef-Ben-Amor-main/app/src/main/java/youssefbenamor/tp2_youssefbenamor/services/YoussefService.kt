package youssefbenamor.tp2_youssefbenamor.services

import android.content.Context
import org.depinfo.sujetbd.Sujet
import org.depinfo.sujetbd.SujetVoteDAO
import org.depinfo.sujetbd.SujetVoteDataBase
import org.depinfo.sujetbd.UtilitaireBD
import org.depinfo.sujetbd.Vote
import java.util.Optional

class YoussefService(db: SujetVoteDataBase) {
    private val dao : SujetVoteDAO = db.dao()

    fun ajouterSujet(contenu: String){
        // validation du contenu
        if (contenu.length == 0){
            throw IllegalArgumentException("Sujet vide")
        }
        if (contenu.length < 5){
            throw IllegalArgumentException("Sujet trop court, 5 min")
        }
        val existant : Optional<Sujet> = dao.sujetParContenu(contenu)
        if (existant.isPresent){
            throw IllegalArgumentException("Sujet déjà existant")
        }
        // Tout va bien, on peut ajouter le sujet dans la BD
        val sujet = Sujet()
        sujet.contenu = contenu
        dao.ajouterSujet(sujet)
    }

    fun ajouterVote(vote: Vote) {
        //Vérification de la note est entre 1 et 5
        if (vote.note < 1 || vote.note > 5)
            throw IllegalArgumentException("La note doit être entre 1 et 5.")
        // Vérification du nom du votant
        if (vote.nomVotant.trim().length < 4)
            throw IllegalArgumentException("Le nom du votant doit contenir au moins 4 caractères imprimables.")
        // Vérification de l'existence d'un vote pour ce sujet et cette personne
        val voteExistant = dao.votePourCeSujetCeVotant(vote.sujetId, vote.nomVotant)
        if (voteExistant.isPresent)
            throw IllegalArgumentException("Un vote existe déjà pour ce sujet et cette personne.")
        // Tout va bien, on peut ajouter le vote dans la BD
        dao.ajouterVote(vote)
    }

    fun listeSujets(): List<Sujet>{
        return dao.sujetsParOrdreDeVotes()
    }
    fun listeVotes(): List<Vote>{
        return dao.tousLesVotes()
    }

    fun moyenneVotes(id: Long): Double{
        val votes = dao.votesPourCeSujet(id)
        if (votes.isEmpty())  return 0.0
        return votes.map { it.note }.average()
    }

    fun distributionVotes(id: Long): Map<Int, Int>{
        val votes = dao.votesPourCeSujet(id)
        return votes.groupingBy { it.note }.eachCount()
    }

    fun supprimerTousLesSujets(){
        if(dao.sujetsParOrdreDeVotes().isEmpty()) throw IllegalArgumentException("Il n'y a pas de sujets à supprimer")
        return dao.supprimerTousLesSujets()
    }

    fun supprimerTousLesVotes(){
        if(dao.tousLesVotes().isEmpty()) throw IllegalArgumentException("Il n'y a pas de votes à supprimer")
        return dao.supprimerTousLesVotes()
    }

    fun sujetParId(id: Long): Sujet? {
        return dao.sujetParSonID(id)
    }
}