package com.pd.mydictionary.viewmodel

import androidx.lifecycle.LiveData
import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.utils.parseOnlineSearchResults

import com.pd.mydictionary.view.main.MainInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class MainViewModel(
    private val interactor: MainInteractor
) : BaseViewModel<AppState>() {
    //переменная состояния активити
    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(word: String, isOnline: Boolean) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInterceptor(word, isOnline) }
    }

    private suspend fun startInterceptor(word: String, isOnline: Boolean) =
        withContext(Dispatchers.IO) {
            _mutableLiveData.postValue(parseOnlineSearchResults(interactor.getData(word, isOnline)))
        }

    //пробрасываем ошибку также через liveData
    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }


}
