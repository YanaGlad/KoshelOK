package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.SwipingTransactionDataItemBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.extension.getTimeString
import com.example.gladkikhvlasovtinkoff.extension.styleText
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionData
import com.example.gladkikhvlasovtinkoff.swipe.SwipeAction
import com.example.gladkikhvlasovtinkoff.swipe.SwipeMenuListener
import gcom.example.gladkikhvlasovtinkoff.swipe.ActionBindHelper

typealias OnActionClick = (transaction: WalletTransactionData, action: SwipeAction) -> Unit

class WalletOperationAdapter internal constructor(private val onActionClicked: OnActionClick) :
    ListAdapter<WalletTransactionData, WalletOperationAdapter.WalletOperationViewHolder>(
        OperationDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletOperationViewHolder =
        WalletOperationViewHolder(
            SwipingTransactionDataItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            currentList, onActionClicked
        )


    override fun onBindViewHolder(holder: WalletOperationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class OperationDiffUtil : DiffUtil.ItemCallback<WalletTransactionData>() {
        override fun areItemsTheSame(
            oldItem: WalletTransactionData,
            newItem: WalletTransactionData
        ): Boolean =
            oldItem.date == newItem.date

        override fun areContentsTheSame(
            oldItem: WalletTransactionData,
            newItem: WalletTransactionData
        ): Boolean =
            oldItem == newItem

    }

    class WalletOperationViewHolder(
        private val binding: SwipingTransactionDataItemBinding,
        private val list: List<WalletTransactionData>,
        val onActionClick: OnActionClick
    ) : RecyclerView.ViewHolder(binding.root), SwipeMenuListener {
        private val actionsBindHelper = ActionBindHelper()

        fun bind(walletOperations: WalletTransactionData) {
            binding.swipeToAction.menuListener = this

            binding.data.dateOperation.text =
                walletOperations.date.getDayString(binding.root.context)
            binding.data.imageOperation.setImageDrawable(
                ResourcesCompat.getDrawable(
                    binding.root.context.resources,
                    walletOperations.imageId,
                    binding.root.context.theme
                )

            )
            binding.data.money.text = if (walletOperations.value.length > 3) walletOperations.value.styleText() else walletOperations.value
                        binding.data.subtitleOperation.text =
                    walletOperations.type
            binding.data.titleOperation.text =
                binding.root.context.getString(walletOperations.categoryTextId)
            binding.data.time.text = walletOperations.date.getTimeString()
        }

        override fun onClosed(view: View) {
        }


        override fun onOpened(view: View) {
            val transaction = list[adapterPosition]
            actionsBindHelper.closeOtherThan(transaction.type)
        }

        override fun onFullyOpened(view: View, quickAction: SwipeAction) {
        }

        override fun onActionClicked(view: View, action: SwipeAction) {
            onActionClick(list[adapterPosition], action)
            binding.swipeToAction.close()
        }

    }
}