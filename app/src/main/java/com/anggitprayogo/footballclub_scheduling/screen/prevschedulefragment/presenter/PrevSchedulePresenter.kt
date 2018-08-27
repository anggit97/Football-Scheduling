package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.presenter

import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.PrevScheduleView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevSchedulePresenter(val view: PrevScheduleView,
                            val retrofit: ServiceGenerator) {

    fun getSchedules(){
        view.onProgress()



        var call = retrofit.createClass().getPrevSchedules("4328")

        call.enqueue(object : Callback<Events>{

            override fun onFailure(call: Call<Events>?, t: Throwable?) {
                view.postProgress()
                view.showError(t?.message)
            }

            override fun onResponse(call: Call<Events>?, response: Response<Events>?) {
                var gson = GsonBuilder().setPrettyPrinting().create().toJson(response?.body())

                if (response!!.isSuccessful){
                    view.showResponse(""+gson)
                    view.showData(response?.body()?.datas)
                }else{
                    view.showResponse("Gagal")
                }

            }

        })
    }

}