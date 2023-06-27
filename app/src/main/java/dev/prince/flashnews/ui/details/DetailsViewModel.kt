package dev.prince.flashnews.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.flashnews.data.repository.Repository
import dev.prince.flashnews.models.Articles
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    fun getNewsByUrlFromDb(url: String): LiveData<Articles> {
        return repository.getNewsByUrl(url)
    }

}