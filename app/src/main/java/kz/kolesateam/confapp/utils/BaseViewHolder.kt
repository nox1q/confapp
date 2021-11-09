package kz.kolesateam.confapp.utils

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(model: M)
}