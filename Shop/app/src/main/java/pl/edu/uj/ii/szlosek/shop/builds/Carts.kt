package pl.edu.uj.ii.szlosek.shop.builds

import pl.edu.uj.ii.szlosek.shop.models.Cart

suspend fun getCarts(): List<Cart> {
    return cartService.getCarts()
}

suspend fun getCart(id: Int): Cart {
    return cartService.getCart(id)
}

suspend fun createCart(cart: Cart) {
    return cartService.createCart(cart)
}

suspend fun updateCart(id: Int, cart: Cart) {
    return cartService.updateCart(id, cart)
}

suspend fun deleteCart(id: Int) {
    return cartService.deleteCart(id)
}