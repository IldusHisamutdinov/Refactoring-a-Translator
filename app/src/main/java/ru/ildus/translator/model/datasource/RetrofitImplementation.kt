package ru.ildus.translator.model.datasource

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import ru.ildus.translator.model.data.DataModel
import ru.ildus.translator.view.FeatureContract
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ildus.translator.model.data.api.BaseInterceptor

class RetrofitImplementation : FeatureContract.DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return getService(BaseInterceptor.interceptor).search(word)
    }

    private fun getService(interceptor: Interceptor): FeatureContract.ApiService {
        return createRetrofit(interceptor).create(FeatureContract.ApiService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient(interceptor))
            .build()
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(logger)
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://dictionary.skyeng.ru/api/public/v1/"
    }
}
