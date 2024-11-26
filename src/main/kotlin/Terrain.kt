enum class Terrain(val symbol: Char) {
    MEADOW(' '),
    FOREST('|'),
    RIVER('*'),
    BRIDGE('='),
    BORDER('#');

    override fun toString(): String = symbol.toString()
}
