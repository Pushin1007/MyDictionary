package com.pd.mydictionary.model.datasourse

import com.pd.mydictionary.model.data.DataModel
import io.reactivex.Observable

class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()
) : DataSource<List<DataModel>> {


    override fun getData(word: String): Observable<List<DataModel>> = remoteProvider.getData(word)
}