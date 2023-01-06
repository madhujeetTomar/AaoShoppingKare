package com.example.aaoshoppingkare.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.aaoshoppingkare.model.Result
import com.example.aaoshoppingkare.remote.ApiInterface

class QuotePagingSource(val quoteApi: ApiInterface) :
    PagingSource<Int, com.example.aaoshoppingkare.model.Result>() {

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        return state.anchorPosition?.let {
            state.closestPageToPosition(it)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(it)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val index = params.key ?: 1
            val response = quoteApi.getQuotes(index)
            LoadResult.Page(
                data = response.body()!!.results,
                prevKey = if (index == 1) null else index.minus(1),
                nextKey = if (index == response.body()!!.totalPages) null else index.plus(1)

            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}