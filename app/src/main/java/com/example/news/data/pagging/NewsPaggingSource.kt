package com.example.news.data.pagging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.Query
import com.example.news.data.model.News
import com.example.news.data.remote.ApiService
import retrofit2.HttpException

class NewsPaggingSource(
    private val apiService: ApiService,
    private val category: String?,
    private val searchQuery: String?,
) : PagingSource<String, News>() {

    override fun getRefreshKey(
        state: PagingState<String, News>
    ): String? {
        return null
    }

    override suspend fun load(
        params: LoadParams<String>
    ): LoadResult<String, News> {

        return try {

            val pageToken = params.key

            val response = apiService.getNewsList(
                category = category,
                page = pageToken,
                searchQuery = searchQuery?.takeIf { it.isNotBlank() },
            )

            Log.d(
                "PAGING_RESPONSE",
                response.toString()
            )

            LoadResult.Page(
                data = response.results ?: emptyList(),

                prevKey = null,

                nextKey = response.nextPage
            )

        } catch (e: Exception) {
            
            if (e is HttpException) {
                Log.e("HTTP_CODE", e.code().toString())
                Log.e("HTTP_BODY", e.response()?.errorBody()?.string().toString())
            }
            
            LoadResult.Error(e)
        }
    }
}