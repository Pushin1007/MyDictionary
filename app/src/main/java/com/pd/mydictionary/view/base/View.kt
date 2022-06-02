package com.pd.mydictionary.view.base

import com.pd.mydictionary.model.data.AppState

interface View {

    fun renderData(appState: AppState)

}