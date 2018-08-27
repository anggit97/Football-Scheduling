package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Events(
        @SerializedName("events")
        @Expose
        var datas: MutableList<DataEvent>
)