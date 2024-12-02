package mapgame.csv

data class Location(val name: String, val connections: MutableMap<String, String>)

fun buildMap(): Map<String, Location> {
    val map: MutableMap<String, Location> = mutableMapOf()
    java.io.File("map-data.csv").readLines().forEach { line: String ->
        val fields = line.split(", ")
        require(fields.size == 3)
        val start = fields[0]
        val dir = fields[1]
        val end = fields[2]
        var location = map[start]
        if (location == null) {
            location = Location(start, mutableMapOf(dir to end))
            map[start] = location
        } else {
            location.connections[dir] = end
        }
    }
    return map
}

fun main() {
    val map = buildMap()
    var location: Location = map.values.first()
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
            if (dir !in location.connections.keys) {
                println("You can't go in that direction.")
            } else {
                newLocation = location.connections[dir]?.let {
                    map[it]
                }
                if (newLocation == null) {
                    println("That location is not on the map yet.")
                }
            }
        }
        location = newLocation
    }
}
