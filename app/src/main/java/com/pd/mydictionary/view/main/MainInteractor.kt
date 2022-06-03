package com.pd.mydictionary.view.main

import com.pd.mydictionary.model.repository.Repository
import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.model.data.DataModel
import com.pd.mydictionary.presernter.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            remoteRepository.getData(word).map { AppState.Success(it) }
        } else {
            localRepository.getData(word).map { AppState.Success(it) }
        }
    }
}