package com.pd.mydictionary.viewmodel

import io.reactivex.rxjava3.core.Observable

/** Интерфейс, который скрывает работу Интерактора */

interface Interactor<T> {

    fun getData(word: String, fromRemoteSource: Boolean): Observable<T>
}