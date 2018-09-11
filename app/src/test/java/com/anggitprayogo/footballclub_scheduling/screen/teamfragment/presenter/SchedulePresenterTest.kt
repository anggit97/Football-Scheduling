package com.anggitprayogo.footballclub_scheduling.screen.teamfragment.presenter

import com.anggitprayogo.footballclub_scheduling.api.repository.ScheduleCallback
import com.anggitprayogo.footballclub_scheduling.api.repository.ScheduleRepository
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.presenter.PrevSchedulePresenter
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.ScheduleView
import com.nhaarman.mockito_kotlin.argumentCaptor
import com.nhaarman.mockito_kotlin.eq
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class SchedulePresenterTest {

    @Mock private lateinit var presenter: PrevSchedulePresenter
    @Mock private lateinit var scheduleRepository: ScheduleRepository
    @Mock private lateinit var events: Events
    @Mock private lateinit var view: ScheduleView

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        presenter = PrevSchedulePresenter(view, scheduleRepository)
    }

    /**
     * Test Apakah API Call schedule pertandingan success
     */
    @Test
    fun getSchedule(){
        val id = "4328"

        presenter.getPrevSchedule();
        argumentCaptor<ScheduleCallback<Events?>>().apply {
            verify(scheduleRepository).getPrevMatch(eq(id), capture())
            firstValue.onDataLoaded(events)
        }

        verify(view).onProgress()
        verify(view).onDataLoaded(events)
        verify(view).postProgress()

    }

    /**
     * Test Apakah API Call schedule pertandingan Gagal
     */
    @Test
    fun getScheduleError(){
        val id = "4328"

        presenter.getPrevSchedule()
        argumentCaptor<ScheduleCallback<Events?>>().apply {
            verify(scheduleRepository).getPrevMatch(eq(id), capture())
            firstValue.onDataError()
        }

        verify(view).onProgress()
        verify(view).onDataError()
        verify(view).postProgress()
    }

}
