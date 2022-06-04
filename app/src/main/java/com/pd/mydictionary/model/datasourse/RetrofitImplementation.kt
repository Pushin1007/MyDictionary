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
        return getService().search(word)
    }

    private fun getService(): SkyEngApi {
        return createRetrofit().create(SkyEngApi::class.java)
    }

    private fun createRetrofit(): Retrofit {
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        return retrofit
    }


}
