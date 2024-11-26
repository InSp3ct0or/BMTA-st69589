fun main() {
    val rows = 15
    val cols = 15
    val numForest = 3

    fun readHeroName(): String {
        print("Enter the hero's name: ")
        return readLine() ?: "Hero"
    }

    fun runCommand(command: String, hero: Hero, gamePlan: GamePlan) {
        val direction = when (command) {
            "north" -> Direction.NORTH
            "south" -> Direction.SOUTH
            "west" -> Direction.WEST
            "east" -> Direction.EAST
            "northeast" -> Direction.NORTHEAST
            "northwest" -> Direction.NORTHWEST
            "southeast" -> Direction.SOUTHEAST
            "southwest" -> Direction.SOUTHWEST
            else -> null
        }

        direction?.let {
            gamePlan.moveHero(hero, it)
        }

        if (command.startsWith("kacej")) {
            val directionStr = command.substring(5).toUpperCase()
            val direction = when (directionStr) {
                "NORTH" -> Direction.NORTH
                "SOUTH" -> Direction.SOUTH
                "WEST" -> Direction.WEST
                "EAST" -> Direction.EAST
                "NORTHEAST" -> Direction.NORTHEAST
                "NORTHWEST" -> Direction.NORTHWEST
                "SOUTHEAST" -> Direction.SOUTHEAST
                "SOUTHWEST" -> Direction.SOUTHWEST
                else -> null
            }

            direction?.let {
                val result = hero.cutDown(it, gamePlan)
                println(result)
            }
        }

        if (command.startsWith("pickUp")) {
            val directionStr = command.substring(6).toUpperCase()
            val direction = when (directionStr) {
                "NORTH" -> Direction.NORTH
                "SOUTH" -> Direction.SOUTH
                "WEST" -> Direction.WEST
                "EAST" -> Direction.EAST
                "NORTHEAST" -> Direction.NORTHEAST
                "NORTHWEST" -> Direction.NORTHWEST
                "SOUTHEAST" -> Direction.SOUTHEAST
                "SOUTHWEST" -> Direction.SOUTHWEST
                else -> null
            }

            direction?.let {
                gamePlan.collectItem(hero)
            }
        }
    }

    val gameField = GameField(rows, cols)
    val heroName = readHeroName()
    val hero = Hero(
        name = heroName,
        position = Position(10, 10),
        health = 100.0,
        attack = 10.0,
        defense = 5.0,
        healing = 0.5
    )

    val gamePlan = GamePlan(gameField, numForest)
    gamePlan.generate()
    gameField.display()

    fun getPossibleCommands(hero: Hero): List<String> {
        val possibleCommands = mutableListOf("north", "south", "west", "east", "northeast", "northwest", "southeast", "southwest")
        val directions = listOf(Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, Direction.NORTHEAST, Direction.NORTHWEST, Direction.SOUTHEAST, Direction.SOUTHWEST)

        for (direction in directions) {
            val newRow = hero.position.row + direction.deltaRow
            val newCol = hero.position.col + direction.deltaCol
            val newPosition = Position(newRow, newCol)

            if (gameField.isValidPosition(newPosition) && gameField.getTerrain(newPosition) == Terrain.FOREST) {
                possibleCommands.add("kacej" + direction.toString())
            }
        }

        return possibleCommands
    }

    fun checkCommand(command: String, hero: Hero): Boolean {
        val validCommands = getPossibleCommands(hero)

        return if (command in validCommands) {
            true
        } else {
            println("Invalid command. Possible commands: ${validCommands.joinToString(", ")}")
            false
        }
    }

    fun readCommand(hero: Hero): String {
        var command: String
        do {
            print("Enter the command: ")
            command = readLine() ?: ""
        } while (!checkCommand(command, hero))
        return command
    }

    while (true) {
        gamePlan.map(hero)
        val command = readCommand(hero)

        runCommand(command, hero, gamePlan)

        hero.heroHealing()
    }
}
