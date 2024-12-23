package fichier

import java.io.File

fun main() {
    // Tu peux tester tes fonctions en les appellants ici.
    lire()
}

/**
 * (1 point) Affiche dans la console le contenu du fichier message.txt qui se trouve dans le projet de départ.
 */
fun lire() {
    println(File("message.txt").readLines())

}

/**
 * (1 point) S’il n’y a pas 2 éléments dans le paramètre args, affiche un message d'erreur, et retourne la valeur -1.
 * (1 point) Crée un fichier dans le répertoire du projet, dont le nom sera déterminé par le premier argument.
 *           Par exemple, si l’argument est « pipo.txt » l’application crée le fichier dans le dossier du projet avec le
 *           nom « pipo.txt ».
 * (1 point) Le fichier aura comme contenu le texte contenu dans le 2ème élément qui est dans le paramètre args.
 * Si tout s'est bien passé, on retourne la valeur 1.
 */
fun ecrire(args: Array<String>): Int {
    if(args.size!=2){
        println("Ta mère c'est une erreur")
        return -1
    }
    File(args[0]).printWriter().use { it.println(args[1]) }
    return 1
}