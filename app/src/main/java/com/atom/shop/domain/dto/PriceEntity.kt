package com.atom.shop.domain.dto


data class PriceEntity(
    val price: String,
    val discount: Int,
    val priceWithDiscount: String,
    val unit: String
)