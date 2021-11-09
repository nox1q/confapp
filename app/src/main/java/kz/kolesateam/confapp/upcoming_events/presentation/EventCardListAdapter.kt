package kz.kolesateam.confapp.upcoming_events.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import kz.kolesateam.confapp.R
import kz.kolesateam.confapp.events.data.model.ApiBranchData
import kz.kolesateam.confapp.events.data.model.ApiEventData
import kz.kolesateam.confapp.utils.BaseAdapter
import kz.kolesateam.confapp.utils.BaseViewHolder

class EventCardListAdapter(
    items: List<ApiBranchData>,
    private val onMoveClicked: (Int, String) -> Unit,
    private val onCardClicked: (String) -> Unit,
    private val onFavoriteClicked: (ApiEventData) -> Unit
) : BaseAdapter<ApiBranchData>(items) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ApiBranchData> {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.vh_event_card_list, parent, false)
        return EventCardListViewHolder(view, onMoveClicked, onCardClicked, onFavoriteClicked)
    }
}