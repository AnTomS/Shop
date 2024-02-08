package com.atom.shop.domain.usecase

import com.atom.shop.domain.dto.ProductEntity
import com.atom.shop.domain.repository.Repository
import javax.inject.Inject

class InsertProductsToBdUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(products: List<ProductEntity>) =
        repository.insertProductsToBd(products)
}