package com.pd.mydictionary.viewmodel

interface Interactor<T> {

     suspend fun getData(word: String, fromRemoteSource: Boolean): T
}