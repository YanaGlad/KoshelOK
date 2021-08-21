package com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.CurrencySwitcherBinding
import com.example.gladkikhvlasovtinkoff.model.CurrencyDataI


class CurrencyAdapter :
    ListAdapter<CurrencyDataI, CurrencyAdapter.CurrencyViewHolder>(
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
            holder.binding.switchMaterial.isChecked = false

        holder.binding.switchMaterial.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                for (i in list.indices){
                    if(holder.binding.switchMaterial.isChecked && i!=position)
                        list[i].isChekced = false
                    else if( i == position)
                        list[i].isChekced = true
                }
            }
            submitList(list)
         }

    }


    //Currency data!
    class OperationDiffUtil : DiffUtil.ItemCallback<CurrencyDataI>() {
        override fun areItemsTheSame(
            oldItem: CurrencyDataI,
            newItem: CurrencyDataI
        ): Boolean {
           return oldItem.isChekced == newItem.isChekced
        }

        override fun areContentsTheSame(
            oldItem: CurrencyDataI,
            newItem: CurrencyDataI
        ): Boolean =
            oldItem == newItem

    }

    class CurrencyViewHolder(
        val binding: CurrencySwitcherBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(walletOperations: CurrencyDataI) {
            binding.switchMaterial.text = "Russian ruble"

        }

    }
}