package pl.edu.uj.ii.szlosek.shop.services

import pl.edu.uj.ii.szlosek.shop.models.Product
import retrofit2.Call
import retrofit2.http.*

interface ProductService {
    @GET("product")
    suspend fun getProducts() : List<Product>

    @GET("product/{id}")
    suspend fun getProduct(@Path("id") id: Int) : Product

    @POST("product")
    suspend fun createProduct(@Body product: Product)

    @PUT("product/{id}")
    suspend fun updateProduct(@Path("id") id: Int, @Body product: Product)

    @DELETE("product/{id}")
    suspend fun deleteProduct(@Path("id") id: Int)
}