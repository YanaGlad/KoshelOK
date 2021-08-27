package com.example.gladkikhvlasovtinkoff.ui.ui.selectcategory

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.CategoryItemBinding
import com.example.gladkikhvlasovtinkoff.databinding.SwipeCategoryItemBinding
import com.example.gladkikhvlasovtinkoff.model.CategoryDataSample
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.swipe.SwipeAction
import com.example.gladkikhvlasovtinkoff.swipe.SwipeMenuListener
import com.example.gladkikhvlasovtinkoff.ui.ui.transactioncreation.category.IconHelper
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.WalletsAdapter
import com.pes.androidmaterialcolorpickerdialog.ColorPicker
import gcom.example.gladkikhvlasovtinkoff.swipe.ActionBindHelper

typealias OnActionClick = (transaction: CategoryDataSample, action: SwipeAction) -> Unit

class OperationCategoryAdapter(
    private val iconHelper: IconHelper,
    val activity: AppCompatActivity,
    private val isGridIcon: Boolean = false,
    private val onActionClicked: OnActionClick
) : RecyclerView.Adapter<OperationCategoryAdapter.ViewHolder>() {

    private val categories: MutableList<TransactionCategoryData> = arrayListOf()
    private var checkedPosition = -1

    private var _checkedItem: MutableLiveData<TransactionCategoryData?> = MutableLiveData(null)
    val checkedItem: LiveData<TransactionCategoryData?>
        get() = _checkedItem


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            activity,
            SwipeCategoryItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false),
            isGridIcon,
            onActionClicked
        )
        holder.binding?.data?.constraintLayout?.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) {
                onItemChecked(holder.adapterPosition)
            }
        }
        return holder
    }

    private fun onItemChecked(position: Int) {
        val oldPosition = checkedPosition
        if (position == oldPosition && position >= 0) {
            checkedPosition = -1
            notifyItemChanged(oldPosition)
            _checkedItem.value = null
            iconHelper.setIcon(categories[position].name, categories[position].id, false)
        } else {
            checkedPosition = position
            if (oldPosition >= 0)
                notifyItemChanged(oldPosition)
            notifyItemChanged(position)
            _checkedItem.value = categories[position]
            iconHelper.setIcon(categories[position].name, categories[position].id, true)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position], position, checkedPosition)
    }

    override fun getItemCount(): Int = categories.size

    fun addItems(categories: List<TransactionCategoryData>) {
        this.categories.clear()
        this.categories.addAll(categories)

        notifyDataSetChanged()
    }

    class ViewHolder(
        val activity: AppCompatActivity,
        val _binding: SwipeCategoryItemBinding,
        private val isGridIcon: Boolean,
        private val onActionClick: OnActionClick
    ) :
        RecyclerView.ViewHolder(_binding.root), SwipeMenuListener {
        var binding: SwipeCategoryItemBinding? = null
        private val actionsBindHelper = ActionBindHelper()
        private lateinit var item: TransactionCategoryData

        init {
            binding = SwipeCategoryItemBinding.bind(itemView)
        }

        fun bind(
            transactionCategoryData: TransactionCategoryData,
            position: Int,
            checkedPosition: Int
        ) {
            item = transactionCategoryData
            if (!isGridIcon)
                binding?.swipeToAction?.menuListener = this
            binding?.data?.transactionDot?.setColorFilter(
                ColorPicker(
                    activity,
                    transactionCategoryData.colorRed,
                    transactionCategoryData.colorGreen,
                    transactionCategoryData.colorBlue
                ).color
            )

            binding?.data?.categoryImageIcon?.setImageDrawable(
                ResourcesCompat.getDrawable(
                    itemView.resources,
                    transactionCategoryData.iconId,
                    itemView.context.theme
                )
            )

            if (isGridIcon) {
                val params = binding?.data?.isCategoryChecked?.getLayoutParams()
                params?.height = 90
                params?.width = 90
                binding?.data?.isCategoryChecked?.layoutParams = params
                binding?.data?.isCategoryChecked?.setColorFilter(
                    ColorPicker(
                        activity,
                        0,
                        0,
                        0
                    ).color
                )
            }

            binding?.data?.categoryName?.text = transactionCategoryData.name
            if (position == checkedPosition) {
                binding?.data?.isCategoryChecked?.visibility = View.VISIBLE
            } else
                binding?.data?.isCategoryChecked?.visibility = View.GONE
        }

        override fun onClosed(view: View) {
        }

        override fun onOpened(view: View) {
            if (!isGridIcon) {
                val walletData = item
                actionsBindHelper.closeOtherThan(walletData.name)
            }
        }

        override fun onFullyOpened(view: View, quickAction: SwipeAction) {}

        override fun onActionClicked(view: View, action: SwipeAction) {
            if (!isGridIcon) {
                onActionClick(item.toSample(), action)
                binding?.swipeToAction?.close()
            }
        }

    }
}
