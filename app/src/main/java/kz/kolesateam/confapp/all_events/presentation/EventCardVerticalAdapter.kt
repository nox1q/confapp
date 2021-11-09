package kz.kolesateam.confapp.all_events.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import kz.kolesateam.confapp.R
import kz.kolesateam.confapp.events.data.model.ApiEventData
import kz.kolesateam.confapp.upcoming_events.presentation.EventCardViewHolder
import kz.kolesateam.confapp.utils.BaseAdapter
import kz.kolesateam.confapp.utils.BaseViewHolder

class EventCardVerticalAdapter(
    items: List<ApiEventData>,
    private val onCardClicked: (String) -> Unit
) : BaseAdapter<ApiEventData>(items) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ApiEventData> {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_event_card_vertical, parent, false)
        return EventCardViewHolder(view, onCardClicked)
    }
}