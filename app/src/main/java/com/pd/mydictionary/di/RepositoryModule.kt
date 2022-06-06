package com.pd.mydictionary.di

import com.pd.mydictionary.NAME_LOCAL
import com.pd.mydictionary.NAME_REMOTE
import com.pd.mydictionary.model.data.DataModel
import com.pd.mydictionary.model.datasourse.DataSource
import com.pd.mydictionary.model.datasourse.RetrofitImplementation
import com.pd.mydictionary.model.datasourse.RoomDataBaseImplementation
import com.pd.mydictionary.model.repository.Repository
import com.pd.mydictionary.model.repository.RepositoryImplementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> = RepositoryImplementation(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal: DataSource<List<DataModel>>
    ): Repository<List<DataModel>> = RepositoryImplementation(dataSourceLocal)


    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideDataSourceRemote(): DataSource<List<DataModel>> = RetrofitImplementation()


    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    internal fun provideDataSourceLocal(): DataSource<List<DataModel>> =
        RoomDataBaseImplementation()
}