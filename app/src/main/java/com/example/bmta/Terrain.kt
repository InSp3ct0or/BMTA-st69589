import com.example.bmta.R

enum class Terrain(val symbol: Char, val imageResourceId: Int) {
    MEADOW('M', R.drawable.ic_meadow),  // Поле
    FOREST('F', R.drawable.ic_forest),  // Лес
    RIVER('R', R.drawable.ic_river),    // Река
    BRIDGE('B', R.drawable.ic_bridge),  // Мост
    BORDER('X', R.drawable.ic_border);  // Граница

    // Выводим символ для отображения
    override fun toString(): String = symbol.toString()
}
