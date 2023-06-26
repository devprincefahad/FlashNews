package dev.prince.flashnews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.flashnews.data.repository.Repository
import dev.prince.flashnews.util.SOURCE_BBC_NEWS
import dev.prince.flashnews.util.SOURCE_REUTERS
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

    val topNews = repository.topNews

    val recommendedNews = repository.recommendedNews

    init {
        viewModelScope.launch {
            repository.getNews(SOURCE_REUTERS)
            repository.getNews(SOURCE_BBC_NEWS)
        }
    }

}