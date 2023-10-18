package com.example.myrecyclerview.View.ui.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myrecyclerview.Model.NetworkClient
import com.example.myrecyclerview.Model.Product
import com.example.myrecyclerview.R
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var productImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)

        // Get the product_id passed in the intent
        val productId = intent.getIntExtra("product_id", -1)

        // Initialize views
        productImageView = findViewById(R.id.productImageView)
        titleTextView = findViewById(R.id.productDetailName)
        descriptionTextView = findViewById(R.id.productDetailDescription)

        // Fetch product details based on productId
        val productService = NetworkClient.productService
        val call = productService.getProductById(productId)

        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                if (response.isSuccessful) {
                    val product = response.body()
                    if (product != null) {
                        titleTextView.text = product.title
                        descriptionTextView.text = product.description
                        Picasso.get().load(product.image).into(productImageView)
                    }
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                // Handle network request failure here
            }
        })
    }
}
