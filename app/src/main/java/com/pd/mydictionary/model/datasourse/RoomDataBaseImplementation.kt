package com.pd.mydictionary.model.datasourse

import com.pd.mydictionary.model.data.DataModel
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }
}