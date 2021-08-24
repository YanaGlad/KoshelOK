package com.example.gladkikhvlasovtinkoff.ui.ui.selectcategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.CategoryItemBinding
import com.example.gladkikhvlasovtinkoff.model.TransactionCategoryData
import com.pes.androidmaterialcolorpickerdialog.ColorPicker


class OperationCategoryAdapter(
    val isGridIcon: Boolean = false
) : RecyclerView.Adapter<OperationCategoryAdapter.ViewHolder>() {

    private val categories: MutableList<TransactionCategoryData> = arrayListOf()
    private var checkedPosition = -1

    private var _checkedItem: MutableLiveData<TransactionCategoryData?> = MutableLiveData(null)
    val checkedItem: LiveData<TransactionCategoryData?>
        get() = _checkedItem

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            CategoryItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false), isGridIcon
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
        this.categories.clear()
        this.categories.addAll(categories)

        notifyDataSetChanged()
    }

    class ViewHolder(val _binding: CategoryItemBinding, private val isGridIcon: Boolean) :
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
            //TODO - поправить
            // binding?.transactionDot?.setImageResource(transactionCategoryData.color)
//            binding?.transactionDot?.setColorFilter(transactionCategoryData.color)

            binding?.categoryImageIcon?.setImageDrawable(
                ResourcesCompat.getDrawable(
                    itemView.resources,
                    transactionCategoryData.iconId,
                    itemView.context.theme
                )
            )

            if (isGridIcon) {
                val params = binding?.isCategoryChecked?.getLayoutParams()
                params?.height = 90
                params?.width = 90
                binding?.isCategoryChecked?.layoutParams = params
                binding?.isCategoryChecked?.setColorFilter(ColorPicker(itemView.context as AppCompatActivity, 0, 0, 0).color)
            }

            binding?.categoryName?.text = transactionCategoryData.name
            if (position == checkedPosition) {
                binding?.isCategoryChecked?.visibility = View.VISIBLE
            } else
                binding?.isCategoryChecked?.visibility = View.GONE
        }

    }
}
