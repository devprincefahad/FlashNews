package dev.prince.flashnews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.prince.flashnews.R
import dev.prince.flashnews.databinding.HeadlineListItemBinding
import dev.prince.flashnews.models.Articles
import dev.prince.flashnews.ui.allnews.AllArticlesFragmentDirections
import dev.prince.flashnews.ui.home.HomeFragmentDirections
import dev.prince.flashnews.util.NEWS_LIMIT

class RecommendedNewsAdapter(
    private val context: Context,
    private val headlines: List<Articles>,
    private val key: String
) : RecyclerView.Adapter<RecommendedNewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecommendedNewsViewHolder(
            HeadlineListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = if (key == "HomeFragment") NEWS_LIMIT else headlines.size

    override fun onBindViewHolder(holder: RecommendedNewsViewHolder, position: Int) {
        with(holder) {
            val headline = headlines[position]
            binding.textTitle.text = headline.title

            if (headline.urlToImage != null) {
                Glide.with(context).load(headline.urlToImage).into(binding.imgHeadline)
            }

            binding.linearLayout.setOnClickListener {
                if (key == "HomeFragment") {
                    it.findNavController().navigate(
                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(headline.url!!)
                    )
                } else {
                    it.findNavController().navigate(
                        AllArticlesFragmentDirections.actionAllArticlesFragmentToDetailsFragment(
                            headline.url!!
                        )
                    )
                }
            }
        }
    }

}

class RecommendedNewsViewHolder(val binding: HeadlineListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
}
