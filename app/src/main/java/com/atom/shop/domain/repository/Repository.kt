package com.atom.shop.domain.repository

import com.atom.shop.domain.dto.ProductEntity
import org.json.JSONObject

interface Repository {

    suspend fun getAllProducts(): List<ProductEntity>
    suspend fun getProductsById(productId: String): ProductEntity?
    suspend fun setFavourite(productId: String, favourite: Boolean)
    suspend fun getFavouritesCount(): Int
    suspend fun insertProductsToBd(products: List<ProductEntity>)
    suspend fun jsonToProductList(jsonObject: JSONObject): List<ProductEntity>
}