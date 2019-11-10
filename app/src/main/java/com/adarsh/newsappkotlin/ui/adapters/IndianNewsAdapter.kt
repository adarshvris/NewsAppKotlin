package com.adarsh.newsappkotlin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adarsh.newsappkotlin.api.IndianNewsDetail
import com.adarsh.newsappkotlin.databinding.IndianNewsListAdapterBinding

class IndianNewsAdapter(
    private val listOfIndianNews: List<IndianNewsDetail>,
    private val onClicked: (IndianNewsDetail) -> Unit
) :
    RecyclerView.Adapter<IndianNewsAdapter.IndianNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndianNewsViewHolder =
        IndianNewsViewHolder(
            IndianNewsListAdapterBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun getItemCount(): Int = listOfIndianNews.size

    override fun onBindViewHolder(holder: IndianNewsViewHolder, position: Int) {
        holder.bind(onItemClicked(listOfIndianNews[position]), listOfIndianNews[position])
    }

    class IndianNewsViewHolder(private val indianNewsListAdapterBinding: IndianNewsListAdapterBinding) :
        RecyclerView.ViewHolder(indianNewsListAdapterBinding.root) {

        fun bind(listener: View.OnClickListener, item: IndianNewsDetail) {
            indianNewsListAdapterBinding.apply {
                inidanNewsDetail = item
                onClickListener = listener
            }
        }
    }

    private fun onItemClicked(indianNewsDetail: IndianNewsDetail): View.OnClickListener {
        return View.OnClickListener {
            onClicked(indianNewsDetail)
        }
    }
}