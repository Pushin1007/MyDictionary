package com.pd.mydictionary.model.datasourse


import com.pd.mydictionary.BASE_URL_LOCATIONS
import com.pd.mydictionary.model.data.DataModel

import io.reactivex.rxjava3.core.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        return getService(BaseInterceptor.interceptor).search(word)
    }

    private fun getService(interceptor: Interceptor): SkyEngApi {
        return createRetrofit(interceptor).create(SkyEngApi::class.java)
    }

    private fun createRetrofit(interceptor: Interceptor): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit
    }

    private fun createOkHttpClient(interceptor: Interceptor): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(interceptor)
        httpClient.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        return httpClient.build()
    }


}
// private val skyEngApi: SkyEngApi by lazy {
//        val retrofit = Retrofit.Builder()
//            .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .build()
//        retrofit.create(SkyEngApi::class.java)
//    }