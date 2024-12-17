fun main() {
    var question: String = "Quel est votre langage de programmation préféré?"
    while (true) {
        println(question)
        var reponse: String = readln()
        if (reponse == "Kotlin") {
            println("Merci, votre langage préféré est effectivement ${reponse}!")
            return
        } else {
            println("Mauvaise réponse!")
        }
    }
}