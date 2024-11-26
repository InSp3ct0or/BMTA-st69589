data class Hero(
    override var name: String = "Hero",
    override var position: Position,
) : Character() {
    var items = arrayListOf<Item>()

    constructor(
        name: String,
        position: Position,
        health: Double,
        attack: Double,
        defense: Double,
        healing: Double
    ) : this(name, position) {
        this.health = health
        this.attack = attack
        this.defense = defense
        this.healing = healing
    }

    // Метод для добавления предмета в инвентарь
    fun addItem(item: Item): String {
        items.add(item)
        item.pickedUp = true
        return "Předmět ${item.name} byl sebrán."
    }

    // Переопределенные геттеры для учета бонусов от предметов
    override var attack: Double = 1.2
        get() {
            var totalAttack = field
            for (item in this.items) totalAttack += item.attack
            return totalAttack
        }

    override var defense: Double = 1.0
        get() {
            var totalDefense = field
            for (item in this.items) totalDefense += item.defense
            return totalDefense
        }

    override var health: Double = 100.0
        get() {
            var totalHealth = field
            for (item in this.items) totalHealth += item.health
            return totalHealth
        }

    override var healing: Double = 0.5
        get() {
            var totalHealing = field
            for (item in this.items) totalHealing += item.healing
            return totalHealing
        }

    // Метод для вывода инвентаря
    override fun toString(): String {
        val itemNames = items.joinToString(", ") { it.name }
        return "Hrdina $name s inventářem: $itemNames"
    }

    // Метод для лечения героя
    fun heroHealing() {
        health += healing
    }

    // Метод для рубки леса
    fun cutDown(direction: Direction, gamePlan: GamePlan): String {
        // Рассчитываем новую позицию для рубки
        val newRow = position.row + direction.deltaRow
        val newCol = position.col + direction.deltaCol
        val newPosition = Position(newRow, newCol)

        // Проверяем, что позиция валидна и на ней находится лес
        val terrain = gamePlan.gameField.getTerrain(newPosition)
        return if (gamePlan.gameField.isValidPosition(newPosition) && terrain == Terrain.FOREST) {
            // Меняем лес на луг
            gamePlan.gameField.setTerrain(newPosition, Terrain.MEADOW)
            "Kácíte les směrem $direction a čistí ho."
        } else {
            "Tady není žádný les, který byste mohli pokácet."
        }
    }
}
