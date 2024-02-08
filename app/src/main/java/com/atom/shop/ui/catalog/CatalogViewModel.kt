package com.atom.shop.ui.catalog

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.shop.domain.dto.ProductEntity
import com.atom.shop.domain.usecase.GetAllProductUseCase
import com.atom.shop.domain.usecase.InsertProductsToBdUseCase
import com.atom.shop.domain.usecase.ParseJsonToProductListUseCase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase,
    private val insertProductsToBdUseCase: InsertProductsToBdUseCase,
    private val parseJsonToProductListUseCase: ParseJsonToProductListUseCase
) : ViewModel() {
    private val _productsLiveData = MutableLiveData<List<ProductEntity>>()
    val productsLiveData: LiveData<List<ProductEntity>> get() = _productsLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun loadProductsFromJson(json: JSONObject?) {
        viewModelScope.launch {
            try {
                Log.d("CatalogViewModel", "Start loading products from JSON")
                val productList = jsonToProductList(json) ?: emptyList()
                Log.d("CatalogViewModel", "JSON object: $json")
                insertProductsToBdUseCase.invoke(productList)
                Log.d("CatalogViewModel", "Products inserted into DB")
                val products = withContext(Dispatchers.IO) {
                    getAllProductUseCase.invoke() // Выполнение в фоновом потоке
                }
                val productId = products.last().id
                Log.e("CatalogViewModel", "First product id: $productId")
                Log.d("CatalogViewModel", "Products loaded from DB: $products")
                _productsLiveData.postValue(products)
                Log.d("CatalogViewModel", "Products LiveData updated")
            } catch (e: Exception) {
                Log.e("CatalogViewModel", "Error loading products", e)
                _errorLiveData.postValue("Error loading products: ${e.message}")
            }
        }
    }

    private fun jsonToProductList(jsonObject: JSONObject?): List<ProductEntity> {
        val gson = Gson()
        val productsArray = jsonObject?.getJSONArray("items")

        return gson.fromJson(
            productsArray?.toString(),
            object : TypeToken<List<ProductEntity>>() {}.type
        )
            ?: emptyList()
    }
}

