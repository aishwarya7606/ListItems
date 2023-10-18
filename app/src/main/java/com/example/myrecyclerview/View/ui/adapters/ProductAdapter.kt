package com.example.myrecyclerview.View.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerview.Model.Product
import com.example.myrecyclerview.R
import com.squareup.picasso.Picasso

class ProductAdapter(private val onItemClick: (Product) -> Unit) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var productList: List<Product> = ArrayList()

    fun setData(productList: List<Product>?) {
        productList?.let {
            this.productList = it
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        // Set a click listener to handle item click
        holder.itemView.setOnClickListener {
            onItemClick(product)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val priceTextView: TextView = itemView.findViewById(R.id.priceTextView)
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bind(product: Product) {
            titleTextView.text = product.title
            priceTextView.text = "$${product.price}"
            Picasso.get().load(product.image).into(imageView)
        }
    }

}
