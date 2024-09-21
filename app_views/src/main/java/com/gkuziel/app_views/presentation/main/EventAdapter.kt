package com.gkuziel.app_views.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gkuziel.app_views.databinding.ItemEventBinding
import com.gkuziel.core.presentation.main.EventUI


class EventAdapter(
    private var items: List<EventUI> = emptyList(),
    private val onItemClick: (EventUI) -> Unit
) : RecyclerView.Adapter<EventAdapter.ItemViewHolder>() {


    fun setItems(items: List<EventUI>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = ItemEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ItemViewHolder(binding)
    }


    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.binding.apply {
            val item = items[position]
            tvId.text = item.id
            tvDescription.text = item.description
            tvSynchronized.text = item.synchronized.toString()
            tvUpdated.text = item.updated.toString()
            tvValidity.text = item.timeLeftToDecay.toString()
            tvResults.text = item.results.size.toString()
            setFontColor(this, item.fontColor)
            root.setOnClickListener {
                if (item.clickable) {
                    onItemClick.invoke(item)
                }
            }
        }
    }


    private fun setFontColor(binding: ItemEventBinding, color: Int) {
        binding.apply {
            tvId.setTextColor(color)
            tvDescription.setTextColor(color)
            tvSynchronized.setTextColor(color)
            tvUpdated.setTextColor(color)
            tvValidity.setTextColor(color)
            tvResults.setTextColor(color)
        }
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(
        val binding: ItemEventBinding
    ) : RecyclerView.ViewHolder(binding.root)
}