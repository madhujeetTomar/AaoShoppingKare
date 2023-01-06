package com.example.aaoshoppingkare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.aaoshoppingkare.viewmodel.QuoteViewModel
import com.example.aaoshoppingkare.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelFactory

    private lateinit var viewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (application as ShopApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider)[QuoteViewModel::class.java]
        viewModel.quotes.observe(this) { it ->
            it.results.forEach { result ->
                Log.d("Quotes: ", "${result.content} by ${result.author}")
            }
        }

    }
}