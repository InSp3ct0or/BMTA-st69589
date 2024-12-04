data class Enemy(
    override var name: String = "Enemy",
    override var position: Position,
    override var health: Double = 100.0,
    override var attack: Double = 5.0,
    override var defense: Double = 2.0
) : Character()
