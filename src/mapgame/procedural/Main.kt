package mapgame.procedural

import Location
import locations
import kotlin.system.exitProcess

val map: Map<String, Location> = locations.associateBy { it.name }

fun main() {
    var location: Location = locations.first()
    while (true) {
        describe(location)
        location = getNewLocation(location)
    }
}

private fun describe(location: Location) {
    println("You are at ${location.name}.")
    location.connections.forEach {
        println("To the ${it.key} is ${it.value}.")
    }
}

private fun getNewLocation(location: Location): Location {
    while (true) {
        println("Which way do you want to go?")
        val dir = readln()
        if (dir == "quit") exitProcess(0)
        val newLocationName = location.connections[dir]
        if (newLocationName == null) {
            println("You can't go in that direction.")
        } else {
            return map.getValue(newLocationName)
        }
    }
}
