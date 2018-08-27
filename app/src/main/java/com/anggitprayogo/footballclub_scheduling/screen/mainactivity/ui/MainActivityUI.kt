package com.anggitprayogo.footballclub_scheduling.screen.mainactivity.ui

import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import com.anggitprayogo.footballclub_scheduling.R
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.design.bottomNavigationView
import org.jetbrains.anko.design.coordinatorLayout
import org.jetbrains.anko.matchParent

class MainActivityUI: AnkoComponent<ViewGroup>{

    companion object {
    }

    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            coordinatorLayout {
                lparams(matchParent, matchParent)
                fitsSystemWindows = true
                id = R.id.root_layout

                bottomNavigationView {
                    lparams(matchParent, 80)
                    id = R.id.navigation_bottom_view
                    foregroundGravity = Gravity.BOTTOM
                }
            }
        }
    }

}