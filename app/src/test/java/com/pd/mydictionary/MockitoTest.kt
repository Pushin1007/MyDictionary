package com.pd.mydictionary

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.pd.mydictionary.model.data.*
import com.pd.mydictionary.model.repository.RepositoryImplementation
import com.pd.mydictionary.presernter.Presenter
import com.pd.mydictionary.rx.TestSchedulerProvider
import com.pd.mydictionary.view.base.View
import com.pd.mydictionary.view.main.MainInteractor
import com.pd.mydictionary.view.main.MainPresenterImpl
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock


class MockitoTest {
    // val view = mock<View>()
//    val appState = mock<AppState>()


    // val remote: RepositoryImplementation = Mockito.mock(RepositoryImplementation::class.java)
    // val local: RepositoryImplementation = Mockito.mock(RepositoryImplementation::class.java)

    @Mock
    private lateinit var remote: RepositoryImplementation

    @Mock
    private lateinit var presenter: MainPresenterImpl<AppState, View>

    @Mock
    private val interactor: MainInteractor = MainInteractor(remote, remote)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = MainPresenterImpl(interactor)
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
