package org.benamor

fun main() {
    pyramide(6, 5, 4, 3, 2)
}
fun pyramide(vararg Hauteurs: Int) {
    for (Hauteur in Hauteurs) {
        var mess = ""
        var nbetoiles: Int = 1
        var nbespace: Int = Hauteur - 1
        var ligne = ""
        for (i in 1..Hauteur) {
            ligne = " ".repeat(nbespace) + "*".repeat(nbetoiles)
            mess += (ligne + "\n")
            nbetoiles += 2
            nbespace -= 1
        }
        print(mess)
    }
}