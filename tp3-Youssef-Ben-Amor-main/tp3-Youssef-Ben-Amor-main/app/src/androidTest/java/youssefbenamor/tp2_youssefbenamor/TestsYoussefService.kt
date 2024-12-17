package youssefbenamor.tp2_youssefbenamor

import android.database.sqlite.SQLiteConstraintException
import androidx.test.platform.app.InstrumentationRegistry
import org.depinfo.sujetbd.UtilitaireBD
import org.depinfo.sujetbd.Vote
import org.junit.Assert.*
import org.junit.Test
import youssefbenamor.tp2_youssefbenamor.services.YoussefService

class TestsYoussefService {
    @Test
    fun testAjouterSujet() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Test ajout sujet valide
        service.ajouterSujet("Sujet de test")
        assertFalse(service.listeSujets().isEmpty())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAjouterSujetVide() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Test ajout sujet vide
        service.ajouterSujet("")
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAjouterSujetTropCourt() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Test ajout sujet trop court
        service.ajouterSujet("Suj")
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAjouterSujetExistant() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Ajouter un sujet pour la première fois
        service.ajouterSujet("Sujet unique")

        // Tenter d'ajouter le même sujet une deuxième fois
        service.ajouterSujet("Sujet unique")
    }
    @Test
    fun testListeSujetsTrieParNombreDeVotes() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Ajouter des sujets
        service.ajouterSujet("Sujet 1")
        service.ajouterSujet("Sujet 2")
        service.ajouterSujet("Sujet 3")

        // Ajouter des votes
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant1", note = 4))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant2", note = 5))
        service.ajouterVote(Vote(sujetId = 2, nomVotant = "Votant3", note = 3))

        // Récupérer la liste des sujets triée par nombre de votes
        val sujetsTries = service.listeSujets()

        // Vérifier que la liste est triée correctement
        assertEquals("Sujet 1", sujetsTries[0].contenu)
        assertEquals("Sujet 2", sujetsTries[1].contenu)
        assertEquals("Sujet 3", sujetsTries[2].contenu)
    }
    @Test
    fun testAjouterMilleSujets() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Ajouter 1000 sujets
        for (i in 1..1000) {
            service.ajouterSujet("Sujet de test $i")
        }

        // Vérifier que 1000 sujets ont été ajoutés
        assertEquals(1000, service.listeSujets().size)
    }

    @Test
    fun testAjouterVoteValide() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Test ajout vote valide
        val vote = Vote(sujetId = 1, nomVotant = "VotantTest", note = 5)
        service.ajouterVote(vote)
        assertFalse(service.listeVotes().isEmpty())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAjouterVoteNomVotantTropCourt() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Test ajout vote avec nom votant trop court
        val vote = Vote(sujetId = 1, nomVotant = "abc", note = 5)
        service.ajouterVote(vote)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAjouterVoteExistant() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Ajouter un vote pour la première fois
        val vote = Vote(sujetId = 1, nomVotant = "VotantTest", note = 5)
        service.ajouterVote(vote)
        assertFalse(service.listeVotes().isEmpty())

        // Tenter d'ajouter le même vote une deuxième fois
        service.ajouterVote(vote)
    }
    @Test(expected = SQLiteConstraintException::class)
    fun testAjouterVoteAvecMauvaisId() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Test ajout vote avec un mauvais ID de sujet
        val vote = Vote(sujetId = 999, nomVotant = "VotantTest", note = 5)
        service.ajouterVote(vote)
    }
    @Test
    fun testDistributionVotesAucunVote() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Test distribution votes avec aucun vote
        val distribution = service.distributionVotes(1)
        assertTrue(distribution.isEmpty())
    }

    @Test
    fun testDistributionVotesAvecVotes() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")

        // Ajouter des votes
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant1", note = 3))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant2", note = 5))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant3", note = 3))

        // Test distribution votes avec des votes
        val distribution = service.distributionVotes(1)
        assertEquals(2, distribution[3])
        assertEquals(1, distribution[5])
    }
    @Test
    fun testMoyenneVotesAucunVote() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Test moyenne votes avec aucun vote
        val moyenne = service.moyenneVotes(1)
        assertEquals(0.0, moyenne, 0.0)
    }
    @Test(expected = IllegalArgumentException::class)
    fun testAjouterVoteNoteTropHaute() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Test ajout vote avec une note trop haute
        val vote = Vote(sujetId = 1, nomVotant = "VotantTest", note = 6)
        service.ajouterVote(vote)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testAjouterVoteNoteTropBasse() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Test ajout vote avec une note trop basse
        val vote = Vote(sujetId = 1, nomVotant = "VotantTest", note = -1)
        service.ajouterVote(vote)
    }
    @Test
    fun testMoyenneVotesAvecVotes() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")

        // Ajouter des votes
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant1", note = 3))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant2", note = 5))

        // Test moyenne votes avec des votes
        val moyenne = service.moyenneVotes(1)
        assertEquals(4.0, moyenne, 0.0)
    }
    @Test
    fun testMoyenneVotesAvecBeaucoupDeVotes() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")

        // Ajouter des votes
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant1", note = 3))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant2", note = 4))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant3", note = 5))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant4", note = 2))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant5", note = 3))

        // Test moyenne votes avec des votes ayant une moyenne non arrondie
        val moyenne = service.moyenneVotes(1)
        assertEquals(3.4, moyenne, 0.0)
    }
    @Test
    fun testAjouterMilleVotes() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")

        // Ajouter 1000 votes
        for (i in 1..1000) {
            val vote = Vote(sujetId = 1, nomVotant = "Votant$i", note = (i % 5) + 1)
            service.ajouterVote(vote)
        }

        // Vérifier que 1000 votes ont été ajoutés
        assertEquals(1000, service.listeVotes().size)
    }
    @Test(expected = SQLiteConstraintException::class)
    fun testMoyenneVotesAvecMauvaisId() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")

        // Ajouter des votes avec un mauvais ID de sujet
        service.ajouterVote(Vote(sujetId = 999, nomVotant = "Votant1", note = 3))
        service.ajouterVote(Vote(sujetId = 999, nomVotant = "Votant2", note = 4))

        // Test moyenne votes avec des votes ayant un mauvais ID
        service.moyenneVotes(999)
    }

    @Test(expected = SQLiteConstraintException::class)
    fun testDistributionVotesAvecMauvaisId() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")

        // Ajouter des votes avec un mauvais ID de sujet
        service.ajouterVote(Vote(sujetId = 999, nomVotant = "Votant1", note = 3))
        service.ajouterVote(Vote(sujetId = 999, nomVotant = "Votant2", note = 4))

        // Test distribution votes avec des votes ayant un mauvais ID
        service.distributionVotes(999)
    }
    @Test
    fun testAjouterVoteNomVotantLong() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")

        // Test ajout vote avec un nom de votant très long
        val vote = Vote(sujetId = 1, nomVotant = "VotantAvecUnNomTrèsTrèsLong", note = 4)
        service.ajouterVote(vote)
        assertFalse(service.listeVotes().isEmpty())
    }
    @Test
    fun testMoyenneVotesSansSujets() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Test moyenne votes sans sujets
        val moyenne = service.moyenneVotes(1)
        assertEquals(0.0, moyenne, 0.0)
    }
    @Test(expected = IllegalArgumentException::class)
    fun testAjouterVoteNoteZero() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")

        // Test ajout vote avec une note de zéro
        val vote = Vote(sujetId = 1, nomVotant = "VotantTest", note = 0)
        service.ajouterVote(vote)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSupprimerTousLesSujetsAucunSujet() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Test suppression de tous les sujets quand il n'y a aucun sujet
        service.supprimerTousLesSujets()
    }

    @Test
    fun testSupprimerTousLesSujetsAvecSujets() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))

        // Ajouter des sujets
        service.ajouterSujet("Sujet 1")
        service.ajouterSujet("Sujet 2")

        // Vérifier que les sujets ont été ajoutés
        assertFalse(service.listeSujets().isEmpty())

        // Supprimer tous les sujets
        service.supprimerTousLesSujets()

        // Vérifier que tous les sujets ont été supprimés
        assertTrue(service.listeSujets().isEmpty())
    }

    @Test(expected = IllegalArgumentException::class)
    fun testSupprimerTousLesVotesAucunVote() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Test suppression de tous les votes quand il n'y a aucun vote
        service.supprimerTousLesVotes()
    }

    @Test
    fun testSupprimerTousLesVotesAvecVotes() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val service = YoussefService(UtilitaireBD.getForTests(appContext))
        service.ajouterSujet("Sujet de test")
        // Ajouter des votes
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant1", note = 4))
        service.ajouterVote(Vote(sujetId = 1, nomVotant = "Votant2", note = 5))

        // Vérifier que les votes ont été ajoutés
        assertFalse(service.listeVotes().isEmpty())

        // Supprimer tous les votes
        service.supprimerTousLesVotes()

        // Vérifier que tous les votes ont été supprimés
        assertTrue(service.listeVotes().isEmpty())
    }
}