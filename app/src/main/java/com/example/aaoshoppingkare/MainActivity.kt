package com.example.aaoshoppingkare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aaoshoppingkare.adapter.QuoteAdapter
import com.example.aaoshoppingkare.view.UiState
import com.example.aaoshoppingkare.viewmodel.QuoteViewModel
import com.example.aaoshoppingkare.viewmodel.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelFactory
    lateinit var quoteAdapter: QuoteAdapter

    private lateinit var viewModel: QuoteViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
        viewModel.getQuotes(1)
        viewModel.quotes.observe(this) { it ->
            it?.let {   render(it)}
            viewModel.errorQuote.observe(this) { error ->
                progressBar.visibility= View.GONE
                Toast.makeText(this@MainActivity, error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setUp() {
        recyclerView = findViewById(R.id.recy_quotes)
        progressBar = findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        quoteAdapter = QuoteAdapter()
        (application as ShopApp).appComponent.inject(this)
        viewModel = ViewModelProvider(this, viewModelProvider)[QuoteViewModel::class.java]
    }


    private fun render(uiState: UiState<List<com.example.aaoshoppingkare.model.Result>>) {
        when (uiState) {
            is UiState.Loading -> {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility= View.GONE
            }
            is UiState.Success -> {
               recyclerView.visibility = View.VISIBLE
                quoteAdapter.submitList(uiState.value)
                recyclerView.adapter = quoteAdapter
              progressBar.visibility=View.GONE
            }
            is UiState.Error -> {
               Toast.makeText(this,uiState.message,Toast.LENGTH_SHORT).show()
                progressBar.visibility=View.GONE
            }
        }
    }
}