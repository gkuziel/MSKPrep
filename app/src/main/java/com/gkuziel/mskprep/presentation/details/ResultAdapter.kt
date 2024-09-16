package com.gkuziel.mskprep.presentation.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gkuziel.mskprep.databinding.ItemResultBinding


class ResultAdapter(
    private var items: List<ResultUi> = emptyList(),
    private val onItemClick: (ResultUi) -> Unit
) : RecyclerView.Adapter<ResultAdapter.ItemViewHolder>() {

    fun setItems(items: List<ResultUi>) {
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