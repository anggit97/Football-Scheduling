package com.anggitprayogo.footballclub_scheduling.screen.detailschedule

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilsTest {

    @Test
    fun toTestSimpleString() {

        var schedule = DetailScheduleActivity()

        val dateFormat = SimpleDateFormat("MM/dd/yyyy")
        val date = dateFormat.parse("02/28/2018")
        assertEquals("Wed, 28 Feb 2018", schedule.toSimpleString(date))
    }
}