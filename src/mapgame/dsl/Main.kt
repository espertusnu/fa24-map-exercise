package mapgame.dsl

import kotlin.system.exitProcess

// credit: https://medium.com/@dumazy/writing-dsls-in-kotlin-part-1-7f5d2193f277

class LocationBuilder(val name: String) {
    var north: String? = null
    var south: String? = null // "Richards Road"
    var east: String? = null
    var west: String? = null
    var northeast: String? = null // "Warren Olney"
    var northwest: String? = null // "Orchard Meadow"
    var southeast: String? = null
    var southwest: String? = null

    fun build(): Location {
        val connections = mutableMapOf<String, String>()
        connections.apply {
            north?.let { put("north", it) }
            south?.let { put("south", it) }
            east?.let { put("east", it) }
            west?.let { put("west", it) }
            northeast?.let { put("northeast", it) }
            northwest?.let { put("northwest", it) }
            southeast?.let { put("southeast", it) }
            southwest?.let { put("southwest", it) }
        }

        return Location(name, connections)
    }
}

// map from names of locations, such as "GSB", to the Location objects.
val map: MutableMap<String, Location> = mutableMapOf()

class Location(val name: String, val connections: Map<String, String>) {
    init {
        // Add this new location to the map from names to locations.
        map[name] = this
    }

    // The caller is responsible for making sure that direction is a key in connections.
    // This will return the associated Location if it exists or null if it hasn't been
    // added to the map.
    private fun getDestination(direction: String): Location? {
        val destName = connections[direction] ?: throw IllegalArgumentException(direction)
        val destination = map[destName]
        return destination
    }

    fun visit() {
        println("You are at $name.")
        connections.forEach {
            println("To the ${it.key} is ${it.value}.")
        }
        var newLocation: Location? = null
        while (newLocation == null) {
            println("Which way do you want to go?")
            val direction = readLine()
            when (direction) {
                "quit" -> exitProcess(0)
                null -> println("Please enter a direction or quit.")
                in connections -> getDestination(direction)?.let {
                    newLocation = it
                } ?: println("That location isn't on the map yet.")

                else -> println("You can't go in that direction.")
            }
        }
        newLocation?.visit()
    }
}

fun location(name: String, block: LocationBuilder.() -> Unit): Location =
    LocationBuilder(name).apply(block).build()

fun main() {
    location("GSB") {
        northeast = "Orchard Meadow"
        northwest = "Warren Olney"
        south = "Richards Road"
    }
    location("Orchard Meadow") {
        east = "Warren Olney"
        southeast = "GSB"
    }
    location("Warren Olney") {
        southwest = "GSB"
        west = "Orchard Meadow"
    }
    location("Richards Road") {
        east = "Shuttle stop"
        south = "Cowell"
        north = "GSB"
    }
    location("Cowell") {
        north = "Richards Road"
        northeast = "Shuttle stop"
    }

    map.values.first().visit()
}
