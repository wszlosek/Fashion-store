package pl.edu.uj.ii.szlosek.shop.builds

import pl.edu.uj.ii.szlosek.shop.models.Color

suspend fun getColors(): List<Color> {
    return colorService.getColors()
}

suspend fun getColor(id: Int): Color {
    return colorService.getColor(id)
}

suspend fun createColor(color: Color) {
    return colorService.createColor(color)
}

suspend fun updateColor(id: Int, color: Color) {
    return colorService.updateColor(id, color)
}

suspend fun deleteColor(id: Int) {
    return colorService.deleteColor(id)
}