package ru.ildus.translator

import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import ru.ildus.translator.model.data.AppState
import ru.ildus.translator.presenter.MainPresenterImpl
import ru.ildus.translator.rx.SchedulerProvider
import ru.ildus.translator.view.FeatureContract
import ru.ildus.translator.view.main.MainInteractor


class MainPresenterImplTest {

    private lateinit var presenter: MainPresenterImpl<AppState, FeatureContract.View>

    @Mock
    private lateinit var T : AppState


    //   private lateinit var V : FeatureContract.View
    private lateinit var currentView: FeatureContract.View

    @Mock
    private lateinit var interactor: MainInteractor

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var schedulerProvider: SchedulerProvider

    @Before
    fun setUp() {
        //Обязательно для аннотаций "@Mock"
        //Раньше было @RunWith(MockitoJUnitRunner.class) в аннотации к самому классу (SearchPresenterTest)
        MockitoAnnotations.initMocks(this)
        //Создаем Презентер, используя моки Репозитория и Вью, проинициализированные строкой выше
        presenter = MainPresenterImpl(interactor, compositeDisposable, schedulerProvider)
    }

    @Test //Проверим вызов метода getData()
    fun getData_Test() {
        val word = "some word"
        val isOnline = true
        presenter.getData("some word", isOnline)

        Mockito.verify(compositeDisposable, Mockito.times(1))
            .add(interactor.getData(word, true)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe { currentView.renderData(AppState.Loading(null)) }
                .subscribeWith(presenter.getObserver())
            )
    }

    @Test
    fun getObserver_Test(){
        presenter.getObserver()
        Mockito.verify(presenter, Mockito.times(1)).getObserver()

    }

}


