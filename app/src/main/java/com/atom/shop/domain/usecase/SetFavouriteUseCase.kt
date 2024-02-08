package com.atom.shop.domain.usecase

import com.atom.shop.domain.repository.Repository
import javax.inject.Inject

class SetFavouriteUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(productId: String, favourite: Boolean) {
        repository.setFavourite(productId, favourite)
    }
}