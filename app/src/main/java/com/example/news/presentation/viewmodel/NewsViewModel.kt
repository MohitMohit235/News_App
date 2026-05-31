package com.example.news.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.news.data.localdatabase.NewsEntity
import com.example.news.data.localdatabase.toNewsEntity
import com.example.news.data.model.News
import com.example.news.data.model.NewsList
import com.example.news.data.repository.NewsRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class NewsViewModel @Inject constructor(
    private val viewModelRepository: NewsRepositoryImpl
): ViewModel(){
        private val _NewsData =
            MutableStateFlow(NewsState())
        val newsData : StateFlow<NewsState> =
            _NewsData

        private var currentcategory: String? =
            null

    private val searchQuery =
        MutableStateFlow("india")

    private val category =
        MutableStateFlow("top")

    private val cuntry =
        MutableStateFlow("in")
    
    
    val bookmarkNews : StateFlow<List<NewsEntity>> = viewModelRepository.getBookMarkNews()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000),emptyList())
    

    val news =
        combine(
            category,
            searchQuery,
            cuntry
        ) { category, query , country->
            Triple(
                category,
                query,
                country
            )

        }
            .flatMapLatest { (category, query,cuntry) ->

                viewModelRepository.getPagesNews(
                    category = category,
                    query = query,
                    cuntry = cuntry
                )
            }
            .cachedIn(viewModelScope)

    fun searchQuery(query: String) {
        searchQuery.value = query
    }

    fun changeCategory(category: String) {
        this.category.value = category
    }

    fun changeCountry(countryCode: String) {
        cuntry.value = countryCode
    }
    
    fun isNewsSave(newsId: String?): Flow<Boolean> = viewModelRepository.isBookmark(newsId = newsId?:"")
    
    init {
        getNewsData(category = "top")
    }


    fun toggleButton(news: News, isCurrentlySaves: Boolean){
        viewModelScope.launch {
            if (isCurrentlySaves){
                viewModelRepository.removeBookMark(news = news.toNewsEntity())
            }else {
                viewModelRepository.addBookMark(news = news.toNewsEntity())
            }
        }
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