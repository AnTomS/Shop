package com.atom.shop.ui.product

import android.content.Context
import android.view.View
import android.widget.TextView
import com.atom.shop.R
import com.atom.shop.domain.dto.ProductEntity

class ProductAdapter(private val context: Context, private val rootView: View) {
    private val nameBrand: TextView = rootView.findViewById(R.id.nameBrand)
    private val name2: TextView = rootView.findViewById(R.id.name2)
    private val infoByQuality: TextView = rootView.findViewById(R.id.info_by_quality)
    private val rating: TextView = rootView.findViewById(R.id.rating)
    private val numbersRating: TextView = rootView.findViewById(R.id.numbers_rating)
    private val newPrice: TextView = rootView.findViewById(R.id.newPrice)
    private val oldPrice: TextView = rootView.findViewById(R.id.oldPrice)
    private val sale: TextView = rootView.findViewById(R.id.sale)
    private val description: TextView = rootView.findViewById(R.id.description)
    private val textViewArticleValue: TextView = rootView.findViewById(R.id.textViewArticleValue)
    private val textViewScopeOfUseValue: TextView = rootView.findViewById(R.id.textViewScopeOfUseValue)
    private val textViewCountryOfManufactureValue: TextView = rootView.findViewById(R.id.textViewCountryOfManufactureValue)
    private val structure: TextView = rootView.findViewById(R.id.structure)

    fun bind(product: ProductEntity) {
        nameBrand.text = product.title
        name2.text = product.subtitle
        infoByQuality.text = "Доступно для заказа ${product.available} штук"
        rating.text = product.feedback.rating.toString()
        numbersRating.text = "(${product.feedback.count})"
        newPrice.text = product.price.priceWithDiscount
        oldPrice.text = product.price.price// Замените это на фактические данные о старой цене
        sale.text = "- ${product.price.discount}%"
        description.text = product.description
        textViewArticleValue.text = product.info.firstOrNull { it.title == "Артикул товара" }?.value ?: ""
        textViewScopeOfUseValue.text = product.info.firstOrNull { it.title == "Область использования" }?.value ?: ""
        textViewCountryOfManufactureValue.text = product.info.firstOrNull { it.title == "Страна производства" }?.value ?: ""
        structure.text = product.ingredients
    }
}