package dev.prince.flashnews.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.flashnews.R
import dev.prince.flashnews.databinding.FragmentHomeBinding
import dev.prince.flashnews.ui.adapters.TopNewsAdapter

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        homeViewModel.topNews.observe(viewLifecycleOwner) { news ->
            binding.recyclerTop.adapter = TopNewsAdapter(requireContext(), news)
        }

        return binding.root
    }

}