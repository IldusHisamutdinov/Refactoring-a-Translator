package ru.ildus.translator.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.view.FeatureContract

abstract class BaseActivity<T : AppState> : AppCompatActivity(), FeatureContract.View {

    protected lateinit var presenter: FeatureContract.Presenter<T, FeatureContract.View>

    protected abstract fun createPresenter(): FeatureContract.Presenter<T, FeatureContract.View>

    abstract override fun renderData(appState: AppState)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}
