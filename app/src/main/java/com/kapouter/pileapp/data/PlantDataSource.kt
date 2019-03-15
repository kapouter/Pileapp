package com.kapouter.pileapp.data

import androidx.paging.PageKeyedDataSource
import com.kapouter.pileapp.model.Plant
import com.kapouter.pileapp.services.TrefleService

class PlantDataSource(private val service: TrefleService, private val query: String) :
    PageKeyedDataSource<Int, Plant>() {
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Plant>) {
        val plants = service.getPlants(query = query, pageSize = params.requestedLoadSize).execute()
        val pageNumber = plants.headers().get("page-number")
        callback.onResult(plants.body() ?: listOf(), getPreviousPage(pageNumber), getNextPage(pageNumber))
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Plant>) {
        val plants =
            service.getPlants(query = query, pageNumber = params.key, pageSize = params.requestedLoadSize).execute()
        val pageNumber = plants.headers().get("page-number")
        callback.onResult(plants.body() ?: listOf(), getNextPage(pageNumber))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Plant>) {
        val plants =
            service.getPlants(query = query, pageNumber = params.key, pageSize = params.requestedLoadSize).execute()
        val pageNumber = plants.headers().get("page-number")
        callback.onResult(plants.body() ?: listOf(), getPreviousPage(pageNumber))
    }

    private fun getPageNumber(pageNumber: String?): Int? {
        if (pageNumber == null) return null
        var page: Int? = null
        try {
            page = pageNumber.toInt()
        } catch (e: NumberFormatException) {
        }
        return page
    }

    private fun getNextPage(pageNumber: String?) = getPageNumber(pageNumber)?.plus(1)
    private fun getPreviousPage(pageNumber: String?): Int? {
        val page = getPageNumber(pageNumber)
        return if (page == null || page < 2) null else page.minus(1)
    }
}