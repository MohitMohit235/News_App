package com.example.news.presentation.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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
        private val viewModelRepository: NewsRepositoryImpl,
) : ViewModel() {
    private val _NewsData =
        MutableStateFlow(NewsState())
    val newsData: StateFlow<NewsState> =
        _NewsData
    
    private var currentcategory: String? =
        null
    
    private val searchQuery =
        MutableStateFlow("india")
    
    private val category =
        MutableStateFlow("top")
    
    private val cuntry =
        MutableStateFlow("in")
    
    
    val news =
        combine(
                category,
                searchQuery
        ) { category, query ->
            Pair(
                    category,
                    query
            )
            
        }
                .flatMapLatest { (category, query) ->
                    
                    viewModelRepository.getPagesNews(
                            category = category,
                            query = query,
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
    
    
    init {
        getNewsData(category = "top")
    }
    
    fun getNewsData(category: String) {
        if (currentcategory == category) return
        currentcategory = category
        viewModelScope.launch {
            
            try {
                
                
                _NewsData.value = _NewsData.value.copy(
                        Loading = true,
                        Falior = null
                )
                
                val result = withContext(Dispatchers.IO) {
                    viewModelRepository.getNews(category)
                }
                
                result.onSuccess { data ->
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
                
                result.onFailure { faildata ->
                    
                    _NewsData.value = _NewsData.value.copy(
                            Loading = false,
                            Falior = faildata.message ?: "Error"
                    )
                }
                
            } catch (e: Exception) {
                
                _NewsData.value = _NewsData.value.copy(
                        Loading = false,
                        Falior = e.message ?: "Error Occurred now"
                )
            }
        }
    }
    
    val bookmarkedNews: StateFlow<List<News>> =
        viewModelRepository.getBookmarkedNews()
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    
    
    val recentlyRead: StateFlow<List<News>> =
        viewModelRepository.getRecentlyRead()
                .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    
    
    fun bookmarkNews(news: News){
        viewModelScope.launch {
            viewModelRepository.saveNews(news.copy(isBookmarked = true))
        }
    }
    
    fun removebookmarkNews(news: News){
        viewModelScope.launch {
            viewModelRepository.saveNews(news.copy(isBookmarked = false))
        }
    }
    
    fun toggleBookmark(news: News) {
        viewModelScope.launch {
            Log.d("BOOKMARK", "artical_id: ${news.artical_id}")
            Log.d("BOOKMARK", "title: ${news.title}")
            viewModelRepository.saveNews(news.copy(isBookmarked = !news.isBookmarked))
        }
    }
    
    
    fun markAsRead(news: News) {
        viewModelScope.launch {
            viewModelRepository.markAsRead(news)
        }
    }
    
    
    
    fun saveNews(news: News) {
        viewModelScope.launch {
            viewModelRepository.saveNews(news)
        }
    }
    
    
    fun deleteNews(news: News) {
        viewModelScope.launch {
            viewModelRepository.deleteNews(news)
        }
    }
    
}


data class NewsState(
        val Loading: Boolean = false,
        val Success: NewsList? = null,
        val Falior: String? = null,
)