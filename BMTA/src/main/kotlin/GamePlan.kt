class GamePlan(private val gameField: GameField, private val numForest: Int) {
    private val random = java.util.Random()

    fun generate() {
        repeat(numForest) {
            generateForest()
        }
    }

    private fun generateForest() {
        val center = generateRandomPositionOnMeadow()
        if (center != null) {
            gameField.setTerrain(center, Terrain.FOREST)
            val neighbors = listOf(
                Position(center.row - 1, center.col),
                Position(center.row + 1, center.col),
                Position(center.row, center.col - 1),
                Position(center.row, center.col + 1)
            )
            for (neighbor in neighbors) {
                if (gameField.isMeadow(neighbor)) gameField.setTerrain(neighbor, Terrain.FOREST)
            }
        }
    }

    private fun generateRandomPositionOnMeadow(): Position? {
        var position: Position
        do {
            position = Position(random.nextInt(gameField.rows), random.nextInt(gameField.cols))
        } while (!gameField.isMeadow(position))
        return position
    }
}
