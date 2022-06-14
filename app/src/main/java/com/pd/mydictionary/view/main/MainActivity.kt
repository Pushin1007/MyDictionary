package com.pd.mydictionary.view.main

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast

import androidx.recyclerview.widget.LinearLayoutManager
import com.pd.mydictionary.BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
import com.pd.mydictionary.R
import com.pd.mydictionary.databinding.ActivityMainBinding
import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.model.data.DataModel

import com.pd.mydictionary.utils.isOnline
import com.pd.mydictionary.view.base.BaseActivity

import com.pd.mydictionary.viewmodel.MainViewModel

import androidx.lifecycle.Observer
import com.pd.mydictionary.MAIN_VIEW_MODEL
import com.pd.mydictionary.utils.convertMeaningsToString
import com.pd.mydictionary.view.description.DescriptionActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named



class MainActivity : BaseActivity<AppState, MainInteractor>() {


    private lateinit var binding: ActivityMainBinding
    override lateinit var model: MainViewModel

    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: android.view.View.OnClickListener =
        android.view.View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    //при нажатии на элемент ресайклер - бросаем тост
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                //при нажатии на элемент вместо тоста показываем картинку
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings[0].imageUrl
                    )
                )
            }
        }

    // проверку интернета проверяем утилитой
    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    model.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//инжектим Коин. Выносим инициализацию элементов в отдельные методы
        initViewModel()
        initViews()
    }

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                val data = appState.data
                if (data.isNullOrEmpty()) {
                    showAlertDialog(
                        getString(R.string.dialog_tittle_sorry),
                        getString(R.string.empty_server_response_on_success)
                    )
                } else {
                    adapter.setData(data)
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
                    binding.progressBarHorizontal.visibility = VISIBLE
                    binding.progressBarRound.visibility = GONE
                    binding.progressBarHorizontal.progress = appState.progress
                } else {
                    binding.progressBarHorizontal.visibility = GONE
                    binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_stub), appState.error.message)
            }
        }
    }

    private fun showViewWorking() {
        binding.loadingFrameLayout.visibility = GONE
    }

    private fun showViewLoading() {
        binding.loadingFrameLayout.visibility = VISIBLE
    }

    /** Koin

    Выносим инициализацию элементов экрана в отдельный метод initViews */

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    private fun initViewModel() {
        // Убедимся, что модель инициализируется раньше View
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("ViewModel должна инициализироваться первой")
        }

        val viewModel: MainViewModel by viewModel( named(MAIN_VIEW_MODEL) )
        model = viewModel
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }


}