package com.pd.mydictionary.model.datasourse




//Источник данных для репозитория
interface DataSource<T : Any> {

    suspend fun getData(word: String) : T
}