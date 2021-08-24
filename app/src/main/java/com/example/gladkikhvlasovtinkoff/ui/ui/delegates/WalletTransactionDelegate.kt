package com.example.gladkikhvlasovtinkoff.ui.ui.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.DateItemBinding
import com.example.gladkikhvlasovtinkoff.databinding.SwipingTransactionDataItemBinding
import com.example.gladkikhvlasovtinkoff.databinding.WalletTransactionDataItemBinding
import com.example.gladkikhvlasovtinkoff.extension.getTimeString
import com.example.gladkikhvlasovtinkoff.extension.getTransactionTypeString
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.swipe.SwipeAction
import com.example.gladkikhvlasovtinkoff.swipe.SwipeMenuListener
import gcom.example.gladkikhvlasovtinkoff.swipe.ActionBindHelper

typealias OnActionClick = (transaction: WalletTransactionModel, action: SwipeAction) -> Unit

class WalletTransactionDelegate internal constructor(
    private val onActionClicked: OnActionClick
) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        WalletOperationViewHolder(
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.swiping_transaction_data_item, parent, false),
            onActionClick = onActionClicked
        )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as WalletOperationViewHolder).bind(item.content() as WalletTransactionModel)
    }

    override fun isOfViewType(item: DelegateItem): Boolean =
        item is TransactionDelegateItem

    class WalletOperationViewHolder(
        val view: View,
        val onActionClick: OnActionClick
    ) : RecyclerView.ViewHolder(view), SwipeMenuListener {

        private val actionsBindHelper = ActionBindHelper()
        private lateinit var item: WalletTransactionModel
        private val binding = SwipingTransactionDataItemBinding.bind(view)

        fun bind(walletOperations: WalletTransactionModel) {
            item = walletOperations
//            binding.data.transactionDot.setImageResource(walletOperations.transactionCategoryData.color)
            binding.swipeToAction.menuListener = this
            binding.data.imageOperation.setImageDrawable(
                ResourcesCompat.getDrawable(
                    binding.root.context.resources,
                    walletOperations.transactionCategoryData.iconId,
                    binding.root.context.theme
                )

            )
            binding.data.titleOperation.text = walletOperations.transactionCategoryData.name
            binding.data.time.text = walletOperations.date.getTimeString()
            binding.data.money.text = walletOperations.amount
            binding.data.subtitleOperation.text =
                if (walletOperations.isIncome) itemView.context.getString(R.string.income_text)
                else itemView.context.getString(R.string.costs_text)
        }

        override fun onClosed(view: View) {}

        override fun onOpened(view: View) {
            actionsBindHelper.closeOtherThan(item.isIncome.getTransactionTypeString(view.context))
        }

        override fun onFullyOpened(view: View, quickAction: SwipeAction) {
        }

        override fun onActionClicked(view: View, action: SwipeAction) {
            onActionClick(item, action)
            binding.swipeToAction.close()
        }
    }
}