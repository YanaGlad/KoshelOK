package com.example.gladkikhvlasovtinkoff.ui.ui.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.DateItemBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.model.TransactionDate

class DateDelegate : AdapterDelegate {
    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ViewHolder(DateItemBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as ViewHolder).bind(item.content() as TransactionDate)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is DateDelegateItem

    class ViewHolder(val binding : DateItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(date : TransactionDate) {
            binding.dateValue.text = date.dateTimestamp.getDayString(binding.root.context)
        }
    }
}