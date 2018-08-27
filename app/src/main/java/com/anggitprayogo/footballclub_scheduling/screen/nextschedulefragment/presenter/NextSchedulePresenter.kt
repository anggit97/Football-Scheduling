package com.anggitprayogo.footballclub_scheduling.screen.nextschedulefragment.presenter

import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.PrevScheduleView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextSchedulePresenter(val view: PrevScheduleView,
                            val retrofit: ServiceGenerator){


    fun getNextSchedules(){
        view.onProgress()

        var call = retrofit.createClass().getNextSchedules("4328")

        call.enqueue(object : Callback<Events>{

            override fun onFailure(call: Call<Events>?, t: Throwable?) {
                view.postProgress()
                view.showError(t?.message)
            }

            override fun onResponse(call: Call<Events>?, response: Response<Events>?) {
                view.postProgress()

                view.showData(response?.body()?.datas)
            }

        })
    }

}