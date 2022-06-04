package com.pd.mydictionary.model.datasourse


import io.reactivex.rxjava3.core.Observable

//Источник данных для репозитория
interface DataSource<T : Any> {

    fun getData(word: String): Observable<T>
}