package ru.ildus.repository.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.ildus.model.data.DataModel
import ru.ildus.model.data.dto.SearchResultDto
import ru.ildus.repository.FeatureContract

class RetrofitImplementation : FeatureContract.DataSource<List<SearchResultDto>> {
    override suspend fun getData(word: String): List<SearchResultDto> {
        return getService(ru.ildus.repository.api.BaseInterceptor.interceptor).searchAsync(word).await()
    }

    private fun getService(interceptor: Interceptor): FeatureContract.ApiService {
        return createRetrofit(interceptor).create(FeatureContract.ApiService::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
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


