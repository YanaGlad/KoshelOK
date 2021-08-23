package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.CurrencySwitcherBinding
import com.example.gladkikhvlasovtinkoff.model.Currency


class CurrencyAdapter :
    ListAdapter<Currency, CurrencyAdapter.CurrencyViewHolder>(
        OperationDiffUtil()
    ) {
    private var lastChecked: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val holder = CurrencyViewHolder(
            CurrencySwitcherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        holder.binding.currencySwitcher.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onItemChecked(holder.adapterPosition)
            }
        }
        return holder
    }

    private fun onItemChecked(position: Int){
        val oldPosition = lastChecked
        if (position == oldPosition && position >= 0) {
            lastChecked = -1
            notifyItemChanged(oldPosition)
        } else {
            lastChecked = position
            if (oldPosition >= 0)
                notifyItemChanged(oldPosition)
            notifyItemChanged(position)
        }
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(getItem(position), position, lastChecked)
    }


    class OperationDiffUtil : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(
            oldItem: Currency,
            newItem: Currency
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Currency,
            newItem: Currency
        ): Boolean = oldItem == newItem
    }

    class CurrencyViewHolder(
        val binding: CurrencySwitcherBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency, position: Int, lastChecked: Int) {
            binding.currencySwitcher.text = currency.name
            binding.currencySwitcher.isChecked = position == lastChecked
        }
    }
}