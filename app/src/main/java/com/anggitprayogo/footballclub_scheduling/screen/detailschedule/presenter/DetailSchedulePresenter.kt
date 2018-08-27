package com.anggitprayogo.footballclub_scheduling.screen.detailschedule.presenter

import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_event.Response
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.view.DetailScheduleView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback

class DetailSchedulePresenter(val view: DetailScheduleView,
                              val retrofit: ServiceGenerator,
                              val id: String) {

    fun getDetailEvent(){
        view.onProgress()

        var call = retrofit.createClass().getDetailSchedule(id)

        call.enqueue(object: Callback<Response>{

            override fun onFailure(call: Call<Response>?, t: Throwable?) {
                view.postProgress()
                view.showError(t?.message)
            }

            override fun onResponse(call: Call<Response>?, response: retrofit2.Response<Response>?) {
                view.postProgress()

                if (response!!.isSuccessful){
                    view.showResults(GsonBuilder().setPrettyPrinting().create().toJson(response?.body()))
                    view.showData(response?.body()?.events?.get(0))
                }else{
                    view.showError("Error")
                }
            }

        })
    }

    fun getDetailTeam(id: String, str: String){
        view.onProgress()
        var call = retrofit.createClass().getTeamDetail(id)
        call.enqueue(object: Callback<Teams>{
            override fun onFailure(call: Call<Teams>?, t: Throwable?) {
                view.postProgress()
                view.showError(t?.message)
            }

            override fun onResponse(call: Call<Teams>?, response: retrofit2.Response<Teams>?) {
                view.postProgress()
                if (response!!.isSuccessful){

                    view.showTeamResult(response?.body()?.teams?.get(0), str)

                }else{
                    view.showError("Gagal")
                }
            }
        })
    }

}