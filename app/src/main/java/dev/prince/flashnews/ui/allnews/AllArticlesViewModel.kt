package dev.prince.flashnews.ui.allnews

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.prince.flashnews.data.network.ApiResult
import dev.prince.flashnews.data.repository.Repository
import dev.prince.flashnews.models.Articles
import dev.prince.flashnews.models.NewsResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllArticlesViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val recommendedNews = repository.recommendedNews

    private val response = MutableLiveData<ApiResult<NewsResponse?>>()

    fun getNewsByCategory(category: String): MutableLiveData<ApiResult<NewsResponse?>> {
        viewModelScope.launch {
            response.value = repository.getNewsByCategory(category)
        }
        return response
    }

}