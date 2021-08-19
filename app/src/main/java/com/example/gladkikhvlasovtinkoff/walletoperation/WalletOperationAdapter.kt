package com.example.gladkikhvlasovtinkoff.walletoperation


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.WalletOperationDataItemBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.extension.getTimeString
import com.example.gladkikhvlasovtinkoff.util.ItemTouchHelperListener

import com.example.gladkikhvlasovtinkoff.util.styleText
import java.util.*

class WalletOperationAdapter internal constructor() :
    ListAdapter<WalletOperationModel, WalletOperationAdapter.WalletOperationViewHolder>(OperationDiffUtil())
    , ItemTouchHelperListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletOperationViewHolder =
        WalletOperationViewHolder(
            WalletOperationDataItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: WalletOperationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class OperationDiffUtil : DiffUtil.ItemCallback<WalletOperationModel>(){
        override fun areItemsTheSame(
            oldItem: WalletOperationModel,
            newItem: WalletOperationModel
        ): Boolean =
            oldItem.date == newItem.date

        override fun areContentsTheSame(
            oldItem: WalletOperationModel,
            newItem: WalletOperationModel
        ): Boolean =
            oldItem == newItem

    }

    class WalletOperationViewHolder (
        val binding: WalletOperationDataItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(walletOperations : WalletOperationModel){
            binding.dateOperation.text = walletOperations.date.getDayString(binding.root.context)
            binding.imageOperation.setImageDrawable(
                ResourcesCompat.getDrawable(
                    binding.root.context.resources,
                    walletOperations.imageId,
                    binding.root.context.theme
                )

            )
            binding.money.text = styleText(walletOperations.value)
            binding.subtitleOperation.text =
                walletOperations.type
            binding.titleOperation.text =
                binding.root.context.getString(walletOperations.categoryTextId)
            binding.time.text = walletOperations.date.getTimeString()
        }

    }

    override fun onItemSwipe(position: Int) {

    }


}