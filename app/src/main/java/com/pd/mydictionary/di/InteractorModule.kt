package com.pd.mydictionary.di

import com.pd.mydictionary.NAME_LOCAL
import com.pd.mydictionary.NAME_REMOTE
import com.pd.mydictionary.model.data.DataModel
import com.pd.mydictionary.model.repository.Repository
import com.pd.mydictionary.view.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class InteractorModule {

    @Provides
    internal fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) repositoryLocal: Repository<List<DataModel>>
    ) = MainInteractor(repositoryRemote, repositoryLocal)
}