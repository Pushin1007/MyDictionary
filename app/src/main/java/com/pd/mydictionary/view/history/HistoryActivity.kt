package com.pd.mydictionary.view.history

import android.os.Bundle
import com.pd.mydictionary.databinding.ActivityHistoryBinding
import com.pd.mydictionary.model.data.AppState
import com.pd.mydictionary.model.data.DataModel
import com.pd.mydictionary.view.base.BaseActivity


import com.pd.mydictionary.HISTORY_VIEW_MODEL
import com.pd.mydictionary.R
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.activityScope
import org.koin.androidx.scope.currentScope


import org.koin.core.qualifier.named

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding
    override lateinit var model: HistoryViewModel
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun initViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException(R.string.ViewModel_initialised_first.toString())
        }
        //val viewModel: HistoryViewModel by viewModel(named(HISTORY_VIEW_MODEL))
        val viewModel: HistoryViewModel by inject()

        model = viewModel
        model.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}
