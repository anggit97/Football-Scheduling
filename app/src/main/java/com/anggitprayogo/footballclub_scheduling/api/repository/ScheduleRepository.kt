package com.anggitprayogo.footballclub_scheduling.api.repository

import com.anggitprayogo.footballclub_scheduling.api.RetrofitService
import com.anggitprayogo.footballclub_scheduling.network.ApiClient
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import retrofit2.Call
import retrofit2.Response

class ScheduleRepository {

    fun getNextMatch(id: String, callback: ScheduleCallback<Events?>){
        RetrofitService.createService(ApiClient::class.java)
                .getNextSchedules(id)
                .enqueue(object : retrofit2.Callback<Events>{
                    override fun onFailure(call: Call<Events>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<Events>?, response: Response<Events>?) {
                        if (response!!.isSuccessful){
                            callback.onDataLoaded(response?.body())
                        }else{
                            callback.onDataError()
                        }
                    }

                })
    }


    fun getPrevMatch(id: String, callback: ScheduleCallback<Events?>){
        RetrofitService.createService(ApiClient::class.java)
                .getPrevSchedules(id)
                .enqueue(object : retrofit2.Callback<Events>{
                    override fun onFailure(call: Call<Events>?, t: Throwable?) {
                        callback.onDataError()
                    }

                    override fun onResponse(call: Call<Events>?, response: Response<Events>?) {
                        if (response?.isSuccessful!!){
                            callback.onDataLoaded(response.body())
                        }else{
                            callback.onDataError()
                        }
                    }

                })
    }

}