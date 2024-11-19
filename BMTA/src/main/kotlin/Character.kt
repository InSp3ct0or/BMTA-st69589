abstract class Character : GameObject() {
    open var health: Double = 100.0
    open var attack: Double = 1.2
    open var defense: Double = 1.0

    fun isDead(): Boolean {
        return health <= 0.0
    }
}
