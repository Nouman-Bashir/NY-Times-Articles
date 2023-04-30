package com.ny.times.nytimesarticles.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.ny.times.nytimesarticles.R
import com.ny.times.nytimesarticles.models.MostPopular

class MostPopularListAdapter(
    context: Context,
    private val clickListener: OnItemClickListener?
) : RecyclerView.Adapter<MostPopularListAdapter.ViewHolder>() {
    private val items = mutableListOf<MostPopular>()

    private val inflater = LayoutInflater.from(context)
    private val imageLoader: RequestManager

    init {
        val requestOptions = RequestOptions()
            .placeholder(R.drawable.image_placeholder)
            .fallback(R.drawable.image_placeholder)
            .centerCrop()
        imageLoader = Glide.with(context).applyDefaultRequestOptions(requestOptions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            inflater.inflate(R.layout.item_article, parent, false),
            clickListener
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun replaceItems(newItems: List<MostPopular>) {

        items.apply {
            clear()
            addAll(newItems)
        }
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(newsItem: MostPopular)
    }

    inner class ViewHolder(
        itemView: View,
        private val listener: OnItemClickListener?
    ) : RecyclerView.ViewHolder(itemView) {
        private val imageViewLeft: ImageView = itemView.findViewById(R.id.xImageViewLeft)
        private val textViewNewsSummary: TextView = itemView.findViewById(R.id.xTextViewNewsSummary)
        private val textViewBy: TextView = itemView.findViewById(R.id.xTextViewBy)
        private val textViewLocation: TextView = itemView.findViewById(R.id.xTextViewLocation)
        private val textViewDate: TextView = itemView.findViewById(R.id.xTextViewDate)

        fun bind(mostPopular: MostPopular) {
            imageLoader.load(mostPopular.media[0].mediaMetaData[0].url)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform())
                .into(imageViewLeft)

            textViewNewsSummary.text = mostPopular.title
            textViewBy.text = mostPopular.byline
            textViewLocation.text = mostPopular.section
            textViewDate.text = mostPopular.publishedDate

            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(items[adapterPosition])
                }
            }
        }
    }
}
