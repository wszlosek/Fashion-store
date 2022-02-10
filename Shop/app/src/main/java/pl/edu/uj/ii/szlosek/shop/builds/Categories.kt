package pl.edu.uj.ii.szlosek.shop.builds

import pl.edu.uj.ii.szlosek.shop.models.Category

suspend fun getCategories(): List<Category> {
    return categoryService.getCategories()
}

suspend fun getCategory(id: Int): Category {
    return categoryService.getCategory(id)
}

suspend fun createCategory(category: Category) {
    return categoryService.createCategory(category)
}

suspend fun updateCategory(id: Int, category: Category) {
    return categoryService.updateCategory(id, category)
}

suspend fun deleteCategory(id: Int) {
    return categoryService.deleteCategory(id)
}