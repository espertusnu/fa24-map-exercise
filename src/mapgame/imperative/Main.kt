package mapgame.imperative

import Location
import locations

val map: Map<String, Location> = locations.associateBy { it.name }

fun main() {
    var location: Location = locations.first()
    while (true) {
        println("You are at ${location.name}.")
        location.connections.forEach {
            println("To the ${it.key} is ${it.value}.")
        }
        var newLocation: Location? = null
        while (newLocation == null) {
            println("Which way do you want to go?")
            val dir = readln()
            if (dir == "quit") return
            val newLocationName = location.connections[dir]
            if (newLocationName == null) {
                println("You can't go in that direction.")
            } else {
                newLocation = map[newLocationName]
            }
        }
        location = newLocation
    }
}
