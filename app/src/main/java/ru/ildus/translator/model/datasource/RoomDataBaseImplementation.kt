package ru.ildus.translator.model.datasource

import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract
import io.reactivex.Observable

class RoomDataBaseImplementation : FeatureContract.DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
