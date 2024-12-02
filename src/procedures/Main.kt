package procedures

import kotlin.system.exitProcess

fun orchardMeadow() {
    println("You are at Orchard Meadow.")
    println("To the east is Warren Olney.")
    println("To the southeast is GSB.")
    while (true) {
        println("Which way do you want to go?")
        val dir = readln()
        when (dir) {
            "southeast" -> run {
                gsb()
                return
            }
            "east" -> println("That location is not on the map yet.")
            "quit" -> exitProcess(0)
            else -> println("You can't go that direction.")
        }
    }
}

fun gsb() {
    println("You are at GSB.")
    println("To the northwest is Orchard Meadow.")
    println("To the northeast is Warren Olney.")
    println("To the south is Richards Road.")
    while (true) {
        println("Which way do you want to go?")
        val dir = readln()
        when (dir) {
            "northwest" -> run {
                orchardMeadow()
                return
            }
            "northeast", "south" ->
                println("That location is not on the map yet.")
            "quit" -> exitProcess(0)
            else -> println("You can't go that direction.")
        }
    }
}

fun main() {
   gsb()
}
