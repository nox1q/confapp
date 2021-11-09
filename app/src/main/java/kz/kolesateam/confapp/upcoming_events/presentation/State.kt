package kz.kolesateam.confapp.upcoming_events.presentation

import kz.kolesateam.confapp.events.data.model.ApiBranchData

sealed class State {
    object ShowLoading : State()
    object HideLoading : State()
    data class Error(val t: Throwable) : State()
    data class Success(val events: List<ApiBranchData>) : State()
    data class ShowUserName(val userName: String) : State()
}
