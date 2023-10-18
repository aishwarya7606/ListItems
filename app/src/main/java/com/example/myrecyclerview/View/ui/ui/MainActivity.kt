package com.example.myrecyclerview.View.ui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myrecyclerview.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Starting the ProductListActivity when the app is launched
        val intent = Intent(this, ProductListActivity::class.java)
        startActivity(intent)
    }

}

