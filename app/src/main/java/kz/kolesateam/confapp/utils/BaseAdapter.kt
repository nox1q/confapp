package kz.kolesateam.confapp.utils

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<M>(private val items: List<M>) :
    RecyclerView.Adapter<BaseViewHolder<M>>() {

    override fun onBindViewHolder(holder: BaseViewHolder<M>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}