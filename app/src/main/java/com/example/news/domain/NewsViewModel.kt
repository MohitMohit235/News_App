package com.example.news.domain

import android.util.Log
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.news.data.model.NewsList
import com.example.news.data.pagging.NewsPaggingSource
import com.example.news.data.remote.ApiService
import com.example.news.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val viewModelRepository: NewsRepository
): ViewModel(){
        private val _NewsData =
            MutableStateFlow(NewsState())
        val newsData : StateFlow<NewsState> =
            _NewsData

        private var currentcategory: String? =
            null

    private val _category =
        MutableStateFlow("top")

    val news = _category.flatMapLatest {
        viewModelRepository.getPagesNews(
            category = it
        )
    }.cachedIn(viewModelScope)

    fun changeCategory(category: String) {
        _category.value = category
    }

    
    init {
        getNewsData(category = "top")
    }


    fun getNewsData(category: String){
        if (currentcategory==category) return
        currentcategory = category
        viewModelScope.launch {

           try {


              _NewsData.value = _NewsData.value.copy(
                  Loading = true,
                  Falior = null
              )

               val result = withContext(Dispatchers.IO){
                   viewModelRepository.getNews(category)
               }

               result.onSuccess{ data ->
                   Log.d(
                       "NEWS_API",
                       "Success: ${data.results}"
                   )

                    _NewsData.value = _NewsData.value.copy(
                        Loading = false,
                        Success = data,
                        Falior = null
                    )
               }

               result.onFailure { faildata->

                   _NewsData.value = _NewsData.value.copy(
                       Loading = false,
                       Falior = faildata.message?:"Error"
                   )
               }

           } catch (e: Exception){

                   _NewsData.value = _NewsData.value.copy(
                       Loading = false,
                       Falior = e.message ?:"Error Occurred now"
                   )

           }
        }
    }
}




data class NewsState(
    val Loading : Boolean = false,
    val Success: NewsList? = null,
    val Falior: String? = null
)