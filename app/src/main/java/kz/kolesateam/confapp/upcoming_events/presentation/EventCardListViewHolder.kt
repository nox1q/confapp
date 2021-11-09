package kz.kolesateam.confapp.upcoming_events.presentation

import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kz.kolesateam.confapp.R
import kz.kolesateam.confapp.events.data.model.ApiBranchData
import kz.kolesateam.confapp.events.data.model.ApiEventData
import kz.kolesateam.confapp.utils.BaseViewHolder

class EventCardListViewHolder(
    itemView: View,
    private val onMoveClicked: (Int, String) -> Unit,
    private val onCardClicked: (String) -> Unit,
    private val onFavoriteClicked: (ApiEventData) -> Unit
) : BaseViewHolder<ApiBranchData>(itemView) {
    override fun bind(model: ApiBranchData) {
        itemView.apply {
            val title: TextView = findViewById(R.id.event_card_list_title_text_view)
            val list: RecyclerView = findViewById(R.id.event_card_list_recycler_view)
            val move: FrameLayout = findViewById(R.id.event_card_frame_title_text_view)
            title.text = model.title
            list.adapter = EventCardAdapter(model.events!!, onCardClicked, onFavoriteClicked)
            move.setOnClickListener {
                onMoveClicked.invoke(model.id ?: -1, model.title ?: "")
            }
        }
    }
}