package pl.edu.uj.ii.szlosek.shop.builds

import pl.edu.uj.ii.szlosek.shop.models.Product

suspend fun getProducts(): List<Product> {
    return productService.getProducts()
}

suspend fun getProduct(id: Int): Product {
    return productService.getProduct(id)
}

suspend fun createProduct(product: Product) {
    return productService.createProduct(product)
}

suspend fun updateProduct(id: Int, product: Product) {
    return productService.updateProduct(id, product)
}

suspend fun deleteProduct(id: Int) {
    return productService.deleteProduct(id)
}