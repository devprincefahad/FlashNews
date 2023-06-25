package dev.prince.flashnews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.prince.flashnews.databinding.TopHeadlineListItemBinding
import dev.prince.flashnews.models.Articles

class TopNewsAdapter(
    private val context: Context,
    private val topHeadlines: List<Articles>
) : RecyclerView.Adapter<TopNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TopNewsViewHolder(
            TopHeadlineListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: TopNewsViewHolder, position: Int) {
        with(holder) {
            val headline = topHeadlines[position]
            binding.topTextTitle.text = headline.title

            if (headline.urlToImage != null) {
                Glide.with(context).load(headline.urlToImage).into(binding.topImgHeadline)
            }

            binding.topCardView.setOnClickListener {
                Toast.makeText(context, "Item clicked", Toast.LENGTH_SHORT).show()
            }
        }

    }

    override fun getItemCount(): Int = topHeadlines.size
}

class TopNewsViewHolder(val binding: TopHeadlineListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
}