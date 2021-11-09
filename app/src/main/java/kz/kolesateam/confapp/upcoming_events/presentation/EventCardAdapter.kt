package kz.kolesateam.confapp.upcoming_events.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import kz.kolesateam.confapp.R
import kz.kolesateam.confapp.events.data.model.ApiEventData
import kz.kolesateam.confapp.utils.BaseAdapter
import kz.kolesateam.confapp.utils.BaseViewHolder

class EventCardAdapter(
    items: List<ApiEventData>,
    private val onCardClicked: (String) -> Unit,
    private val onFavoriteClicked: (ApiEventData) -> Unit
) : BaseAdapter<ApiEventData>(items) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ApiEventData> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vh_event_card, parent, false)
        return EventCardViewHolder(view, onCardClicked, onFavoriteClicked)
    }
}