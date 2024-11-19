class GamePlan(private val gameField: GameField, private val numForest: Int) {
    private val random = java.util.Random()
    private val enemies = mutableListOf<Enemy>()



    private fun generateEnemies(numEnemies: Int) {
        repeat(numEnemies) {
            val position = generateFreeRandomPositionOnMeadow()
            if (position != null) {
                val enemy = when (random.nextInt(3)) {
                    0 -> Enemy(
                        name = "Skeleton",
                        position = position,
                        health = 10.0,
                        attack = 3.0,
                        defense = 1.5
                    )
                    1 -> Enemy(
                        name = "Zombie",
                        position = position,
                        health = 15.0,
                        attack = 4.0,
                        defense = 2.0
                    )
                    2 -> Enemy(
                        name = "Orc",
                        position = position,
                        health = 20.0,
                        attack = 5.0,
                        defense = 3.0
                    )
                    else -> throw IllegalStateException("Unexpected enemy type")
                }
                enemies.add(enemy)
            }
        }
    }



    fun generate() {
        repeat(numForest) {
            generateForest()
        }
        generateEnemies(5)
    }


    fun Position.isFree(gameObjects: List<GameObject>): Boolean {
        return gameObjects.none { it.position == this }
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

    fun generateFreeRandomPositionOnMeadow(): Position? {
        var position: Position
        do {
            position = generateRandomPositionOnMeadow() ?: return null
        } while (!position.isFree(enemies))
        return position
    }


    fun map(hero: Hero) {
        for (row in 0 until gameField.rows) {
            for (col in 0 until gameField.cols) {
                val position = Position(row, col)

                // Hero
                when {
                    hero.position == position -> print("H")

                    // Enemy
                    enemies.any { it.position == position } -> {
                        val enemy = enemies.first { it.position == position }
                        when (enemy.name) {
                            "Orc" -> print("\u001B[31mN\u001B[0m")
                            "Zombie" -> print("\u001B[32mN\u001B[0m")
                            "Skeleton" -> print("\u001B[30mN\u001B[0m")
                            else -> print("?") // error
                        }
                    }

                    // Default map
                    else -> print(gameField.getTerrain(position)?.symbol ?: ' ')
                }
            }
            println()
        }
    }



    fun moveHero(hero: Hero, direction: Direction) {
        val newRow = hero.position.row + direction.deltaRow
        val newCol = hero.position.col + direction.deltaCol
        val newPosition = Position(newRow, newCol)

        if (gameField.isValidPosition(newPosition)) {
            val terrain = gameField.getTerrain(newPosition)
            if (terrain == Terrain.MEADOW || terrain == Terrain.BRIDGE) {
                hero.position = newPosition
            } else {
                println("You can't go through the woods or river")
            }
        } else {
            println("You can't go across the border")
        }
    }




}
