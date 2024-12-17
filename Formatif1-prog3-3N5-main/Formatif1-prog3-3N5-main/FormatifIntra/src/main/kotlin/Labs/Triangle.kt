package org.benAmor

fun main() {
    println(triangle(5))
}
fun triangle(hauteur : Int) : String {
    var message=""
    for (i in 1..hauteur) {
        for (j in 1..i) {
            message+="*"
        }
        message+="\n"
    }
    return message
}
