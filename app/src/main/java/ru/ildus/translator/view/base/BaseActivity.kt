package ru.ildus.translator.view.base

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import ru.ildus.utils.ui.AlertDialogFragment
import ru.ildus.translator.R
import ru.ildus.model.data.AppState
import ru.ildus.model.data.DataModel
import ru.ildus.repository.FeatureContract

import ru.ildus.translator.viewmodel.BaseViewModel
import ru.ildus.utils.network.OnlineLiveData
import ru.ildus.utils.ui.viewById

private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"

abstract class BaseActivity<T : AppState, I : FeatureContract.Interactor<T>> : AppCompatActivity() {
    protected abstract val model: BaseViewModel<T>
    protected var viewSnack: Snackbar? = null
    protected var isNetworkAvailable: Boolean = true

    val progressBarHorizontal by viewById<ProgressBar>(R.id.progress_bar_horizontal)
    private val progressBarRound by viewById<ProgressBar>(R.id.progress_bar_round)
    private val loadingFrameLayout by viewById<FrameLayout>(R.id.loading_frame_layout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeToNetworkChange()
    }

    override fun onResume() {
        super.onResume()
        if (!isNetworkAvailable && isDialogNull()) {
            viewSnack?.dismiss()
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
                    progressBarHorizontal.visibility = VISIBLE
                    progressBarRound.visibility = GONE
                    progressBarHorizontal.progress = appState.progress!!
                } else {
                    progressBarHorizontal.visibility = GONE
                    progressBarRound.visibility = VISIBLE
                }
            }
            is AppState.Error -> {
                showViewWorking()
                showAlertDialog(getString(R.string.error_textview_stub), appState.error.message)
            }
        }
    }

    protected fun showNoInternetConnectionDialog() {
        showNetworkMessage()
    }

    private fun dismissNetworkMessage() {
        viewSnack?.dismiss()
    }

    fun showNetworkMessage() {
        val viewSnack =
            Snackbar.make(findViewById(android.R.id.content), "", Snackbar.LENGTH_INDEFINITE)
                .setAction("clear") { }
        viewSnack.setBackgroundTint(Color.LTGRAY)
        viewSnack.setTextColor(Color.RED)
        viewSnack.setMaxInlineActionWidth(1)
        viewSnack.setText(R.string.dialog_message_device_is_offline)
        val view = viewSnack.view
        val params = view.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        val textView =
            view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
        textView.textSize = 16f
        viewSnack.show()
    }

    protected fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    private fun showViewWorking() {
        loadingFrameLayout.visibility = GONE
    }

    private fun showViewLoading() {
        loadingFrameLayout.visibility = VISIBLE
    }

    private fun isDialogNull(): Boolean {
        return supportFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) == null
    }

    private fun subscribeToNetworkChange() {
        OnlineLiveData(this).observe(
            this@BaseActivity, Observer<Boolean> {
                isNetworkAvailable = it
                if (!isNetworkAvailable) {
                    showNetworkMessage()
                } else {
                    if (viewSnack != null)
                        dismissNetworkMessage()
                }
            }
        )
    }

    abstract fun setDataToAdapter(data: List<DataModel>)
}
