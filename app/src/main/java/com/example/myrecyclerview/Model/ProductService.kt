package com.example.myrecyclerview.Model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("products?limit=10")
    fun getProducts(): Call<List<Product>>
    @GET("products/{id}")
    fun getProductById(@Path("id") productId: Int): Call<Product>
}
