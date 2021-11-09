package kz.kolesateam.confapp.favourite_events

import kz.kolesateam.confapp.events.data.model.ApiEventData

interface FavoriteEventsRepository {

    fun saveFavoriteEvent(apiEventData: ApiEventData)

    fun removeFavoriteEvent(eventId: Int)

    fun isFavorite(eventId: Int): Boolean

    fun getAllFavoriteEvents(): List<ApiEventData>
}