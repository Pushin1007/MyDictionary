package com.pd.mydictionary.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.pd.mydictionary.BOTTOM_SHEET_FRAGMENT_DIALOG_TAG
import com.pd.mydictionary.R
import com.pd.mydictionary.databinding.ActivityMainBinding
import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.model.data.DataModel

import com.pd.mydictionary.parsers.isOnline
import com.pd.mydictionary.view.base.BaseActivity

import com.pd.mydictionary.viewmodel.MainViewModel

import androidx.lifecycle.Observer
import com.pd.mydictionary.MAIN_VIEW_MODEL
import com.pd.mydictionary.parsers.convertMeaningsToString
import com.pd.mydictionary.view.description.DescriptionActivity
import com.pd.mydictionary.view.history.HistoryActivity
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.scope.currentScope

import org.koin.core.qualifier.named
import org.koin.core.scope.Scope


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

private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException(R.string.ViewModel_initialised_first.toString())
        }
       // val viewModel: MainViewModel by viewModel( named(MAIN_VIEW_MODEL))
    val viewModel: MainViewModel by currentScope.inject()
        model = viewModel
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }


}