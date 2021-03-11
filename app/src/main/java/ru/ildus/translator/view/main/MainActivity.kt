package ru.ildus.translator.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import org.koin.android.viewmodel.ext.android.viewModel
import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.translator.R
import ru.ildus.translator.databinding.ActivityMainBinding
import ru.ildus.translator.di.injectDependencies
import ru.ildus.translator.utils.convertMeaningsToString
import ru.ildus.translator.view.base.BaseActivity
import ru.ildus.translator.view.history.HistoryActivity
import ru.ildus.translator.view.main.adapter.MainAdapter
import ru.ildus.utils.network.isOnline

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "dialog"
private const val DESCRIPTION_ACTIVITY_PATH = "ru.ildus.descriptionscreen.DescriptionActivity"
private const val DESCRIPTION_ACTIVITY_FEATURE_NAME = "descriptionscreen"

class MainActivity : BaseActivity<AppState, MainInteractor>() {
    override val model: MainViewModel by viewModel()
    private lateinit var splitInstallManager: SplitInstallManager

    lateinit var binding: ActivityMainBinding
    private val adapter: MainAdapter by lazy { MainAdapter(onListItemClickListener) }
    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }
    private val onListItemClickListener: MainAdapter.OnListItemClickListener =
        object : MainAdapter.OnListItemClickListener {
            override fun onItemClick(data: DataModel) {
                splitInstallManager = SplitInstallManagerFactory.create(applicationContext)

                val request =
                    SplitInstallRequest
                        .newBuilder()
                        .addModule(DESCRIPTION_ACTIVITY_FEATURE_NAME)
                        .build()

                splitInstallManager
                    .startInstall(request)
                    .addOnSuccessListener {
                        val intent = Intent().setClassName(packageName, DESCRIPTION_ACTIVITY_PATH)
                        intent.putExtra("f76a2", data.text!!)
                        intent.putExtra(
                            "0eeb9", convertMeaningsToString(data.meanings!!)
                        )
                        intent.putExtra(
                            "6e4b1", data.meanings!![0].imageUrl
                        )
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            applicationContext,
                            "Couldn't download feature: " + it.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }
        }

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
        val view = binding.root
        setContentView(view)
        iniViewModel()
        initViews()
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
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

    private fun iniViewModel() {
        check(binding.mainActivityRecyclerview.adapter == null) {
            "The ViewModel should be initialised first"
        }
        injectDependencies()
        model.subscribe().observe(this@MainActivity, Observer<AppState> { renderData(it) })
    }

    private fun initViews() {
        binding.searchFab.setOnClickListener(fabClickListener)
        binding.mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
        binding.mainActivityRecyclerview.adapter = adapter
    }
}

