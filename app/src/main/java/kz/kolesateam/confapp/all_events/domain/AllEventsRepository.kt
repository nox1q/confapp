package kz.kolesateam.confapp.all_events.domain

import kz.kolesateam.confapp.all_events.data.datasource.AllEventsDataSource
import kz.kolesateam.confapp.events.data.model.ApiEventData
import kz.kolesateam.confapp.upcoming_events.domain.NetworkResponseException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AllEventsRepository(
    private val allEventsDataSource: AllEventsDataSource
) {
    fun loadAllEvents(
        branchId: Int,
        onSuccess: (List<ApiEventData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        allEventsDataSource.getAllEvents(branchId)
            .enqueue(object : Callback<List<ApiEventData>> {
                override fun onResponse(
                    call: Call<List<ApiEventData>>,
                    response: Response<List<ApiEventData>>
                ) {
                    if (response.isSuccessful) {
                        onSuccess(response.body()!!)
                    } else {
                        onFailure(NetworkResponseException())
                    }
                }

                override fun onFailure(call: Call<List<ApiEventData>>, t: Throwable) {
                    onFailure(t)
                }
            })
    }
}