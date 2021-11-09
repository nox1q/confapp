package kz.kolesateam.confapp.all_events.presentation

import kz.kolesateam.confapp.events.data.model.ApiEventData

sealed class AllEventsState{
    object ShowLoading : AllEventsState()
    object HideLoading : AllEventsState()
    data class Error(val t: Throwable) : AllEventsState()
    data class Success(val events: List<ApiEventData>) : AllEventsState()
}
