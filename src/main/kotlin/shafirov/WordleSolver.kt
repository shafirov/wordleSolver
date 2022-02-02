package shafirov

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val dict = File(if ("--russian" in args) "data/ru.txt" else "data/en.txt").readLines()

    val attempts = File("input.txt").readLines().map { it.split(' ') }.filter { it.size == 2 }.map { it[0] to it[1] }

    println("${attempts.size} attempts played:")
    for ((w, r) in attempts) {
        println("$w -> $r")

        if (w.length != 5) {
            println("Only 5 letter words accepted")
            exitProcess(1)
        }

        if (r.length != 5 || r.any { it !in setOf('g', 'b', 'y') }) {
            println("The response to the guess should be exacty 5 characters of: b - for black (retter does not exist), y - for yellow (exists but in a wrong position), g - for green (correct letter at the correct place)")
            exitProcess(1)
        }
    }


    val filter = WordMask().apply {
        attempts.forEach { learn(it.first, it.second) }
    }

    val candidates = dict.filter { w -> filter.matches(w) }


    println("${candidates.size} matches found: ")

    candidates.take(20).forEach {
        println(it)
    }
}