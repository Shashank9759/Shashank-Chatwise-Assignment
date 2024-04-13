package com.studies.chatwiseassignment.Api

import com.studies.chatwiseassignment.Models.Products
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface productAPI {
    @GET("/products")
    suspend fun getProducts() : Response<Products>
}