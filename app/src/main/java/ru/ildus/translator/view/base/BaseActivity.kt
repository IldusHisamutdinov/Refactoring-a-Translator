package ru.ildus.translator.view.base

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.ildus.translator.AlertDialogFragment
import ru.ildus.translator.R
import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.utils.network.isOnline
import ru.ildus.translator.view.FeatureContract
import ru.ildus.translator.viewmodel.BaseViewModel

abstract class BaseActivity<T : AppState, I : FeatureContract.Interactor<T>> : AppCompatActivity() {
    abstract val model: BaseViewModel<T>
  //  private lateinit var binding: LoadingLayoutBinding
    protected var isNetworkAvailable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        isNetworkAvailable = isOnline(applicationContext)
    }

    override fun onResume() {
        super.onResume()
        isNetworkAvailable = isOnline(applicationContext)
        if (!isNetworkAvailable && isDialogNull()) {
            showNoInternetConnectionDialog()
        }
    }

    protected fun renderData(appState: T) {
        when (appState) {
            is AppState.Success -> {
                showViewWorking()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                showViewLoading()
                if (appState.progress != null) {
      //              binding.progressBarHorizontal.visibility = VISIBLE
    //                binding.progressBarRound.visibility = GONE
   //                 binding.progressBarHorizontal.progress = appState.progress
                } else {
   //                 binding.progressBarHorizontal.visibility =GONE
    //                binding.progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_textview_stub), appState.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showAlertDialog(
            getString(R.string.dialog_title_device_is_offline),
            getString(R.string.dialog_message_device_is_offline)
        )
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewWorking() {
 //       binding.loadingFrameLayout.visibility = GONE
    }

    private fun showViewLoading() {
  //      binding.loadingFrameLayout.visibility = VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    abstract fun setDataToAdapter(data: List<DataModel>)

    companion object {
        private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"
    }
}

