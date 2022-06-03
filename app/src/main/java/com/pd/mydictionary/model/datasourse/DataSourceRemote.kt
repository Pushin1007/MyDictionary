package com.pd.mydictionary.model.datasourse

import com.pd.mydictionary.model.data.DataModel
import io.reactivex.Observable

class DataSourceRemote(
    private val remoteProvider: RetrofitImplementation = RetrofitImplementation()
) : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}