package com.pd.mydictionary.view.main

import com.pd.mydictionary.NAME_LOCAL
import com.pd.mydictionary.NAME_REMOTE
import com.pd.mydictionary.model.repository.Repository
import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.model.data.DataModel

import com.pd.mydictionary.viewmodel.Interactor

import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject
import javax.inject.Named

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