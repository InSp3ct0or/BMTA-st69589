enum class Direction(val deltaRow: Int, val deltaCol: Int) {
    NORTH(-1, 0),
    SOUTH(1, 0),
    WEST(0, -1),
    EAST(0, 1),
    NORTHEAST(-1, 1),
    NORTHWEST(-1, -1),
    SOUTHEAST(1, 1),
    SOUTHWEST(1, -1);
}
