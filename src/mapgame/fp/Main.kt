package mapgame.fp

import Location
import locations
import kotlin.system.exitProcess

fun makeFunction(location: Location): () -> Unit =
    fun() {
        println("You are at ${location.name}.")
        location.connections.forEach {
            println("To the ${it.key} is ${it.value}.")
        }
        while (true) {
            println("Which way do you want to go?")
            when (val dir = readln()) {
                "quit" -> exitProcess(0)
                in location.connections -> run {
                    map[location.connections[dir]]?.let {
                        it()
                        return
                    } ?: println("That location is not on the map yet.")
                }

                else -> {
                    println("You can't go in that direction.")
                }
            }
        }
    }

val map: Map<String, () -> Unit> = locations.associate {
    Pair(it.name, makeFunction(it))
}

fun main() {
    map.values.first()()
}
