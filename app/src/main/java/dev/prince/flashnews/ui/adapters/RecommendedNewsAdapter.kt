package dev.prince.flashnews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.prince.flashnews.databinding.HeadlineListItemBinding
import dev.prince.flashnews.models.Articles

class RecommendedNewsAdapter(
    private val context: Context,
    private val headlines: List<Articles>
) : RecyclerView.Adapter<RecommendedNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecommendedNewsViewHolder(
            HeadlineListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = headlines.size

    override fun onBindViewHolder(holder: RecommendedNewsViewHolder, position: Int) {

        with(holder) {
            val headline = headlines[position]
            binding.textTitle.text = headline.title

            if (headline.urlToImage != null) {
                Glide.with(context).load(headline.urlToImage).into(binding.imgHeadline)
            }

            binding.linearLayout.setOnClickListener {
                Toast.makeText(context, "Item clicked", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

class RecommendedNewsViewHolder(val binding: HeadlineListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
}
