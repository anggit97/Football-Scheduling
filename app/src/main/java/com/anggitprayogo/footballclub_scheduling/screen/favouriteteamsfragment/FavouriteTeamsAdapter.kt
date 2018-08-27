package com.anggitprayogo.footballclub_scheduling.screen.favouriteteamsfragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.data.Favourite
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import kotlin.coroutines.experimental.coroutineContext

class FavouriteTeamsAdapter(private val favourite: List<Favourite>,
                            private val context: Context?,
                            private val listnener: (Favourite) -> Unit): RecyclerView.Adapter<FavouriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        return FavouriteViewHolder(TeamUI().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favourite.size

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        holder.bindItems(favourite.get(position), context!!,  listnener)
    }
}

class TeamUI: AnkoComponent<ViewGroup>{

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                orientation = LinearLayout.HORIZONTAL
                padding = dip(16)

                imageView {
                    id = R.id.team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }

}

class FavouriteViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val teamName: TextView = view.findViewById(R.id.team_name)
    private val teamBadge: ImageView = view.findViewById(R.id.team_badge)

    fun bindItems(get: Favourite, context: Context,listnener: (Favourite) -> Unit) {

        teamName.text = get.teamName

        Glide.with(context)
                .load(get.teamBadge)
                .into(teamBadge)

        listnener(get)

    }

}