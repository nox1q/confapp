package kz.kolesateam.confapp.upcoming_events.presentation

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import kz.kolesateam.confapp.R
import kz.kolesateam.confapp.events.data.model.ApiEventData
import kz.kolesateam.confapp.utils.BaseViewHolder

class EventCardViewHolder(
    itemView: View,
    private val onCardClicked: (String) -> Unit,
    private val onFavoriteClicked: ((ApiEventData) -> Unit)? = null
) : BaseViewHolder<ApiEventData>(itemView) {

    override fun bind(model: ApiEventData) {
        itemView.apply {
            val favouriteImageView: ImageView = findViewById(R.id.event_card_favourite_image_view)
            val startEndPlace: TextView = findViewById(R.id.event_card_start_end_place_text_view)
            val speakerName: TextView = findViewById(R.id.event_card_speaker_name_text_view)
            val description: TextView = findViewById(R.id.event_card_description_text_view)
            val job: TextView = findViewById(R.id.event_card_job_text_view)
            val card: FrameLayout = findViewById(R.id.vh_event_card)
            val reportState: CardView = findViewById(R.id.next_report_card_view)

            reportState.visibility = if (adapterPosition == 0) View.GONE else View.VISIBLE
            speakerName.text = model.speaker?.fullName
            job.text = model.speaker?.job
            description.text = model.title
            startEndPlace.text = "${model.startTime} - ${model.endTime} • ${model.place}"

            card.setOnClickListener {
                onCardClicked.invoke(model.title ?: "")
            }

            favouriteImageView.setOnClickListener {
                val imageResource =
                    if (model.isSelected) R.drawable.ic_like_big else R.drawable.ic_like_big_filled
                favouriteImageView.setImageResource(imageResource)
                model.isSelected = !model.isSelected
                onFavoriteClicked?.invoke(model)
            }
        }
    }
}
