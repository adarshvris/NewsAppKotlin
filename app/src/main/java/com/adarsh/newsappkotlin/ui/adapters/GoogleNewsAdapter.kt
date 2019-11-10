package com.adarsh.newsappkotlin.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adarsh.newsappkotlin.api.GoogleNewsDetail
import com.adarsh.newsappkotlin.databinding.GoogleNewsListAdapterBinding

class GoogleNewsAdapter(
    private val googleNewsList: List<GoogleNewsDetail>,
    val onClicked: (googleNewsDetails: GoogleNewsDetail) -> Unit
) :
    RecyclerView.Adapter<GoogleNewsAdapter.GoogleNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoogleNewsViewHolder =
        GoogleNewsViewHolder(
            GoogleNewsListAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = googleNewsList.size


    override fun onBindViewHolder(holder: GoogleNewsViewHolder, position: Int) {
        holder.bind(onItemClicked(googleNewsList[position]), googleNewsList[position])
    }

    class GoogleNewsViewHolder(private val newsListAdapterBinding: GoogleNewsListAdapterBinding) :
        RecyclerView.ViewHolder(newsListAdapterBinding.root) {

        fun bind(clickListener: View.OnClickListener, item: GoogleNewsDetail) {
            newsListAdapterBinding.apply {
                googleNewsDetail = item
                itemClicked = clickListener
            }
        }
    }

    private fun onItemClicked(googleNewsDetail: GoogleNewsDetail): View.OnClickListener {
        return View.OnClickListener {
            onClicked(googleNewsDetail)
        }
    }

}