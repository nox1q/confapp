package kz.kolesateam.confapp.all_events.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kz.kolesateam.confapp.all_events.domain.AllEventsRepository
import kz.kolesateam.confapp.favourite_events.FavoriteEventsRepository

class AllEventsViewModel(
    private val allEventsRepository: AllEventsRepository,
    private val favoriteEventsRepository: FavoriteEventsRepository
) : ViewModel() {
    val viewState: MutableLiveData<AllEventsState> = MutableLiveData()

    fun onViewSubscribed(id: Int) {
        viewState.value = AllEventsState.ShowLoading
        allEventsRepository.loadAllEvents(
            id,
            onSuccess = {
                viewState.value = AllEventsState.Success(it)
                viewState.value = AllEventsState.HideLoading
            },
            onFailure = {
                viewState.value = AllEventsState.Error(it)
                viewState.value = AllEventsState.HideLoading
            }
        )
    }
}