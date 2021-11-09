package kz.kolesateam.confapp.upcoming_events.data.datasource

import kz.kolesateam.confapp.events.data.model.ApiBranchData
import retrofit2.Call
import retrofit2.http.GET

interface UpcomingEventsDataSource {

    @GET("/upcoming_events")
    fun getUpcomingEvents(): Call<List<ApiBranchData>>
}