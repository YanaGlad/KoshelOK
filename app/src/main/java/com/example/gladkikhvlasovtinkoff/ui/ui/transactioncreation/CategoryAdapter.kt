package com.example.gladkikhvlasovtinkoff.ui.ui.selectcategory

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.CategoryItemBinding
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import com.example.gladkikhvlasovtinkoff.model.userCateroryKeys

class OperationCategoryAdapter : RecyclerView.Adapter<OperationCategoryAdapter.ViewHolder>() {

    private val categories: MutableList<TransactionCategoryData> = mutableListOf()
    private var checkedPosition = -1

    private var _checkedItem: MutableLiveData<TransactionCategoryData?> = MutableLiveData(null)
    val checkedItem: LiveData<TransactionCategoryData?>
        get() = _checkedItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            CategoryItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
        holder.itemView.setOnClickListener {
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
        } else {
            checkedPosition = position
            if (oldPosition >= 0)
                notifyItemChanged(oldPosition)
            notifyItemChanged(position)
            _checkedItem.value = categories[position]
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position], position, checkedPosition)
    }


    override fun getItemCount(): Int = categories.size

    fun addItems(categories: List<TransactionCategoryData>) {
        val oldSize = categories.size
        this.categories.addAll(categories)
        notifyItemRangeChanged(oldSize - 1, categories.size)
    }

    class ViewHolder(val _binding: CategoryItemBinding) :
        RecyclerView.ViewHolder(_binding.root) {
        var binding: CategoryItemBinding? = null

        init {
            binding = CategoryItemBinding.bind(itemView)
        }

        fun bind(
            transactionCategoryData: TransactionCategoryData,
            position: Int,
            checkedPosition: Int
        ) {
            binding?.transactionDot?.setImageResource(transactionCategoryData.color)
            binding?.categoryImageIcon?.setImageDrawable(
                ResourcesCompat.getDrawable(
                    itemView.resources,
                    transactionCategoryData.iconId,
                    itemView.context.theme
                )
            )
            binding?.categoryName?.text = transactionCategoryData.name
            if (position == checkedPosition) {
                binding?.isCategoryChecked?.visibility = View.VISIBLE
            } else
                binding?.isCategoryChecked?.visibility = View.GONE
        }

    }
}
