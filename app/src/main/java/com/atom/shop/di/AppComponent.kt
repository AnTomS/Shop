package com.atom.shop.di

import android.util.Log
import com.atom.shop.domain.repository.Repository
import com.atom.shop.ui.catalog.CatalogFragment
import com.atom.shop.ui.product.ProductFragment
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, RoomModule::class, DomainModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: CatalogFragment) {
        Log.d("AppComponent", "CatalogFragment")
    }

    fun inject(fragment: ProductFragment) {
        Log.d("AppComponent", "ProductFragment")
    }


    fun provideRepositoryImpl(): Repository


}