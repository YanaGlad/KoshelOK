package com.example.gladkikhvlasovtinkoff.ui.ui.walletoperation


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.SwipeToActionBinding
import com.example.gladkikhvlasovtinkoff.extension.getDayString
import com.example.gladkikhvlasovtinkoff.extension.getTimeString
import com.example.gladkikhvlasovtinkoff.swipe.SwipeAction
import com.example.gladkikhvlasovtinkoff.swipe.SwipeMenuListener
import com.example.gladkikhvlasovtinkoff.util.styleText
import gcom.example.gladkikhvlasovtinkoff.swipe.ActionBindHelper
import java.util.*

typealias OnActionClick = (transaction: WalletOperationModel, action: SwipeAction) -> Unit

class WalletOperationAdapter internal constructor(private val onActionClicked: OnActionClick) :
    ListAdapter<WalletOperationModel, WalletOperationAdapter.WalletOperationViewHolder>(
        OperationDiffUtil()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletOperationViewHolder =
        WalletOperationViewHolder(
            SwipeToActionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            currentList, onActionClicked
        )


    override fun onBindViewHolder(holder: WalletOperationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class OperationDiffUtil : DiffUtil.ItemCallback<WalletOperationModel>() {
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

    class WalletOperationViewHolder(
        val binding: SwipeToActionBinding,
        val list: List<WalletOperationModel>,
        val onActionClick: OnActionClick
    ) : RecyclerView.ViewHolder(binding.root), SwipeMenuListener {
        private val actionsBindHelper = ActionBindHelper()

        fun bind(walletOperations: WalletOperationModel) {
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
            binding.data.money.text =
                if (walletOperations.value.length > 3) styleText(walletOperations.value) else walletOperations . value
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