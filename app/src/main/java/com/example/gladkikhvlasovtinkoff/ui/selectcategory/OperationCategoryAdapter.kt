package com.example.gladkikhvlasovtinkoff.ui.selectcategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.OperationCategoryItemBinding
import com.example.gladkikhvlasovtinkoff.model.OperationCategoryData

class OperationCategoryAdapter  : RecyclerView.Adapter<OperationCategoryAdapter.ViewHolder>() {

    private val categories : MutableList<OperationCategoryData> = mutableListOf()
    private var checkedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_select_operation_category, parent, false)
        )

        holder.itemView.setOnClickListener{
            if(holder.adapterPosition != RecyclerView.NO_POSITION) {
                onItemChecked(holder.adapterPosition)
            }
        }

        return holder
    }

    private fun onItemChecked(position : Int) {

        val oldPosition = checkedPosition
        if(position == oldPosition && position >= 0) {
            checkedPosition = -1
            notifyItemChanged(oldPosition)
        }else{
                checkedPosition = position
                if(oldPosition >= 0)
                    notifyItemChanged(oldPosition)
                notifyItemChanged(position)
            }
    }

    fun getCheckedItem() : OperationCategoryData? =
        if(checkedPosition >= 0 && checkedPosition in 0 until itemCount) categories[checkedPosition]
        else null


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position], position, checkedPosition)
    }


    override fun getItemCount(): Int = categories.size

    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var binding : OperationCategoryItemBinding? = null
        init{
            binding = OperationCategoryItemBinding.bind(itemView)
        }

        fun bind(operationCategoryData : OperationCategoryData, position: Int, checkedPosition : Int) {
            binding?.categoryImage?.setImageDrawable(
                ResourcesCompat.getDrawable(
                    itemView.resources,
                    operationCategoryData.iconId,
                    itemView.context.theme)
            )
            binding?.categoryName?.text = itemView.context.getString(operationCategoryData.nameId)
            if(position == checkedPosition){
                binding?.isCategoryChecked?.visibility = View.VISIBLE
            }else
                binding?.isCategoryChecked?.visibility = View.GONE
        }

    }
}