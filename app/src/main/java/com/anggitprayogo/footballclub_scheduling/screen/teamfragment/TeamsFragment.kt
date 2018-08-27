package com.anggitprayogo.footballclub_scheduling.screen.teamfragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.adapter.TeamsAdapter
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.anggitprayogo.footballclub_scheduling.screen.detailteams.TeamActivity
import com.anggitprayogo.footballclub_scheduling.screen.teamfragment.presenter.TeamPresenter
import com.anggitprayogo.footballclub_scheduling.screen.teamfragment.view.TeamsView
import kotlinx.android.synthetic.main.activity_detail_schedule.*
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.*
import retrofit2.Retrofit


class TeamsFragment : Fragment(), AnkoComponent<Context>, TeamsView {

    private var teams: MutableList<TeamsItem> = mutableListOf()
    lateinit var spinner: Spinner
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var presenter: TeamPresenter
    lateinit var rvEvent: RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var leagueName: String
    lateinit var adapter: TeamsAdapter
    lateinit var retrofit: ServiceGenerator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val spinnerItems = resources.getStringArray(R.array.names)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = context?.let { TeamsAdapter(it, teams){

            startActivity<TeamActivity>(
                    "id" to it.idTeam
            )

        } }!!


        rvEvent.adapter = adapter

        retrofit = ServiceGenerator()
        presenter = TeamPresenter(this, retrofit)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeams(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        swipeRefreshLayout.onRefresh {
            presenter.getTeams(leagueName)
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return createView(AnkoContext.Companion.create(ctx))
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui){
        linearLayout{
            lparams(width = matchParent, height = matchParent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()
            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeColors(
                        resources.getColor(R.color.colorAccent),
                        resources.getColor(android.R.color.holo_green_light),
                        resources.getColor(android.R.color.holo_orange_light),
                        resources.getColor(android.R.color.holo_red_light)
                )

                relativeLayout {
                    lparams(matchParent, matchParent)

                    rvEvent = recyclerView {
                        lparams(matchParent, matchParent)
                        layoutManager = LinearLayoutManager(ctx)
                        adapter = TeamsAdapter(context, teams, {
                            toast(it.strTeam!!)
                        })
                    }

                    progressBar = progressBar {
                    }.lparams {
                        centerHorizontally()
                    }
                }

            }.lparams(matchParent, matchParent)
        }
    }


    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
        rvEvent.visibility = View.GONE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
        rvEvent.visibility = View.VISIBLE
    }

    override fun showError(error: String?) {
        d("ERROR", error)
    }

    override fun showTeamList(data: List<TeamsItem>) {
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }
}
