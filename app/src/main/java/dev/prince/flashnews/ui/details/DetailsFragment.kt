package dev.prince.flashnews.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import dev.prince.flashnews.databinding.FragmentDetailsBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater, container, false)
        val url = arguments?.getString("newsUrl")

        url?.let {
            detailsViewModel.getNewsByUrlFromDb(it).observe(viewLifecycleOwner) { article ->
                binding.tvNewsTitle.text = article.title
                binding.tvNewsDescription.text = article.description
                Glide.with(requireContext()).load(article.urlToImage).into(binding.detailsImage)
            }
        }

        binding.imgBackArrow.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }

}