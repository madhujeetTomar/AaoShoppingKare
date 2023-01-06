package com.example.aaoshoppingkare.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aaoshoppingkare.R
import com.example.aaoshoppingkare.model.Result

class QuoteAdapter : ListAdapter<Result, QuoteAdapter.QuotesViewHolder>(QuotesDiffUtils()) {

    class QuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val textQuote: TextView
            get() = itemView.findViewById(R.id.textQuote)
        private val textAuthor: TextView
            get() = itemView.findViewById(R.id.textAuthor)

        fun bind(item: Result) {

            textQuote.text = item.content
            textAuthor.text = item.author
        }
    }

    class QuotesDiffUtils : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Result,
            newItem: Result
        ): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        return QuotesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_quotes, parent, false)
        )

    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}