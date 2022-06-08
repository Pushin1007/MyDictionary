package com.pd.mydictionary.model.repository


//это слой получения и хранения данных, которые он передает интерактору
interface Repository<T> {

    suspend fun getData(word :String): T
}