package com.anggitprayogo.footballclub_scheduling.screen.nextschedulefragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.constant.Constant
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.DetailScheduleActivity
import com.anggitprayogo.footballclub_scheduling.screen.nextschedulefragment.presenter.NextSchedulePresenter
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.PrevScheduleFragmentUI
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.DataEvent
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.ui.PrevScheduleAdapter
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.PrevScheduleView
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity

class NextScheduleFragment : Fragment(), PrevScheduleView{

    var events: MutableList<DataEvent>? = mutableListOf()
    lateinit var presenter: NextSchedulePresenter
    lateinit var rvNextSchedule: RecyclerView
    lateinit var adapter: PrevScheduleAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var retrofit: ServiceGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = PrevScheduleFragmentUI<Fragment>().createView(AnkoContext.Companion.create(ctx, this))
        retrofit = ServiceGenerator()

        presenter = NextSchedulePresenter(this, retrofit)

        rvNextSchedule = view.findViewById(R.id.rv_prev_schedule)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout)

        setupRecyclerview()

        presenter.getNextSchedules()

        swipeRefreshLayout.setOnRefreshListener {
            presenter.getNextSchedules()
        }

        // Inflate the layout for this fragment
        return view
    }

    private fun setupRecyclerview() {
        rvNextSchedule.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        adapter = PrevScheduleAdapter(events){
            startActivity<DetailScheduleActivity>(
                    Constant.ID_EVENT to it.idEvent
            )
        }

        rvNextSchedule.adapter = adapter
    }


    override fun onProgress() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun showError(error: String?) {
        d("Error", error)
    }

    override fun showResponse(response: String?) {

    }

    override fun postProgress() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun isEmpty() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showData(datas: MutableList<DataEvent>?) {
        events?.clear()

        events!!.addAll(datas!!)

        adapter.notifyDataSetChanged()

    }

}
