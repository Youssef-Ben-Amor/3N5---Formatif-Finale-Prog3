package org.ballestero

import kotlin.random.Random

fun main(){
    val tableau = creeTableauAleatoire(100)
    stat(tableau)
}

fun creeTableauAleatoire(n: Int): Array<Int>{
    return  Array(n) {Random.nextInt(0, 101)}
}

fun stat(tableau: Array<Int>){
    val somme = tableau.sum()
    val moyenne = tableau.average()
    val max = tableau.max()
    val min = tableau.min()

    println("Somme : " + somme)
    println("Moyenne : " + moyenne)
    println("Max : " + max)
    println("Min : " + min)
}