package com.pd.mydictionary.view.main

import com.pd.mydictionary.model.repository.Repository
import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.model.data.DataModel
import com.pd.mydictionary.presernter.Interactor

import io.reactivex.rxjava3.core.Observable

class MainInteractor @Inject constructor(
    @Named(NAME_REMOTE) val repositoryRemote: Repository<List<DataModel>>,
    @Named(NAME_LOCAL) val repositoryLocal: Repository<List<DataModel>>
) : Interactor<AppState> {

    override fun getData(word: String, fromRemoteSource: Boolean): Observable<AppState> {
        return if (fromRemoteSource) {
            repositoryRemote.getData(word).map { AppState.Success(it) }
        } else {
            repositoryLocal.getData(word).map { AppState.Success(it) }
        }
    }
}