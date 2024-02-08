package com.atom.shop.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atom.shop.domain.dto.ProductEntity
import com.atom.shop.domain.usecase.GetProductsByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductsByIdUseCase // Добавляем use case для получения продукта по ID
) : ViewModel() {
    private val _productLiveData = MutableLiveData<ProductEntity?>()
    val productLiveData: MutableLiveData<ProductEntity?> get() = _productLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String> get() = _errorLiveData

    fun loadProductById(productId: String) {
        viewModelScope.launch {
            try {
                Log.d("ProductViewModel", "Loading product by ID: $productId")
                val product = withContext(Dispatchers.IO) {
                    getProductByIdUseCase.invoke(productId) // Получаем продукт по ID в фоновом потоке
                }
                Log.d("ProductViewModel", "Product loaded: $product")
                _productLiveData.postValue(product)
                Log.d("ProductViewModel", "Product LiveData updated")
            } catch (e: Exception) {
                Log.e("ProductViewModel", "Error loading product", e)
                _errorLiveData.postValue("Error loading product: ${e.message}")
            }
        }
    }
}