package com.anggitprayogo.footballclub_scheduling.screen.schedulefavouritefragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.constant.Constant
import com.anggitprayogo.footballclub_scheduling.data.ScheduleTeamFavourite
import com.anggitprayogo.footballclub_scheduling.data.database
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.DetailScheduleActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast


class ScheduleFavouriteFragment : Fragment(), AnkoComponent<Context> {

    private var favouritesSchedule: MutableList<ScheduleTeamFavourite> = mutableListOf()

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ScheduleFavouriteAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.Companion.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        adapter = ScheduleFavouriteAdapter(favouritesSchedule){

            startActivity<DetailScheduleActivity>(
                    Constant.ID_EVENT to it.scheduleId
            )

        }
        recyclerView.adapter = adapter

        showFavouriteSchedule()

        swipeRefreshLayout.setOnRefreshListener {
            showFavouriteSchedule()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {

        linearLayout {
            lparams(width = matchParent, height = matchParent)
            leftPadding = dip(10)
            rightPadding = dip(10)

            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(android.R.color.holo_red_dark,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                recyclerView = recyclerView {
                    lparams(width = matchParent, height = matchParent)
                    layoutManager = LinearLayoutManager(ctx)
                }

            }

        }
    }

    fun showFavouriteSchedule(){
        favouritesSchedule.clear()
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = true
            val results = select(ScheduleTeamFavourite.TABLE_NAME)
            val favourites = results.parseList(classParser<ScheduleTeamFavourite>())
            favouritesSchedule.addAll(favourites)
            swipeRefreshLayout.isRefreshing = false

            adapter.notifyDataSetChanged()
        }
    }

}
