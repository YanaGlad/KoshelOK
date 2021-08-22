package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.SwipingTransactionDataItemBinding
import com.example.gladkikhvlasovtinkoff.extension.convertToStyled
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.extension.getTimeString
import com.example.gladkikhvlasovtinkoff.extension.getTransactionTypeString
import com.example.gladkikhvlasovtinkoff.model.WalletTransactionModel
import com.example.gladkikhvlasovtinkoff.swipe.SwipeAction
import com.example.gladkikhvlasovtinkoff.swipe.SwipeMenuListener
import gcom.example.gladkikhvlasovtinkoff.swipe.ActionBindHelper

typealias OnActionClick = (transaction: WalletTransactionModel, action: SwipeAction) -> Unit

class WalletOperationAdapter internal constructor(
    val context: Context,
    private val onActionClicked: OnActionClick
) :
    ListAdapter<WalletTransactionModel, WalletOperationAdapter.WalletOperationViewHolder>(
        OperationDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletOperationViewHolder =
        WalletOperationViewHolder(
            context,
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

    class OperationDiffUtil : DiffUtil.ItemCallback<WalletTransactionModel>() {
        override fun areItemsTheSame(
            oldItem: WalletTransactionModel,
            newItem: WalletTransactionModel
        ): Boolean =
            oldItem.date == newItem.date

        override fun areContentsTheSame(
            oldItem: WalletTransactionModel,
            newItem: WalletTransactionModel
        ): Boolean =
            oldItem == newItem

    }

    class WalletOperationViewHolder(
        private val context: Context,
        private val binding: SwipingTransactionDataItemBinding,
        private val list: List<WalletTransactionModel>,
        val onActionClick: OnActionClick
    ) : RecyclerView.ViewHolder(binding.root), SwipeMenuListener {
        private val actionsBindHelper = ActionBindHelper()

        fun bind(walletOperations: WalletTransactionModel) {
            binding.swipeToAction.menuListener = this

            binding.data.dateOperation.text =
                walletOperations.date.getDayString(binding.root.context)
            binding.data.imageOperation.setImageDrawable(
                ResourcesCompat.getDrawable(
                    binding.root.context.resources,
                    walletOperations.transactionCategoryData.iconId,
                    binding.root.context.theme
                )

            )
            //TODO добавить стайлинг текста
            binding.data.titleOperation.text = walletOperations.transactionCategoryData.name
            binding.data.time.text = walletOperations.date.getTimeString()
            binding.data.dateOperation.text = walletOperations.date.getDayString(context)
            binding.data.money.text = walletOperations.amount
            binding.data.subtitleOperation.text =
                if (walletOperations.isIncome) context.getString(R.string.income_text)
                else context.getString(R.string.costs_text)
        }

        override fun onClosed(view: View) {
        }

        override fun onOpened(view: View) {
            val transaction = list[adapterPosition]
            actionsBindHelper.closeOtherThan(transaction.isIncome.getTransactionTypeString(view.context))
        }

        override fun onFullyOpened(view: View, quickAction: SwipeAction) {
        }

        override fun onActionClicked(view: View, action: SwipeAction) {
            onActionClick(list[adapterPosition], action)
            binding.swipeToAction.close()
        }
    }
}