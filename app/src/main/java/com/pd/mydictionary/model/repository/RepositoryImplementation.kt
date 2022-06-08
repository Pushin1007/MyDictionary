package com.pd.mydictionary.model.repository

import com.pd.mydictionary.model.datasourse.DataSource
import com.pd.mydictionary.model.data.DataModel

import io.reactivex.rxjava3.core.Observable

class RepositoryImplementation(
    private val dataSource: DataSource<List<DataModel>>
) : Repository<List<DataModel>> {
    //возвращает данные используя DataSource
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }
}