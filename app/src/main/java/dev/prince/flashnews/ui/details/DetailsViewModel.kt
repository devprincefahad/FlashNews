package dev.prince.flashnews.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.flashnews.data.repository.Repository
import dev.prince.flashnews.models.Articles
import dev.prince.flashnews.models.Category
import dev.prince.flashnews.util.SOURCE_BBC_NEWS
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.getNews(SOURCE_BBC_NEWS)
        }
    }

    fun getNewsByUrlFromDb(url: String): LiveData<Articles> {
        return repository.getNewsByUrl(url)
    }

}