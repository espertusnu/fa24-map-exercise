package mapgame.procedures

import kotlin.system.exitProcess

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

            "northeast" -> run {
                warrenOlney()
                return
            }

            "south" -> run {
                richardsRoad()
                return
            }

            "quit" -> exitProcess(0)
            else -> println("You can't go that direction.")
        }
    }
}

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

            "east" -> run {
                warrenOlney()
                return
            }

            "quit" -> exitProcess(0)
            else -> println("You can't go that direction.")
        }
    }
}

fun warrenOlney() {
    println("You are at Warren Olney.")
    println("To the west is Orchard Meadow.")
    println("To the southwest is GSB.")
    while (true) {
        println("Which way do you want to go?")
        val dir = readln()
        when (dir) {
            "southwest" -> run {
                gsb()
                return
            }

            "west" -> run {
                orchardMeadow()
                return
            }

            "quit" -> exitProcess(0)
            else -> println("You can't go that direction.")
        }
    }
}

fun main() {
    gsb()
}

fun richardsRoad() {
    TODO("Implement Richards Road")
}


