package com.studies.chatwiseassignment.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.studies.chatwiseassignment.Adapter.productAdapter
import com.studies.chatwiseassignment.Api.productAPI
import com.studies.chatwiseassignment.Models.Products
import com.studies.chatwiseassignment.R

import com.studies.chatwiseassignment.databinding.ActivityProductsBinding
import com.studies.chatwiseassignment.utils.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Response

class ProductsActivity : AppCompatActivity() {
    private val binding : ActivityProductsBinding by lazy {
        ActivityProductsBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.product)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val productsApi = RetrofitHelper.getInstance().create(productAPI::class.java)
        // launching a new coroutine
        GlobalScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    productsApi.getProducts()
                }

                if (response.isSuccessful) {
                    val products: Products? = response.body()
                    if (products != null) {
                        Log.d("@@@@@", products.toString())
                        val adapter = productAdapter(this@ProductsActivity, products.products)
                        withContext(Dispatchers.Main) {
                            binding.productSRV.adapter = adapter
                            binding.progressbar.visibility = View.GONE
                        }

                    } else {
                        Log.d("@@@@@", "null")
                        withContext(Dispatchers.Main) {
                            binding.progressbar.visibility = View.GONE
                        }

                    }
                } else {
                    Log.e("@@@@@", "Failed to fetch products: ${response.errorBody()}")
                    // Handle error response
                }
            } catch (e: Exception) {
                // Handle network errors or other exceptions
                Log.e("@@@@@", "Failed to fetch products: ${e.message}", e)
                // Update UI or show error message
            }
    }

    }
}