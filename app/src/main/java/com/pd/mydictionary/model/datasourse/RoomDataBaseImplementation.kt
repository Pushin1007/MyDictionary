package com.pd.mydictionary.model.datasourse

import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.model.data.DataModel
import com.pd.mydictionary.room.HistoryDao
import com.pd.mydictionary.parsers.convertDataModelSuccessToEntity
import com.pd.mydictionary.parsers.mapHistoryEntityToSearchResult

class RoomDataBaseImplementation(private val historyDao: HistoryDao) :
    DataSourceLocal<List<DataModel>> {

    override suspend fun getData(word: String): List<DataModel> {
        return mapHistoryEntityToSearchResult(historyDao.all())
    }

    override suspend fun saveToDB(appState: AppState) {
        convertDataModelSuccessToEntity(appState)?.let {
            historyDao.insert(it)
        }
    }
}

