package pl.edu.uj.ii.szlosek.shop.builds

import pl.edu.uj.ii.szlosek.shop.models.ShopLocalization

suspend fun getShops(): List<ShopLocalization> {
    return shopLocalizationService.getShops()
}

suspend fun getShop(id: Int): ShopLocalization {
    return shopLocalizationService.getShop(id)
}

suspend fun createShop(shop: ShopLocalization) {
    return shopLocalizationService.createShop(shop)
}

suspend fun updateShop(id: Int, shop: ShopLocalization) {
    return shopLocalizationService.updateShop(id, shop)
}

suspend fun deleteShop(id: Int) {
    return shopLocalizationService.deleteShop(id)
}
