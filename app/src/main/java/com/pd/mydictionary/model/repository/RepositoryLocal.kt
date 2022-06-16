package com.pd.mydictionary.model.repository

import com.pd.mydictionary.model.data.AppState

interface RepositoryLocal<T> : Repository<T> {

    suspend fun saveToDB(appState: AppState)
}