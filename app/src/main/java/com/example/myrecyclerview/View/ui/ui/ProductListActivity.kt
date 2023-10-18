package com.example.myrecyclerview.View.ui.ui


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myrecyclerview.Model.NetworkClient
import com.example.myrecyclerview.R
import com.example.myrecyclerview.Model.Product
import com.example.myrecyclerview.View.ui.adapters.ProductAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        recyclerView = findViewById(R.id.productRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProductAdapter{ product ->
            // Handle item click by opening a new activity
            val intent = Intent(this, ProductDetailActivity::class.java)
            intent.putExtra("product_id", product.id)
            startActivity(intent)
        }
        recyclerView.adapter = adapter

        // Fetch the list of products from the API when the activity is created
        val productService = NetworkClient.productService
        val call = productService.getProducts()

        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                if (response.isSuccessful) {
                    val productList = response.body()
                    adapter.setData(productList)
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {
                // Handle network request failure here
            }
        })
    }
}
