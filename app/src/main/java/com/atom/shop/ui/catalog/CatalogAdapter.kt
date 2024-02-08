package com.atom.shop.ui.catalog

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.RecyclerView
import com.atom.shop.R
import com.atom.shop.domain.dto.ProductEntity

class CatalogAdapter(
    private var productList: List<ProductEntity>,
    private val clickListener: CatalogItemClickListener
) : RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    interface CatalogItemClickListener {
        fun onFavoriteButtonClick(productId: String)
        fun onCatalogItemClick(productId: String)
    }

    inner class CatalogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        val imageViewProduct: ImageView = itemView.findViewById(R.id.imageViewProduct)
        val oldPrice: TextView = itemView.findViewById(R.id.oldPrice)
        val newPrice: TextView = itemView.findViewById(R.id.newPrice)
        val sale: TextView = itemView.findViewById(R.id.sale)
        val nameBrand: TextView = itemView.findViewById(R.id.nameBrand)
        val description: TextView = itemView.findViewById(R.id.description)
        val rating: TextView = itemView.findViewById(R.id.rating)
        val numbersRating: TextView = itemView.findViewById(R.id.numbers_rating)
        val btnForFavorite: ImageButton = itemView.findViewById(R.id.btn_for_favorite)
        val btnPlus: AppCompatImageButton = itemView.findViewById(R.id.btn_for_add_favorite)

        init {
            itemView.setOnClickListener(this)
            btnForFavorite.setOnClickListener(this)
            btnPlus.setOnClickListener(this)
        }

        override fun onClick(view: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                when (view?.id) {
                    R.id.btn_for_favorite -> {
                        val product = productList[position]
                        clickListener.onFavoriteButtonClick(product.id)
                    }

                    R.id.btn_for_add_favorite -> {
                        // Нажатие на кнопку "Добавить в избранное"
                        // Ничего не делаем, так как это просто визуальное действие
                    }

                    else -> {
                        // Нажатие на карточку товара
                        val product = productList[position]
                        clickListener.onCatalogItemClick(product.id)
                    }
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_for_catalog, parent, false)
        return CatalogViewHolder(view)
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val product = productList[position]



        holder.imageViewProduct.setImageResource(R.drawable.d1)
        holder.oldPrice.text = product.price.price
        holder.newPrice.text = product.price.priceWithDiscount
        holder.sale.text = product.price.discount.toString()
        holder.nameBrand.text = product.title
        holder.description.text = product.subtitle
        holder.rating.text = product.favourite.toString()
        holder.numbersRating.text = product.feedback.rating.toString()
        holder.btnForFavorite.setImageResource(if (product.favourite) R.drawable.heart_active else R.drawable.heart_inactive)


        holder.btnPlus.setOnClickListener {

        }


        holder.btnForFavorite.setOnClickListener {
            clickListener.onFavoriteButtonClick(product.id)
        }

        holder.itemView.setOnClickListener {

            clickListener.onCatalogItemClick(product.id)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun updateData(newProductList: List<ProductEntity>) {
        productList = newProductList
        notifyDataSetChanged()
    }
}