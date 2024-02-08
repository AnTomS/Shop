package com.atom.shop.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.atom.shop.domain.dto.FavouriteData
import com.atom.shop.domain.dto.ProductEntity


@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun getAllProducts(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE favourite = 1")
    fun getAllFavouriteProducts(): List<ProductEntity>

    @Query("SELECT * FROM products WHERE id = :productId LIMIT 1")
    suspend fun getProduct(productId: String): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(products: List<ProductEntity>)

    @Query("DELETE FROM products")
    suspend fun deleteAll()


    @Query("SELECT COUNT(*) FROM products WHERE favourite = 1")
    suspend fun getFavouriteCount(): Int

    @Update(entity = ProductEntity::class)
    suspend fun setFavouriteData(favouriteData: List<FavouriteData>)

    @Query("UPDATE products SET favourite = :favourite WHERE id = :productId")
    suspend fun setFavourite(productId: String, favourite: Boolean)

}