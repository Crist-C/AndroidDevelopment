package com.ccastro.androiddevelopment

enum class Difficulty {
    EASY, MEDIUM, HARD
}
/**
*   Nota: Una clase de datos debe tener al menos un parámetro en su constructor,
*   y todos los parámetros del constructor deben estar marcados con val o var.
*   Una clase de datos tampoco puede ser abstract, open, sealed ni inner.
 *
 *   Cuando una clase se define como una clase de datos, se implementan los siguientes métodos:
 *
 * equals()
 * hashCode() (verás este método cuando trabajes con ciertos tipos de colecciones)
 * toString()
 * componentN(): component1(), component2(), etc.
 * copy()
 */
data class Question<T>(
    val questionText: String,
    val answer: T,
    val difficulty: Difficulty
)

interface ProgressPrintable {
    var progressText: String
    fun printProgress()
}

class Quiz : ProgressPrintable {

    val question1 = Question<String>("Quoth the raven ___", "nevermore", Difficulty.HARD)
    val question2 = Question<Boolean>("The sky is green. True or false", false, Difficulty.EASY)
    val question3 = Question<Int>("How many days are there between full moons?", 2, Difficulty.MEDIUM)

    companion object StudentProcess {
        /**
         * Aunque estas propiedades se declaran en el objeto StudentProgress,
         * se puede acceder a ellas con notación de puntos usando solo el nombre de la clase Quiz.
         * */
        val totalQuestion: Int = 10
        val answered: Int = 3
    }

    override var progressText: String = ""
        get() = "$answered of $totalQuestion answered."
        set(value) {field = value}

    override fun printProgress() {
        repeat(answered) { print("▓")}
        repeat(totalQuestion - answered) { print("▒")}
        println()
        println(progressText)
    }

}

/**Nota: Las propiedades de extensión no pueden almacenar datos, por lo que deben ser de solo acceso. */
val Quiz.StudentProcess.progressTextExtensionVal: String
    get() = "$answered of $totalQuestion answered."

fun Quiz.StudentProcess.printProgressBarExtensionFun() {
    repeat(answered) { print("▓")}
    repeat(totalQuestion - answered) { print("▒")}
    println()
    println(Quiz.progressTextExtensionVal)
}

fun main() {

    val newQuiz = Quiz()

    // Metodos que se autosobrescriben al ser una data class
    println(newQuiz.question1.toString())
    println(newQuiz.question1.equals(newQuiz.question2))
    println(newQuiz.question1.equals(newQuiz.question1))

    // Accediendo a los atributos de la clase a travez de la  funcion componentN()
    println(newQuiz.question3.component1())
    println(newQuiz.question3.component2())
    println(newQuiz.question3.component3())

    Quiz.printProgressBarExtensionFun()

    newQuiz.printProgress()
}
