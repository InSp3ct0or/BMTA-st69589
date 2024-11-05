fun main() {
    val rows = 20
    val cols = 20
    val numForest = 4


    val gameField = GameField(rows, cols)


    val gamePlan = GamePlan(gameField, numForest)
    gamePlan.generate()


    gameField.display()
}
