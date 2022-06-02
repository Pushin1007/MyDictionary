package com.pd.mydictionary.presernter

import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.view.base.View

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    // получение данных из Интернета или нет
    fun getData(word: String, isOnline: Boolean)
}