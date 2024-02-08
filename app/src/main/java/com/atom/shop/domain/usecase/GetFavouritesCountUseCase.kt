package com.atom.shop.domain.usecase

import com.atom.shop.domain.repository.Repository
import javax.inject.Inject

class GetFavouritesCountUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.getFavouritesCount()
}