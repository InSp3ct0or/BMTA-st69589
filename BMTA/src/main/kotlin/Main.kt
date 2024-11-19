fun main() {
    val rows = 20
    val cols = 20
    val numForest = 5

    fun readHeroName(): String {
        print("Enter the hero's name: ")
        return readLine() ?: "Hero"
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

    fun checkCommand(command: String): Boolean {
        val validCommands = listOf("north", "south", "west", "east", "northeast", "northwest", "southeast", "southwest")

        return if (command in validCommands) {
            true
        } else {
            println("Invalid command. Possible commands: ${validCommands.joinToString(", ")}")
            false
        }
    }

    fun readCommand(): String {
        var command: String
        do {
            print("Enter the command: ")
            command = readLine() ?: ""
        } while (!checkCommand(command))
        return command
    }


    while (true) {
        gamePlan.map(hero)
        val command = readCommand()

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


        hero.heroHealing()
    }




}
