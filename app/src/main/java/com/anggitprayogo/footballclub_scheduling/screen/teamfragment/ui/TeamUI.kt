package com.anggitprayogo.footballclub_scheduling.screen.teamfragment.ui

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.anggitprayogo.footballclub_scheduling.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamUI: AnkoComponent<ViewGroup>{

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {

        cardView {
            lparams(matchParent, dip(40))

            linearLayout {
                lparams(matchParent, matchParent)
                orientation = LinearLayout.HORIZONTAL
                weightSum = 12f

                imageView {
                    id = R.id.iv_team
                }.lparams(dip(0), matchParent)

                textView {
                    id = R.id.tv_team
                    textColor = android.R.color.black
                }.lparams(dip(0), matchParent)
            }
        }

    }
}