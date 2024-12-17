import java.io.File

fun main(args: Array<String>) {
    var nomFichier1: String = args[0]
    var nomFichier2: String = args[1]
    var fichier1: File = File(nomFichier1)
    if (fichier1.exists()) {
        try {
            var liste1: List<String> = fichier1.readText().split("\n")
            println(liste1)

            var liste1b: List<String> = fichier1.readLines()
            println(liste1b)

            var texte2: String = liste1.joinToString(separator = "\n-----------\n")
            println(texte2)

            var fichier2: File = File(nomFichier2)
            fichier2.writeText(texte2)
        } catch (e: Exception) {
            println("Le fichier $nomFichier1 ne peut pas être lu.")
        }
    }
}