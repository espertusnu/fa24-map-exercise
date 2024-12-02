package hardcoded
data class Location(val name: String, val connections: Map<String, String>)

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
