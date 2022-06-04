package com.pd.mydictionary.presernter


import io.reactivex.rxjava3.core.Observable

interface Interactor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T> //RxJava
}