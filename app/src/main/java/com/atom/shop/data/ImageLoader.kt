package com.atom.shop.data

import com.atom.shop.R

object ImageLoader {

    fun imageFromId(productId: String): List<Any> {
        return when (productId) {
            "cbf0c984-7c6c-4ada-82da-e29dc698bb50" -> listOf(R.drawable.d6, R.drawable.d5)
            "54a876a5-2205-48ba-9498-cfecff4baa6e" -> listOf(R.drawable.d1, R.drawable.d2)
            "75c84407-52e1-4cce-a73a-ff2d3ac031b3" -> listOf(R.drawable.d5, R.drawable.d6)
            "16f88865-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.d3, R.drawable.d4)
            "26f88856-ae74-4b7c-9d85-b68334bb97db" -> listOf(R.drawable.d2, R.drawable.d3)
            "15f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.d6, R.drawable.d1)
            "88f88865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.d4, R.drawable.d3)
            "55f58865-ae74-4b7c-9d81-b78334bb97db" -> listOf(R.drawable.d1, R.drawable.d5)
            else -> listOf()
        }
    }
}