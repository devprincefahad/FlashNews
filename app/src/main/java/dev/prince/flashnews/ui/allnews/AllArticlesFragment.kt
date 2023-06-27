package dev.prince.flashnews.ui.allnews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.prince.flashnews.R
import dev.prince.flashnews.databinding.FragmentAllArticlesBinding
import dev.prince.flashnews.databinding.FragmentHomeBinding

class AllArticlesFragment : Fragment() {

    private lateinit var binding: FragmentAllArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAllArticlesBinding.inflate(inflater, container, false)

        val category = arguments?.getString("category")

        binding.toolbarTitleTv.text = category.toString()

        return binding.root
    }

}