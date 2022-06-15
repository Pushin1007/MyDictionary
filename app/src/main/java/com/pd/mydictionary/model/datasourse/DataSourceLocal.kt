package com.pd.mydictionary.model.datasourse

import com.pd.mydictionary.model.data.AppState

interface DataSourceLocal<T> : DataSource<T> {

    suspend fun saveToDB(appState: AppState)
}