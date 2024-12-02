package fp

import kotlin.system.exitProcess

data class Location(val name: String, val connections: Map<String, String>)

fun makeFunction(location: Location): () -> Unit = { ->
    fun getInput() {
        println("Which way do you want to go?")
        when (val dir = readln()) {
            "quit" -> exitProcess(0)
            in location.connections -> run {
                map[location.connections[dir]]?.let {
                    it()
                    return
                } ?: println("That location is not on the map yet.")
            }
            else -> println("You can't go in that direction.")
        }
        getInput()
    }

    println("You are at ${location.name}.")
    location.connections.forEach {
        println("To the ${it.key} is ${it.value}.")
    }
    getInput()
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
            "west" to "Orchard Meadow"
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

val map: Map<String, () -> Unit> = locations.associate {
    Pair(it.name, makeFunction(it))
}

fun main() {
    map.values.first()()
}
