package com.atom.shop.data.repository

import com.atom.shop.data.dao.ProductDao
import com.atom.shop.domain.dto.FeedbackEntity
import com.atom.shop.domain.dto.InfoEntity
import com.atom.shop.domain.dto.PriceEntity
import com.atom.shop.domain.dto.ProductEntity
import com.atom.shop.domain.repository.Repository
import org.json.JSONObject
import javax.inject.Inject


class RepositoryImpl @Inject constructor(
    private val productDao: ProductDao
) : Repository {
    override suspend fun getAllProducts(): List<ProductEntity> {
        return productDao.getAllProducts()
    }

    override suspend fun getProductsById(productId: String): ProductEntity? {
        return productDao.getProduct(productId)
    }

    override suspend fun setFavourite(productId: String, favourite: Boolean) {
        return productDao.setFavourite(productId, favourite)
    }

    override suspend fun getFavouritesCount(): Int {
        return productDao.getFavouriteCount()
    }

    override suspend fun insertProductsToBd(products: List<ProductEntity>) {
        productDao.insert(products)
    }

    override suspend fun jsonToProductList(jsonObject: JSONObject): List<ProductEntity> {
        val productsArray = jsonObject.getJSONArray("products")
        val productList = mutableListOf<ProductEntity>()

        for (i in 0 until productsArray.length()) {
            val productObject = productsArray.getJSONObject(i)

            val id = productObject.getString("id")
            val title = productObject.getString("title")
            val subtitle = productObject.getString("subtitle")

            // Парсинг объекта PriceEntity
            val priceObject = productObject.getJSONObject("price")
            val price = PriceEntity(
                price = priceObject.getString("price"),
                discount = priceObject.getInt("discount"),
                priceWithDiscount = priceObject.getString("priceWithDiscount"),
                unit = priceObject.getString("unit")
            )

            // Парсинг объекта FeedbackEntity
            val feedbackObject = productObject.getJSONObject("feedback")
            val feedback = FeedbackEntity(
                count = feedbackObject.getInt("count"),
                rating = feedbackObject.getDouble("rating")
            )

            // Парсинг массива тегов
            val tagsArray = productObject.getJSONArray("tags")
            val tagsList = mutableListOf<String>()
            for (j in 0 until tagsArray.length()) {
                tagsList.add(tagsArray.getString(j))
            }

            val available = productObject.getInt("available")
            val description = productObject.getString("description")

            // Парсинг массива InfoEntity
            val infoArray = productObject.getJSONArray("info")
            val infoList = mutableListOf<InfoEntity>()
            for (k in 0 until infoArray.length()) {
                val infoObject = infoArray.getJSONObject(k)
                val infoTitle = infoObject.getString("title")
                val infoValue = infoObject.getString("value")
                infoList.add(InfoEntity(infoTitle, infoValue))
            }

            val ingredients = productObject.getString("ingredients")

            val favourite = productObject.getBoolean("favourite")

            // Создание объекта ProductEntity
            val productEntity = ProductEntity(
                id = id,
                title = title,
                subtitle = subtitle,
                price = price,
                feedback = feedback,
                tags = tagsList,
                available = available,
                description = description,
                info = infoList,
                ingredients = ingredients,
                favourite = favourite
            )

            productList.add(productEntity)
        }

        return productList
    }
}