package dev.prince.flashnews.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.flashnews.R
import dev.prince.flashnews.databinding.FragmentHomeBinding
import dev.prince.flashnews.models.Category
import dev.prince.flashnews.ui.adapters.CategoryAdapter
import dev.prince.flashnews.ui.adapters.TopNewsAdapter
import java.util.Calendar

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val categoryList = listOf(
            Category(getString(R.string.general), R.drawable.general),
            Category(getString(R.string.health), R.drawable.health),
            Category(getString(R.string.technology), R.drawable.technology),
            Category(getString(R.string.science), R.drawable.science),
            Category(getString(R.string.business), R.drawable.business),
            Category(getString(R.string.sports), R.drawable.sports)
        )

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.tvGreeting.text = displayGreeting()

        homeViewModel.topNews.observe(viewLifecycleOwner) { news ->
            binding.recyclerTop.adapter = TopNewsAdapter(requireContext(), news)
        }
//        binding.recyclerCategory.layoutManager =
//            GridLayoutManager(requireContext(), 3, GridLayoutManager.HORIZONTAL, false)
        binding.recyclerCategory.adapter = CategoryAdapter(requireContext(), categoryList)

        return binding.root
    }

    private fun displayGreeting(): String {
        val calendar = Calendar.getInstance()
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val greeting = if (currentHour in 0..11) {
            getString(R.string.good_morning)
        } else {
            getString(R.string.good_evening)
        }
        return greeting
    }

}