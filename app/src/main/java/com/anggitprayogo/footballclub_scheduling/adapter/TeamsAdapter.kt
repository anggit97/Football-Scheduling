package com.anggitprayogo.footballclub_scheduling.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.anggitprayogo.footballclub_scheduling.screen.teamfragment.ui.TeamUI
import com.bumptech.glide.Glide
import org.jetbrains.anko.AnkoContext

class TeamsAdapter(val context: Context,val teams: List<TeamsItem>, val listener: (TeamsItem) -> Unit)
    : RecyclerView.Adapter<TeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItems(context, teams.get(position), listener)
    }
}

class TeamViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val ivTeam = view.findViewById<ImageView>(R.id.iv_team)
    val tvTeam = view.findViewById<TextView>(R.id.tv_team)

    fun bindItems(context: Context, teams: TeamsItem, listener: (TeamsItem) -> Unit){

        Glide.with(context)
                .load(teams.strTeamBadge)
                .into(ivTeam)

        tvTeam.text = teams.strTeam

        itemView.setOnClickListener {
            listener(teams)
        }
    }

}