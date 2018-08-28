package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.api.ApiRepository
import com.anggitprayogo.footballclub_scheduling.constant.Constant
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.DetailScheduleActivity
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.DataEvent
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.presenter.PrevSchedulePresenter
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.ui.PrevScheduleAdapter
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.PrevScheduleView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class PrevScheduleFragment : Fragment(), PrevScheduleView {

    var events: MutableList<DataEvent>? = mutableListOf()
    lateinit var presenter: PrevSchedulePresenter
    lateinit var serviceGenerator: ServiceGenerator
    lateinit var rvPrevSchedule: RecyclerView
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var adapter: PrevScheduleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var view = PrevScheduleFragmentUI<Fragment>().createView(org.jetbrains.anko.AnkoContext.Companion.create(ctx, this))

        rvPrevSchedule = view.findViewById(R.id.rv_prev_schedule)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        serviceGenerator = ServiceGenerator()
        val request = ApiRepository()
        val gson = Gson()

        presenter = PrevSchedulePresenter(this, serviceGenerator, gson, request)

        presenter.getSchedulesCouroutine()


        rvPrevSchedule.layoutManager = LinearLayoutManager(this!!.activity, LinearLayoutManager.VERTICAL, false)
        adapter = PrevScheduleAdapter(events){
            startActivity<DetailScheduleActivity>(
                    Constant.ID_EVENT to it.idEvent
            )
        }
        rvPrevSchedule.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            presenter.getSchedulesCouroutine()
        }

        return view
    }

    override fun onProgress() {
        Log.d("MAIN", "LOADING")
        swipeRefreshLayout.isRefreshing = true
    }

    override fun showError(error: String?) {
        Log.d("MAIN ERROR :", error)
    }

    override fun showResponse(response: String?) {
        Log.d("MAIN RESPONSE :", response)
    }

    override fun postProgress() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun isEmpty() {

    }

    override fun showData(datas: MutableList<DataEvent>?) {

        swipeRefreshLayout.isRefreshing = false
        events!!.clear()

        events?.addAll(datas!!)
        adapter.notifyDataSetChanged()


        d("Datas", "" + datas?.size)

    }

}

class PrevScheduleFragmentUI<T>: AnkoComponent<T>{

    override fun createView(ui: AnkoContext<T>): View {
        return with(ui){

            swipeRefreshLayout {
                id = R.id.swipeRefreshLayout

                linearLayout {
                    lparams(matchParent, matchParent){
                        bottomMargin = 60
                    }
                    orientation = LinearLayout.VERTICAL

                    recyclerView {
                        id = R.id.rv_prev_schedule
                        lparams(matchParent, matchParent)
                    }
                }

            }

        }
    }

}
