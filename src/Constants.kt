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
            "east" to "Shuttle Stop",
            "south" to "Cowell",
            "north" to "GSB"
        )
    ),
    Location(
        "Cowell",
        mapOf(
            "north" to "Richards Road",
            "northeast" to "Shuttle Stop"
        )
    ),
    Location(
        "Shuttle Stop",
        mapOf(
            "west" to "Richards Road",
            "southwest" to "Cowell",
        )
    )
)