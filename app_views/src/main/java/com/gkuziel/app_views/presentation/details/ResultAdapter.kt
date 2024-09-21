package com.gkuziel.app_views.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gkuziel.app_views.databinding.ItemResultBinding
import com.gkuziel.core.presentation.details.ResultUI


class ResultAdapter(
    private var items: List<ResultUI> = emptyList(),
    private val onItemClick: (ResultUI) -> Unit
) : RecyclerView.Adapter<ResultAdapter.ItemViewHolder>() {

    fun setItems(items: List<ResultUI>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val binding = ItemResultBinding.inflate(
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
            tvType.text = item.type.toString()
            tvValue.text = item.value.toString()
            root.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }

    override fun getItemCount() = items.size

    inner class ItemViewHolder(
        val binding: ItemResultBinding
    ) : RecyclerView.ViewHolder(binding.root)
}