package com.example.aaoshoppingkare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaoshoppingkare.adapter.QuoteAdapter
import com.example.aaoshoppingkare.viewmodel.QuoteViewModel
import com.example.aaoshoppingkare.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelFactory

    private lateinit var viewModel: QuoteViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recy_quotes)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        val quoteAdapter = QuoteAdapter()
        (application as ShopApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider)[QuoteViewModel::class.java]
        viewModel.quotes.observe(this) { it ->
            quoteAdapter.submitList(it.results)
           recyclerView.adapter = quoteAdapter
            it.results.forEach { result ->
                Log.d("Quotes: ", "${result.content} by ${result.author}")
            }
        }

    }
}