data class Hero(
    override var name: String = "Hero",
    override var position: Position,
    var healing: Double = 0.5
) : Character() {
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

    fun heroHealing() {
        health += healing
    }
}
