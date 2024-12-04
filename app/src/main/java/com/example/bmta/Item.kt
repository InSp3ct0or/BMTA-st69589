data class Item(
    override var name: String,
    override var position: Position,
    var pickedUp: Boolean = false,
    var health: Double = 0.0,
    var attack: Double = 0.0,
    var defense: Double = 0.0,
    var healing: Double = 0.0
) : GameObject() {

}
