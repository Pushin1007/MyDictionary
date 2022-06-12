package com.pd.mydictionary.di

import com.pd.mydictionary.*
import com.pd.mydictionary.model.data.DataModel
import com.pd.mydictionary.model.datasourse.RetrofitImplementation
import com.pd.mydictionary.model.datasourse.RoomDataBaseImplementation
import com.pd.mydictionary.model.repository.Repository
import com.pd.mydictionary.model.repository.RepositoryImplementation
import com.pd.mydictionary.view.main.MainInteractor
import com.pd.mydictionary.viewmodel.MainViewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module


//используется во всём приложении
val application = module {
    //Синглтон внешнего репозитория
    single<Repository<List<DataModel>>>(qualifier=named(NAME_REMOTE)) {
        RepositoryImplementation(RetrofitImplementation())
    }
    // Синглтон внутренний репозиторий
    single<Repository<List<DataModel>>>(qualifier=named(NAME_LOCAL)) {
        RepositoryImplementation(RoomDataBaseImplementation())
    }
}

//зависимости конкретного экрана
val mainScreen = module {
    // Создаем фабрики - каждый раз новый экземпляр
    factory (qualifier=named(MAIN_INTERACTOR)){ MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory (qualifier=named(MAIN_VIEW_MODEL)){ MainViewModel(get(named(MAIN_INTERACTOR))) }
}