package com.pd.mydictionary.model.datasourse

//Источник данных для репозитория
interface DataSource<T> {

    suspend fun getData(word: String) : T
}

