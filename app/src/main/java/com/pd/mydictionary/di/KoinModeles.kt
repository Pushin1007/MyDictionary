package com.pd.mydictionary.di

import com.pd.mydictionary.NAME_LOCAL
import com.pd.mydictionary.NAME_REMOTE
import com.pd.mydictionary.model.data.DataModel
import com.pd.mydictionary.model.datasourse.RetrofitImplementation
import com.pd.mydictionary.model.datasourse.RoomDataBaseImplementation
import com.pd.mydictionary.model.repository.Repository
import com.pd.mydictionary.model.repository.RepositoryImplementation
import com.pd.mydictionary.view.main.MainInteractor
import com.pd.mydictionary.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


//используется во всём приложении
val application = module {
    //Синглтон внешнего репозитория
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    // Синглтон внутренний репозиторий
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

//зависимости конкретного экрана
val mainScreen = module {
    // Создаем фабрики - каждый раз новый экземпляр
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}