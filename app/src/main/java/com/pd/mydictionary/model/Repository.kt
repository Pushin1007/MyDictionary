package com.pd.mydictionary.model

import io.reactivex.Observable

//это слой получения и хранения данных, которые он передает интерактору
interface Repository<T> {

    fun getData(word: String): Observable<T>
}