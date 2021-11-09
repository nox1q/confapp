package kz.kolesateam.confapp.upcoming_events.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.kolesateam.confapp.events.data.model.ApiEventData
import kz.kolesateam.confapp.favourite_events.DefaultFavoriteEventsRepository
import kz.kolesateam.confapp.favourite_events.FavoriteEventsRepository
import kz.kolesateam.confapp.upcoming_events.domain.UpcomingEventsRepository
import kz.kolesateam.confapp.upcoming_events.domain.UserDataSource

class UpcomingEventsViewModel(
    private val upcomingEventsRepository: UpcomingEventsRepository,
    private val favoriteEventsRepository: FavoriteEventsRepository,
    private val userDataSource: UserDataSource
) : ViewModel() {
    val viewState: MutableLiveData<State> = MutableLiveData()

    init {
        loadEvents()
        fetchName()
    }

    fun onMarkAsFavoriteClicked(eventData: ApiEventData) {
        val eventId = eventData.id ?: -1
        val isFavorite = favoriteEventsRepository.isFavorite(eventId)

        if (!isFavorite)
            favoriteEventsRepository.saveFavoriteEvent(eventData)
        else
            favoriteEventsRepository.removeFavoriteEvent(eventId)
    }

    private fun loadEvents() {
        viewState.value = State.ShowLoading
        upcomingEventsRepository.loadEvents(
            onSuccess = {
                viewState.value = State.Success(it)
                viewState.value = State.HideLoading
            },
            onFailure = {
                viewState.value = State.Error(it)
                viewState.value = State.HideLoading
            }
        )
    }

    private fun fetchName() {
        viewState.value = State.ShowUserName(userDataSource.getUserName())
    }
}
