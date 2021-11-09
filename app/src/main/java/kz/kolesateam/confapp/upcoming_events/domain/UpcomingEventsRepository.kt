package kz.kolesateam.confapp.upcoming_events.domain

import kz.kolesateam.confapp.events.data.model.ApiBranchData
import kz.kolesateam.confapp.upcoming_events.data.datasource.UpcomingEventsDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpcomingEventsRepository(
    private val upcomingEventsDataSource: UpcomingEventsDataSource
) {
    fun loadEvents(
        onSuccess: (List<ApiBranchData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        upcomingEventsDataSource.getUpcomingEvents()
            .enqueue(object : Callback<List<ApiBranchData>> {
                override fun onResponse(
                    call: Call<List<ApiBranchData>>,
                    response: Response<List<ApiBranchData>>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()!!)
                    } else {
                        onFailure(NetworkResponseException())
                    }
                }

                override fun onFailure(call: Call<List<ApiBranchData>>, t: Throwable) {
                    onFailure(t)
                }
            })
    }
}
