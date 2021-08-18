package com.example.gladkikhvlasovtinkoff.ui.selectcategory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.databinding.OperationCategoryItemBinding
import com.example.gladkikhvlasovtinkoff.model.OperationCategoryData

class OperationCategoryAdapter  : RecyclerView.Adapter<OperationCategoryAdapter.ViewHolder>() {

    private val categories : MutableList<OperationCategoryData> = mutableListOf()
    private var checkedPosition = -1

    private var _checkedItem : MutableLiveData<OperationCategoryData?> = MutableLiveData(null)
    val checkedItem :  LiveData<OperationCategoryData?>
    get() = _checkedItem

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
            _checkedItem.value = null
        }else{
                checkedPosition = position
                if(oldPosition >= 0)
                    notifyItemChanged(oldPosition)
                notifyItemChanged(position)
            _checkedItem.value = categories[position]
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position], position, checkedPosition)
    }


    override fun getItemCount(): Int = categories.size

    fun addItems(categories : List<OperationCategoryData>){
        val oldSize = categories.size
        this.categories.addAll(categories)
        notifyItemRangeChanged(oldSize - 1, categories.size)
    }

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
