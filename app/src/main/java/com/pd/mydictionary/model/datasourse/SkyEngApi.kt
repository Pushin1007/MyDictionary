package com.pd.mydictionary.model.datasourse

import com.pd.mydictionary.model.data.DataModel

import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyEngApi {

    @GET("words/search")
    fun searchAsync(@Query("search") wordToSearch: String): Deferred<List<DataModel>>

}