package com.pd.mydictionary.model

import io.reactivex.Observable

//Источник данных для репозитория
interface DataSource<T> {

    fun getData(word: String): Observable<T>
}