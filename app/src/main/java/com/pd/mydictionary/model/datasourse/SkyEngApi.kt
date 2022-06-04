package com.pd.mydictionary.model.datasourse

import com.pd.mydictionary.model.data.DataModel

import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface SkyEngApi {

    @GET("words/search")
    fun search(@Query("search") wordToSearch: String): Observable<List<DataModel>>
}