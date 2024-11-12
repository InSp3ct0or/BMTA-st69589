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

    fun map(hero: Hero) {
        for (row in 0 until gameField.rows) {
            for (col in 0 until gameField.cols) {
                if (hero.row == row && hero.col == col) {
                    print("H")
                } else {
                    print(gameField.getTerrain(Position(row, col))?.symbol ?: ' ')
                }
            }
            println()
        }
    }

    fun moveHero(hero: Hero, direction: Direction) {
        val newRow = hero.row + direction.deltaRow
        val newCol = hero.col + direction.deltaCol
        val newPosition = Position(newRow, newCol)

        if (gameField.isValidPosition(newPosition)) {
            val terrain = gameField.getTerrain(newPosition)
            if (terrain == Terrain.MEADOW || terrain == Terrain.BRIDGE) {
                hero.row = newRow
                hero.col = newCol
            } else {
                println("You can't go through the woods or river")
            }
        } else {
            println("You can't go across the border")
        }
    }



}
