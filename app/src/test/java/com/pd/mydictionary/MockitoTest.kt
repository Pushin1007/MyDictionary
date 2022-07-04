package com.pd.mydictionary

import com.nhaarman.mockito_kotlin.verify
import com.pd.mydictionary.model.data.*
import com.pd.mydictionary.model.repository.RepositoryImplementation
import com.pd.mydictionary.rx.ISchedulerProvider
import com.pd.mydictionary.rx.TestSchedulerProvider
import com.pd.mydictionary.view.base.View
import com.pd.mydictionary.view.main.MainInteractor
import com.pd.mydictionary.view.main.MainPresenterImpl
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

import org.mockito.Mockito
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class MockitoTest {

    private lateinit var presenter: MainPresenterImpl<AppState, View>
    private var schedulerProvider: ISchedulerProvider = TestSchedulerProvider()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Mock
    lateinit var remote: RepositoryImplementation

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val interactor = MainInteractor(remote, remote)
        presenter = MainPresenterImpl(interactor, compositeDisposable, schedulerProvider)
    }


    @Test
    fun testWordsRepo() {
        val searchWord = "text"
        val translation = Translation("text")
        val meanings = listOf(Meanings(translation, "imageUrl"))
        val dataModel = listOf(DataModel(searchWord, meanings))

        Mockito.`when`(remote.getData(searchWord)).thenReturn(Observable.just(dataModel))
        presenter.getData(searchWord, true)
        verify(remote, Mockito.times(1)).getData(searchWord)
    }
}
