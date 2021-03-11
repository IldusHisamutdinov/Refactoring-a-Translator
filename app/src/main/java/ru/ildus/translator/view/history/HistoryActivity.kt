package ru.ildus.translator.view.history

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.translator.databinding.ActivityHistoryBinding
import ru.ildus.translator.view.base.BaseActivity
import ru.ildus.translator.view.history.adapter.HistoryAdapter

class HistoryActivity: BaseActivity<AppState, HistoryInteractor>() {
    override val model: HistoryViewModel by viewModel()
    lateinit var binding: ActivityHistoryBinding
    private val adapter: HistoryAdapter by lazy { HistoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        model.getData("", false)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        model.subscribe().observe(this@HistoryActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.historyActivityRecyclerview.adapter = adapter
    }
}