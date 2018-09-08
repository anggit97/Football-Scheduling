package com.anggitprayogo.footballclub_scheduling.api

import com.anggitprayogo.footballclub_scheduling.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {

    private fun initRetrofit(): Retrofit{
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    fun <T> createService(service: Class<T>): T{
        return initRetrofit().create(service)
    }

}