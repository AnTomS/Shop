package com.atom.shop.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.atom.shop.domain.usecase.GetAllProductUseCase
import com.atom.shop.domain.usecase.InsertProductsToBdUseCase
import com.atom.shop.domain.usecase.ParseJsonToProductListUseCase
import com.atom.shop.ui.catalog.CatalogViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule {
    @Provides
    @Singleton
    fun provideCatalogViewModelFactory(
        getAllProductUseCase: GetAllProductUseCase,
        insertProductsToBdUseCase: InsertProductsToBdUseCase,
        parseJsonToProductListUseCase: ParseJsonToProductListUseCase
    ): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CatalogViewModel::class.java)) {
                    return CatalogViewModel(
                        getAllProductUseCase,
                        insertProductsToBdUseCase,
                        parseJsonToProductListUseCase
                    ) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        }
    }
}