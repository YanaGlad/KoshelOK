package com.example.gladkikhvlasovtinkoff.ui.ui.wallets

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
    private val clickListener: OnWalletClickListener,
    private val onActionClicked: OnActionClick
) :
    ListAdapter<WalletData, WalletsAdapter.WalletViewHolder>(
        OperationDiffUtil()
    ) {

    interface OnWalletClickListener {
        fun onWalletClick(walletData: WalletData, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val holder = WalletViewHolder(
            SwipeWalletItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ),
            onActionClicked
        )
        holder.binding.data.mainLayout.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                clickListener.onWalletClick(getItem(holder.adapterPosition), holder.adapterPosition)
            }
        }
        return holder
    }

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
        val binding: SwipeWalletItemBinding,
        private val onActionClick: OnActionClick
    ) : RecyclerView.ViewHolder(binding.root), SwipeMenuListener {

        private val actionsBindHelper = ActionBindHelper()
        private lateinit var item : WalletData

        fun bind(walletOperations: WalletData) {
            item = walletOperations
            binding.swipeToAction.menuListener = this
            binding.data.walletItemName.text = walletOperations.name
            binding.data.walletItemBalance.text = walletOperations.amount
        }

        override fun onClosed(view: View) {
        }

        override fun onOpened(view: View) {
            val walletData = item
            actionsBindHelper.closeOtherThan(walletData.name)
        }

        override fun onFullyOpened(view: View, quickAction: SwipeAction) {}

        override fun onActionClicked(view: View, action: SwipeAction) {
            onActionClick(item, action)
            binding.swipeToAction.close()
        }
    }
}