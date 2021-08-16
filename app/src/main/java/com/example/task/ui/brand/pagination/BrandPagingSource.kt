package com.example.task.ui.brand.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.task.data.remote.StoreRemote
import com.example.task.ui.models.Product
import retrofit2.HttpException
import java.io.IOException

class BrandPagingSource(
    private val storeRemote: StoreRemote,

    ) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val pageNumber = params.key ?: 1


        return try {
            val response = storeRemote.getBrandDetails(pageNumber, params.loadSize)


            LoadResult.Page(
                data = response.products,
                prevKey = if (pageNumber == 1) null else pageNumber - 1,
                nextKey = if (response.products.isEmpty()) null else pageNumber + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return null
    }
}