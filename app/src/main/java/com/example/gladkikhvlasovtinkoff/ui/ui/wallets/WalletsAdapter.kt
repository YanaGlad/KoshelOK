package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.SwipeWalletItemBinding
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.swipe.SwipeAction
import com.example.gladkikhvlasovtinkoff.swipe.SwipeMenuListener
import gcom.example.gladkikhvlasovtinkoff.swipe.ActionBindHelper

typealias OnActionClick = (transaction: WalletData, action: SwipeAction) -> Unit

class WalletsAdapter internal constructor(
    val context: Context,
    private val onActionClicked: OnActionClick
) :
    ListAdapter<WalletData, WalletsAdapter.WalletViewHolder>(
        OperationDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder =
        WalletViewHolder(
            SwipeWalletItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            currentList,
            onActionClicked
        )


    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class OperationDiffUtil : DiffUtil.ItemCallback<WalletData>() {
        override fun areItemsTheSame(
            oldItem: WalletData,
            newItem: WalletData
        ): Boolean =
            oldItem.name == newItem.name

        override fun areContentsTheSame(
            oldItem: WalletData,
            newItem: WalletData
        ): Boolean =
            oldItem == newItem

    }

    class WalletViewHolder(
        private val binding: SwipeWalletItemBinding,
        private val list: List<WalletData>,
        val onActionClick: OnActionClick
    ) : RecyclerView.ViewHolder(binding.root), SwipeMenuListener {
        private val actionsBindHelper = ActionBindHelper()

        fun bind(walletOperations: WalletData) {
            binding.swipeToAction.menuListener = this
            binding.data.walletItemName.text = walletOperations.name
            binding.data.walletItemBalance.text = walletOperations.amount
        }

        override fun onClosed(view: View) {
        }


        override fun onOpened(view: View) {
            val walletData = list[adapterPosition]
            actionsBindHelper.closeOtherThan(walletData.name)
        }

        override fun onFullyOpened(view: View, quickAction: SwipeAction) {
        }

        override fun onActionClicked(view: View, action: SwipeAction) {
            onActionClick(list[adapterPosition], action)
            binding.swipeToAction.close()
        }
    }


}