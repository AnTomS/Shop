package com.atom.shop.di

import com.atom.shop.data.repository.RepositoryImpl
import com.atom.shop.domain.repository.Repository
import com.atom.shop.domain.usecase.GetAllProductUseCase
import com.atom.shop.domain.usecase.GetFavouritesCountUseCase
import com.atom.shop.domain.usecase.GetProductsByIdUseCase
import com.atom.shop.domain.usecase.InsertProductsToBdUseCase
import com.atom.shop.domain.usecase.ParseJsonToProductListUseCase
import com.atom.shop.domain.usecase.SetFavouriteUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {


    @Singleton
    @Provides
    fun provideRepository(repositoryImpl: RepositoryImpl): Repository {
        return repositoryImpl
    }

    @Singleton
    @Provides
    fun provideGetAllProductUseCase(repository: Repository): GetAllProductUseCase {
        return GetAllProductUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetFavouritesCountUseCase(repository: Repository): GetFavouritesCountUseCase {
        return GetFavouritesCountUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideGetProductsByIdUseCase(repository: Repository): GetProductsByIdUseCase {
        return GetProductsByIdUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideSetFavouriteUseCase(repository: Repository): SetFavouriteUseCase {
        return SetFavouriteUseCase(repository)
    }

    @Singleton
    @Provides
    fun provideInsertProductsToBdUseCase(repository: Repository): InsertProductsToBdUseCase {
        return InsertProductsToBdUseCase(repository)
    }


    @Singleton
    @Provides
    fun provideParseJsonToProductListUseCase(repository: Repository): ParseJsonToProductListUseCase {
        return ParseJsonToProductListUseCase(repository)
    }
}