package com.anggitprayogo.footballclub_scheduling.screen.favouriteteamsfragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.design.R.attr.colorAccent
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.data.Favourite
import com.anggitprayogo.footballclub_scheduling.data.database
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import org.jetbrains.anko.support.v4.toast

class FavoriteTeamsFragment : Fragment(), AnkoComponent<Context>{

    private var favourites: MutableList<Favourite> = mutableListOf()

    lateinit var adapter: FavouriteTeamsAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var listFavourite: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.Companion.create(ctx))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = FavouriteTeamsAdapter(favourites, activity){
            it.teamName?.let { it1 -> toast(it1) }
        }

        listFavourite.adapter = adapter

        showFavourite()

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            showFavourite()
        }
    }

    private fun showFavourite(){
        favourites.clear()
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(Favourite.TABLE_FAVOURITE)
            val favourite = result.parseList(classParser<Favourite>())
            favourites.addAll(favourite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(width = matchParent, height = matchParent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeResources(android.R.color.holo_red_dark,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light,
                        android.R.color.holo_red_light)

                listFavourite = recyclerView {
                    lparams(width = matchParent, height = matchParent)
                    layoutManager = LinearLayoutManager(ctx)
                }
            }
        }
    }
}
