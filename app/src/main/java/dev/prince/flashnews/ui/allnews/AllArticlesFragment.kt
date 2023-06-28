package dev.prince.flashnews.ui.allnews

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.flashnews.R
import dev.prince.flashnews.data.network.ApiResult
import dev.prince.flashnews.databinding.FragmentAllArticlesBinding
import dev.prince.flashnews.ui.adapters.RecommendedNewsAdapter
import dev.prince.flashnews.ui.details.DetailsViewModel

@AndroidEntryPoint
class AllArticlesFragment : Fragment() {

    private lateinit var binding: FragmentAllArticlesBinding
    private val allArticlesViewModel: AllArticlesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllArticlesBinding.inflate(inflater, container, false)
        val category = arguments?.getString("category")

        binding.toolbarTitleTv.text = category.toString()

        binding.imgBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }
        val key = "AllArticlesFragment"

        if (category.equals(getString(R.string.recommended_news))) {
            allArticlesViewModel.recommendedNews.observe(viewLifecycleOwner) { recommendedNews ->
                binding.recyclerView.adapter =
                    RecommendedNewsAdapter(requireContext(), recommendedNews, key)
            }
        } else {
            category?.lowercase()?.let {
                allArticlesViewModel.getNewsByCategory(it).observe(viewLifecycleOwner) { categoryNews ->
                        Log.d("data-category","$categoryNews")
                        when (categoryNews) {
                            is ApiResult.Success -> {
                                val articles = categoryNews.data?.articles
                                Log.d("data-category","articles: $articles")
                                binding.recyclerView.adapter =
                                    articles?.let { articleList ->
                                        RecommendedNewsAdapter(requireContext(),
                                            articleList, key)
                                    }
                            }

                            is ApiResult.Error -> {
                                Log.d("error", "${R.string.error_occurred}")
                            }
                        }
                    }
            }
        }

        return binding.root
    }

}