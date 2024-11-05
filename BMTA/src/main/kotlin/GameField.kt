class GameField(val rows: Int, val cols: Int) {

    private val field: Array<Array<Terrain>> = Array(rows) { Array(cols) { Terrain.MEADOW } }

    init {
        for (row in 0 until rows) {
            field[row][0] = Terrain.BORDER
            field[row][cols - 1] = Terrain.BORDER
        }
        for (col in 0 until cols) {
            field[0][col] = Terrain.BORDER
            field[rows - 1][col] = Terrain.BORDER
        }
        generateRiver()
        generateBridge()
    }

    fun setTerrain(position: Position, terrain: Terrain) {
        if (isValidPosition(position)) {
            field[position.row][position.col] = terrain
        }
    }

    fun getTerrain(position: Position): Terrain? {
        return if (isValidPosition(position)) field[position.row][position.col] else null
    }

    fun isMeadow(position: Position): Boolean {
        return isValidPosition(position) && field[position.row][position.col] == Terrain.MEADOW
    }

    private fun isValidPosition(position: Position): Boolean {
        return position.row in 0 until rows && position.col in 0 until cols
    }

    private fun generateRiver() {
        val riverRow = rows / 2
        for (col in 1 until cols - 1) {
            field[riverRow][col] = Terrain.RIVER
        }
    }

    private fun generateBridge() {
        val riverRow = rows / 2
        val bridgeCol = (1 until cols - 1).random()
        field[riverRow][bridgeCol] = Terrain.BRIDGE
    }

    fun display() {
        for (row in field) {
            for (cell in row) {
                print(cell)
            }
            println()
        }
    }
}
