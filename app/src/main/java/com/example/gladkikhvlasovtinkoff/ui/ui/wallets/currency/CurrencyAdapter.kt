package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.CurrencySwitcherBinding
import com.example.gladkikhvlasovtinkoff.model.Currency
import com.example.gladkikhvlasovtinkoff.model.CurrencyData


class CurrencyAdapter(val onCurrencySwitcher : OnCurrencySwitcher) :
    ListAdapter<CurrencyData, CurrencyAdapter.CurrencyViewHolder>(
        OperationDiffUtil()
    ) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder =
        CurrencyViewHolder(
            CurrencySwitcherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        val list = currentList
        holder.bind(getItem(position))
        if(!getItem(position).isChekced)
            holder.binding.currencySwitcher.isChecked = false


        holder.binding.currencySwitcher.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                onCurrencySwitcher.changeViewModel(Currency(getItem(position).id, getItem(position).code, getItem(position).name))

                for (i in list.indices){
                    if(holder.binding.currencySwitcher.isChecked && i!=position)
                        list[i].isChekced = false
                    else if( i == position)
                        list[i].isChekced = true
                }
            }
            submitList(list)
        }
    }


    //Currency data!
    class OperationDiffUtil : DiffUtil.ItemCallback<CurrencyData>() {
        override fun areItemsTheSame(
            oldItem: CurrencyData,
            newItem: CurrencyData
        ): Boolean {
            return oldItem.isChekced == newItem.isChekced
        }

        override fun areContentsTheSame(
            oldItem: CurrencyData,
            newItem: CurrencyData
        ): Boolean =
            oldItem == newItem

    }

    class CurrencyViewHolder(
        val binding: CurrencySwitcherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(walletOperations: CurrencyData) {
            binding.currencySwitcher.text = walletOperations.name

        }

    }
}