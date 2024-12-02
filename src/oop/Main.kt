package oop

import kotlin.system.exitProcess

class Location(val name: String, val connections: Map<String, String>) {
    fun visit() {
        println("You are at $name.")
        connections.forEach {
            println("To the ${it.key} is ${it.value}.")
        }
        while (true) {
            println("Which way do you want to go?")
            when (val dir = readln()) {
                "quit" -> exitProcess(0)
                in connections -> run {
                    map[connections[dir]]?.let {
                        it.visit()
                        return
                    } ?: println("That location is not on the map yet.")
                }
                else -> println("You can't go in that direction.")
            }
        }
    }
}

val locations: List<Location> = listOf(
    Location(
        "GSB",
        mapOf(
            "northwest" to "Orchard Meadow",
            "northeast" to "Warren Olney",
            "south" to "Richards Road"
        )
    ),
    Location(
        "Warren Olney",
        mapOf(
            "southwest" to "GSB",
            "west" to "Orchard Meadow"
        )
    ),
    Location(
        "Orchard Meadow",
        mapOf(
            "east" to "Warren Olney",
            "southeast" to "GSB"
        )
    ),
    Location(
        "Richards Road",
        mapOf(
            "east" to "Shuttle stop",
            "south" to "Cowell",
            "north" to "GSB"
        )
    ),
    Location(
        "Cowell",
        mapOf(
            "north" to "Richards Road",
            "northeast" to "Shuttle stop"
        )
    )
)

val map: Map<String, Location> = locations.associateBy { it.name }

fun main() {
    locations.first().visit()
}
