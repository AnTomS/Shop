package com.atom.shop.domain.usecase

import com.atom.shop.domain.dto.ProductEntity
import com.atom.shop.domain.repository.Repository
import org.json.JSONObject
import javax.inject.Inject

class ParseJsonToProductListUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(jsonObject: JSONObject): List<ProductEntity> {
        // Вызываем функцию из репозитория для парсинга JSON и преобразования в список объектов ProductEntity
        return repository.jsonToProductList(jsonObject)
    }
}